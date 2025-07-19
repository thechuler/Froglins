package net.rbk.froglins.Eventos;



import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.rbk.froglins.DataComponent.ModDataComponent;
import net.rbk.froglins.Efectos.ModEffects;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Item.custom.Crusher;
import net.rbk.froglins.Recetas.HarmfulViscosityBrewingRecipe;

import java.util.HashSet;
import java.util.Set;


@EventBusSubscriber(modid = Froglins.MODID)
public class EventosServer {


    @SubscribeEvent
    public static void EventoMuerteEntidad(LivingDeathEvent event) {
        ConvertirFroglinEnZombie(event.getEntity(),event.getSource().getEntity());
        ManejarConversionZombie(event.getEntity());
    }


    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addRecipe(new HarmfulViscosityBrewingRecipe());
    }



    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ToolTipHarmfulViscosity(event);
    }



    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
      AplicarEfectoEnAtaque(event);
    }





    @SubscribeEvent
    public static void EventoCurarEntidad(LivingHealEvent event){
        if(event.getEntity().hasEffect(ModEffects.INFECTION)){
            event.setAmount(0);
        }
    }







    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof Crusher hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : Crusher.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }





// Aplica el efecto de la Harmful Viscosity a la victima
    private static void AplicarEfectoEnAtaque(AttackEntityEvent event){
        if (!(event.getTarget() instanceof LivingEntity target)) return;
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (stack.has(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get())) {
            MobEffectInstance effect = stack.get(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());
            Holder<MobEffect> effectHolder = effect.getEffect();
            int duration = 100; // ticks
            int amplifier = effect.getAmplifier();
            target.addEffect(new MobEffectInstance(effectHolder, duration, amplifier));
        }
    }



    //Maneja la conversion zombie por efecto.
    private static void ManejarConversionZombie(Entity entidad){
            if (((LivingEntity) entidad).hasEffect(ModEffects.INFECTION)) {
                ZombieFroglin nuevoZombie = ModEntities.ZOMBIE_FROGLIN.get().create(entidad.level());
                nuevoZombie.moveTo(entidad.blockPosition(), entidad.getYRot(), entidad.getXRot());
                entidad.level().addFreshEntity(nuevoZombie);
            }
        if (entidad instanceof ZombieFroglin zFroglin && !zFroglin.level().isClientSide) {
            AreaEffectCloud nube = new AreaEffectCloud(zFroglin.level(),
                    zFroglin.getX(), zFroglin.getY(), zFroglin.getZ());
            nube.setRadius(5.0F); // radio inicial
            nube.setRadiusPerTick(-0.02F); // cómo se reduce con el tiempo
            nube.setDuration(400); // duración total en ticks (10 segundos)
            nube.setWaitTime(0); // sin delay

            nube.setPotionContents(PotionContents.EMPTY);
            nube.addEffect(new MobEffectInstance(ModEffects.INFECTION, 1200)); // tu efecto personalizado

            zFroglin.level().addFreshEntity(nube);
        }
    }



  //Convierte un froglin en su version zombificada
    private static void ConvertirFroglinEnZombie(Entity victima, Entity atacante){
        if(victima instanceof Froglin && atacante instanceof ZombieFroglin){
            int varianteOriginal =((Froglin) victima).getVariante();
            ZombieFroglin nuevoZombie = ModEntities.ZOMBIE_FROGLIN.get().create(victima.level());

            if (nuevoZombie != null) {
                nuevoZombie.setVariante(varianteOriginal);
                nuevoZombie.moveTo(victima.blockPosition(), victima.getYRot(), victima.getXRot());
                victima.level().addFreshEntity(nuevoZombie);
            }
        }
    }



    //Tooltip de items con el Compound Tag
    private static void ToolTipHarmfulViscosity(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.has(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get())) {
            MobEffectInstance effect = stack.get(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());

            // Obtenemos nombre traducido del efecto
            Component effectName = Component.translatable(effect.getDescriptionId());

            // Color del efecto (forzamos alpha 0xFF por si acaso)
            int color = 0xFF000000 | effect.getEffect().value().getColor();

            // Línea personalizada con color dinámico
            Component line = Component.translatable("tooltip.froglins.harmful_viscosity")
                    .append(effectName.copy().withStyle(style -> style.withColor(color)));

            event.getToolTip().add(line);
        }
    }





}

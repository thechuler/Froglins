package net.rbk.froglins.Item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.rbk.froglins.DataComponent.ModDataComponent;
import net.rbk.froglins.Otros.Utilidades;

public class HarmfulViscosity extends Item {
    public HarmfulViscosity(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        ItemStack manoIzquierda = player.getItemInHand(InteractionHand.OFF_HAND);
        ItemStack manoDerecha = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (puedeCraftear(manoIzquierda, manoDerecha)) {
            ItemStack arma, viscosidad;

            if (manoIzquierda.getItem() instanceof TieredItem) {
                arma = manoIzquierda;
                viscosidad = manoDerecha;
            } else {
                arma = manoDerecha;
                viscosidad = manoIzquierda;
            }
            MobEffectInstance efecto = viscosidad.get(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());
            arma.set(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get(), efecto);
            viscosidad.shrink(1);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BREWING_STAND_BREW, player.getSoundSource(), 1.0F, 1.0F);
            Utilidades.spawnearParticulas(player, 100, ParticleTypes.EFFECT);
            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        }

        return InteractionResultHolder.fail(player.getItemInHand(usedHand));
    }




    private boolean puedeCraftear(ItemStack left, ItemStack right) {
        boolean leftIsWeapon = left.getItem() instanceof TieredItem;
        boolean rightIsWeapon = right.getItem() instanceof TieredItem;

        boolean leftIsViscosity = left.getItem() instanceof HarmfulViscosity;
        boolean rightIsViscosity = right.getItem() instanceof HarmfulViscosity;

        boolean leftHasEffect = left.has(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());
        boolean rightHasEffect = right.has(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());

        return (
                // Caso 1: viscosidad con efecto a la derecha + arma a la izquierda
                (rightIsViscosity && rightHasEffect && leftIsWeapon) ||

                        // Caso 2: viscosidad con efecto a la izquierda + arma a la derecha
                        (leftIsViscosity && leftHasEffect && rightIsWeapon)
        );
    }

}

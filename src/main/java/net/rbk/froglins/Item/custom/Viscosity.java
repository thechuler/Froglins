package net.rbk.froglins.Item.custom;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rbk.froglins.DataComponent.ModDataComponent;
import net.rbk.froglins.Entidades.Lanzable.ThrowableViscosity;

import java.util.Collection;

public class Viscosity extends Item {
    public Viscosity(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        Collection<MobEffectInstance> efectos = player.getActiveEffects();
        ItemStack viscosidad = player.getItemInHand(usedHand);

        if (!efectos.isEmpty() && !viscosidad.has(ModDataComponent.HARMFUL_VISCOSITY_EFFECT)) {
            MobEffectInstance efecto = efectos.iterator().next();
            Holder<MobEffect> tipo = efecto.getEffect();
            viscosidad.set(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get(),efecto);
            player.removeEffect(tipo);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SLIME_HURT, player.getSoundSource(), 1.0F, 2.0F);

            return InteractionResultHolder.consume(player.getItemInHand(usedHand));
        }

        if(viscosidad.has(ModDataComponent.HARMFUL_VISCOSITY_EFFECT)){
            MobEffectInstance efecto = viscosidad.get(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());
            ThrowableViscosity projectile = new ThrowableViscosity(player,level);;
            projectile.setEffect(efecto);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(projectile);
            viscosidad.shrink(1);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SLIME_SQUISH, player.getSoundSource(), 1.0F, 2.0F);

            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        }



        return InteractionResultHolder.fail(player.getItemInHand(usedHand));

    }}


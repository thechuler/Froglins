package net.rbk.froglins.Entidades.Lanzable;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Item.ModItems;

public class ThrowableViscosity extends ThrowableItemProjectile {
    private MobEffectInstance storedEffect;

    public ThrowableViscosity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.VISCOSITY.asItem();
    }


    public ThrowableViscosity(LivingEntity shooter, Level level) {
        super(ModEntities.VISCOSITY_PROJECTILE.get(), shooter, level);
    }

    public void setEffect(MobEffectInstance effect) {
        this.storedEffect = effect;
    }

    public MobEffectInstance getEffect() {
        return storedEffect;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide()) {
            if (storedEffect != null && result.getEntity() instanceof LivingEntity target) {
                target.addEffect(new MobEffectInstance(storedEffect));
            }

            this.discard();
        }
    }
}


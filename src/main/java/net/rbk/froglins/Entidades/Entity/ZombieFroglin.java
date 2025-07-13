package net.rbk.froglins.Entidades.Entity;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.Level;

import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ZombieFroglin extends AbstractFroglin  {


    public ZombieFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 2.5)
                .add(Attributes.FOLLOW_RANGE,100)
                .add(Attributes.STEP_HEIGHT,2); 
    }




    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, (entity) -> !(entity instanceof ZombieFroglin) && !(entity instanceof WaterAnimal)));
        this.goalSelector.addGoal(4, new BreakDoorGoal(this, difficulty -> difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD  ));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }






    //cambiar efecto y agregar probabilidad
    @Override
    public boolean doHurtTarget(Entity pEntity) {

        if(!this.level().isClientSide()&& pEntity instanceof LivingEntity){
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS,200));
        }
        return super.doHurtTarget(pEntity);

    }


    @Override
    public void tick() {
        rageOnNight();
        this.level().isDay();

        super.tick();
    }



    private void rageOnNight() {
        double nuevaVelocidad = this.level().isDay() ? 0.2 : 0.6;
        double nuevoDaño = this.level().isDay() ? 2.5 : 5.5;

        if (Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).getBaseValue() != nuevaVelocidad) {
            Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(nuevaVelocidad);
        }
        if (Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).getBaseValue() != nuevoDaño) {
            Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(nuevoDaño);
        }


    }



    //------------------------------------SONIDOS---------------------------//
    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.ZOMBIE_FROGLIN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.ZOMBIE_FROGLIN_HURT.get();
    }


    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ZOMBIE_FROGLIN_DEATH.get();
    }

















    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }
}

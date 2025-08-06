package net.rbk.froglins.Entidades.Entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TropicalFroglin extends AbstractFroglin{
    public TropicalFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    private static final EntityDataAccessor<Byte> ESTATREPANDO = SynchedEntityData.defineId(TropicalFroglin.class, EntityDataSerializers.BYTE);
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ESTATREPANDO, (byte) 0);
    }



    private boolean estaTrepando = false;

    public final AnimationState treparAnimationState = new AnimationState();

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 4.5)
                .add(Attributes.FOLLOW_RANGE,20)
                .add(Attributes.STEP_HEIGHT,1.5);

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractGolem.class, false));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }





    @Override
    public void tick() {


        if(this.level().isClientSide()){

            if(this.isClimbing() && !estaTrepando){
                treparAnimationState.start(this.tickCount);
                estaTrepando = true;
            }else if(!this.isClimbing() && estaTrepando){
                treparAnimationState.stop();
                estaTrepando = false;
            }


        }
        if (!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
            if(this.isClimbing() ){
                this.activarChase = false;
            }else{
                this.activarChase = true;
            }
        }

        super.tick();
    }









    protected PathNavigation createNavigation(Level pLevel) {
        return new WallClimberNavigation(this, pLevel);
    }

    public boolean onClimbable() {
        return this.isClimbing();
    }



    public void setClimbing(boolean climbing) {
        byte b0 = this.entityData.get(ESTATREPANDO);
        if (climbing) {
            b0 |= 1;
        } else {
            b0 &= -2;
        }
        this.entityData.set(ESTATREPANDO, b0);
    }


    public boolean isClimbing() {
        return (this.entityData.get(ESTATREPANDO) & 1) != 0;
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) ) {
            return false;
        }
        if(source.getDirectEntity() instanceof LivingEntity){
            ((LivingEntity) source.getDirectEntity()).addEffect(new MobEffectInstance(MobEffects.POISON,200));
        }
        return super.hurt(source, amount);
    }





    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }











    //--------------------------------------SONIDOS---------------------//
    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.TROPICAL_FROGLIN_AMBIENT.get();
    }

    @Override
    public List<ResourceLocation> getVariantTextures() {
        return List.of();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.FROGLIN_HURT.get();
    }


    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.FROGLIN_DEATH.get();
    }

}

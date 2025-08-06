package net.rbk.froglins.Entidades.Entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractFroglin extends Animal {

    public static final EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(AbstractFroglin.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Integer> VARIANTE = SynchedEntityData.defineId(AbstractFroglin.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(AbstractFroglin.class, EntityDataSerializers.BOOLEAN);


    public boolean activarChase = true;
    public final AnimationState deathAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;
    private boolean animarAtaque;



    protected AbstractFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }



    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("variante", getVariante());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setVariante(pCompound.getInt("variante"));
    }

    public void setVariante(int variante) {
        this.entityData.set(VARIANTE, variante);
    }

    public int getVariante() {
        return this.entityData.get(VARIANTE);
    }




    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(RUGIR, false);
        builder.define(ATACAR, false);
        builder.define(VARIANTE, 0);
    }

    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }
    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }





    //-------------Atributos Base---------------
    public static AttributeSupplier.Builder createBaseAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 4.5)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.STEP_HEIGHT, 1.5);

    }





    @Override
    public void tick() {
        if(this.level().isClientSide()){
            setUpAnimationStates();
            ManageRugido();
           ManageAtaque();
        } else {
            boolean shouldAttack = this.getTarget() != null && this.distanceTo(this.getTarget()) < 6F && activarChase;

            if (shouldAttack != getData(ATACAR)) {
                setData(ATACAR, shouldAttack);
            }
        }



        super.tick();
    }








    private void ManageAtaque(){
        boolean atacar = getData(ATACAR);

        if (atacar && !animarAtaque) {
            animarAtaque = true;
            attackAnimationState.start(this.tickCount);
        } else if (!atacar && animarAtaque) {
            animarAtaque = false;
            attackAnimationState.stop();
        }
    }


//Logica Rugido (Animacion)
    private void ManageRugido(){
        if(getData(RUGIR) && this.rugidoAnimationTimeout <= 0){
            if(this.level().isClientSide()){
                setData(RUGIR,false);
                this.rugidoAnimationTimeout = 60;
                 this.gruñirAnimationState.start(this.tickCount);
                    this.level().playLocalSound(this.blockPosition(),GetRugidoSound(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch(),false);
            }
        }else{
            this.rugidoAnimationTimeout --;
        }
        if(!getData(RUGIR) && this.rugidoAnimationTimeout <= 0){
            gruñirAnimationState.stop();
        }
    }

















    @Override
    public void die(@NotNull DamageSource damageSource) {
        if (this.level().isClientSide()) {
            this.deathAnimationState.start(this.tickCount);
        }

       super.die(damageSource);
    }


    //Setup Estados Animacion
    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }
    }



    public abstract SoundEvent GetRugidoSound();

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        setData(RUGIR,true);
        return null;
    }

    public abstract List<ResourceLocation> getVariantTextures();




}

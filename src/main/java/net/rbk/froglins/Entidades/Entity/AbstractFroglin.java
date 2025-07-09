package net.rbk.froglins.Entidades.Entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFroglin extends Animal {

    public static final EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(AbstractFroglin.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Integer> VARIANTE = SynchedEntityData.defineId(AbstractFroglin.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;



    protected AbstractFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }









    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(RUGIR, false);
        builder.define(VARIANTE, 0);
    }

    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }
    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }








    @Override
    public void tick() {
        if(this.level().isClientSide()){
            setUpAnimationStates();
            ManageRugido();
        }
        super.tick();
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




    //Setup Estados Animacion
    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }
    }



    public SoundEvent GetRugidoSound (){
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        setData(RUGIR,true);
        return null;
    }






}

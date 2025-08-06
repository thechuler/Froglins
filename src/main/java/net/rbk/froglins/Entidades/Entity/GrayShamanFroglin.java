package net.rbk.froglins.Entidades.Entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;

import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rbk.froglins.Entidades.AI.KeepDistanceGoal;

import net.rbk.froglins.Entidades.AI.ShamanSummonGoal;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrayShamanFroglin extends AbstractFroglin  {
    public GrayShamanFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }
    public int  castTimeOut = 0;
    public AnimationState summonAnimationState = new AnimationState();


    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFroglin.createBaseAttributes()
                .add(Attributes.MOVEMENT_SPEED,0.5);
    }


    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.FROGLIN_AMBIENT.get();
    }

    @Override
    public List<ResourceLocation> getVariantTextures() {
        return List.of(
                ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/shaman_froglin/shaman_froglin.png"));
    }


    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }






    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new KeepDistanceGoal(this,9.5f,10f));
        this.goalSelector.addGoal(1 , new ShamanSummonGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Froglin.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TropicalFroglin.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WoollyFroglin.class, true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }



    @Override
    public void tick() {
        if(!this.level().isClientSide()){
    //        System.out.println("Target actual: " + this.getTarget());
        }
        super.tick();
    }

//    protected void registerGoals() {
//        super.registerGoals();
//        this.goalSelector.addGoal(0, new FloatGoal(this));
//        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, Player.class, 8.0F, 0.8, 1.0));
//        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, AbstractGolem.class, 8.0F, 0.8, 1.0));
//        this.goalSelector.addGoal(4, new ShamanSummonGoal(this));
//        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
//        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
//        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
//        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal(this, Player.class, true)).setUnseenMemoryTicks(300));
//        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractGolem.class, false));
//        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, false));
//    }

}

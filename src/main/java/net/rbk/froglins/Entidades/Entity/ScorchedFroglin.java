package net.rbk.froglins.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Otros.Utilidades;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.Nullable;


import java.util.List;

public class ScorchedFroglin extends AbstractFroglin{
    public ScorchedFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }



    public final AnimationState fireAnimationState = new AnimationState();


    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFroglin.createBaseAttributes();
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
        super.tick();

    }







    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.FROGLIN_AMBIENT.get();
    }

    @Override
    public List<ResourceLocation> getVariantTextures() {
        return List.of();
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.getItem() == Items.COAL;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return new ScorchedFroglin(ModEntities.SCORCHED_FROGLIN.get(),serverLevel);
    }
}

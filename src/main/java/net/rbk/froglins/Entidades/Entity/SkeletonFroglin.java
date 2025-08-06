package net.rbk.froglins.Entidades.Entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Otros.Utilidades;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkeletonFroglin extends AbstractFroglin{
    public SkeletonFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFroglin.createBaseAttributes()
                .add(Attributes.MAX_HEALTH,4)
                .add(Attributes.ATTACK_DAMAGE,2)
                .add(Attributes.MOVEMENT_SPEED,0.2);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }


    @Override
    public void tick() {


        if (!this.level().isClientSide()) {
            ManageOwnDead();
        }




        super.tick();
    }

    private void ManageOwnDead(){
        if(this.getTarget() == null || !this.getTarget().isAlive()){
            Utilidades.spawnearParticulas(this,15, ParticleTypes.CLOUD);
            this.discard();
        }
    }


    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.FROGLIN_AMBIENT.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    public List<ResourceLocation> getVariantTextures() {
        return List.of(
                ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/skeleton_froglin/skeleton_froglin.png")
        );
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}

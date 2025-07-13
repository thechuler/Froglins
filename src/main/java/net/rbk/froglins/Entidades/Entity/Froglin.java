package net.rbk.froglins.Entidades.Entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;



public class Froglin extends AbstractFroglin {
    public Froglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }




    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 4.5)
                .add(Attributes.FOLLOW_RANGE,20)
                .add(Attributes.STEP_HEIGHT,1.5);

    }



    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {

        super.defineSynchedData(builder);
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







    //-----------------------------------VARIANTES------------------------------------//

    @Override
    public @NotNull SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        // Obtener el bioma en la posición de spawn
        var biome = level.getBiome(this.blockPosition());
        if (biome.is(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)) {
            this.setVariante(2);
        } else if (biome.is(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)){
            this.setVariante(1);
        } else {
            this.setVariante(0);
        }
        return data;
    }













    //--------------------------------------SONIDOS---------------------//
    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.FROGLIN_AMBIENT.get();
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












    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

}

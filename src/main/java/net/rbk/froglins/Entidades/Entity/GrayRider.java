package net.rbk.froglins.Entidades.Entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrayRider extends AbstractFroglin{
    public GrayRider(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }




    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFroglin.createBaseAttributes();
    }


    @Override
    public @NotNull SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        Frogronk frogronk = ModEntities.FROGRONK.get().create((ServerLevel) level);
        if(frogronk != null){
            frogronk.moveTo(this.getX(), this.getY(), this.getZ());
            ((ServerLevel) level).addFreshEntity(frogronk);
            this.startRiding(frogronk);
        }

        return data;
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
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}

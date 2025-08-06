package net.rbk.froglins.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.FuentesDeDaño.ModDamageSources;
import net.rbk.froglins.Item.ModItems;
import net.rbk.froglins.Otros.Utilidades;
import net.rbk.froglins.Sonidos.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WoollyFroglin extends AbstractFroglin {

    public final AnimationState chargeAnimationState = new AnimationState();
    public static final EntityDataAccessor<Boolean> CARGAR = SynchedEntityData.defineId(WoollyFroglin.class, EntityDataSerializers.BOOLEAN);

    private int chargeAnimationTimeout = 0;
    private int chargeTicks = -1;

    public WoollyFroglin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CARGAR, false);
    }

    @Override
    public @NotNull SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        int random = (int)(Math.random() * 3);
        setVariante(random);
        return data;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.STEP_HEIGHT, 1.5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 0.6, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractGolem.class, false));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            LivingEntity target = this.getTarget();

            if (target != null && target.distanceTo(this) < 2.0 && chargeTicks == -1) {
                setData(CARGAR, true);
                chargeTicks = 0;
                this.setDeltaMovement(Vec3.ZERO);
                this.getNavigation().stop();
            }

            if (chargeTicks >= 0) {
                this.setDeltaMovement(Vec3.ZERO);
                this.yBodyRot = this.yRotO;
                this.setYRot(this.yRotO);
                this.setXRot(this.xRotO);

                chargeTicks++;

                if (chargeTicks == 35) {
                    realizarGolpe();
                }

                if (chargeTicks >= 60) {
                    setData(CARGAR, false);
                    chargeTicks = -1;
                }
            }

        } else {
            if (getData(CARGAR) && chargeAnimationTimeout <= 0) {
                chargeAnimationTimeout = 60;
                chargeAnimationState.start(this.tickCount);
            } else {
                attackAnimationState.stop();
                chargeAnimationTimeout--;
            }

            if (!getData(CARGAR) && chargeAnimationTimeout <= 0) {
                chargeAnimationState.stop();
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide()) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (itemstack.getItem() == Items.BUCKET) {
                ItemStack froglinMilk = new ItemStack(ModItems.FROGLIN_MILK_BUCKET.get());

                boolean replaced = false;

                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setItemInHand(hand, froglinMilk);
                        replaced = true;
                    } else if (!player.getInventory().add(froglinMilk)) {
                        player.drop(froglinMilk, false);
                    }
                } else {
                    player.setItemInHand(hand, froglinMilk);
                    replaced = true;
                }

                level().playSound(null, this.blockPosition(), SoundEvents.COW_MILK, SoundSource.NEUTRAL, 1.0F, 1.0F);

                return InteractionResult.CONSUME;
            }
        }
        return super.mobInteract(player, hand);
    }



    private void realizarGolpe() {
        BlockPos center = this.blockPosition();
        Level level = this.level();
        int radio = 3;
        double fuerzaEmpuje = 1.6;

        if (level instanceof ServerLevel serverLevel) {
            // A) ROMPER BLOQUES y EMITIR PARTICULAS
            for (BlockPos pos : BlockPos.betweenClosed(center.offset(-radio, -1, -radio), center.offset(radio, 1, radio))) {
                double distancia = center.distSqr(pos);
                if (distancia > radio * radio) continue;

                BlockState blockState = level.getBlockState(pos);
                float hardness = blockState.getDestroySpeed(level, pos);

                if (!blockState.isAir() && hardness >= 0 && hardness <= 2.0F) {
                    level.destroyBlock(pos, false);
                    BlockParticleOption particle = new BlockParticleOption(ParticleTypes.BLOCK, blockState);
                    Utilidades.spawnearParticulas(this,60,particle);
                    SoundType soundType = blockState.getSoundType();
                    level.playSound(null, pos, soundType.getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }

            }

            // B) EMPUJAR ENTIDADES HACIA ARRIBA
            List<LivingEntity> entidades = serverLevel.getEntitiesOfClass(
                    LivingEntity.class,
                    new net.minecraft.world.phys.AABB(center).inflate(radio),
                    entidad -> entidad != this && entidad.isAlive()
            );

            for (LivingEntity entidad : entidades) {
                double dx = entidad.getX() - center.getX();
                double dz = entidad.getZ() - center.getZ();
                if ((dx * dx + dz * dz) <= (radio * radio)) {
                    Vec3 motion = entidad.getDeltaMovement();
                    entidad.setDeltaMovement(motion.x, fuerzaEmpuje, motion.z);
                    entidad.hurt(ModDamageSources.woollyFroglinCrush(serverLevel,this), 10.0F);

                    entidad.hurtMarked = true;
                }
            }
        }
    }

    // ---------------------- SONIDOS ---------------------- //

    @Override
    public SoundEvent GetRugidoSound() {
        return ModSounds.WOOLLY_FROGLIN_AMBIENT.get();
    }

    @Override
    public List<ResourceLocation> getVariantTextures() {
        return List.of(
                ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/woolly_froglin/woolly_froglin.png"),
                ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/woolly_froglin/woolly_froglin2.png"),
                ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/woolly_froglin/woolly_froglin3.png")
        );
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.WOOLLY_FROGLIN_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.WOOLLY_FROGLIN_AMBIENT.get();
    }

    // ---------------------- REPRODUCCIÓN ---------------------- //

    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }
}

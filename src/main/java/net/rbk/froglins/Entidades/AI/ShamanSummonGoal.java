package net.rbk.froglins.Entidades.AI;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.rbk.froglins.Entidades.Entity.GrayShamanFroglin;
import net.rbk.froglins.Entidades.Entity.SkeletonFroglin;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Otros.Utilidades;


public class ShamanSummonGoal extends Goal {
    private GrayShamanFroglin entity;




    public ShamanSummonGoal(GrayShamanFroglin Shaman){
        entity = Shaman;
    }

    @Override
    public boolean canUse() {
        return  entity.getTarget() != null && entity.getTarget().isAlive()  ;
    }

    @Override
    public boolean canContinueToUse() {
        return  entity.getTarget() != null && entity.getTarget().isAlive()  ;
    }

    @Override
    public void start() {
        super.start();
        SpawnSkeletons();
        entity.castTimeOut = 100;
    }


    @Override
    public void tick() {
        super.tick();
        if(entity.castTimeOut <= 0){
            SpawnSkeletons();
            entity.castTimeOut = 100;
        }else{
            entity.castTimeOut--;
        }
    }

    private void SpawnSkeletons(){


        for (int i = 0; i < Utilidades.GenerarNumeroAleatorio(1,4); i++) {
            BlockPos spawnPos = Utilidades.GenerarPosicionAleatoriaEnArea(entity.getOnPos(),3);

            SkeletonFroglin newEntity = ModEntities.SKELETON_FROGLIN.get().create(entity.level());

            if (newEntity != null) {
                newEntity.moveTo(spawnPos, entity.getYRot(), entity.getXRot());
                entity.level().addFreshEntity(newEntity);
                entity.level().playSound(null, spawnPos, SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.HOSTILE, 1.0F, 1.0F); // Sonido
                newEntity.setTarget(entity.getTarget());
                Utilidades.spawnearParticulas(newEntity,15, ParticleTypes.CLOUD);
            }


        }
    }
}

package net.rbk.froglins.Entidades.AI;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.phys.Vec3;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;

public class KeepDistanceGoal extends Goal {
    private final Mob mob;
    float minDist;
    float maxDist;
    public KeepDistanceGoal(Mob mob,float min,float max) {
        this.mob = mob;
        minDist = min;
        maxDist = max;
    }

    @Override
    public boolean canUse() {
        return mob.getTarget() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return mob.getTarget() != null;
    }


    @Override
    public void tick() {
        System.out.println("Target actual: " + mob.getTarget());
        float distancia = mob.distanceTo(mob.getTarget());

        // Vector desde el target hasta el mob (nos da la dirección para alejarse)
        Vec3 direccion = mob.position().subtract(mob.getTarget().position()).normalize();

        if (distancia < minDist) {
            // Alejarse → mover en dirección opuesta (hacia atrás)
            Vec3 destino = mob.position().add(direccion.scale(5.0)); // Nueva posición alejada
            mob.getNavigation().moveTo(destino.x, destino.y, destino.z, 1.0D);
        } else if (distancia > maxDist) {
            // Acercarse → moverse hacia el target
            mob.getNavigation().moveTo(mob.getTarget(), 1.0D);
        }else {
            mob.getNavigation().stop();
            LivingEntity target = mob.getTarget();
            if (target != null) {
                mob.getLookControl().setLookAt(target, 30.0F, 30.0F); // mira al objetivo suavemente
            }
        }

        super.tick();
    }

}

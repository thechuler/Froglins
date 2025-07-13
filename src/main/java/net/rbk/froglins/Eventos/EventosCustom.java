package net.rbk.froglins.Eventos;


import net.minecraft.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.rbk.froglins.Efectos.ModEffects;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Utilidades;

import java.util.List;

@EventBusSubscriber(modid = Froglins.MODID)
public class EventosCustom {


    @SubscribeEvent
    public static void EventoMuerteEntidad(LivingDeathEvent event) {
        ConvertirFroglinEnZombie(event.getEntity(),event.getSource().getEntity());
        ManejarConversionZombie(event.getEntity());
    }





    @SubscribeEvent
    public static void EventoCurarEntidad(LivingHealEvent event){
        if(event.getEntity().hasEffect(ModEffects.INFECTION)){
            event.setAmount(0);
        }


    }








    private static void ManejarConversionZombie(Entity entidad){

            if (((LivingEntity) entidad).hasEffect(ModEffects.INFECTION)) {
                ZombieFroglin nuevoZombie = ModEntities.ZOMBIE_FROGLIN.get().create(entidad.level());
                nuevoZombie.moveTo(entidad.blockPosition(), entidad.getYRot(), entidad.getXRot());
                entidad.level().addFreshEntity(nuevoZombie);
            }

            if (entidad instanceof ZombieFroglin) {
                ZombieFroglin zFroglin = (ZombieFroglin) entidad;
                List<LivingEntity> entidadesObtenidas = Utilidades.ObtenerEntidadesEnArea(zFroglin.level(), zFroglin.getOnPos(), 10);
                for (LivingEntity encontrado : entidadesObtenidas) {
                    if (!(encontrado instanceof ZombieFroglin)) {
                        encontrado.addEffect(new MobEffectInstance(ModEffects.INFECTION, 1200));
                    }
                }
            }

    }

    /**
     * Convierte un Froglin en su versión zombificada.
     *
     * @param victima La entidad Froglin que será transformada en zombie (Debe de ser un Frogling).
     * @param atacante La entidad que causó la transformación (Debe de ser un Frogling Zombie).
     */
    private static void ConvertirFroglinEnZombie(Entity victima, Entity atacante){
        if(victima instanceof Froglin && atacante instanceof ZombieFroglin){
            int varianteOriginal =((Froglin) victima).getVariante();
            ZombieFroglin nuevoZombie = ModEntities.ZOMBIE_FROGLIN.get().create(victima.level());

            if (nuevoZombie != null) {
                nuevoZombie.setVariante(varianteOriginal);
                nuevoZombie.moveTo(victima.blockPosition(), victima.getYRot(), victima.getXRot());
                victima.level().addFreshEntity(nuevoZombie);
            }
        }
    }





}

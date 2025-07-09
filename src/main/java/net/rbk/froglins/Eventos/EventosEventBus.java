package net.rbk.froglins.Eventos;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Froglins;

@EventBusSubscriber(modid = Froglins.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EventosEventBus {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FroglinModel.LAYER_LOCATION, FroglinModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FROGLIN.get(), Froglin.createAttributes().build());
        event.put(ModEntities.ZOMBIE_FROGLIN.get(), ZombieFroglin.createAttributes().build());
    }


    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

}

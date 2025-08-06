package net.rbk.froglins.Eventos;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.rbk.froglins.Entidades.Entity.*;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Entidades.Modelos.*;
import net.rbk.froglins.Froglins;

@EventBusSubscriber(modid = Froglins.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EventosEventBus {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FroglinModel.LAYER_LOCATION, FroglinModel::createBodyLayer);
        event.registerLayerDefinition(WoollyFroglinModel.LAYER_LOCATION, WoollyFroglinModel::createBodyLayer);
        event.registerLayerDefinition(ScorchedFroglinModel.LAYER_LOCATION, ScorchedFroglinModel::createBodyLayer);
        event.registerLayerDefinition(ShamanFroglinModel.LAYER_LOCATION, ShamanFroglinModel::createBodyLayer);
        event.registerLayerDefinition(FrogronkModel.LAYER_LOCATION, FrogronkModel::createBodyLayer);
        event.registerLayerDefinition(GrayFroglinModel.LAYER_LOCATION, GrayFroglinModel::createBodyLayer);
        event.registerLayerDefinition(GrayRiderModel.LAYER_LOCATION, GrayRiderModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FROGLIN.get(), Froglin.createAttributes().build());
        event.put(ModEntities.ZOMBIE_FROGLIN.get(), ZombieFroglin.createAttributes().build());
        event.put(ModEntities.TROPICAL_FROGLIN.get(), TropicalFroglin.createAttributes().build());
        event.put(ModEntities.WOOLLY_FROGLIN.get(), WoollyFroglin.createAttributes().build());
        event.put(ModEntities.SCORCHED_FROGLIN.get(), ScorchedFroglin.createAttributes().build());
        event.put(ModEntities.SKELETON_FROGLIN.get(), SkeletonFroglin.createAttributes().build());
        event.put(ModEntities.GRAY_SHAMAN_FROGLIN.get(), GrayShamanFroglin.createAttributes().build());
        event.put(ModEntities.FROGRONK.get(), Frogronk.createAttributes().build());
        event.put(ModEntities.GRAY_FROGLIN.get(), GrayFroglin.createAttributes().build());
        event.put(ModEntities.GRAY_RIDER_FROGLIN.get(), GrayRider.createAttributes().build());
    }


    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.WOOLLY_FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.TROPICAL_FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.ZOMBIE_FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ZombieFroglin::checkZombieFroglinSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);


    }

}

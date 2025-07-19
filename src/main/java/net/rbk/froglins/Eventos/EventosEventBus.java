package net.rbk.froglins.Eventos;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.TropicalFroglin;
import net.rbk.froglins.Entidades.Entity.WoollyFroglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Entidades.Modelos.WoollyFroglinModel;
import net.rbk.froglins.Froglins;

@EventBusSubscriber(modid = Froglins.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EventosEventBus {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FroglinModel.LAYER_LOCATION, FroglinModel::createBodyLayer);
        event.registerLayerDefinition(WoollyFroglinModel.LAYER_LOCATION, WoollyFroglinModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FROGLIN.get(), Froglin.createAttributes().build());
        event.put(ModEntities.ZOMBIE_FROGLIN.get(), ZombieFroglin.createAttributes().build());
        event.put(ModEntities.TROPICAL_FROGLIN.get(), TropicalFroglin.createAttributes().build());
        event.put(ModEntities.WOOLLY_FROGLIN.get(), WoollyFroglin.createAttributes().build());
    }


    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ZombieFroglin::checkZombieFroglinSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.ZOMBIE_FROGLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

    }

}

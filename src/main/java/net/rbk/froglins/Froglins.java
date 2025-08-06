package net.rbk.froglins;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.rbk.froglins.DataComponent.ModDataComponent;
import net.rbk.froglins.Efectos.ModEffects;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Entidades.Modelos.*;
import net.rbk.froglins.Entidades.Renders.*;
import net.rbk.froglins.Item.CreativeTab;
import net.rbk.froglins.Item.ModItems;
import net.rbk.froglins.Sonidos.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;


@Mod(Froglins.MODID)
public class Froglins {

    public static final String MODID = "froglins";



    private static final Logger LOGGER = LogUtils.getLogger();



    public Froglins(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModItems.registrar(modEventBus);
        ModDataComponent.registrar(modEventBus);
        ModEffects.registrar(modEventBus);
        CreativeTab.registrar(modEventBus);
        ModEntities.registrar(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {


    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }


    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.FROGLIN.get(), context -> new FroglinRender(context, new FroglinModel(context.bakeLayer(FroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.TROPICAL_FROGLIN.get(), context -> new TropicalFroglinRender(context, new TropicalFroglinModel<>(context.bakeLayer(FroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.ZOMBIE_FROGLIN.get(), context -> new ZombieFroglinRender(context, new FroglinModel(context.bakeLayer(FroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.WOOLLY_FROGLIN.get(), context -> new WoollyFroglinRender(context, new WoollyFroglinModel(context.bakeLayer(WoollyFroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.SCORCHED_FROGLIN.get(), context -> new ScorchedFroglinRender(context, new ScorchedFroglinModel(context.bakeLayer(ScorchedFroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.GRAY_SHAMAN_FROGLIN.get(), context -> new ShamanFroglinRender(context, new ShamanFroglinModel<>(context.bakeLayer(ShamanFroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.SKELETON_FROGLIN.get(), context -> new SkeletonFroglinRender(context, new FroglinModel<>(context.bakeLayer(FroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.FROGRONK.get(), context -> new FrogronkRender(context, new FrogronkModel(context.bakeLayer(FrogronkModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.GRAY_FROGLIN.get(), context -> new GrayFroglinRender(context, new GrayFroglinModel<>(context.bakeLayer(GrayFroglinModel.LAYER_LOCATION)), 0.5f));
            EntityRenderers.register(ModEntities.GRAY_RIDER_FROGLIN.get(), context -> new GrayRiderFroglinRender(context, new GrayRiderModel<>(context.bakeLayer(GrayRiderModel.LAYER_LOCATION)), 0.5f));


            EntityRenderers.register(ModEntities.VISCOSITY_PROJECTILE.get(), ViscosityProjectileRenderer::new);

        }
    }


}

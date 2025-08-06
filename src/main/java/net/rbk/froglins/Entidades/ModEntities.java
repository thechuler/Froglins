package net.rbk.froglins.Entidades;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Entidades.Entity.*;
import net.rbk.froglins.Entidades.Lanzable.ThrowableViscosity;
import net.rbk.froglins.Froglins;

import java.util.function.Supplier;


public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTIDADES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Froglins.MODID);


    public static final Supplier<EntityType<Froglin>> FROGLIN =
            ENTIDADES.register("froglin", () -> EntityType.Builder.of(Froglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("froglin"));

    public static final Supplier<EntityType<ZombieFroglin>> ZOMBIE_FROGLIN =
            ENTIDADES.register("zombie_froglin", () -> EntityType.Builder.of(ZombieFroglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("zombie_froglin"));


    public static final Supplier<EntityType<WoollyFroglin>> WOOLLY_FROGLIN =
            ENTIDADES.register("woolly_froglin", () -> EntityType.Builder.of(WoollyFroglin::new, MobCategory.CREATURE)
                    .sized(1.2f, 1.5f).build("woolly_froglin"));




    public static final Supplier<EntityType<TropicalFroglin>> TROPICAL_FROGLIN =
            ENTIDADES.register("tropical_froglin", () -> EntityType.Builder.of(TropicalFroglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("tropical_froglin"));

    public static final Supplier<EntityType<ScorchedFroglin>> SCORCHED_FROGLIN =
            ENTIDADES.register("scorched_froglin", () -> EntityType.Builder.of(ScorchedFroglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("scorched_froglin"));

    public static final Supplier<EntityType<SkeletonFroglin>> SKELETON_FROGLIN =
            ENTIDADES.register("skeleton_froglin", () -> EntityType.Builder.of(SkeletonFroglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("skeleton_froglin"));


    public static final Supplier<EntityType<GrayShamanFroglin>> GRAY_SHAMAN_FROGLIN =
            ENTIDADES.register("gray_shaman_froglin", () -> EntityType.Builder.of(GrayShamanFroglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("gray_shaman_froglin"));


    public static final Supplier<EntityType<GrayFroglin>> GRAY_FROGLIN =
            ENTIDADES.register("gray_froglin", () -> EntityType.Builder.of(GrayFroglin::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f).build("gray_froglin"));


    public static final Supplier<EntityType<GrayRider>> GRAY_RIDER_FROGLIN =
            ENTIDADES.register("gray_rider_froglin", () -> EntityType.Builder.of(GrayRider::new, MobCategory.CREATURE)
                    .sized(1.2f, 1.5f).build("gray_rider_froglin"));




    public static final Supplier<EntityType<Frogronk>> FROGRONK =
            ENTIDADES.register("frogronk", () -> EntityType.Builder.of(Frogronk::new, MobCategory.CREATURE)
                    .sized(0.9f, 2f).build("frogronk"));




    public static final Supplier<EntityType<ThrowableViscosity>> VISCOSITY_PROJECTILE =
            ENTIDADES.register("viscosity_projectile", () -> EntityType.Builder.<ThrowableViscosity>of(ThrowableViscosity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("viscosity_projectile"));



    public static void registrar(IEventBus eventBus) {
        ENTIDADES.register(eventBus);
    }




}

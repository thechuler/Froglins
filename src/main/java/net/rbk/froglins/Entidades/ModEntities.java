package net.rbk.froglins.Entidades;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Froglins;

import java.util.function.Supplier;


public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTIDADES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Froglins.MODID);


    public static final Supplier<EntityType<Froglin>> FROGLIN =
            ENTIDADES.register("froglin", () -> EntityType.Builder.of(Froglin::new, MobCategory.CREATURE)
                    .sized(1.2f, 1.5f).build("froglin"));

    public static final Supplier<EntityType<ZombieFroglin>> ZOMBIE_FROGLIN =
            ENTIDADES.register("zombie_froglin", () -> EntityType.Builder.of(ZombieFroglin::new, MobCategory.CREATURE)
                    .sized(1.2f, 1.5f).build("zombie_froglin"));




    public static void registrar(IEventBus eventBus) {
        ENTIDADES.register(eventBus);
    }




}

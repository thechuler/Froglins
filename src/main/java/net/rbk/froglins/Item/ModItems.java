package net.rbk.froglins.Item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Sonidos.ModSounds;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Froglins.MODID);

//--------------------Registros de items----------------------------

    public static final DeferredItem<Item> RAW_FROG_MEAT = ITEMS.register("raw_frog_meat",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(5).saturationModifier(3).effect(new MobEffectInstance(MobEffects.POISON,100),1).build())));


    public static final DeferredItem<Item> COOKED_FROG_MEAT = ITEMS.register("cooked_frog_meat",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(10).saturationModifier(8).build())));


    public static final DeferredItem<Item> FROGLIN_SPAWN_EGG = ITEMS.register("froglin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.FROGLIN,0x428430,0xa8a84e,new Item.Properties()));


    public static final DeferredItem<Item> ZOMBIE_FROGLIN_SPAWN_EGG = ITEMS.register("zombie_froglin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ZOMBIE_FROGLIN,0x428430,0xa8a84e,new Item.Properties()));


    public static final DeferredItem<Item> DISCODELPAPU = ITEMS.register("discodelpapu",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.YSYA_KEY).stacksTo(64).food(new FoodProperties.Builder().nutrition(10).saturationModifier(8).build())));




    public static void registrar(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

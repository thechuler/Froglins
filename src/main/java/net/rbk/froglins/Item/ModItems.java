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
import net.rbk.froglins.Item.custom.Crusher;
import net.rbk.froglins.Item.custom.HarmfulViscosity;
import net.rbk.froglins.Item.custom.Viscosity;
import net.rbk.froglins.Sonidos.ModSounds;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Froglins.MODID);


//--------------------Registros de items----------------------------

    public static final DeferredItem<Item> RAW_FROG_MEAT = ITEMS.register("raw_frog_meat",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(5).saturationModifier(3).effect(new MobEffectInstance(MobEffects.POISON, 100), 1).build())));


    public static final DeferredItem<Item> COOKED_FROG_MEAT = ITEMS.register("cooked_frog_meat",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(10).saturationModifier(8).build())));

    public static final DeferredItem<Item> RAW_FROG_BROCHETTE = ITEMS.register("raw_frog_brochette",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(7).saturationModifier(5).effect(new MobEffectInstance(MobEffects.BLINDNESS, 100), 1).build())));


    public static final DeferredItem<Item> COOKED_FROG_BROCHETTE = ITEMS.register("cooked_frog_brochette",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(13).saturationModifier(10).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100), 1).build())));


    public static final DeferredItem<HarmfulViscosity> HARMFUL_VISCOSITY = ITEMS.register("harmful_viscosity",
            () -> new HarmfulViscosity(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));


    public static final DeferredItem<Viscosity> VISCOSITY = ITEMS.register("viscosity",
            () -> new Viscosity(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));


    public static final DeferredItem<Item> HEAVY_HORN = ITEMS.register("heavy_horn",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));


    public static final DeferredItem<Item> TROPICAL_EYE = ITEMS.register("tropical_eye",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));


    public static final DeferredItem<Crusher> CRUSHER = ITEMS.register("crusher",
            () -> new Crusher(Tiers.DIAMOND, new Item.Properties().rarity(Rarity.UNCOMMON)
                    .attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 7F, -3.5f))));


    public static final DeferredItem<Item> DISCODELPAPU = ITEMS.register("discodelpapu",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.YSYA_KEY).stacksTo(64).food(new FoodProperties.Builder().nutrition(10).saturationModifier(8).build())));


    public static final DeferredItem<Item> TROPICAL_FROGLIN_SPAWN_EGG = ITEMS.register("tropical_froglin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.TROPICAL_FROGLIN, 0xf68b23, 0xf8e9c5, new Item.Properties()));


    public static final DeferredItem<Item> FROGLIN_SPAWN_EGG = ITEMS.register("froglin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.FROGLIN, 0x428430, 0xa8a84e, new Item.Properties()));


    public static final DeferredItem<Item> ZOMBIE_FROGLIN_SPAWN_EGG = ITEMS.register("zombie_froglin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ZOMBIE_FROGLIN, 0x26421d, 0xbabd58, new Item.Properties()));


    public static final DeferredItem<Item> WOOLLY_FROGLIN_SPAWN_EGG = ITEMS.register("wolly_froglin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.WOOLLY_FROGLIN, 0x583D29, 0x2B353F, new Item.Properties()));





    public static void registrar(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

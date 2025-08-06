package net.rbk.froglins.GeneracionDeMundo;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Froglins;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> SPAWN_FROGLIN = registerKey("spawn_froglin");
    public static final ResourceKey<BiomeModifier> SPAWN_FROGLIN_ZOMBIE = registerKey("spawn_froglin_zombie");
    public static final ResourceKey<BiomeModifier> SPAWN_FROGLIN_TROPICAL = registerKey("spawn_froglin_tropical");
    public static final ResourceKey<BiomeModifier> SPAWN_FROGLIN_WOOLLY = registerKey("spawn_froglin_woolly");



    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var biomes = context.lookup(Registries.BIOME);

        HolderSet<Biome> TROPICALBIOMES = HolderSet.direct(
                biomes.getOrThrow(Biomes.JUNGLE),
                biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                biomes.getOrThrow(Biomes.BAMBOO_JUNGLE)
        );

        HolderSet<Biome> WOOLLYBIOMES = HolderSet.direct(
                biomes.getOrThrow(Biomes.TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                biomes.getOrThrow(Biomes.SNOWY_PLAINS),
                biomes.getOrThrow(Biomes.SNOWY_SLOPES),
                biomes.getOrThrow(Biomes.SNOWY_TAIGA)
        );





        context.register(SPAWN_FROGLIN_WOOLLY, new BiomeModifiers.AddSpawnsBiomeModifier(
                WOOLLYBIOMES,
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.WOOLLY_FROGLIN.get(), 20, 2, 4))));

        context.register(SPAWN_FROGLIN, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.FROGLIN.get(), 20, 1, 3))));

        context.register(SPAWN_FROGLIN_TROPICAL, new BiomeModifiers.AddSpawnsBiomeModifier(
                TROPICALBIOMES,
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TROPICAL_FROGLIN.get(), 20, 3, 6))));

        context.register(SPAWN_FROGLIN_ZOMBIE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ZOMBIE_FROGLIN.get(), 20, 1, 3))));

    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, name));
    }
}
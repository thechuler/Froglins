package net.rbk.froglins.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.rbk.froglins.datagen.Loot.EntityLootTable;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DGLootTable extends LootTableProvider {
    public DGLootTable(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
                output,
                Set.of(),
                List.of(new SubProviderEntry(EntityLootTable::new, LootContextParamSets.ENTITY)),
                lookupProvider
        );
    }
}

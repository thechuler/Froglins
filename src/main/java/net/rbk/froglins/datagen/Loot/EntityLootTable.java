package net.rbk.froglins.datagen.Loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.rbk.froglins.Entidades.ModEntities;
import net.rbk.froglins.Item.ModItems;


import java.util.function.Supplier;
import java.util.stream.Stream;

public class EntityLootTable extends EntityLootSubProvider {
    public EntityLootTable(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        this.add(ModEntities.FROGLIN.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.RAW_FROG_MEAT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                ));



        this.add(ModEntities.ZOMBIE_FROGLIN.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                ));

    }


    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTIDADES.getEntries().stream().map(Supplier::get);
    }


}

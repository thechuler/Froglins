package net.rbk.froglins.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Item.ModItems;

public class DGItemModel extends ItemModelProvider {
    public DGItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Froglins.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModItems.RAW_FROG_MEAT.get());
        basicItem(ModItems.COOKED_FROG_MEAT.get());
        withExistingParent(ModItems.FROGLIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.ZOMBIE_FROGLIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}

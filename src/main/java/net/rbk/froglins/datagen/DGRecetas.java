package net.rbk.froglins.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.rbk.froglins.Item.ModItems;

import java.util.concurrent.CompletableFuture;

public class DGRecetas extends RecipeProvider implements IConditionBuilder {
    public DGRecetas(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.HARMFUL_VISCOSITY,1)
                .requires(ModItems.VISCOSITY)
                .requires(Items.POTION)
                .unlockedBy("has_neutral_viscosity", has(ModItems.VISCOSITY.get()))
                .save(recipeOutput);

    }
}

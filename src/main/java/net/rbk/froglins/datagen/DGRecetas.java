package net.rbk.froglins.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Item.ModItems;

import java.util.concurrent.CompletableFuture;

public class DGRecetas extends RecipeProvider implements IConditionBuilder {
    public DGRecetas(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.HARMFUL_VISCOSITY, 1)
                .requires(ModItems.VISCOSITY)
                .requires(Items.POTION)
                .unlockedBy("has_neutral_viscosity", has(ModItems.VISCOSITY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.RAW_FROG_BROCHETTE, 1)
                .requires(ModItems.RAW_FROG_MEAT)
                .requires(Items.STICK)
                .requires(Items.POTATO)
                .unlockedBy("has_frog_meat", has(ModItems.RAW_FROG_MEAT.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CRUSHER.get())
                .pattern(" P ")
                .pattern(" V ")
                .define('P', ModItems.HEAVY_HORN.get())
                .define('V', Items.STICK)
                .unlockedBy("has_heavy_horn", has(ModItems.HEAVY_HORN.get()))
                .save(recipeOutput);






        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ModItems.RAW_FROG_MEAT.get()),
                        RecipeCategory.MISC,
                        ModItems.COOKED_FROG_MEAT.get(),
                        0.35f,
                        200
                )
                .unlockedBy("has_frog_meat", has(ModItems.RAW_FROG_MEAT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "cooked_frog_meat_smelting"));

        SimpleCookingRecipeBuilder.campfireCooking(
                        Ingredient.of(ModItems.RAW_FROG_MEAT.get()),
                        RecipeCategory.FOOD,
                        ModItems.COOKED_FROG_MEAT.get(),
                        0.35f,
                        600 // 30 segundos
                )
                .unlockedBy("has_frog_meat", has(ModItems.RAW_FROG_MEAT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "cooked_frog_meat_campfire"));

        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(ModItems.RAW_FROG_MEAT.get()),
                        RecipeCategory.FOOD,
                        ModItems.COOKED_FROG_MEAT.get(),
                        0.35f,
                        100
                )
                .unlockedBy("has_frog_meat", has(ModItems.RAW_FROG_MEAT.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "cooked_frog_meat_smoking"));







        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ModItems.RAW_FROG_BROCHETTE.get()),
                        RecipeCategory.MISC,
                        ModItems.COOKED_FROG_BROCHETTE.get(),
                        0.35f,
                        200
                )
                .unlockedBy("has_brochette", has(ModItems.RAW_FROG_BROCHETTE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "cooked_brochette_smelting"));

        SimpleCookingRecipeBuilder.campfireCooking(
                        Ingredient.of(ModItems.RAW_FROG_BROCHETTE.get()),
                        RecipeCategory.FOOD,
                        ModItems.COOKED_FROG_BROCHETTE.get(),
                        0.35f,
                        600 // 30 segundos
                )
                .unlockedBy("has_brochette", has(ModItems.RAW_FROG_BROCHETTE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "cooked_brochette_campfire"));

        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(ModItems.RAW_FROG_BROCHETTE.get()),
                        RecipeCategory.FOOD,
                        ModItems.COOKED_FROG_BROCHETTE.get(),
                        0.35f,
                        100
                )
                .unlockedBy("has_brochette", has(ModItems.RAW_FROG_BROCHETTE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "cooked_brochette_smoking"));




    }




}

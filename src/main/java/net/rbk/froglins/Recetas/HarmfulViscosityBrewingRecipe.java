package net.rbk.froglins.Recetas;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.rbk.froglins.DataComponent.ModDataComponent;
import net.rbk.froglins.Item.ModItems;

import java.util.Optional;

public class HarmfulViscosityBrewingRecipe implements IBrewingRecipe {

    @Override
    public boolean isInput(ItemStack input) {
        return input.is(Items.POTION) && input.get(DataComponents.POTION_CONTENTS) != null;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.is(ModItems.VISCOSITY.get());
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (!isInput(input) || !isIngredient(ingredient)) {
            return ItemStack.EMPTY;
        }

        PotionContents contents = input.get(DataComponents.POTION_CONTENTS);
        Optional<Holder<Potion>> optionalPotion = contents.potion();
        if (optionalPotion.isEmpty()) return ItemStack.EMPTY;

        Potion potion = optionalPotion.get().value();


        if (potion.getEffects().isEmpty()) return ItemStack.EMPTY;
        MobEffectInstance effect = potion.getEffects().get(0);

        ItemStack output = new ItemStack(ModItems.HARMFUL_VISCOSITY.get());
        output.set(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get(), effect);

        return output;
    }
}

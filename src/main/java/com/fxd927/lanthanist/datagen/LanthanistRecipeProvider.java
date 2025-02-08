package com.fxd927.lanthanist.datagen;

import com.fxd927.lanthanist.Lanthanist;
import com.fxd927.lanthanist.registries.LanthanistBlocks;
import com.fxd927.lanthanist.registries.LanthanistItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LanthanistRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public LanthanistRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> RARE_EARTH_SMELTABLES = List.of(LanthanistItems.RAW_RARE_EARTH,
                LanthanistBlocks.RARE_EARTH_ORE, LanthanistBlocks.DEEPSLATE_RARE_EARTH_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LanthanistBlocks.DYSPROSIUM_BLOCK.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', LanthanistItems.DYSPROSIUM_INGOT.get())
                .unlockedBy("has_dysprosium_ingot", has(LanthanistItems.DYSPROSIUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LanthanistItems.DYSPROSIUM_INGOT.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', LanthanistItems.DYSPROSIUM_NUGGET.get())
                .unlockedBy("has_dysprosium_nugget", has(LanthanistItems.DYSPROSIUM_NUGGET)).save(recipeOutput, "lanthanist:dysprosium_ingot_from_nuggets");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LanthanistItems.DYSPROSIUM_INGOT.get(), 9)
                .requires(LanthanistBlocks.DYSPROSIUM_BLOCK)
                .unlockedBy("has_dysprosium_block", has(LanthanistBlocks.DYSPROSIUM_BLOCK)).save(recipeOutput, "lanthanist:dysprosium_ingot_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LanthanistItems.DYSPROSIUM_NUGGET.get(), 9)
                .requires(LanthanistItems.DYSPROSIUM_INGOT)
                .unlockedBy("has_dysprosium_ingot", has(LanthanistItems.DYSPROSIUM_INGOT)).save(recipeOutput);

        oreSmelting(recipeOutput, RARE_EARTH_SMELTABLES, RecipeCategory.MISC, LanthanistItems.DYSPROSIUM_INGOT.get(), 1.0f, 200, "dysprosium");
        oreBlasting(recipeOutput, RARE_EARTH_SMELTABLES, RecipeCategory.MISC, LanthanistItems.DYSPROSIUM_INGOT.get(), 1.0f, 100, "dysprosium");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Lanthanist.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

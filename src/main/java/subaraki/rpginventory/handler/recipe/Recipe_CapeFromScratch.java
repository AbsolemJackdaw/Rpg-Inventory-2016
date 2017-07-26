package subaraki.rpginventory.handler.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.mod.RpgInventory;
import static subaraki.rpginventory.item.RpgItems.*;

public class Recipe_CapeFromScratch implements IRecipeFactory {
	@Override
	public IRecipe parse(JsonContext context, JsonObject json) {
		ShapedOreRecipe recipe = ShapedOreRecipe.factory(context, json);

		ShapedPrimer primer = new ShapedPrimer();
		primer.width = recipe.getWidth();
		primer.height = recipe.getHeight();
		primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
		primer.input = recipe.getIngredients();

		return new CraftCapes(new ResourceLocation(RpgInventory.MODID, "cape_crafting"), recipe.getRecipeOutput(), primer);
	}

	public static class CraftCapes extends ShapedOreRecipe {

		RpgInventoryItem cloaks[] = new RpgInventoryItem[]{
				cloakBlack,cloakRed,cloakGreen,cloakBrown,cloakBlue,
				cloakPurple,cloakCyan,cloakSilver,cloakGray,cloakPink,cloakLime,
				cloakYellow,cloakLightblue,cloakMagenta,cloakOrange,cloakWhite
		};


		public CraftCapes(ResourceLocation group, ItemStack result, ShapedPrimer primer) {
			super(group, result, primer);
		}

		@Override
		public boolean matches(InventoryCrafting crafting, World world) {

			Item wool = Item.getItemFromBlock(Blocks.WOOL);

			ItemStack firstWoolFound = ItemStack.EMPTY;
			int meta = -1;
			int string = 0;
			
			int identicalWools = 0;

			for(int slot = 0 ; slot < crafting.getSizeInventory(); slot++)
			{
				if(!firstWoolFound.isEmpty())
				{
					if(crafting.getStackInSlot(slot).getItem().equals(firstWoolFound.getItem()))
						if(firstWoolFound.getMetadata() == crafting.getStackInSlot(slot).getMetadata())

						identicalWools++;
				}
				else if(crafting.getStackInSlot(slot).getItem().equals(wool))
				{
					firstWoolFound = crafting.getStackInSlot(slot).copy();
					meta = firstWoolFound.getMetadata();
				}
				for(ItemStack stack : OreDictionary.getOres("string"))
				{
					if(stack.getItem().equals(crafting.getStackInSlot(slot).getItem()))
						string++;
				}
			}


			if(identicalWools == 3 && string == 2)
			{
				output = new ItemStack(cloaks[15-meta], 1);
				return true;
			}

			return false;
		}
	}
}
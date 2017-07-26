package subaraki.rpginventory.handler.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import net.minecraftforge.oredict.ShapedOreRecipe;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.item.RpgItems;
import subaraki.rpginventory.mod.RpgInventory;
import static subaraki.rpginventory.item.RpgItems.*;

public class Recipe_CapeFromDye implements IRecipeFactory {
	@Override
	public IRecipe parse(JsonContext context, JsonObject json) {
		ShapedOreRecipe recipe = ShapedOreRecipe.factory(context, json);

		ShapedPrimer primer = new ShapedPrimer();
		primer.width = recipe.getWidth();
		primer.height = recipe.getHeight();
		primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
		primer.input = recipe.getIngredients();

		return new CraftCapes(new ResourceLocation(RpgInventory.MODID, "cape_dye_crafting"), recipe.getRecipeOutput(), primer);
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

			Item dye = Items.DYE;

			ItemStack firstDyeFound = ItemStack.EMPTY;
			int meta = -1;
			boolean cape = false;
			
			int identicalDyes = 0;

			for(int slot = 0 ; slot < crafting.getSizeInventory(); slot++)
			{
				if(!firstDyeFound.isEmpty())
				{
					if(crafting.getStackInSlot(slot).getItem().equals(firstDyeFound.getItem()))
						if(firstDyeFound.getMetadata() == crafting.getStackInSlot(slot).getMetadata())
						identicalDyes++;
				}
				else if(crafting.getStackInSlot(slot).getItem().equals(dye))
				{
					firstDyeFound = crafting.getStackInSlot(slot).copy();
					meta = firstDyeFound.getMetadata();
				}
				
				if(crafting.getStackInSlot(slot).getItem().equals(RpgItems.cloakWhite))
					cape = true;
			}


			if(identicalDyes == 7 && cape)
			{
				output = new ItemStack(cloaks[meta], 1);
				return true;
			}

			return false;
		}
	}
}
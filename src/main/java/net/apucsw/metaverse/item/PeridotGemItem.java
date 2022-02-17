
package net.apucsw.metaverse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

//import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.client.gui.screen.Screen;

import net.apucsw.metaverse.itemgroup.MetaverseContentsItemGroup;
import net.apucsw.metaverse.MetaverseModElements;

import java.util.List;

@MetaverseModElements.ModElement.Tag
public class PeridotGemItem extends MetaverseModElements.ModElement {
	@ObjectHolder("metaverse:peridot_gem")
	public static final Item block = null;

	public PeridotGemItem(MetaverseModElements instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MetaverseContentsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("peridot_gem");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			if(Screen.hasShiftDown()) {
				list.add(new StringTextComponent("\u00A7fPeridot is an olivine that is valuable as a gemstone."));
				list.add(new StringTextComponent("\u00A7fThe beautiful peridot green color is reminiscent of 'him'."));
			} else {
				list.add(new StringTextComponent("\u00A7fHold \u00A7bSHIFT\u00A7f for a description."));
			}
		}
	}
}

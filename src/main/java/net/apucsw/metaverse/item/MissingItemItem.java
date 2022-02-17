
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

import net.apucsw.metaverse.itemgroup.MetaverseContentsItemGroup;
import net.apucsw.metaverse.MetaverseModElements;

import java.util.List;

@MetaverseModElements.ModElement.Tag
public class MissingItemItem extends MetaverseModElements.ModElement {
	@ObjectHolder("metaverse:missing_item")
	public static final Item block = null;

	public MissingItemItem(MetaverseModElements instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MetaverseContentsItemGroup.tab).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("missing_item");
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
			return 0F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u00A7cERROR 404: NOT FOUND!"));
			list.add(new StringTextComponent("\u00A7c\u00A7kERR_CODE_RED:DESCRIPTION_DATA_EXPUNGED"));
		}
	}
}

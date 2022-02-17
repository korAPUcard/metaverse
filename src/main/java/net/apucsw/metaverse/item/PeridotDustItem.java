
package net.apucsw.metaverse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.apucsw.metaverse.itemgroup.MetaverseContentsItemGroup;
import net.apucsw.metaverse.MetaverseModElements;

@MetaverseModElements.ModElement.Tag
public class PeridotDustItem extends MetaverseModElements.ModElement {
	@ObjectHolder("metaverse:peridot_dust")
	public static final Item block = null;

	public PeridotDustItem(MetaverseModElements instance) {
		super(instance, 23);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MetaverseContentsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("peridot_dust");
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
	}
}

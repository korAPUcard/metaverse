
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
public class PeridotGearItem extends MetaverseModElements.ModElement {
	@ObjectHolder("metaverse:peridot_gear")
	public static final Item block = null;

	public PeridotGearItem(MetaverseModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MetaverseContentsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("peridot_gear");
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
			list.add(new StringTextComponent("\u00A7bMouse hover to see more detail"));
			list.add(new StringTextComponent("\u00A7fIt is a cog wheel made of beautiful paridot that contains the mystery of the universe."));
		}
	}
}

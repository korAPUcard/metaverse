
package net.apucsw.metaverse.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.apucsw.metaverse.itemgroup.MetaverseContentsItemGroup;
import net.apucsw.metaverse.MetaverseModElements;

import java.util.List;
import java.util.Collections;

@MetaverseModElements.ModElement.Tag
public class MissingBlockBlock extends MetaverseModElements.ModElement {
	@ObjectHolder("metaverse:missing_block")
	public static final Block block = null;

	public MissingBlockBlock(MetaverseModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(MetaverseContentsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.MISCELLANEOUS)
					.sound(new ForgeSoundType(1.0f, 1.0f, () -> new SoundEvent(new ResourceLocation("metaverse:missing_paste_corrupt")),
							() -> new SoundEvent(new ResourceLocation("metaverse:missing_paste_squish")),
							() -> new SoundEvent(new ResourceLocation("metaverse:missing_paste_impact")),
							() -> new SoundEvent(new ResourceLocation("metaverse:missing_paste_impact")),
							() -> new SoundEvent(new ResourceLocation("metaverse:missing_paste_impact"))))
					.hardnessAndResistance(-1, 3600000).setLightLevel(s -> 0).harvestLevel(5).harvestTool(ToolType.PICKAXE).setRequiresTool()
					.setNeedsPostProcessing((bs, br, bp) -> true).setEmmisiveRendering((bs, br, bp) -> true));
			setRegistryName("missing_block");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u00A7c\u00A7kERR_CODE_RED:DESCRIPTION_DATA_EXPUNGED"));
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.PINK;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}

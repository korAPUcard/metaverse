
package net.apucsw.metaverse.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.apucsw.metaverse.item.MissingPasteItem;
import net.apucsw.metaverse.MetaverseModElements;

@MetaverseModElements.ModElement.Tag
public class MetaverseContentsItemGroup extends MetaverseModElements.ModElement {
	public MetaverseContentsItemGroup(MetaverseModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmetaverse_contents") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(MissingPasteItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}

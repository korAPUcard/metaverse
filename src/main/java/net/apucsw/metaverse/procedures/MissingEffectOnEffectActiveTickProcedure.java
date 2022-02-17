package net.apucsw.metaverse.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.FlowingFluidBlock;

import net.apucsw.metaverse.block.MissingFluidBlock;
import net.apucsw.metaverse.MetaverseModVariables;
import net.apucsw.metaverse.MetaverseMod;

import java.util.Map;

public class MissingEffectOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MetaverseMod.LOGGER.warn("Failed to load dependency world for procedure MissingEffectOnEffectActiveTick!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MetaverseMod.LOGGER.warn("Failed to load dependency entity for procedure MissingEffectOnEffectActiveTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.MILK_BUCKET)) : false) {
			if (world instanceof World && !world.isRemote()) {
				((World) world)
						.playSound(null, new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("metaverse:missing_paste_corrupt")),
								SoundCategory.NEUTRAL, (float) 0.5, (float) 1);
			} else {
				((World) world).playSound((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("metaverse:missing_paste_corrupt")),
						SoundCategory.NEUTRAL, (float) 0.5, (float) 1, false);
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Items.MILK_BUCKET);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (MissingFluidBlock.block instanceof FlowingFluidBlock
						? new ItemStack(((FlowingFluidBlock) MissingFluidBlock.block).getFluid().getFilledBucket())
						: ItemStack.EMPTY);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			{
				double _setval = (1 + (entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MetaverseModVariables.PlayerVariables())).lostMilkBuckets);
				entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.lostMilkBuckets = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}

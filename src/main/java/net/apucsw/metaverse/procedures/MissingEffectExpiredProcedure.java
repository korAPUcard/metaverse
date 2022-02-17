package net.apucsw.metaverse.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

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

import net.apucsw.metaverse.item.MissingItemItem;
import net.apucsw.metaverse.block.MissingFluidBlock;
import net.apucsw.metaverse.MetaverseModVariables;
import net.apucsw.metaverse.MetaverseMod;

import java.util.Map;
import java.util.HashMap;

public class MissingEffectExpiredProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MetaverseMod.LOGGER.warn("Failed to load dependency world for procedure MissingEffectExpired!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MetaverseMod.LOGGER.warn("Failed to load dependency entity for procedure MissingEffectExpired!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("metaverse:data_decorruption")),
					SoundCategory.NEUTRAL, (float) 0.5, (float) 1);
		} else {
			((World) world).playSound((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("metaverse:data_decorruption")),
					SoundCategory.NEUTRAL, (float) 0.5, (float) 1, false);
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).addExperienceLevel((int) 1);
		if ((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack((MissingFluidBlock.block instanceof FlowingFluidBlock
						? new ItemStack(((FlowingFluidBlock) MissingFluidBlock.block).getFluid().getFilledBucket())
						: ItemStack.EMPTY))
				: false) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = (MissingFluidBlock.block instanceof FlowingFluidBlock
						? new ItemStack(((FlowingFluidBlock) MissingFluidBlock.block).getFluid().getFilledBucket())
						: ItemStack.EMPTY);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 999,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(MissingItemItem.block);
				_setstack.setCount((int) (1 * (entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MetaverseModVariables.PlayerVariables())).lostMilkBuckets));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(Items.IRON_NUGGET);
				_setstack.setCount((int) (27 * (entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MetaverseModVariables.PlayerVariables())).lostMilkBuckets));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					{
						double _setval = 0;
						entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.lostMilkBuckets = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 5);
		}
		{
			boolean _setval = (false);
			entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.disableRemoveEffect = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}

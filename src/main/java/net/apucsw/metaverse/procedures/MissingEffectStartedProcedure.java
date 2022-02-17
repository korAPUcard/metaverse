package net.apucsw.metaverse.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.apucsw.metaverse.MetaverseModVariables;
import net.apucsw.metaverse.MetaverseMod;

import java.util.Map;

public class MissingEffectStartedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MetaverseMod.LOGGER.warn("Failed to load dependency world for procedure MissingEffectStarted!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MetaverseMod.LOGGER.warn("Failed to load dependency entity for procedure MissingEffectStarted!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("metaverse:missing_paste_corrupt")),
					SoundCategory.NEUTRAL, (float) 0.5, (float) 1);
		} else {
			((World) world).playSound((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("metaverse:missing_paste_corrupt")),
					SoundCategory.NEUTRAL, (float) 0.5, (float) 1, false);
		}
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).giveExperiencePoints((int) -1);
		}
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 8);
		{
			double _setval = 0;
			entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.lostMilkBuckets = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			boolean _setval = (true);
			entity.getCapability(MetaverseModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.disableRemoveEffect = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}

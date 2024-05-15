package com.chailotl.particular.mixin;

import com.chailotl.particular.Main;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class InjectBlock
{
	@Inject(at = @At("TAIL"), method = "randomDisplayTick")
	public void spawnFireflies(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci)
	{
		Block block = state.getBlock();
		boolean isGrass = block == Blocks.GRASS || block == Blocks.TALL_GRASS;
		if (isGrass ||
			block instanceof FlowerBlock ||
			block instanceof TallFlowerBlock)
		{
			if (isGrass && random.nextInt(6) != 0)
			{
				return;
			}

			Main.spawnFirefly(world, pos, random);
		}
	}
}
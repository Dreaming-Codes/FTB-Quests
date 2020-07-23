package com.feed_the_beast.ftbquests;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Objects;

public class EventHandlerCommon {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerPickupXP(PlayerEvent.PlayerLoggedInEvent e) {
        System.out.println("Ciao");
        Minecraft mc = Minecraft.getMinecraft();
        String biome = Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(mc.world.getBiome(new BlockPos(Objects.requireNonNull(mc.getRenderViewEntity()).posX, mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ)))).toString();
        System.out.println(biome);
    }

}

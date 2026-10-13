package com.example.xppercent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@Mod(value = XpPercentMod.MODID, dist = Dist.CLIENT)
public class XpPercentMod {
    public static final String MODID = "xppercent";

    public XpPercentMod(IEventBus modEventBus) {
        modEventBus.addListener(this::registerGuiLayers);
    }

    private void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAbove(
            VanillaGuiLayers.EXPERIENCE_BAR,
            ResourceLocation.fromNamespaceAndPath(MODID, "xp_percentage"),
            (guiGraphics, deltaTracker) -> {
                Minecraft mc = Minecraft.getInstance();
                LocalPlayer player = mc.player;

                if (player == null || mc.options.hideGui) {
                    return;
                }

                int percent = Math.round(player.experienceProgress * 100);
                String text = percent + "%";

                int screenWidth = mc.getWindow().getGuiScaledWidth();
                int screenHeight = mc.getWindow().getGuiScaledHeight();

                int x = (screenWidth / 2) + 95;
                int y = screenHeight - 14;

                guiGraphics.drawString(mc.font, text, x, y, 0x80FF20, true);
            }
        );
    }
}
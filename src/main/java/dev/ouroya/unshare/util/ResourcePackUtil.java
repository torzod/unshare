package dev.ouroya.unshare.util;

import dev.ouroya.unshare.Unshade;
import dev.ouroya.unshare.pack.ModResourcePack;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.ResourcePack;

import java.nio.file.Path;

public class ResourcePackUtil {

    public static ResourcePack loadResourcePack() {
        ModContainer mod = FabricLoader.getInstance().getModContainer(Unshade.MOD_ID)
                .orElseThrow(() -> new RuntimeException("Mod container not found?"));

        Path packFolder = mod.findPath("unshade")
                .orElseThrow(() -> new RuntimeException("Resource pack not found"));
        ResourcePack pack = new ModResourcePack(packFolder);
        Unshade.LOGGER.info("Resource pack " + pack.getName() + " loaded");
        return pack;
    }
}

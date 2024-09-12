package dev.tildejustin.shareware.util;

import dev.tildejustin.shareware.Unshade;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourcePack;

import java.io.File;
import java.nio.file.Path;

public class ResourcePackUtil {

    public static ResourcePack loadResourcePack() {
        ModContainer mod = FabricLoader.getInstance().getModContainer(Unshade.MOD_ID)
                .orElseThrow(() -> new RuntimeException("Mod container not found?"));
        Path resourcePath = mod.getRootPaths().get(0);
        Unshade.LOGGER.info("Resource pack path: " + resourcePath.toString());

        File packFolder = new File(resourcePath.toFile(), "unshade");
        ResourcePack pack = new DirectoryResourcePack(packFolder);
        Unshade.LOGGER.info("Resource pack " + pack.getName() + " loaded");
        return pack;
    }
}

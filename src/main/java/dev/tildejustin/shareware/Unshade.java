package dev.tildejustin.shareware;

import dev.tildejustin.shareware.util.ResourcePackUtil;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Unshade implements ModInitializer {

    public static Unshade INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "shareware-multiplayer";

    public ResourcePack modPack;

    @Override
    public void onInitialize() {
        INSTANCE = this;
        try {
            modPack = ResourcePackUtil.loadResourcePack();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load resource pack", e);
        }
    }
}

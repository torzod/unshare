package dev.ouroya.unshare.pack;

import net.minecraft.resource.AbstractFilenameResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class ModResourcePack extends AbstractFilenameResourcePack {
    private final Path packPath;

    public ModResourcePack(Path packPath) {
        super(null);
        this.packPath = packPath;
    }

    private Path resolvePath(String file) {
        Path resolved = packPath.resolve(file).toAbsolutePath().normalize();
        return Files.exists(resolved) ? resolved : null;
    }

    @Override
    protected InputStream openFilename(String string) throws IOException {
        Path resolved = resolvePath(string);
        if (resolved != null) {
            return Files.newInputStream(resolved);
        }

        return null;
    }

    @Override
    protected boolean containsFilename(String string) {
        Path resolved = resolvePath(string);
        return resolved != null && Files.isRegularFile(resolved);
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String path, int depth, Predicate<String> pred) {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getNamespaces(ResourceType resourceType) {
        if (resourceType == ResourceType.ASSETS) {
            return Collections.singleton("minecraft");
        }

        return Collections.emptySet();
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public String getName() {
        return "unshade";
    }
}

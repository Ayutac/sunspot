package studio.abos.mc.sunspot.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import studio.abos.mc.sunspot.Sunspot;

@Environment(EnvType.CLIENT)
public interface SPMaterialRegistry {

    Material FLAMEFALL_FIRE_0 = new Material(TextureAtlas.LOCATION_BLOCKS, Sunspot.id("block/flamefall_fire_0"));
    Material FLAMEFALL_FIRE_1 = new Material(TextureAtlas.LOCATION_BLOCKS, Sunspot.id("block/flamefall_fire_1"));

}

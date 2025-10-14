package studio.abos.mc.sunspot.client.registry;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import studio.abos.mc.sunspot.Sunspot;

public interface SPMaterialRegistry {

    Material FLAMEFALL_FIRE_0 = new Material(TextureAtlas.LOCATION_BLOCKS, Sunspot.id("block/flamefall_fire_0"));
    Material FLAMEFALL_FIRE_1 = new Material(TextureAtlas.LOCATION_BLOCKS, Sunspot.id("block/flamefall_fire_1"));

}

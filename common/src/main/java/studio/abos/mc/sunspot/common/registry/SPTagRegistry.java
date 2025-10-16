package studio.abos.mc.sunspot.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import studio.abos.mc.sunspot.Sunspot;

public interface SPTagRegistry {

    TagKey<Item> SUBSTRATE_MATERIAL = TagKey.create(Registries.ITEM, Sunspot.id("substrate_material"));

    TagKey<EntityType<?>> UNAFFECTED_BY_AFFIX = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_affix"));

    static void init() {
        // intentionally left empty
    }
}

package studio.abos.mc.sunspot.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import studio.abos.mc.sunspot.Sunspot;

public interface SPTagRegistry {

    TagKey<Item> SUBSTRATE_MATERIAL = TagKey.create(Registries.ITEM, Sunspot.id("substrate_material"));
    TagKey<Item> UNAFFECTED_BY_ASH_TRANSFORMATION = TagKey.create(Registries.ITEM, Sunspot.id("unaffected_by_ash_transformation"));

    TagKey<EntityType<?>> UNAFFECTED_BY_AFFIX = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_affix"));
    TagKey<EntityType<?>> UNAFFECTED_BY_ASH = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_ash"));

    static void init() {
        // intentionally left empty
    }
}

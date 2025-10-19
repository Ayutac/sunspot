package studio.abos.mc.sunspot.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import studio.abos.mc.sunspot.Sunspot;

public interface SPTagRegistry {

    TagKey<Item> SUBSTRATE_MATERIAL = TagKey.create(Registries.ITEM, Sunspot.id("substrate_material"));
    TagKey<Item> UNAFFECTED_BY_ASH_TRANSFORMATION = TagKey.create(Registries.ITEM, Sunspot.id("unaffected_by_ash_transformation"));
    TagKey<Item> SEVERED_INTO_GRAVEL = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_gravel"));

    TagKey<EntityType<?>> UNAFFECTED_BY_AFFIX = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_affix"));
    TagKey<EntityType<?>> UNAFFECTED_BY_ASH = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_ash"));
    TagKey<EntityType<?>> UNAFFECTED_BY_IMPEL = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_impel"));
    TagKey<EntityType<?>> UNAFFECTED_BY_OFFSET = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_offset"));
    TagKey<EntityType<?>> UNAFFECTED_BY_REVITALISE = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_revitalise"));
    TagKey<EntityType<?>> UNAFFECTED_BY_SEVER = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_sever"));
    TagKey<EntityType<?>> UNAFFECTED_BY_SUSTAIN = TagKey.create(Registries.ENTITY_TYPE, Sunspot.id("unaffected_by_sustain"));

    static void init() {
        // intentionally left empty
    }
}

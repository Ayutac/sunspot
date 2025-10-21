package studio.abos.mc.sunspot.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import studio.abos.mc.sunspot.Sunspot;

public interface SPTagRegistry {

    TagKey<Item> SUBSTRATE_MATERIAL = TagKey.create(Registries.ITEM, Sunspot.id("substrate_material"));
    TagKey<Item> UNAFFECTED_BY_ASH_TRANSFORMATION = TagKey.create(Registries.ITEM, Sunspot.id("unaffected_by_ash_transformation"));
    TagKey<Item> SEVERED_INTO_COBBLESTONE = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_cobblestone"));
    TagKey<Item> SEVERED_INTO_COBBLED_DEEPSLATE = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_cobbled_deepslate"));
    TagKey<Item> SEVERED_INTO_BLACKSTONE = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_blackstone"));
    TagKey<Item> SEVERED_INTO_GRAVEL = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_gravel"));
    TagKey<Item> SEVERED_INTO_SAND = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_sand"));
    TagKey<Item> SEVERED_INTO_RED_SAND = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_red_sand"));
    TagKey<Item> SEVERED_INTO_DIRT = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_dirt"));
    TagKey<Item> SEVERED_INTO_NETHERRACK = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_netherrack"));
    TagKey<Item> SEVERED_INTO_FOUR_NETHER_BRICK = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_four_nether_brick"));
    TagKey<Item> SEVERED_INTO_FOUR_NETHER_QUARTZ = TagKey.create(Registries.ITEM, Sunspot.id("severed_into_four_nether_quartz"));

    TagKey<Block> GLASS_BLOCKS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "glass_blocks"));

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

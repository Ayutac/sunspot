package studio.abos.mc.sunspot.datagen.provider.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemPreRegistry;
import studio.abos.mc.sunspot.common.registry.SPTagRegistry;

import java.util.concurrent.CompletableFuture;

public class SPTagProviders {

    public static class SPItemTags extends FabricTagProvider.ItemTagProvider {

        public SPItemTags(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(final @NotNull HolderLookup.Provider lookup) {
            getOrCreateTagBuilder(SPTagRegistry.SUBSTRATE_MATERIAL).add(Items.BONE_BLOCK);
            getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_ASH_TRANSFORMATION).add(
                    SPItemPreRegistry.ASH_RESIDUE.getId(),
                    SPItemPreRegistry.ASH_RESIDUE_BLOCK.getId()
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_GRAVEL).add(
                    Items.COBBLESTONE,
                    Items.COBBLED_DEEPSLATE,
                    Items.GRANITE,
                    Items.DIORITE,
                    Items.ANDESITE,
                    Items.TUFF
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_SAND).add(
                    Items.GRAVEL,
                    Items.SANDSTONE,
                    Items.SMOOTH_SANDSTONE,
                    Items.CUT_SANDSTONE,
                    Items.CHISELED_SANDSTONE
            );
            getOrCreateTagBuilder(SPTagRegistry.SEVERED_INTO_RED_SAND).add(
                    Items.RED_SANDSTONE,
                    Items.SMOOTH_RED_SANDSTONE,
                    Items.CUT_RED_SANDSTONE,
                    Items.CHISELED_RED_SANDSTONE
            );
        }
    }

    public static class SPEntityTypeTags extends FabricTagProvider.EntityTypeTagProvider {

        public SPEntityTypeTags(final @NotNull FabricDataOutput output, final @NotNull CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(final @NotNull HolderLookup.Provider lookup) {
            var unaffectedByAffix = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_AFFIX);
            unaffectedByAffix.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByAffix.add(EntityType.VEX);

            var unaffectedByAsh = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_ASH);
            unaffectedByAsh.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByAsh.add(EntityType.VEX);

            var unaffectedByImpel = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_IMPEL);
            unaffectedByImpel.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByImpel.add(EntityType.VEX);

            var unaffectedByOffset = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_OFFSET);
            unaffectedByOffset.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByOffset.add(EntityType.VEX);

            var unaffectedByRevitalise = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_REVITALISE);
            unaffectedByRevitalise.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedByRevitalise.add(EntityType.VEX);
            unaffectedByRevitalise.addOptionalTag(EntityTypeTags.UNDEAD);

            var unaffectedBySever = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_SEVER);
            unaffectedBySever.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedBySever.add(EntityType.VEX);

            var unaffectedBySustain = getOrCreateTagBuilder(SPTagRegistry.UNAFFECTED_BY_SUSTAIN);
            unaffectedBySustain.add(SPEntityTypeRegistry.FLAMEFALL.getId());
            unaffectedBySustain.add(EntityType.VEX);
        }
    }
}

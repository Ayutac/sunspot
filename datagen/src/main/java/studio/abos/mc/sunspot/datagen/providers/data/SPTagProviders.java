package studio.abos.mc.sunspot.datagen.providers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.common.registry.SPEntityTypeRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;
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
                    SPItemRegistry.ASH_RESIDUE.getId(),
                    SPItemRegistry.ASH_RESIDUE_BLOCK.getId()
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
        }
    }
}

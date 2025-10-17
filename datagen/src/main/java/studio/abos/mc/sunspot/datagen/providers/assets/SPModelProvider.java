package studio.abos.mc.sunspot.datagen.providers.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import studio.abos.mc.sunspot.common.registry.SPBlockRegistry;
import studio.abos.mc.sunspot.common.registry.SPItemRegistry;

public class SPModelProvider extends FabricModelProvider {

    public SPModelProvider(final FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(final BlockModelGenerators gen) {
        gen.createNonTemplateModelBlock(SPBlockRegistry.SUBSTRATE_3.get());
        gen.createNonTemplateModelBlock(SPBlockRegistry.SUBSTRATE_4.get());
        gen.createTrivialCube(SPBlockRegistry.ASH_RESIDUE.get());
        gen.createTrivialCube(SPBlockRegistry.LM_WORKBENCH.get());
        generateCubeAllPowered(gen, SPBlockRegistry.LM_BATTERY.get());
        generateCubeAllPowered(gen, SPBlockRegistry.LM_BATTERY_CREATIVE.get());
        generateCubeAllPowered(gen, SPBlockRegistry.AFFIX.get());
        generateCubeAllPowered(gen, SPBlockRegistry.ASH.get());
    }

    public static void generateCubeAllPowered(final BlockModelGenerators gen, final Block block) {
        gen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(PropertyDispatch.property(BlockStateProperties.POWERED)
                .select(true, Variant.variant().with(VariantProperties.MODEL, gen.createSuffixedVariant(block, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                .select(false, Variant.variant().with(VariantProperties.MODEL, TexturedModel.CUBE.create(block, gen.modelOutput)))));
    }

    @Override
    public void generateItemModels(final ItemModelGenerators gen) {
        gen.generateFlatItem(SPItemRegistry.SUBSTRATE_2.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.ASH_RESIDUE.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_HELMET.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.FOURSPACE_SHIFTER.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.FLAMEFALL_ROD.get(), ModelTemplates.FLAT_ITEM);
    }
}

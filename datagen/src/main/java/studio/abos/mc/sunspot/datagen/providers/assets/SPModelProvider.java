package studio.abos.mc.sunspot.datagen.providers.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
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
    }

    @Override
    public void generateItemModels(final ItemModelGenerators gen) {
        gen.generateFlatItem(SPItemRegistry.SUBSTRATE_2.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_HELMET.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.MANTLE_BASE_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(SPItemRegistry.FOURSPACE_SHIFTER.get(), ModelTemplates.FLAT_ITEM);
    }
}

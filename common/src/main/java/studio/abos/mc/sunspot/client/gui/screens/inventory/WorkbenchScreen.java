package studio.abos.mc.sunspot.client.gui.screens.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.sunspot.Sunspot;
import studio.abos.mc.sunspot.common.inventory.WorkbenchMenu;

@Environment(EnvType.CLIENT)
public class WorkbenchScreen extends AbstractContainerScreen<WorkbenchMenu> {

    public static final ResourceLocation BACKGROUND = Sunspot.id("textures/gui/container/workbench.png");
    public static final ResourceLocation PROGRESS_SPRITE = Sunspot.id("container/workbench/progress");

    public WorkbenchScreen(final @NotNull WorkbenchMenu workbenchMenu, final @NotNull Inventory inventory, final @NotNull Component component) {
        super(workbenchMenu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    protected void renderBg(final @NotNull GuiGraphics guiGraphics, final float f, final int i, final int j) {
        guiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        final int progress = Mth.ceil(menu.getProgress() * 24f);
        guiGraphics.blitSprite(PROGRESS_SPRITE, 24, 24, 0, 0, leftPos + 79, topPos + 30, progress, 24);
    }
}

package studio.abos.mc.sunspot.compat.jei;

import mezz.jei.api.gui.widgets.IRecipeWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class JeiSpriteWidget implements IRecipeWidget {

    private final ResourceLocation sprite;
    private final ScreenPosition position;
    private final int blitOffset;
    private final int width;
    private final int height;

    public JeiSpriteWidget(final @NotNull ResourceLocation sprite, final int x, final int y, final int blitOffset, final int width, final int height) {
        this.sprite = sprite;
        this.blitOffset = blitOffset;
        this.width = width;
        this.height = height;
        position = new ScreenPosition(x, y);
    }

    public JeiSpriteWidget(final @NotNull ResourceLocation sprite, final int x, final int y, final int width, final int height) {
        this(sprite, x, y, 0, width, height);
    }

    @Override
    public @NotNull ScreenPosition getPosition() {
        return position;
    }

    @Override
    public void drawWidget(final @NotNull GuiGraphics graphics, final double mouseX, final double mouseY) {
        graphics.blitSprite(sprite, 0, 0, blitOffset, width, height);
    }
}

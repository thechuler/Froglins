package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.rbk.froglins.Entidades.Lanzable.ThrowableViscosity;
import net.rbk.froglins.Item.ModItems;

public class ViscosityProjectileRenderer extends EntityRenderer<ThrowableViscosity> {

    private final ItemRenderer itemRenderer;

    public ViscosityProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public ResourceLocation getTextureLocation(ThrowableViscosity throwableViscosity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }




    @Override
    public void render(ThrowableViscosity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        // Rotaciones / transformaciones opcionales
        poseStack.scale(0.5f, 0.5f, 0.5f);

        itemRenderer.renderStatic(ModItems.VISCOSITY.toStack(), ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, entity.level(), entity.getId());
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, bufferSource, packedLight);
    }


}

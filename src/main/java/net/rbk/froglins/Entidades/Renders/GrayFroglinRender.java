package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.GrayFroglin;
import net.rbk.froglins.Entidades.Entity.WoollyFroglin;
import net.rbk.froglins.Entidades.Modelos.GrayFroglinModel;

public class GrayFroglinRender extends MobRenderer<GrayFroglin, GrayFroglinModel<GrayFroglin>> {
    public GrayFroglinRender(EntityRendererProvider.Context context, GrayFroglinModel<GrayFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }



    @Override
    public void render(GrayFroglin pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pPoseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }



    @Override
    public ResourceLocation getTextureLocation(GrayFroglin grayFroglin) {
        switch (grayFroglin.getVariante()){
            case 0:
                return grayFroglin.getVariantTextures().get(0);
            case 1:
                return grayFroglin.getVariantTextures().get(1);
            case 2:
                return grayFroglin.getVariantTextures().get(2);

            case 3:
                return grayFroglin.getVariantTextures().get(3);
        }

        return grayFroglin.getVariantTextures().get(0);
    }
}

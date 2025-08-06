package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.WoollyFroglin;
import net.rbk.froglins.Entidades.Modelos.WoollyFroglinModel;
import net.rbk.froglins.Froglins;

public class WoollyFroglinRender extends MobRenderer<WoollyFroglin, WoollyFroglinModel<WoollyFroglin>> {
    public WoollyFroglinRender(EntityRendererProvider.Context context, WoollyFroglinModel<WoollyFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }



    @Override
    public void render(WoollyFroglin entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render( entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(WoollyFroglin woollyFroglin) {
            switch (woollyFroglin.getVariante()){
                case 0:
                    return woollyFroglin.getVariantTextures().get(0);
                case 1:
                    return woollyFroglin.getVariantTextures().get(1);
                case 2:
                    return woollyFroglin.getVariantTextures().get(2);
            }

        return woollyFroglin.getVariantTextures().get(0);
    }




}

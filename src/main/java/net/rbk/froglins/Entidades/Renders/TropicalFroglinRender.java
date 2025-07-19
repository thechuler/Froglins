package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.TropicalFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Entidades.Modelos.TropicalFroglinModel;
import net.rbk.froglins.Froglins;

public class TropicalFroglinRender extends MobRenderer<TropicalFroglin, TropicalFroglinModel<TropicalFroglin>> {
    public TropicalFroglinRender(EntityRendererProvider.Context context, TropicalFroglinModel<TropicalFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }
    private static final ResourceLocation textura =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/tropical_froglin/tropical_froglin.png");



    @Override
    public void render(TropicalFroglin entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render( entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(TropicalFroglin tropicalFroglin) {
        return textura;
    }
}

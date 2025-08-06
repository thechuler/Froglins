package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.Frogronk;
import net.rbk.froglins.Entidades.Modelos.FrogronkModel;
import net.rbk.froglins.Froglins;

public class FrogronkRender extends MobRenderer<Frogronk, FrogronkModel<Frogronk>> {
    public FrogronkRender(EntityRendererProvider.Context context, FrogronkModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }
    private static final ResourceLocation textura =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/frogronk/tamedfrogronk.png");


    @Override
    public void render(Frogronk entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {


        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Frogronk frogronk) {
        return textura;
    }
}

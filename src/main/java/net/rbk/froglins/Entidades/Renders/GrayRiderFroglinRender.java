package net.rbk.froglins.Entidades.Renders;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.rbk.froglins.Entidades.Entity.GrayRider;
import net.rbk.froglins.Entidades.Modelos.GrayRiderModel;
import net.rbk.froglins.Froglins;

public class GrayRiderFroglinRender extends MobRenderer<GrayRider, GrayRiderModel<GrayRider>> {


    public GrayRiderFroglinRender(EntityRendererProvider.Context context, GrayRiderModel<GrayRider> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }
    private static final ResourceLocation textura =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/gray_rider_froglin/gray_rider_froglin.png");


    @Override
    public ResourceLocation getTextureLocation(GrayRider grayRider) {
        return textura;
    }
}

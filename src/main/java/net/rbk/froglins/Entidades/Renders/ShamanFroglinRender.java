package net.rbk.froglins.Entidades.Renders;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.GrayShamanFroglin;

import net.rbk.froglins.Entidades.Modelos.ShamanFroglinModel;


public class ShamanFroglinRender  extends MobRenderer<GrayShamanFroglin, ShamanFroglinModel<GrayShamanFroglin>> {
    public ShamanFroglinRender(EntityRendererProvider.Context context, ShamanFroglinModel<GrayShamanFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }






    @Override
    public ResourceLocation getTextureLocation(GrayShamanFroglin shamanFroglin) {
        return shamanFroglin.getVariantTextures().get(0);
    }
}

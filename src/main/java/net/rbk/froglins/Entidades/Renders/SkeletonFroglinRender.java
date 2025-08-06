package net.rbk.froglins.Entidades.Renders;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.SkeletonFroglin;
import net.rbk.froglins.Entidades.Entity.TropicalFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Entidades.Modelos.TropicalFroglinModel;

public class SkeletonFroglinRender  extends MobRenderer<SkeletonFroglin, FroglinModel<SkeletonFroglin>> {
    public SkeletonFroglinRender(EntityRendererProvider.Context context, FroglinModel<SkeletonFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonFroglin skeletonFroglin) {
        return skeletonFroglin.getVariantTextures().get(0);
    }
}

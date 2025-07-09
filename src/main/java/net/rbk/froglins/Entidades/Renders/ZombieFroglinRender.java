package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Froglins;
import org.jetbrains.annotations.NotNull;

public class ZombieFroglinRender extends MobRenderer<AbstractFroglin, FroglinModel<AbstractFroglin>> {

    public ZombieFroglinRender(EntityRendererProvider.Context context, FroglinModel<AbstractFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
        this.addLayer(new ZombieFroglinEyesRender(this));
    }



    private static final ResourceLocation textura =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/froglin/zombie_froglin.png");





    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractFroglin abstractFroglin) {
        return textura;
    }
}

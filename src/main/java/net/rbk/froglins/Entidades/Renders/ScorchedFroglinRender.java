package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.ScorchedFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;

import net.rbk.froglins.Entidades.Modelos.ScorchedFroglinModel;
import net.rbk.froglins.Froglins;

public class ScorchedFroglinRender extends MobRenderer<ScorchedFroglin, ScorchedFroglinModel<ScorchedFroglin>> {

    private static final ResourceLocation textura =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/scorched_froglin/scorched_froglin.png");

    public ScorchedFroglinRender(EntityRendererProvider.Context context, ScorchedFroglinModel<ScorchedFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }


    @Override
    public void render(ScorchedFroglin pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.7f,0.7f,0.7f);
        }
        
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(ScorchedFroglin scorchedFroglin) {
        return textura;
    }
}

package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Froglins;

public class FroglinRender  extends MobRenderer<AbstractFroglin, FroglinModel<AbstractFroglin>> {



    private static final ResourceLocation tempered =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/froglin/froglin.png");
    private static final ResourceLocation warm =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/froglin/cold_froglin.png");
    private static final ResourceLocation cold =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/froglin/warm_froglin.png");

    public FroglinRender(EntityRendererProvider.Context context, FroglinModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }


    @Override
    public void render(AbstractFroglin pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.6f, 0.6f, 0.6f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }
        super.render( pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }

    @Override
    public ResourceLocation getTextureLocation(AbstractFroglin abstractFroglin) {
        if(abstractFroglin instanceof Froglin froglin){
            switch (froglin.getVariante()){
                case 0:
                    return tempered;
                case 1:
                    return cold;
                case 2:
                    return warm;
            }
        }

     return tempered;
    }


}

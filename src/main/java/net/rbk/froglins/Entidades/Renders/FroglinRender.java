package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Froglins;

import java.util.List;

public class FroglinRender  extends MobRenderer<AbstractFroglin, FroglinModel<AbstractFroglin>> {





    public FroglinRender(EntityRendererProvider.Context context, FroglinModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }


    @Override
    public void render(AbstractFroglin pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pPoseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(AbstractFroglin froglin) {
        int variante = froglin.getVariante();
        List<ResourceLocation> texturas = froglin.getVariantTextures();

        if (variante >= 0 && variante < texturas.size()) {
            return texturas.get(variante);
        }

        return texturas.get(0);
    }


}

package net.rbk.froglins.Entidades.Renders;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Froglins;

public class ZombieFroglinEyesRender <T extends AbstractFroglin> extends EyesLayer<T,FroglinModel<T>>  {

    private static final ResourceLocation EYE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/zombie_froglin/zombie_froglineyes.png");
    private static final RenderType EYE_RENDER_TYPE = RenderType.eyes(EYE_TEXTURE);

    public ZombieFroglinEyesRender(RenderLayerParent<T, FroglinModel<T>> renderer) {
        super(renderer);
    }


    @Override
    public RenderType renderType() {
        return EYE_RENDER_TYPE;
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {



        if (!shouldRenderEyes(livingEntity)) return;

        super.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);

    }


    public boolean shouldRenderEyes(AbstractFroglin froglin) {
        if (froglin.level().dimensionType().hasFixedTime()) return true; // Nether o End
        long time = froglin.level().getDayTime() % 24000L;
        return time >= 13000 && time <= 23000; // De noche
    }

}

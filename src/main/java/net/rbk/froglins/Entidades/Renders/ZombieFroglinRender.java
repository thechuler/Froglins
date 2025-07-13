package net.rbk.froglins.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.froglins.Entidades.Entity.AbstractFroglin;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Entidades.Entity.ZombieFroglin;
import net.rbk.froglins.Entidades.Modelos.FroglinModel;
import net.rbk.froglins.Froglins;
import org.jetbrains.annotations.NotNull;

public class ZombieFroglinRender extends MobRenderer<AbstractFroglin, FroglinModel<AbstractFroglin>> {



    private static final ResourceLocation tempered =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/zombie_froglin/zombie_froglin.png");
    private static final ResourceLocation warm =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/zombie_froglin/cold_zombie_froglin.png");
    private static final ResourceLocation cold =  ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "textures/entity/zombie_froglin/warm_zombie_froglin.png");

    public ZombieFroglinRender(EntityRendererProvider.Context context, FroglinModel<AbstractFroglin> model, float shadowRadius) {
        super(context, model, shadowRadius);
        this.addLayer(new ZombieFroglinEyesRender(this));
    }






    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractFroglin abstractFroglin) {
        if(abstractFroglin instanceof ZombieFroglin froglin){
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

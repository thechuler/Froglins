package net.rbk.froglins.Entidades.Modelos;

import net.minecraft.client.model.geom.ModelPart;

import net.rbk.froglins.Entidades.Animaciones.TropicalFroglinAnimaciones;
import net.rbk.froglins.Entidades.Entity.TropicalFroglin;

public class TropicalFroglinModel<T extends TropicalFroglin> extends FroglinModel<T> {
    public TropicalFroglinModel(ModelPart root) {
        super(root);
    }


    @Override
    public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
        super.setupAnim(t, v, v1, v2, v3, v4);
        this.animate(t.treparAnimationState, TropicalFroglinAnimaciones.TREPAR, v3, 1f);
    }
}

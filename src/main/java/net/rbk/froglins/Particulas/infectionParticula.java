package net.rbk.froglins.Particulas;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;

public class infectionParticula extends TextureSheetParticle {
    protected infectionParticula(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
    }



    @Override
    public ParticleRenderType getRenderType() {
        return null;
    }
}

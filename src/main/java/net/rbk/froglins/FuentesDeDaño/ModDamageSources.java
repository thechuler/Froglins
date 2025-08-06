package net.rbk.froglins.FuentesDeDaño;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.rbk.froglins.Froglins;

public class ModDamageSources {
    public static final ResourceKey<DamageType> WOOlLY_FROGLIN_CRUSH =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Froglins.MODID, "woolly_froglin_crush"));

    public static DamageSource woollyFroglinCrush(ServerLevel level, Entity source) {
        Holder<DamageType> type = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(WOOlLY_FROGLIN_CRUSH);
        return new DamageSource(type, source);
    }



}

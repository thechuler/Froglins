package net.rbk.froglins.Efectos;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Sonidos.ModSounds;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFECTOS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Froglins.MODID);


    public static final Holder<MobEffect> INFECTION = EFECTOS.register("infection",
            ()-> new Infeccion(MobEffectCategory.HARMFUL,12).withSoundOnAdded(ModSounds.ZOMBIE_FROGLIN_AMBIENT.get())
    );


    public static void registrar(IEventBus eventBus) {
        EFECTOS.register(eventBus);
    }
}

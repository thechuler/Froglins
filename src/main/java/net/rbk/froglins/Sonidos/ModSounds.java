package net.rbk.froglins.Sonidos;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.animal.frog.Frog;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Entidades.Entity.Froglin;
import net.rbk.froglins.Froglins;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SONIDOS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Froglins.MODID);





    public static final Supplier<SoundEvent> FROGLIN_AMBIENT = registerSoundEvent("froglin_ambient");
    public static final Supplier<SoundEvent> FROGLIN_HURT = registerSoundEvent("froglin_hurt");
    public static final Supplier<SoundEvent> FROGLIN_DEATH = registerSoundEvent("froglin_death");







    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Froglins.MODID, name);
        return SONIDOS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
    public static void register(IEventBus eventBus) {
        SONIDOS.register(eventBus);
    }
}

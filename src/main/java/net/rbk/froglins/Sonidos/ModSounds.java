package net.rbk.froglins.Sonidos;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongs;
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

    public static final Supplier<SoundEvent> ZOMBIE_FROGLIN_AMBIENT = registerSoundEvent("zombie_froglin_ambient");
    public static final Supplier<SoundEvent> ZOMBIE_FROGLIN_HURT = registerSoundEvent("zombie_froglin_hurt");
    public static final Supplier<SoundEvent> ZOMBIE_FROGLIN_DEATH = registerSoundEvent("zombie_froglin_death");




    public static final Supplier<SoundEvent> YSYA = registerSoundEvent("ysya");
    public static final ResourceKey<JukeboxSong> YSYA_KEY = crearCancion("ysya");






    public static ResourceKey<JukeboxSong> crearCancion(String nombre){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Froglins.MODID,nombre));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Froglins.MODID, name);
        return SONIDOS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
    public static void register(IEventBus eventBus) {
        SONIDOS.register(eventBus);
    }
}

package net.rbk.froglins.DataComponent;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Froglins;

import java.util.function.Supplier;

public class ModDataComponent {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Froglins.MODID);

    public static final Supplier<DataComponentType<MobEffectInstance>> HARMFUL_VISCOSITY_EFFECT =
            DATA_COMPONENTS.register("harmful_viscosity_effect", () ->
                    DataComponentType.<MobEffectInstance>builder()
                            .persistent(MobEffectInstance.CODEC) // Se guarda en NBT
                            .networkSynchronized(MobEffectInstance.STREAM_CODEC) // Sincroniza en red
                            .build()
            );




    public static void registrar(IEventBus bus) {
        DATA_COMPONENTS.register(bus);
    }

}

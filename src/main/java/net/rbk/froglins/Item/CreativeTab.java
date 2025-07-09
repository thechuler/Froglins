package net.rbk.froglins.Item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.froglins.Froglins;

import java.util.function.Supplier;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Froglins.MODID);





    //Toma los registros de la clase ModItems  y los agrega automaticamente a la pestaña principal del mod
    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("main_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.DIAMOND))
                    .title(Component.translatable("creativetab.main_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        for (int i = 0; i < ModItems.ITEMS.getEntries().size(); i++) {
                            pOutput.accept(ModItems.ITEMS.getEntries().stream().toList().get(i).get().asItem());
                        }
                    })
                    .build());


    public static void registrar(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }

}

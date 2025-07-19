package net.rbk.froglins.Eventos;

import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.rbk.froglins.DataComponent.ModDataComponent;
import net.rbk.froglins.Froglins;
import net.rbk.froglins.Item.ModItems;

@EventBusSubscriber(modid = Froglins.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class EventosClient {

    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) {
                MobEffectInstance effect = stack.get(ModDataComponent.HARMFUL_VISCOSITY_EFFECT.get());
                if (effect != null) {
                    int color = effect.getEffect().value().getColor();
                    int fullColor = 0xFF000000 | color; // fuerza alpha a 255
                    return fullColor;
                }
                return getInterpolatedRainbowColor(); // rojo opaco como fallback
            }
            return 0xFFFFFFFF; // blanco opaco para otras capas
        }, ModItems.HARMFUL_VISCOSITY.get());
    }




    private static int getInterpolatedRainbowColor() {
        long tick = System.currentTimeMillis() / 50;
        int[] colors = {
                0xFFFF0000, // rojo
                0xFFFF7F00, // naranja
                0xFFFFFF00, // amarillo
                0xFF00FF00, // verde
                0xFF0000FF, // azul
                0xFF4B0082, // índigo
                0xFF8B00FF  // violeta
        };
        int index = (int) (tick / 15 % colors.length);
        int nextIndex = (index + 1) % colors.length;
        float blend = (tick % 15) / 15f;
        return lerpColor(colors[index], colors[nextIndex], blend);
    }



    private static int lerpColor(int colorA, int colorB, float t) {
        int aA = (colorA >> 24) & 0xFF;
        int rA = (colorA >> 16) & 0xFF;
        int gA = (colorA >> 8) & 0xFF;
        int bA = colorA & 0xFF;

        int aB = (colorB >> 24) & 0xFF;
        int rB = (colorB >> 16) & 0xFF;
        int gB = (colorB >> 8) & 0xFF;
        int bB = colorB & 0xFF;
        int a = (int) (aA + (aB - aA) * t);
        int r = (int) (rA + (rB - rA) * t);
        int g = (int) (gA + (gB - gA) * t);
        int b = (int) (bA + (bB - bA) * t);
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

}

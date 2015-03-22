package info.ata4.minecraft.dragon.client;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import info.ata4.minecraft.dragon.DragonMounts;
import info.ata4.minecraft.dragon.client.gui.GuiDragonDebug;
import info.ata4.minecraft.dragon.client.render.DragonRenderer;
import info.ata4.minecraft.dragon.server.ServerProxy;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ClientProxy extends ServerProxy {
    
    @Override
    public void onInit(FMLInitializationEvent evt) {
        super.onInit(evt);
        
        // register tick handlers
        TickRegistry.registerTickHandler(new DragonTickHandler(), Side.CLIENT);
        
        // register entity renderer
        RenderingRegistry.registerEntityRenderingHandler(EntityTameableDragon.class, new DragonRenderer());
        
        // register keybindings
        KeyBindingRegistry.registerKeyBinding(new DragonKeyHandler());
        
        // register sounds
        MinecraftForge.EVENT_BUS.register(this);
        
        // register GUI
        if (DragonMounts.instance.getConfig().isDebug()) {
            MinecraftForge.EVENT_BUS.register(new GuiDragonDebug());
        }
    }
    
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event) {
        SoundManager manager = event.manager;
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/step1.ogg");
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/step2.ogg");
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/step3.ogg");
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/step4.ogg");
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/breathe1.ogg");
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/breathe2.ogg");
        manager.addSound(DragonMounts.AID + ":mob/enderdragon/death.ogg");
    }
}

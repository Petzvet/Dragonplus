package info.ata4.minecraft.dragon;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import info.ata4.minecraft.dragon.server.ServerProxy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main control class for Forge.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
@Mod(
    modid = DragonMounts.ID,
    name = DragonMounts.NAME,
    version = DragonMounts.VERSION,
    useMetadata = true
)
@NetworkMod
(
    clientSideRequired = true,
    serverSideRequired = false
)
public class DragonMounts {
    
    @SidedProxy(
        serverSide = "info.ata4.minecraft.dragon.server.ServerProxy",
        clientSide = "info.ata4.minecraft.dragon.client.ClientProxy"
    )
    public static ServerProxy proxy;
    
    public final static String ID = "DragonMounts";
    public final static String AID = ID.toLowerCase();
    public final static String NAME = "Dragon Mounts";
    public final static String VERSION = "r32";
    
    @Instance(ID)
    public static DragonMounts instance;
    
    public static final Logger L = Logger.getLogger("DragonMounts");
    
    private DragonMountsConfig config;
    
    @EventHandler
    public void onPreInit(FMLPreInitializationEvent evt) {
        // load config
        config = new DragonMountsConfig(evt.getSuggestedConfigurationFile());
        
        // configure logger
        L.setParent(FMLLog.getLogger());
        L.setLevel(config.isDebug() ? Level.ALL : Level.INFO);
    }

    @EventHandler
    public void onInit(FMLInitializationEvent evt) {
        proxy.onInit(evt);
}
    
    @EventHandler
    public void onServerStarted(FMLServerStartedEvent evt) {
        proxy.onServerStarted(evt);
    }
    
    @EventHandler
    public void onServerStopped(FMLServerStoppedEvent evt) {
        proxy.onServerStopped(evt);
    }
    
    public DragonMountsConfig getConfig() {
        return config;
    }
}

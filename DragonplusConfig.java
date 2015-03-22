package info.ata4.minecraft.dragon;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonMountsConfig {
    
    private static final Logger L = DragonMounts.L;
    
    private File configFile;
    private Configuration config;
    
    // non-string defaults so I don't have to hardcode them twice...
    private static final boolean DEF_EGGS_IN_CHESTS = false;
    private static final boolean DEF_DEBUG = false;
    
    // config properties
    private Property eggsInChests;
    private Property debug;
    
    public DragonMountsConfig(File configFile) {
        this.configFile = configFile;
        load();
        save();
    }
    
    public void load() {
        try {
            if (config == null) {
                config = new Configuration(configFile);
            } else {
                config.load();
            }
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't load config file" , ex);
        }
        
        try {
            eggsInChests = config.get("server", "eggsInChests", DEF_EGGS_IN_CHESTS, "Spawns dragon eggs in generated chests when enabled");
            debug = config.get("client", "debug", DEF_EGGS_IN_CHESTS, "Debug mode. Unless you're a developer or are told to activate it, you don't want to set this to true.");
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't load config file", ex);
        }
    }
    
    public void save() {
        try {
            if (config.hasChanged()) {
                config.save();
            }
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't save config file", ex);
        }
    }
    
    public boolean isEggsInChests() {
        return eggsInChests.getBoolean(DEF_EGGS_IN_CHESTS);
    }
    
    public void setEggsInChests(boolean enabled) {
        eggsInChests.set(enabled);
    }
    
    public boolean isDebug() {
        return debug.getBoolean(DEF_DEBUG);
    }
    
    public void setDebug(boolean enabled) {
        debug.set(enabled);
    }
}

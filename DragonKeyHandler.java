package info.ata4.minecraft.dragon.client;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import info.ata4.minecraft.dragon.server.network.RemoteKey;
import info.ata4.minecraft.dragon.server.network.RemoteKeyPacketHandler;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.packet.Packet250CustomPayload;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonKeyHandler extends KeyHandler {
    
    public static final KeyBinding KEY_FLY_UP = new KeyBinding("key.dragon.flyUp", Keyboard.KEY_R);
    public static final KeyBinding KEY_FLY_DOWN = new KeyBinding("key.dragon.flyDown", Keyboard.KEY_F);
    
    private final Minecraft mc = Minecraft.getMinecraft();

    public DragonKeyHandler() {
        super(new KeyBinding[] {KEY_FLY_UP, KEY_FLY_DOWN}, new boolean[] {false, false});
    }
    
    private void sendPacket(KeyBinding kb) {
        // don't send packets when not riding dragons
        if (!(mc.thePlayer.ridingEntity instanceof EntityTameableDragon)) {
            return;
        }
        
        // don't send packets if the GUI is active
        if (mc.currentScreen != null) {
            return;
        }
        
        RemoteKey rk = new RemoteKey(kb);
        Packet250CustomPayload packet = new Packet250CustomPayload(RemoteKeyPacketHandler.CHANNEL, rk.write());
        PacketDispatcher.sendPacketToServer(packet);
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        if (tickEnd) {
            sendPacket(kb);
        }
    }

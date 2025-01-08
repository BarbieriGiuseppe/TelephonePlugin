package restar.dev.telephonePlugin.listeners;

import org.bukkit.event.Listener;
import restar.dev.telephonePlugin.utils.PlayerFile;

public class PlayerListeners implements Listener {

    private PlayerFile playerFile;

    public PlayerListeners(PlayerFile playerFile) {

        this.playerFile = playerFile;
    }
}

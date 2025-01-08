package restar.dev.telephonePlugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import restar.dev.telephonePlugin.utils.PlayerFile;

public class PlayerListeners implements Listener {

    private PlayerFile playerFile;

    public PlayerListeners(PlayerFile playerFile) {

        this.playerFile = playerFile;
    }
    
    public void setPlayerFile(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        String playerName = player.getName();

        playerFile.setName(playerName);
        playerFile.setUuid(uuid);

        playerFile.createPlayerFile(uuid,playerName);


    }
}

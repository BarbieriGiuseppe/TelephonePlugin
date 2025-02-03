package restar.dev.telephonePlugin.listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import restar.dev.telephonePlugin.utils.PlayerInfo;
import restar.dev.telephonePlugin.utils.YamlFileUtils;

import java.util.HashMap;
import java.util.UUID;

public class PlayerListeners implements Listener {

    private OfflinePlayer player;
    YamlFileUtils playerFile = new YamlFileUtils();

    /*With this listener when the users join for the first time it creates a file associated to him*/
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            player = event.getPlayer();
            playerFile.createFile(player);
            playerFile.get().createSection("UUID");
            playerFile.get().set("UUID", player.getUniqueId().toString());
            playerFile.get().createSection("Name");
            playerFile.get().set("Nome", player.getName());
            playerFile.get().createSection("Pin");
            playerFile.get().set("PhoneNumber", "");

            playerFile.saveFile();

        }
    }
}

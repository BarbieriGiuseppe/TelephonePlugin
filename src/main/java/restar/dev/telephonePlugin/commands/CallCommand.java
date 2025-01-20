package restar.dev.telephonePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restar.dev.telephonePlugin.utils.PlayerFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CallCommand implements CommandExecutor {

    private final Map<UUID, UUID> callRequests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 1) {
                player.sendMessage("Correct usage: /call <player>");
                return false;
            }

            if (!player.hasPermission("telephoneplugin.call")) {
                player.sendMessage("You have no permission to call other players");
                return false;
            }

            PlayerFile playerFile = new PlayerFile();
            playerFile.setPlayerFile(player);

            if (playerFile.getPhoneNumber() == 0) {
                player.sendMessage("You do not have a phone number.");
                return false;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage("Player not found.");
                return false;
            }

            if(targetPlayer.getName().equals(player.getName())){
                player.sendMessage("You cannot call yourself.");
                return false;
            }

            if (!targetPlayer.isOnline()) {
                player.sendMessage("Player " + args[0] + " is offline.");
                return false;
            }

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            targetPlayer.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

            callRequests.put(targetPlayer.getUniqueId(), player.getUniqueId());
            player.sendMessage("You are calling " + targetPlayer.getName() + ".");
            targetPlayer.sendMessage(player.getName() + " is calling you! use /callaccept to accept or /calldeny to refuse.");

        }
        return false;
    }

    public UUID getCaller(UUID targetPlayerId) {
        return callRequests.get(targetPlayerId);
    }

    public void removeCallRequest(UUID targetPlayerId) {
        callRequests.remove(targetPlayerId);
    }
}
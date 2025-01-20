package restar.dev.telephonePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CallResponseCommand implements CommandExecutor {

    private final CallCommand callCommand;
    private final boolean accept;

    public CallResponseCommand(CallCommand callCommand, boolean accept) {
        this.callCommand = callCommand;
        this.accept = accept;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player targetPlayer) {
            UUID callerId = callCommand.getCaller(targetPlayer.getUniqueId());
            if (callerId == null) {
                targetPlayer.sendMessage("No calls incoming");
                return false;
            }

            Player caller = Bukkit.getPlayer(callerId);
            if (caller == null) {
                targetPlayer.sendMessage("Caller is offline");
                callCommand.removeCallRequest(targetPlayer.getUniqueId());
                return false;
            }

            if (accept) {
                targetPlayer.sendMessage("You accepted " + caller.getName() + "call.");
                caller.sendMessage(targetPlayer.getName() + "Accepted your call");
            } else {
                targetPlayer.sendMessage("You refused " + caller.getName() + "call.");
                caller.sendMessage(targetPlayer.getName() + "Refused your call");
            }

            callCommand.removeCallRequest(targetPlayer.getUniqueId());
            return true;
        }
        return false;
    }
}
package restar.dev.telephonePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restar.dev.telephonePlugin.utils.PlayerInfo;
import restar.dev.telephonePlugin.utils.YamlFileUtils;

import java.util.Objects;

public class ChangeNumberCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayerInfo playerInfo = new PlayerInfo();
        YamlFileUtils yamlFileUtils = new YamlFileUtils();

        if (sender instanceof Player player) {
            Player targetPlayer = player;

            if (args.length == 1) {
                if (!player.hasPermission("telephoneplugin.changenumber.others")) {
                    player.sendMessage("You have no permission to change other players phone number");
                    return false;
                }

                targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    player.sendMessage("Player not found.");
                    return false;
                }

            }

            yamlFileUtils.loadFile(player);
            String phoneNumber = playerInfo.generatePhoneNumber();
            yamlFileUtils.get().set("PhoneNumber", phoneNumber);
            yamlFileUtils.saveFile();

            sender.sendMessage("Phone number changed for: " + targetPlayer.getName());
            targetPlayer.sendMessage("Your phone number has been changed " + phoneNumber);

            return true;
        }
        return false;
    }
}
package restar.dev.telephonePlugin.utils;
import org.bukkit.OfflinePlayer;

import java.util.Random;


public class PlayerInfo {

    private OfflinePlayer player;
    private String phoneNumber;
    private final static int PREFIX = 39;
    private final static int MINRND = 10000000;
    private final static int MAXRND = 90000000;

    public PlayerInfo() {
    }


    public OfflinePlayer getPlayer() {
        return player;
    }

    public void setPlayer(OfflinePlayer player) {
        this.player = player;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String generatePhoneNumber(){
            Random random = new Random();
            int number = Integer.parseInt(""+ PREFIX +  MINRND + random.nextInt(MAXRND));

            return String.valueOf(number);
    }
}
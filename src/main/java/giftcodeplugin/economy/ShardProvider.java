package giftcodeplugin.economy;

import org.bukkit.entity.Player;
import chanhne.economy.api.ShardAPI;

public class ShardProvider {

    public double get(Player player) {
        return ShardAPI.getBalance(player);
    }

    public boolean has(Player player, double amount) {
        return ShardAPI.has(player, amount);
    }

    public void add(Player player, double amount) {
        ShardAPI.add(player, amount);
    }

    public boolean take(Player player, double amount) {
        return ShardAPI.take(player, amount);
    }
}
package giftcodeplugin.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultProvider {

    private Economy economy;

    public VaultProvider(JavaPlugin plugin) {
        setupEconomy(plugin);
    }

    private void setupEconomy(JavaPlugin plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) return;
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp != null) economy = rsp.getProvider();
    }

    public boolean isEnabled() {
        return economy != null;
    }

    public Economy getEconomy() {
        return economy;
    }

    public void add(Player player, double amount) {
        if (economy != null && amount > 0) {
            economy.depositPlayer(player, amount);
        }
    }

    public boolean remove(Player player, double amount) {
        return economy != null
                && amount > 0
                && economy.withdrawPlayer(player, amount).transactionSuccess();
    }

    public double getBalance(Player player) {
        return economy == null ? 0 : economy.getBalance(player);
    }

    public boolean has(Player player, double amount) {
        return economy != null && economy.has(player, amount);
    }
}
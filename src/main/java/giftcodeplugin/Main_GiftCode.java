package giftcodeplugin;

import giftcodeplugin.commands.GiftCode_Admin;
import giftcodeplugin.commands.GiftCode_Redeem;
import giftcodeplugin.commands.GiftCode_Completer;
import giftcodeplugin.config.Config_GiftCode;
import giftcodeplugin.core.Messager;
import giftcodeplugin.economy.ShardProvider;
import giftcodeplugin.economy.VaultProvider;
import giftcodeplugin.listener.CreateGiftCodeListener;
import giftcodeplugin.manager.BuilderManager;
import giftcodeplugin.manager.ChatInputManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class Main_GiftCode extends JavaPlugin {

    private static Economy economy;
    private Messager messageManager;

    private VaultProvider vaultProvider;
    private ShardProvider shardProvider;

    @Override
    public void onEnable() {

        // saveDefaultConfig();
        saveResource("messages.yml", false);
        messageManager = new Messager(this);
        vaultProvider = new VaultProvider(this);
        shardProvider = new ShardProvider();

        // Lấy file cấu hình của plugin
        File file = new File(getDataFolder(), "Giftcode.yml");
        if (!file.exists()) saveResource("Giftcode.yml", false);

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Config_GiftCode configGiftCode = new Config_GiftCode(file, config);
        BuilderManager builderManager = new BuilderManager();
        ChatInputManager chatInputManager = new ChatInputManager();

        getServer().getPluginManager().registerEvents(new CreateGiftCodeListener(this, configGiftCode, builderManager, chatInputManager),this);
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS,
            event -> {
                // Đăng ký lệnh
                Objects.requireNonNull(getCommand("gc")).setExecutor(new GiftCode_Redeem(this, configGiftCode, builderManager));
                Objects.requireNonNull(getCommand("code")).setExecutor(new GiftCode_Admin(this, configGiftCode));
                Objects.requireNonNull(getCommand("gc")).setTabCompleter(new GiftCode_Completer(configGiftCode));
            }
        );

        getLogger().info("GiftCode Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GiftCode Plugin disabled!");
    }

    public Messager getMessageManager() {
        return messageManager;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public ShardProvider getShardProvider() {
        return shardProvider;
    }

    public VaultProvider getVaultProvider() {
        return vaultProvider;
    }
}
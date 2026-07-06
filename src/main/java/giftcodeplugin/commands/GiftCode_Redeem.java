package giftcodeplugin.commands;

import giftcodeplugin.config.Config_GiftCode;
import giftcodeplugin.core.GiftCode;
import giftcodeplugin.core.NumberFomat;
import giftcodeplugin.gui.CreateGiftCodeGUI;
import giftcodeplugin.manager.BuilderManager;
import giftcodeplugin.Main_GiftCode;
import giftcodeplugin.builder.GiftCodeBuilder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiftCode_Redeem implements CommandExecutor {

    private final Main_GiftCode plugin;
    private final Config_GiftCode configGiftCode;
    private final BuilderManager builderManager;

    public GiftCode_Redeem(Main_GiftCode plugin, Config_GiftCode configGiftCode, BuilderManager builderManager) {
        this.plugin = plugin;
        this.configGiftCode = configGiftCode;
        this.builderManager = builderManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Kiểm tra quyền
        if (!sender.hasPermission("giftcode.admin")) {
            plugin.getMessageManager().send(sender, "no_permission");
            return true;
        }

        if (args.length == 0) {
            plugin.getMessageManager().send(sender, "plugin_user");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                handleCreate(sender, args);
                break;

            case "delete":
                if (args.length < 2) {
                    plugin.getMessageManager().send(sender, "delete_user");
                    return true;
                }
                configGiftCode.deleteGiftCode(args[1]);
                plugin.getMessageManager().send(sender, "giftcode.deleted", "code", args[1]);

                break;

            case "reload":
                configGiftCode.reload();
                plugin.getMessageManager().reload();
                plugin.getMessageManager().send(sender, "reload_plugin");
                break;

            case "enable":
                if (args.length < 2) {
                    plugin.getMessageManager().send(sender, "enable_user");
                    return true;
                }
                configGiftCode.enableGiftCode(args[1]);
                plugin.getMessageManager().send(sender,"giftcode.enableGiftCode","code", args[1]);
                break;

            case "disable":
                if (args.length < 2) {
                    plugin.getMessageManager().send(sender, "disable_user");
                    return true;
                }
                configGiftCode.disableGiftCode(args[1]);
                plugin.getMessageManager().send(sender,"giftcode.disableGiftCode","code", args[1]);
                break;

            case "list":
                plugin.getMessageManager().send(sender, "list_giftcode");
                for (String code : configGiftCode.getGiftCodes().keySet()) {
                    sender.sendMessage("- " + code);
                }
                break;

            case "permission":
                if (args.length < 3) {
                    plugin.getMessageManager().send(sender, "permission.usage");
                    return true;
                }

                GiftCode giftCode = configGiftCode.getGiftCode(args[1]);

                if (giftCode == null) {
                    plugin.getMessageManager().send(sender, "giftcode.not_found");
                    return true;
                }

                switch (args[2].toLowerCase()) {
                    case "add":
                        if (args.length < 4) {
                            plugin.getMessageManager().send(sender, "usage_permission.add");
                            return true;
                        }
                        configGiftCode.setPermission(args[1], args[3]);
                        plugin.getMessageManager().send(sender,"permission.add","code", args[1],"permission", args[3]);
                        break;

                    case "delete":
                        configGiftCode.setPermission(args[1], "");
                        plugin.getMessageManager().send(sender, "permission.delete","code", args[1]);
                        break;

                    case "list":
                        plugin.getMessageManager().send(sender,"permission.list","code", args[1],"permission",
                                giftCode.getPermission().isBlank() ? "-" : giftCode.getPermission());

                        break;
                    default:
                        plugin.getMessageManager().send(sender, "permission.usage");
                        break;
                }

                return true;

            default:
                plugin.getMessageManager().send(sender, "missing_argument");
                break;
        }

        return true;
    }

    private void handleCreate(CommandSender sender, String[] args) {

        if (args.length < 2) {
            plugin.getMessageManager().send(sender, "create_user");
            return;
        }

        if (!(sender instanceof Player)) {
            plugin.getMessageManager().send(sender, "player_only");
            return;
        }
        Player player = (Player) sender;

        // /gc create random <num>
        // if (args.length >= 3 && args[1].equalsIgnoreCase("random")) {
        //     int num = 1;
        //     try {
        //         num = Integer.parseInt(args[2]);
        //     } catch (NumberFormatException ignored) {}// Tạo giftcode random, mỗi code dùng 1 lần
        //         List<String> codes = configGiftCode.createRandomCodes(num);

        //         // Gửi tin nhắn
        //         plugin.getMessageManager().send(sender, "giftcode.random_created", "num", String.valueOf(num));
        //     for (String c : codes) {
        //         sender.sendMessage(
        //             Component.text("▶ " + c, NamedTextColor.GOLD)
        //                     .clickEvent(ClickEvent.copyToClipboard(c))
        //                     .hoverEvent(HoverEvent.showText(Component.text("Click để sao chép", NamedTextColor.YELLOW)))
        //         );
        //     }
        //     return;
        // }
        boolean random = args[1].equalsIgnoreCase("random");
        String code;
        int uses = 1;
        int amount = 1;
        long expireAt = 0;

        if (random) {
            code = configGiftCode.generateRandomCode();
            if (args.length >= 3) uses = Integer.parseInt(args[2]);
            if (args.length >= 4) expireAt = NumberFomat.parseDuration(args[3]);
        } else {
            code = args[1];
            if (configGiftCode.exists(code)) {
                player.sendMessage(plugin.getMessageManager().get(player,
                        "giftcode.already_exists",
                        "code", code));
                return;
            }

            if (args.length >= 3) uses = Integer.parseInt(args[2]);
            if (args.length >= 4) expireAt = NumberFomat.parseDuration(args[3]);
        }

        if (args.length >= 3) {
            try {amount = Integer.parseInt(args[2]);}
            catch (NumberFormatException e) {
                plugin.getMessageManager().send(player, "invalid_number");
                return;
            }
        }

        GiftCodeBuilder builder = new GiftCodeBuilder(code, uses, expireAt);
        builder.setRandomCode(random);
        builder.setRandomAmount(amount);

        builderManager.create(player.getUniqueId(), builder);
        new CreateGiftCodeGUI(plugin).open(player, builder);
    }
}
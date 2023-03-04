package net.risenteam.combatlog.commands;

import net.risenteam.combatlog.Core;
import net.risenteam.risencore.commands.RisenCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandCombatLog extends RisenCommand {

    private final Core core;
    public CommandCombatLog(Core core, String string) {
        super(string);
        this.core = core;
        this.setPermission("combatlog.command.combatlog");
        this.setPermissionMessage(ChatColor.RED + "You don't have permission to use this command!");
    }

    @Override
    public void onCommand(CommandSender sender, String label, String[] args) {
        if(args.length == 0){
            sendHelp(sender);
            return;
        }

        if(args[0].equalsIgnoreCase("reload")){
            this.core.reloadConfig();
            sender.sendMessage(this.core.getLanguageManager().getMessage("prefix") + " " + this.core.getLanguageManager().getMessage("configReload"));
            return;
        }


        sendHelp(sender);
    }

    public void sendHelp(CommandSender sender){
        sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "    Help");
        sender.sendMessage(ChatColor.WHITE + "    /" + ChatColor.RED + this.getName() + " reload");
    }

}

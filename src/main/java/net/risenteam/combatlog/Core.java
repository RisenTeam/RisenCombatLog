package net.risenteam.combatlog;

import net.risenteam.combatlog.commands.CommandCombatLog;
import net.risenteam.combatlog.lang.LanguageManager;
import net.risenteam.risencore.RisenPlugin;
import net.risenteam.risencore.commands.CommandManager;
import net.risenteam.risencore.utils.Logger;
import net.risenteam.risencore.version.VersionChecker;

public class Core extends RisenPlugin {

    private final LanguageManager languageManager;
    private final CommandManager commandManager;
    private CombatLogManager combatLogManager;

    public Core(){
        this.saveDefaultConfig();
        this.saveResource("lang/en_US.yml", false);
        this.saveResource("lang/fr_FR.yml", false);
        this.languageManager = new LanguageManager(this);
        this.commandManager = new CommandManager();
    }

    @Override
    public void onEnable() {
        this.combatLogManager = new CombatLogManager(this);
        Logger.success("CombatLog has been enabled!");

        new VersionChecker(this, 108352).logVersion();
        this.commandManager.registerCommand(new CommandCombatLog(this, this.getConfig().getString("commandName", "combatlog")));
    }

    @Override
    public void onDisable() {
        Logger.success("CombatLog has been disabled!");
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public CombatLogManager getCombatLogManager() {
        return combatLogManager;
    }
}

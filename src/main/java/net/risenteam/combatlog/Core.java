package net.risenteam.combatlog;

import net.risenteam.combatlog.lang.LanguageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private final LanguageManager languageManager;
    private CombatLogManager combatLogManager;

    public Core(){
        this.saveDefaultConfig();
        this.saveResource("lang/en_US.yml", false);
        this.saveResource("lang/fr_FR.yml", false);
        this.languageManager = new LanguageManager(this);
    }

    @Override
    public void onEnable() {
        this.combatLogManager = new CombatLogManager(this);

        getLogger().info("CombatLog has been enabled!"); // TODO: Implement RisenCore and setup the core's logger
    }

    @Override
    public void onDisable() {
        getLogger().info("CombatLog has been disabled!"); // TODO: Implement RisenCore and setup the core's logger
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public CombatLogManager getCombatLogManager() {
        return combatLogManager;
    }
}

package net.risenteam.combatlog.lang;

import net.risenteam.combatlog.Core;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class LanguageManager {

    private FileConfiguration langConfig;

    public LanguageManager(Core core) {
        String language = core.getConfig().getString("language");
        File langFile = new File(core.getDataFolder(), "lang/" + language + ".yml");

        if(!langFile.exists()) {
            langFile = new File(core.getDataFolder(), "lang/en.yml");
        }

        this.langConfig = YamlConfiguration.loadConfiguration(langFile);
        core.getLogger().info("Successfully loaded lang " + language + "!");
    }

    public String getMessage(String key, Object...objects) {
        String message = this.langConfig.getString(key, "Message not found, please contact an administrator! (Key: " + key + ")");
        for(Object object : objects) {
            if(object instanceof Player) {
                message = message.replaceFirst("%player%", ((Player) object).getName());
            }
        }
        return ChatColor.translateAlternateColorCodes('&',  message);
    }

}

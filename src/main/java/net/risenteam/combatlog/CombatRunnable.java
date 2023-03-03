package net.risenteam.combatlog;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CombatRunnable implements Runnable{

    private final CombatLogManager combatLogManager;

    public CombatRunnable(CombatLogManager combatLogManager) {
        this.combatLogManager = combatLogManager;
    }

    @Override
    public void run() {

        for(UUID uuid : this.combatLogManager.getPlayersInCombat()) {
            long combatTime = this.combatLogManager.getCombatTime(uuid);
            Core core = this.combatLogManager.getCore();
            if(System.currentTimeMillis() - combatTime >= (core.getConfig().getLong("combatTime", 15) * 1000)) {
                this.combatLogManager.removePlayerFromCombat(uuid);
                String message = core.getLanguageManager().getMessage("no-longer-in-combat");
                Player player = Bukkit.getPlayer(uuid);
                if(player != null) player.sendMessage(core.getLanguageManager().getMessage("prefix") + " " + message);
            }
        }
    }

}

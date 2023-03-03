package net.risenteam.combatlog;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatLogManager {

    private final Core core;
    private final Map<UUID, Long> combatMap = new HashMap<>();

    public CombatLogManager(Core core) {
        this.core = core;
        
    }

    public Collection<UUID> getPlayersInCombat() {
        return this.combatMap.keySet();
    }

    public void addPlayerToCombat(Player player) {
        this.combatMap.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public boolean isPlayerInCombat(Player player) {
        return this.combatMap.containsKey(player.getUniqueId());
    }

    public long getCombatTime(Player player) {
        return this.getCombatTime(player.getUniqueId());
    }

    public long getCombatTime(UUID uuid) {
        return this.combatMap.get(uuid);
    }

    public void removePlayerFromCombat(Player player) {
        this.removePlayerFromCombat(player.getUniqueId());
    }

    public void removePlayerFromCombat(UUID uuid) {
        this.combatMap.remove(uuid);
    }

    public void clearCombatMap() {
        this.combatMap.clear();
    }

    public Core getCore() {
        return core;
    }
}

package net.risenteam.combatlog.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final Map<UUID, Long> combatMap = new HashMap<>();

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();
        if(entity instanceof Player) {
            Player player = (Player) entity;
            this.combatMap.put(player.getUniqueId(), System.currentTimeMillis());
        }
    }
}

package net.risenteam.combatlog.listeners;

import net.risenteam.combatlog.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    private final Core core;

    public PlayerListener(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getDamager();
        if(entity instanceof Player) {
            Player player = (Player) entity;
            this.core.getCombatLogManager().addPlayerToCombat(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(this.core.getCombatLogManager().isPlayerInCombat(player)) {

            if (this.core.getConfig().getBoolean("dropInventory", true)) {
                for (ItemStack itemStack : player.getInventory()) {
                    if (itemStack != null && itemStack.getType() != Material.AIR) {
                        player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                    }
                }

                for (ItemStack itemStack : player.getInventory().getArmorContents()) {
                    if (itemStack != null && itemStack.getType() != Material.AIR) {
                        player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                    }
                }

                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
            }

            if(this.core.getConfig().getBoolean("dropExperience", true)) {
                player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(player.getTotalExperience());
                player.setLevel(0);
                player.setExp(0);
            }

            for (String deathCommand : this.core.getConfig().getStringList("deathCommand")) {
                if(deathCommand != null) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), deathCommand.replace("%player%", player.getName()));
                }
            }

            this.core.getCombatLogManager().removePlayerFromCombat(player);
        }
    }
}

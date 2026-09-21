package com.wardensmp;

import org.bukkit.Sound;
import org.bukkit.Particle;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import java.util.HashMap;
import java.util.UUID;

public class WardenShardListener implements Listener {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Warden Shard")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                
                long currentTime = System.currentTimeMillis();
                
                if (cooldowns.containsKey(player.getUniqueId()) && cooldowns.get(player.getUniqueId()) > currentTime) {
                    long timeLeft = (cooldowns.get(player.getUniqueId()) - currentTime) / 1000;
                    player.sendMessage(ChatColor.RED + "Warden Shriek is on cooldown for " + timeLeft + " seconds!");
                    return;
                }

                cooldowns.put(player.getUniqueId(), currentTime + (12 * 1000));

                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 1.0f);
                player.getWorld().spawnParticle(Particle.SONIC_BOOM, player.getEyeLocation().add(player.getLocation().getDirection().multiply(2)), 1);

                Vector direction = player.getEyeLocation().getDirection().normalize();
                for (int i = 1; i  0) {
                                knockbackDir.normalize();
                            } else {
                                knockbackDir = direction.clone().setY(0).normalize();
                            }

                            if (target instanceof Player) {
                                Player targetPlayer = (Player) target;
                                boolean isUnarmored = true;

                                for (ItemStack armorPiece : targetPlayer.getInventory().getArmorContents()) {
                                    if (armorPiece != null && armorPiece.getType() != Material.AIR) {
                                        isUnarmored = false;
                                        break;
                                    }
                                }

                                if (isUnarmored) {
                                    Vector launchVector = knockbackDir.multiply(1.8).setY(0.35);
                                    target.setVelocity(launchVector);
                                } else {
                                    Vector standardLaunch = knockbackDir.multiply(0.8).setY(0.25);
                                    target.setVelocity(standardLaunch);
                                }
                            } else {
                                target.setVelocity(knockbackDir.multiply(1.0).setY(0.3));
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}

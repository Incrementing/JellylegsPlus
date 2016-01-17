package main.incrementing.pw;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/*
 *  Powered by SpigotMC (https://spigotmc.org/).
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class EntityDamageEventHandler implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            if (new JellylegsFile(p.getUniqueId() + ".yml").getConf().getBoolean("isenabled")) {
                e.setCancelled(true);
                if (Jellylegs.getInstance().getConfig().getBoolean("settings.falldmg_message")) {
                    if (e.getDamage() > ((Player) e.getEntity()).getHealth()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.falldmg").replaceAll("#hp#", String.valueOf(p.getHealth() / 2))));
                        return;
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.falldmg").replaceAll("#hp#", String.valueOf(e.getDamage() / 2))));
                }
            }
        }
    }
}

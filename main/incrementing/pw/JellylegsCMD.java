package main.incrementing.pw;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
public class JellylegsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("jellylegs")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                JellylegsFile file = new JellylegsFile(p.getUniqueId() + ".yml");

                if (args.length == 0) {
                    if (p.hasPermission("jellylegs.toggle")) {
                        if (file.getConf().getBoolean("isenabled")) {
                            file.getConf().set("isenabled", false);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.toggled_off")));
                            file.saveConf();
                        }
                        else {
                            file.getConf().set("isenabled", true);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.toggled_on")));
                            file.saveConf();
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.no_permission_toggle")));
                    }
                }
                else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (p.hasPermission("jellylegs.*")) {
                            p.sendMessage("§5§l[§dJellyLegs§5§l+] §dConfig.yml has been reloaded! :)");
                            Jellylegs.getInstance().reloadConfig();
                            return true;
                        }
                    }

                    if (p.hasPermission("jellylegs.toggle.others")) {
                        String name = args[0];

                        if (Bukkit.getPlayer(name) == null) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.player_offline").replaceAll("#player#", name)));
                        }
                        else {
                            Player target = Bukkit.getPlayer(name);
                            JellylegsFile tFile = new JellylegsFile(target.getUniqueId() + ".yml");
                            if (tFile.getConf().getBoolean("isenabled")) {
                                tFile.getConf().set("isenabled", false);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.toggled_off_for_other").replaceAll("#player#", target.getName())));
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.toggled_off_by_other").replaceAll("#player#", p.getName())));
                                tFile.saveConf();
                            }
                            else {
                                tFile.getConf().set("isenabled", true);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.toggled_on_for_other").replaceAll("#player#", target.getName())));
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.toggled_on_by_other").replaceAll("#player#", p.getName())));
                                tFile.saveConf();
                            }
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.no_permission_toggle_others")));
                    }
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellylegs.getInstance().getConfig().getString("messages.syntax")));
                }
            }
            else {
                sender.sendMessage("[Jellylegs+] This command is for players only.");
            }
        }
        return true;
    }
}

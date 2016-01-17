package main.incrementing.pw;

import org.bukkit.plugin.java.JavaPlugin;

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
public class Jellylegs extends JavaPlugin {

    private static Jellylegs instance;

    public static Jellylegs getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getCommand("jellylegs").setExecutor(new JellylegsCMD());
        this.getServer().getPluginManager().registerEvents(new PlayerJoinEventHandler(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitEventHandler(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageEventHandler(), this);
        if (!getConfig().contains("settings") || !getConfig().contains("messages")) {
            getConfig().options().header(
                    "JellyLegs+ - Developed by @Incrementing.\nPlease use '&' for formatting codes.\nShould you have any problems with the plugin please contact me on SpigotMC."
            );
            getConfig().set("settings.disable_on_leave", false);
            getConfig().set("settings.falldmg_message", true);

            getConfig().set("messages.syntax", "&4&l* &cThat's not the correct syntax. Usage: /jellylegs [name]");
            getConfig().set("messages.player_offline", "&4&l* &c#player# isn't online!");
            getConfig().set("messages.no_permission_toggle", "&4&l* &cYou don't have permission to use jellylegs.");
            getConfig().set("messages.no_permission_toggle_others", "&4&l* &cYou don't have permission to toggle others Jellylegs.");
            getConfig().set("messages.toggled_on", "&2&l* &aJellylegs has been enabled!");
            getConfig().set("messages.toggled_off", "&4&l* &cJellylegs has been disabled!");
            getConfig().set("messages.toggled_on_for_other", "&2&l* &aJellylegs has been enabled for #player#!");
            getConfig().set("messages.toggled_on_by_other", "&2&l* &aJellylegs has been enabled by #player#!");
            getConfig().set("messages.toggled_off_for_other", "&4&l* &cJellylegs has been disabled for #player#!");
            getConfig().set("messages.toggled_off_by_other", "&4&l* &cJellylegs has been disabled by #player#!");
            getConfig().set("messages.falldmg", "&8&l* &7You survived a fall that would of caused #hp#&4&l❤&7 of damage!");

            saveConfig();
        }
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}

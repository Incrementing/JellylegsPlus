package main.incrementing.pw;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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
public class JellylegsFile {
    protected FileConfiguration config = null;
    protected File file = null;
    protected String fileName = null;

    public JellylegsFile(String s) {
        fileName = s;
    }

    public void reloafConf() {
        if (file == null) {
            file = new File(Jellylegs.getInstance().getDataFolder() + File.separator + "Userdata", fileName);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConf() {
        if (config == null) {
            reloafConf();
        }
        return config;
    }

    public void saveConf() {
        if (config == null || file == null) {
            reloafConf();
        }
        try {
            getConf().save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

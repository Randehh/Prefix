package randy.prefix;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class prefixGroupLoader {
	
	static File file = new File("plugins" + File.separator + "Prefix" + File.separator + "config.yml");
	static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	static File permissionsfile = new File("plugins" + File.separator + "PermissionsBukkit" + File.separator + "config.yml");
	static FileConfiguration permissionsconfig = YamlConfiguration.loadConfiguration(permissionsfile);	
	
	public static void loadGroups(){
		if(!permissionsfile.exists()){
			Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("Prefix"));
			System.out.print("[Prefix]: PermissionsBukkit not found, disabling plugin.");
			return;
		}
		Object[] groups = permissionsconfig.getConfigurationSection("users").getKeys(false).toArray();
		int i;
		for(i = 0; i < groups.length; i++){
			String playergroup = permissionsconfig.getList("users."+groups[i]+".groups").get(0).toString();
			prefix.ranks.put(groups[i].toString(), playergroup);
		}
		prefix.formats.put("main", config.getString("main_format"));
		Object[] formats = config.getConfigurationSection("groups").getKeys(false).toArray();
		int e;
		for(e = 0; e < formats.length; e++){
			String prefixx = config.getString("groups."+formats[e]+".prefix");
			String suffix = config.getString("groups."+formats[e]+".suffix");
			prefix.formats.put(formats[e].toString(), prefixx+"///"+suffix);
		}
	}
}

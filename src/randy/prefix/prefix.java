package randy.prefix;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.java.JavaPlugin;

public class prefix extends JavaPlugin{
	
	//Name, Rank
	static HashMap<String, String> ranks = new HashMap<String, String>();
	//Rank, prefix|suffix
	//Note: main format - rank: "mainformat"
	static HashMap<String, String> formats = new HashMap<String, String>();
	
	private final prefixPlayerListener playerListener = new prefixPlayerListener();

	@Override
	public void onDisable() {
		System.out.print("[Prefix] disabled.");
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_CHAT, (Listener) playerListener, Priority.Highest, Bukkit.getServer().getPluginManager().getPlugin("Prefix"));
		prefixGroupLoader.loadGroups();
		System.out.print("[Prefix] succesfully enabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandName, String[] args){
		if(commandName.equalsIgnoreCase("prefix")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("prefix.reload")){
						ranks.clear();
						formats.clear();
						prefixGroupLoader.loadGroups();
						sender.sendMessage(ChatColor.RED + "Prefix succesfully reloaded.");
						return true;
					}else{
						sender.sendMessage("You don't have permission to reload prefix.");
						return true;
					}
				}
			}
		}
		return false;
	}
}

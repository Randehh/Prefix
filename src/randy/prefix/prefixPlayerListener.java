package randy.prefix;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class prefixPlayerListener extends PlayerListener{
	
	public void onPlayerChat(PlayerChatEvent event){
		Player player = event.getPlayer();
		String message = formatMessage(event.getMessage(), player);
		event.setFormat(message);
	}
	
	public String formatMessage(String text, Player player){
		String name = ChatColor.stripColor(player.getDisplayName());
		String playergroup = prefix.ranks.get(name);
		String message = prefix.formats.get("main");
		String[] presuffix = prefix.formats.get(playergroup).split("///");
		message = message
		.replace("[prefix]", presuffix[0])
		.replace("[suffix]", presuffix[1])
		.replace("[playername]", name)
		.replace("[message]", text)
		.replaceAll("&0", ChatColor.BLACK+"")
		.replaceAll("&1", ChatColor.DARK_BLUE+"")
		.replaceAll("&2", ChatColor.DARK_GREEN+"")
		.replaceAll("&3", ChatColor.DARK_AQUA+"")
		.replaceAll("&4", ChatColor.DARK_RED+"")
		.replaceAll("&5", ChatColor.DARK_PURPLE+"")
		.replaceAll("&6", ChatColor.GOLD+"")
		.replaceAll("&7", ChatColor.GRAY+"")
		.replaceAll("&8", ChatColor.DARK_GRAY+"")
		.replaceAll("&9", ChatColor.BLUE+"")
		.replaceAll("&a", ChatColor.GREEN+"")
		.replaceAll("&b", ChatColor.AQUA+"")
		.replaceAll("&c", ChatColor.RED+"")
		.replaceAll("&d", ChatColor.LIGHT_PURPLE+"")
		.replaceAll("&e", ChatColor.YELLOW+"")
		.replaceAll("&f", ChatColor.WHITE+"");
		return message;
	}
}

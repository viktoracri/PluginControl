package dev.acri.plugincontrol.util;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messager {
	
	public static void sendMessageWithSound(CommandSender sender, String message, Sound sound) {
		
		message = "§8[§9§lPlugin§b§lControl§8] " + message;
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			p.sendMessage(message);
			if(sound != null) p.playSound(p.getLocation(), sound, 1, 1);
			
		} else {
			
			sender.sendMessage(message);
			
		}
		
	}

}

package dev.acri.plugincontrol.event;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener{
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		PluginCommand cmd = Bukkit.getPluginCommand(e.getMessage().split(" ")[0].replace("/", ""));
		
		if(!cmd.getPlugin().isEnabled()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cThis command is disabled.");
		}
		
	}

}

package dev.acri.plugincontrol.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.acri.plugincontrol.Main;
import dev.acri.plugincontrol.inventory.InventoryManager;

public class PluginControlCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cOnly executable by players.");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("plugincontrol.use")) {
			sender.sendMessage("§cNo permission.");
			return true;
		}
		
		InventoryManager invManager = Main.getInventoryManager();
		InventoryManager.updateInventory(p, invManager.getPluginControlInventory(1));
		
		
		
		return true;
	}

}

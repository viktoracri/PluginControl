package dev.acri.plugincontrol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import dev.acri.plugincontrol.command.PluginControlCommand;
import dev.acri.plugincontrol.event.InventoryClickListener;
import dev.acri.plugincontrol.event.PlayerCommandPreprocessListener;
import dev.acri.plugincontrol.inventory.InventoryManager;
import dev.acri.plugincontrol.util.Metrics;

public class Main extends JavaPlugin{
	
	private static Main instance;
	private static InventoryManager inventoryManager = new InventoryManager();
	
	@Override
	public void onEnable() {
		Main.instance = this;
		
		getCommand("pc").setExecutor(new PluginControlCommand());
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
		
		new Metrics(this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public static Main getInstance() {
		return instance;
	}

	
	public static InventoryManager getInventoryManager() {
		return inventoryManager;
	}
}

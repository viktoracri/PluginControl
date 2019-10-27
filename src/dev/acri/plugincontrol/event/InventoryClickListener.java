package dev.acri.plugincontrol.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import dev.acri.plugincontrol.Main;
import dev.acri.plugincontrol.inventory.InventoryManager;
import dev.acri.plugincontrol.inventory.PCItem;
import dev.acri.plugincontrol.util.HiddenStringUtils;
import dev.acri.plugincontrol.util.Messager;

public class InventoryClickListener implements Listener{
	
	InventoryManager invManager = Main.getInventoryManager();
	
	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent e) {
		
		
		
		
		Player p = (Player) e.getWhoClicked();
		ItemStack currentItem = e.getCurrentItem();
		ClickType clickType = e.getClick();
		
		if(e.getClickedInventory() == null) {
			return ;
		}
	
		if(clickType == ClickType.UNKNOWN)
			return;
		if(currentItem == null)
			return;
		
		if(currentItem.getType().equals(Material.AIR))
			return;

		
		boolean isMenu = false;

		
		if(e.getView().getTopInventory().getItem(4) != null) {
			if(e.getView().getTopInventory().getItem(4).getType() == Material.BOOK) {
				if(e.getView().getTopInventory().getItem(4).hasItemMeta()) {
					if(HiddenStringUtils.extractHiddenString(e.getView().getTopInventory().getItem(4).getItemMeta().getDisplayName()).contains("pcmenu;")) {
						isMenu = true;
					}
				}
			}
		}
		
		if(isMenu) {
			
			e.setCancelled(true);
			
			if(!HiddenStringUtils.hasHiddenString(currentItem.getItemMeta().getDisplayName()))
				return;
			 
			if(!HiddenStringUtils.extractHiddenString(currentItem.getItemMeta().getDisplayName()).split(";")[0].equalsIgnoreCase("PCITEM"))
				return;

			
			PCItem item = PCItem.getItem(HiddenStringUtils.extractHiddenString(currentItem.getItemMeta().getDisplayName()).split(";")[1]);
			if(item != null) {
				if(item == PCItem.PLUGIN_CONTAINER) {
					Plugin plugin = Bukkit.getPluginManager().getPlugin(HiddenStringUtils.extractHiddenString(currentItem.getItemMeta().getDisplayName()).split(";")[2]);
					
					InventoryManager.updateInventory(p, invManager.getPluginDescriptionInventory(plugin));
				}else if(item == PCItem.PAGE_NEXT) {
					InventoryManager.updateInventory(p, invManager.getPluginControlInventory(Integer.parseInt(HiddenStringUtils.extractHiddenString(p.getOpenInventory().getTopInventory().getItem(4).getItemMeta().getDisplayName()).split(";")[2]) + 1));
				}else if(item == PCItem.PAGE_PREV) {
					InventoryManager.updateInventory(p, invManager.getPluginControlInventory(Integer.parseInt(HiddenStringUtils.extractHiddenString(p.getOpenInventory().getTopInventory().getItem(4).getItemMeta().getDisplayName()).split(";")[2]) - 1));
				}else if(item == PCItem.NOTHING) {
					
				}
				
				
				if(HiddenStringUtils.extractHiddenString(p.getOpenInventory().getTopInventory().getItem(4).getItemMeta().getDisplayName()).split(";")[1].equalsIgnoreCase("plugin")) {
					Plugin plugin = Bukkit.getPluginManager().getPlugin(HiddenStringUtils.extractHiddenString(p.getOpenInventory().getTopInventory().getItem(4).getItemMeta().getDisplayName()).split(";")[2]);
					
					
					if(item == PCItem.PLUGIN_DISABLE) {
						Bukkit.getPluginManager().disablePlugin(plugin);
						InventoryManager.updateInventory(p, invManager.getPluginDescriptionInventory(plugin));
						Messager.sendMessageWithSound(p, "§cDisabled the plugin §a§l" + plugin.getName(), Sound.ENTITY_CHICKEN_EGG);
					}else if(item == PCItem.PLUGIN_ENABLE) {
						Bukkit.getPluginManager().enablePlugin(plugin);
						InventoryManager.updateInventory(p, invManager.getPluginDescriptionInventory(plugin));
						Messager.sendMessageWithSound(p, "§aEnabled the plugin §a§l" + plugin.getName(), Sound.ENTITY_CHICKEN_EGG);
					}else if(item == PCItem.PLUGIN_RELOAD) {
						Bukkit.getPluginManager().disablePlugin(plugin);
						Bukkit.getPluginManager().enablePlugin(plugin);
						InventoryManager.updateInventory(p, invManager.getPluginDescriptionInventory(plugin));
						Messager.sendMessageWithSound(p, "§eReloaded the plugin §a§l" + plugin.getName(), Sound.ENTITY_CHICKEN_EGG);
					}else if(item == PCItem.PLUGIN_CONFIG_RELOAD) {
						plugin.reloadConfig();
						InventoryManager.updateInventory(p, invManager.getPluginDescriptionInventory(plugin));
						Messager.sendMessageWithSound(p, "§bReloaded the configs of §a§l" + plugin.getName(), Sound.ENTITY_CHICKEN_EGG);
					}else if(item == PCItem.PAGE_BACK) {
						InventoryManager.updateInventory(p, invManager.getPluginControlInventory(1));
					}
					
					
					
				}

			}else if(currentItem.hasItemMeta()){
				if(currentItem.getItemMeta().hasDisplayName()) {
					if(HiddenStringUtils.hasHiddenString(currentItem.getItemMeta().getDisplayName())) {
						if(HiddenStringUtils.extractHiddenString(currentItem.getItemMeta().getDisplayName()).contains(";")) {
							String[] str = HiddenStringUtils.extractHiddenString(currentItem.getItemMeta().getDisplayName()).split(";");
							if(str[1].equalsIgnoreCase("back")) {
								if(str[2].equalsIgnoreCase("main"))
									InventoryManager.updateInventory(p, invManager.getPluginControlInventory(0));

								p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1l, 1l);

							
							}
							
						}
					}
				}
				
				
			}
			
			
			
			
		}
	}
}

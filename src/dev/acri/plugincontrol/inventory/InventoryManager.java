package dev.acri.plugincontrol.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import dev.acri.plugincontrol.Main;
import dev.acri.plugincontrol.util.HiddenStringUtils;
import dev.acri.plugincontrol.util.ItemStackUtils;

public class InventoryManager {

	public Inventory getPluginControlInventory(int page) {
		Inventory inv = Bukkit.createInventory(null, 54, "PluginControl"); 
		
		List<String> lore = new ArrayList<String>();
		
		for(int i = 45; i < 54; i++) {
			ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§7");
			item.setItemMeta(meta);
			inv.setItem(i, item);
		}
		
		for(int i = 0; i < 9; i++) {
			ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§7");
			item.setItemMeta(meta);
			inv.setItem(i, item);
		}
		
		
		
		lore.add("§8» §6§lPluginControl v" + Main.getInstance().getDescription().getVersion() + "§7 by §aViktoracri");
		lore.add("§8» §7Discord: §9Viktoracri#0556");
		lore.add("§7");
		lore.add("§7Control and manager all of your plugins easily");
		lore.add("§7within this GUI. Report all bugs on the spigot page.");
		lore.add("§7");
		lore.add("§8» §7Please report any bugs via discord.");
		inv.setItem(4, ItemStackUtils.getItemStack(Material.BOOK, HiddenStringUtils.encodeString("pcmenu;main;" + page + ";") + "§3PluginControl", lore));
		lore.clear();
		
		List<Plugin> plugins = Arrays.asList(Bukkit.getPluginManager().getPlugins());
		

		int i = 0;
		for(int x = (page - 1) * 28; x < plugins.size(); x++) {
			Plugin plugin = plugins.get(x);
			
			if(!plugin.isEnabled()) {
				lore.add("§4» §c§lPlugin is disabled");
				lore.add("§7");
			}
			
			lore.add("§8» §7Name: §b" + plugin.getDescription().getName());
			lore.add("§8» §7Version: §b" + plugin.getDescription().getVersion());
			
			String msg = "";
			if(plugin.getDescription().getAuthors().size() > 1)
				msg = "§8» §7Authors: §b";
			else
				msg = "§8» §7Author: §b";
			
			final String OGmsg = msg;
			
			int newlines = 0;
			for(String author : plugin.getDescription().getAuthors()) {
				if(msg.length() > 50*(newlines+1)) {
					msg += "%N§b   ";
					newlines++;
				}
				msg += author + "§7, §b";
			}
			
			
			
			if(!msg.equalsIgnoreCase(OGmsg)) {
				msg = msg.substring(0, msg.length() - 4);
				for(String str : msg.split("%N"))
					lore.add(str);
			}
				

			
			lore.add("§7");
			lore.add("§a» §7Click to control plugin");
			
			
			String name =  PCItem.PLUGIN_CONTAINER.getDisplayname().replace("%a", plugin.getName());
			if(!plugin.isEnabled()) {
				name =  PCItem.PLUGIN_CONTAINER.getDisplayname().replace("%a", "§c" + plugin.getName());	
			}	
			name = HiddenStringUtils.replaceHiddenString(name, HiddenStringUtils.extractHiddenString(name) + plugin.getName());
			
			
			ItemStack item = ItemStackUtils.getItemStack(PCItem.PLUGIN_CONTAINER.getItem(), name, lore);
			if(!plugin.isEnabled()) {
				item = ItemStackUtils.getItemStack(Material.COAL, name, lore);
			}
			lore.clear();
			
			
			
			
			inv.setItem(10 + i +((int)(i / 7)) * 2, item);
			i += 1;
			if(i >= 28)break;
			
		}
		
		if(plugins.size() > 28 * page) {
			lore.add("§a» §7Click to go to the next page");
			inv.setItem(51, ItemStackUtils.getItemStack(PCItem.PAGE_NEXT.getItem(), PCItem.PAGE_NEXT.getDisplayname(), lore));
			lore.clear();
		}else {
			inv.setItem(51, ItemStackUtils.getItemStack(PCItem.PAGE_NO_NEXT.getItem(), PCItem.PAGE_NO_NEXT.getDisplayname(), lore));
		}
		
		if(page > 1) {
			lore.add("§a» §7Click to go to the previous page");
			inv.setItem(47, ItemStackUtils.getItemStack(PCItem.PAGE_PREV.getItem(), PCItem.PAGE_PREV.getDisplayname(), lore));
			lore.clear();
		}else {
			inv.setItem(47, ItemStackUtils.getItemStack(PCItem.PAGE_NO_PREV.getItem(), PCItem.PAGE_NO_PREV.getDisplayname(), lore));
		}
		
		inv.setItem(49, ItemStackUtils.getItemStack(Material.PAPER, HiddenStringUtils.encodeString(page + ";") + "§7Page: §e§l" + page));
		
		
		
		
		return inv;
	}
	
	public Inventory getPluginDescriptionInventory(Plugin plugin) {
		Inventory inv = Bukkit.createInventory(null, 27, plugin.getName()); 
		
		List<String> lore = new ArrayList<String>();

		
		for(int i = 0; i < 9; i++) {
			ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§7");
			item.setItemMeta(meta);
			inv.setItem(i, item);
		}
		
		
		
		lore.add("§3» §7Control this plugin by clicking on the ");
		lore.add("  §7items below. You can turn the plugin ");
		lore.add("  §7on or off or read the plugin information.");
		inv.setItem(4, ItemStackUtils.getItemStack(Material.BOOK, HiddenStringUtils.encodeString("pcmenu;plugin;" + plugin.getName() + ";") + "§3" + plugin.getName(), lore));
		lore.clear();
		
		lore.add("§a» §7Click to go back");
		inv.setItem(18, ItemStackUtils.getItemStack(PCItem.PAGE_BACK.getItem(), PCItem.PAGE_BACK.getDisplayname(), lore));
		lore.clear();
		// Plugin Control
		
		if(plugin.isEnabled()) {
			lore.add("§4» §cWARNING: This may lead to data loss.");
			lore.add("§4» §cProceed with caution.");
			lore.add("§7");
			lore.add("§a» §cClick to disable!");
			inv.setItem(21, ItemStackUtils.getItemStack(PCItem.PLUGIN_DISABLE.getItem(), PCItem.PLUGIN_DISABLE.getDisplayname(), lore));
			lore.clear();
			
			lore.add("§4» §cWARNING: This may lead to data loss.");
			lore.add("§4» §cProceed with caution.");
			lore.add("§7");
			lore.add("§a» §eClick to reload!");
			inv.setItem(22, ItemStackUtils.getItemStack(PCItem.PLUGIN_RELOAD.getItem(), PCItem.PLUGIN_RELOAD.getDisplayname(), lore));
			lore.clear();
			
			lore.add("§8» §7This will force reload the configs");
			lore.add("  §7if the plugin has any.");
			lore.add("§7");
			lore.add("§a» §bClick to reload config!");
			inv.setItem(23, ItemStackUtils.getItemStack(PCItem.PLUGIN_CONFIG_RELOAD.getItem(), PCItem.PLUGIN_CONFIG_RELOAD.getDisplayname(), lore));
			lore.clear();
		}else {
			lore.add("§8» §7This will force enable this plugin.");
			lore.add("§7");
			lore.add("§a» §aClick to enable!");
			inv.setItem(21, ItemStackUtils.getItemStack(PCItem.PLUGIN_ENABLE.getItem(), PCItem.PLUGIN_ENABLE.getDisplayname(), lore));
			lore.clear();
			
			lore.add("§8» §c§lPlugin is diabled");
			lore.add("§7");
			lore.add("§7You can't reload this plugin since it is disabled.");
			inv.setItem(22, ItemStackUtils.getItemStack(PCItem.PLUGIN_CANT_RELOAD.getItem(), PCItem.PLUGIN_CANT_RELOAD.getDisplayname(), lore));
			lore.clear();
			
			lore.add("§8» §c§lPlugin is diabled");
			lore.add("§7");
			lore.add("§7You can't reload the config since the plugin is disabled.");
			inv.setItem(23, ItemStackUtils.getItemStack(PCItem.PLUGIN_CANT_CONFIG_RELOAD.getItem(), PCItem.PLUGIN_CANT_CONFIG_RELOAD.getDisplayname(), lore));
			lore.clear();
		}
		
		
		
		
		if(plugin.isEnabled()) lore.add("§8» §7Status: §aENABLED");
		else lore.add("§8» §7Status: §cDISABLED");
		
		lore.add("§7 ");
		lore.add("§2» §7Name: §a" + plugin.getName());
		lore.add("§9» §7Version: §b" + plugin.getDescription().getVersion());
		
		if(!plugin.getDescription().getAuthors().isEmpty()) {
			lore.add("§5» §7Main Author: §d" + plugin.getDescription().getAuthors().get(0));
		}else {
			lore.add("§5» §7Main Author: §aNo author specified");
		}
		
		inv.setItem(13, ItemStackUtils.getItemStack(Material.PAPER, "§6Information", lore));
		lore.clear();
		
		
		
		// Author(s)
		
		
		
		if(!plugin.getDescription().getAuthors().isEmpty()) {
			String msg = "";
			if(plugin.getDescription().getAuthors().size() > 1)
				msg = "§5» §7Authors: §d";
			else
				msg = "§5» §7Author: §d";
			
			final String OGmsg = msg;
			
			int newlines = 0;
			for(String author : plugin.getDescription().getAuthors()) {
				if(msg.length() > 50*(newlines+1)) {
					msg += "%N§d   ";
					newlines++;
				}
				msg += author + "§7, §d";
			}
			
			if(!msg.equalsIgnoreCase(OGmsg)) {
				msg = msg.substring(0, msg.length() - 4);
				for(String str : msg.split("%N"))
					lore.add(str);
			}
		}else {
			lore.add("§8» §cPlugin author not specified.");
		}
		
		
		inv.setItem(10, ItemStackUtils.getItemStack(Material.BOOK_AND_QUILL, "§aAuthors", lore));
		lore.clear();
		
		
		// Description
		
		if(plugin.getDescription().getDescription() != null) {
			String msg = "§6» §e";
			int newlines = 0;
			for(String word : plugin.getDescription().getDescription().split(" ")) {
				if(msg.length() > 50*(newlines+1)) {
					msg+= "%N§e   ";
					newlines++;
				}
				msg += word + " ";
			}
			msg = msg.substring(0, msg.length());
			for(String str : msg.split("%N"))
				lore.add(str);
			
			
		}else {
			lore.add("§8» §cPlugin description not set");
		}
		
		inv.setItem(11, ItemStackUtils.getItemStack(Material.FEATHER, "§aDescription", lore));
		lore.clear();
		
		
		// Website
		
		if(plugin.getDescription().getWebsite() != null) {
			lore.add("§1» §9" + plugin.getDescription().getWebsite());
		}else {
			lore.add("§8» §cPlugin website not specified.");
		}
		
		inv.setItem(15, ItemStackUtils.getItemStack(Material.FLINT, "§aWebsite", lore));
		lore.clear();
		

		// Dependencies
		
		if(!plugin.getDescription().getDepend().isEmpty()) {
			
			String msg = "§3» §7Depends on: §b";
			int newlines = 0;
			for(String depend : plugin.getDescription().getDepend()) {
				if(msg.length() > 50*(newlines+1)) {
					msg+= "%N§b   ";
					newlines++;
				}
				msg += depend + "§7, §b";
			}
			msg = msg.substring(0, msg.length()-6);
			for(String str : msg.split("%N"))
				lore.add(str);
		}else {
			lore.add("§3» §7Depends on: §cNothing");
		}
		
		if(!plugin.getDescription().getSoftDepend().isEmpty()) {
			
			String msg = "§3» §7Softly depends on: §b";
			int newlines = 0;
			for(String depend : plugin.getDescription().getSoftDepend()) {
				if(msg.length() > 50*(newlines+1)) {
					msg+= "%N§b   ";
					newlines++;
				}
				msg += depend + "§7, §b";
			}
			msg = msg.substring(0, msg.length()-6);
			for(String str : msg.split("%N"))
				lore.add(str);
		}else {
			lore.add("§3» §7Softly depends on: §cNothing");
		}
		inv.setItem(16, ItemStackUtils.getItemStack(Material.EMERALD, "§aDependencies", lore));
		lore.clear();
		
		
		
		
		
		
		
		return inv;
	}
	
	public static void updateInventory(Player p, Inventory inv) {
		
		
		Inventory pInv = p.getOpenInventory().getTopInventory();
		
		if(pInv == null ) {
			p.openInventory(inv);
			return;
		}
		
		if(pInv.getSize() != inv.getSize()) {
			p.openInventory(inv);
			return;
		}
		
		
		for(int i = 0; i < pInv.getSize(); i++) {
			ItemStack pItem = pInv.getItem(i);
			ItemStack item = inv.getItem(i);
			if(pItem != item) {
				p.getOpenInventory().getTopInventory().setItem(i, item);
			}
		}
		
	}
	
}

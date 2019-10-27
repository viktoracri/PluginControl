package dev.acri.plugincontrol.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import dev.acri.plugincontrol.util.HiddenStringUtils;
import dev.acri.plugincontrol.util.ItemStackUtils;

public enum PCItem {
	

	PAGE_NEXT("§eNext Page", ItemStackUtils.getSkull(null, "MHF_ArrowRight")),
	PAGE_PREV("§ePrevious Page", ItemStackUtils.getSkull(null, "MHF_ArrowLeft")),
	PAGE_NO_PREV("§cThis is the first page", Material.BARRIER),
	PAGE_NO_NEXT("§cThis is the last page", Material.BARRIER),
	PLUGIN_CONTAINER("§a%a", Material.BOOKSHELF),
	PAGE_BACK("§eGo back", ItemStackUtils.getSkull("§aPrevious Page", "MHF_ArrowLeft")),
	
	PLUGIN_DISABLE("§cDisable Plugin", new ItemStack(Material.STAINED_CLAY,1, (byte) 14)),
	PLUGIN_ENABLE("§aEnable Plugin", new ItemStack(Material.STAINED_CLAY,1, (byte) 5)),
	PLUGIN_RELOAD("§eReload Plugin", new ItemStack(Material.STAINED_CLAY,1, (byte) 4)),
	PLUGIN_CANT_RELOAD("§eReload Plugin", Material.BARRIER),
	PLUGIN_CONFIG_RELOAD("§bReload Config", new ItemStack(Material.STAINED_CLAY,1, (byte) 3)),
	PLUGIN_CANT_CONFIG_RELOAD("§bReload Config", Material.BARRIER),
	
	NOTHING("", Material.AIR);
	
	private String displayname;
	private ItemStack item;
	
	PCItem(String displayname, ItemStack item){
		this.displayname = displayname;
		this.item = item;
	}
	
	PCItem(String displayname, Material item){
		this.displayname = displayname;
		this.item = new ItemStack(item);
	}
	
	
	public String getDisplayname() {
		return HiddenStringUtils.encodeString("PCItem;" + this.toString() + ";") + displayname;
	}
	
	public String getJustDisplayname() {
		return displayname;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public static PCItem getItem(String enumName) {
		for(PCItem wcItem : values()) {
			if(wcItem.name().equalsIgnoreCase(enumName))
				return wcItem;
		}
		return null;
	}
	
}

package dev.acri.plugincontrol.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackUtils {

	public static ItemStack getItemStack(Material mat, String displayname) {
		
		ItemStack it = new ItemStack(mat);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	public static ItemStack getItemStack(Material mat, int subid, String displayname) {
		
		ItemStack it = new ItemStack(mat, 1, (short) subid);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	public static ItemStack getItemStack(Material mat, String displayname, List<String> lore) {
		
		ItemStack it = new ItemStack(mat);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(lore);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	public static ItemStack getItemStack(ItemStack item, String displayname, List<String> lore) {
		
		ItemStack it = item;
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(lore);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	public static ItemStack getItemStack(Material mat, String displayname, String lore) {
		
		ItemStack it = new ItemStack(mat);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		List<String> loreList = new ArrayList<>();
		loreList.add(lore);
		meta.setLore(loreList);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	public static ItemStack getItemStack(Material mat, int subid, String displayname, List<String> lore) {
		
		ItemStack it = new ItemStack(mat, 1, (short) subid);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(lore);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	public static  ItemStack getItemStack(Material mat, int subid, String displayname, String lore) {
		
		ItemStack it = new ItemStack(mat, 1, (short) subid);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(displayname);
		List<String> loreList = new ArrayList<>();
		loreList.add(lore);
		meta.setLore(loreList);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getSkull(String displayname, String owner) {
		
		ItemStack it = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) it.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setOwner(owner);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getSkull(String displayname, String owner, List<String> lore) {
		
		ItemStack it = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) it.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setOwner(owner);
		meta.setLore(lore);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getSkull(String displayname, String owner, String lore) {
		
		ItemStack it = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) it.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setOwner(owner);
		List<String> loreList = new ArrayList<>();
		loreList.add(lore);
		meta.setLore(loreList);
		it.setItemMeta(meta);
		
		return it;
		
	}
	
}

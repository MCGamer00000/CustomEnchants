package me.mcgamer00000.customenchants.utils;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Gem {
	
	private final Enchant enchant;
	private final float chance;
	
	public Gem(Enchant enchant, float chance) {
		this.enchant = enchant;
		this.chance = chance;
	}
	
	public static Gem getGem(ItemStack item) {
		if(item.getType() != Material.EMERALD) return null;
		if(!item.hasItemMeta()) return null;
		if(!item.getItemMeta().hasDisplayName()) return null;
		if(!item.getItemMeta().hasLore()) return null;
		if(item.getItemMeta().getLore().size()<3) return null;
		
		String enchantStr = ChatColor.stripColor(item.getItemMeta().getLore().get(1));
		String percent = ChatColor.stripColor(item.getItemMeta().getLore().get(2));
		String[] enchantArray = enchantStr.split("\\s+");
		int lvl = NumberUtils.toInt(enchantArray[enchantArray.length - 1], -1);
		if(lvl == -1) return null;
		String name = enchantStr.replace(" " + lvl, "");
		Enchant enchant = new Enchant(name, lvl);
		
		String[] percentArray = percent.split(" ");
		if(percentArray.length < 2) return null;
		float chance = NumberUtils.toFloat(percentArray[1].substring(0, percentArray[1].length()-1), -1);
		if(chance == -1) return null;
		return new Gem(enchant, chance);
	}

	public Enchant getEnchant() {
		return enchant;
	}

	public float getChance() {
		return chance;
	}
	
}

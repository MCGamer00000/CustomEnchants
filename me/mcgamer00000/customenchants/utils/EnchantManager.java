package me.mcgamer00000.customenchants.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;

import me.mcgamer00000.customenchants.enchants.ExplosionEnchant;

public class EnchantManager {

	Map<String, BlockBreakEnchant> blockBreakEnchants = new HashMap<String,BlockBreakEnchant>();
	
	public EnchantManager() {
		blockBreakEnchants.put("explosion", new ExplosionEnchant());
	}
	
	public List<Enchant> getEnchants(List<String> lore) {
		if(lore == null) return new ArrayList<Enchant>();
		List<Enchant> enchants = new ArrayList<>();
		for(String s: lore) {
			String enchant = stripColor(s);
			String name = "";
			String lvl = "";
			String alpha = "abcdefghijklmnopqrstuvwxyz";
			String numbers = "1234567890";
			for(char c: enchant.toCharArray()) {
				if(alpha.contains(c+"")&&lvl.equals(""))
					name += c;
				else if(!name.equals("")&&numbers.contains(c+""))
					lvl += c;
				else {
					break;
				}
			}
			if(name.equals("")||lvl.equals("")) continue;
			try {
				enchants.add(new Enchant(name, Integer.valueOf(lvl)));
			} catch(Exception e) {
				continue;
			}
		}
		return enchants;
	}
	
	public String stripColor(String s) {
		return s.replace(ChatColor.COLOR_CHAR+"", "");
	}

	public Map<String,BlockBreakEnchant> getBlockBreakEnchants() {
		return blockBreakEnchants;
	}
	
}

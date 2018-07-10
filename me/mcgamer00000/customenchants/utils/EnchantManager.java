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
		blockBreakEnchants.put("Explosion", new ExplosionEnchant());
	}
	
	public List<Enchant> getEnchants(List<String> lore) {
		List<Enchant> enchants = new ArrayList<>();
		if(lore == null) return enchants;
		for(String s: lore) {
			String enchant = stripColor(s);
			String[] enchantArray = enchant.split(" ");
			if(enchantArray.length < 2) continue;
			String lvl = enchantArray[enchantArray.length-1];
			String name = enchant.replace(" " + lvl, "");
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
		/**String s2 = s;
		while(s2.contains(ChatColor.COLOR_CHAR+""))
			s2 = s2.replace(ChatColor.COLOR_CHAR+""+s.charAt(s.indexOf(ChatColor.COLOR_CHAR)+1), "");**/
		return ChatColor.stripColor(s);
	}

	public Map<String,BlockBreakEnchant> getBlockBreakEnchants() {
		return new HashMap<String,BlockBreakEnchant>(blockBreakEnchants);
	}
	
}

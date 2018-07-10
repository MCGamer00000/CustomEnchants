package me.mcgamer00000.customenchants.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;

public class EnchantManager {

	private final Map<String, BlockBreakEnchant> blockBreakEnchants = new HashMap<>();
	
	public List<Enchant> getEnchants(List<String> lore) {
		List<Enchant> enchants = new ArrayList<>();
		if(lore == null) return enchants;
		
		for(String s : lore) {
			String enchant = stripColor(s);
			String[] enchantArray = enchant.split("\\s+");

			if(enchantArray.length < 2) continue;
			int lvl = NumberUtils.toInt(enchantArray[enchantArray.length - 1], -1);
			String name = enchant.replace(" " + lvl, "");

			if(name.trim().isEmpty() || lvl == -1) continue;
			enchants.add(new Enchant(name, lvl));
		}
		
		return enchants;
	}

	public void registerEnchant(String name, BlockBreakEnchant enchant) {
		this.blockBreakEnchants.put(name, enchant);
	}

	public Map<String, BlockBreakEnchant> getBlockBreakEnchants() {
		return new HashMap<>(blockBreakEnchants);
	}
	
	private String stripColor(String s) {
		/**String s2 = s;
		while(s2.contains(ChatColor.COLOR_CHAR+""))
			s2 = s2.replace(ChatColor.COLOR_CHAR+""+s.charAt(s.indexOf(ChatColor.COLOR_CHAR)+1), "");**/
		return ChatColor.stripColor(s);
	}
	
}

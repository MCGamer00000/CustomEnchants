package me.mcgamer00000.customenchants.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;

public class EnchantManager {

	private final Map<String, BlockBreakEnchant> blockBreakEnchants = new HashMap<>();
	private final Map<String, PlayerAttackEnchant> playerAttackEnchants = new HashMap<>();
	
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

	public void registerBlockBreakEnchant(String name, BlockBreakEnchant enchant) {
		this.blockBreakEnchants.put(name, enchant);
	}

	public void registerPlayerAttackEnchant(String name, PlayerAttackEnchant enchant) {
		this.playerAttackEnchants.put(name, enchant);
	}

	public Map<String, BlockBreakEnchant> getBlockBreakEnchants() {
		return new HashMap<>(blockBreakEnchants);
	}

	public Map<String, PlayerAttackEnchant> getPlayerAttackEnchants() {
		return new HashMap<>(playerAttackEnchants);
	}
	
	private String stripColor(String s) {
		return ChatColor.stripColor(s);
	}
	
}

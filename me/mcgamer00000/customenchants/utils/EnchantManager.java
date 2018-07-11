package me.mcgamer00000.customenchants.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantManager {

	private final Map<String, Collection<Material>> enchantNames = new HashMap<>();
	private static final Collection<Material> tools = (Collection<Material>) Arrays.asList(
			Material.DIAMOND_AXE,Material.DIAMOND_PICKAXE,Material.DIAMOND_SPADE,Material.DIAMOND_HOE,
			Material.GOLD_AXE,Material.GOLD_PICKAXE,Material.GOLD_SPADE,Material.GOLD_HOE,
			Material.IRON_AXE,Material.IRON_PICKAXE,Material.IRON_SPADE,Material.IRON_HOE,
			Material.STONE_AXE,Material.STONE_PICKAXE,Material.STONE_SPADE,Material.STONE_HOE,
			Material.WOOD_AXE,Material.WOOD_PICKAXE,Material.WOOD_SPADE,Material.WOOD_HOE
			);
	private static final Collection<Material> weapons = (Collection<Material>) Arrays.asList(
			Material.STONE_SWORD,Material.GOLD_SWORD,Material.IRON_SWORD,Material.DIAMOND_SWORD,Material.WOOD_SWORD,
			Material.STONE_AXE,Material.GOLD_AXE,Material.IRON_AXE,Material.DIAMOND_AXE,Material.WOOD_AXE
			);
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
	
	public void addEnchant(Enchant e, ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = null;
		if(!meta.hasLore())
			lore = new ArrayList<>();
		else 
			lore = meta.getLore();
		lore.add(ChatColor.GRAY + e.getName() + " " + e.getLevel());
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	public void registerBlockBreakEnchant(String name, BlockBreakEnchant enchant) {
		this.blockBreakEnchants.put(name, enchant);
		if(!this.enchantNames.containsKey(name)) this.enchantNames.put(name, tools);
	}

	public void registerPlayerAttackEnchant(String name, PlayerAttackEnchant enchant) {
		this.playerAttackEnchants.put(name, enchant);
		if(!this.enchantNames.containsKey(name)) this.enchantNames.put(name, weapons);
	}

	public Map<String, Collection<Material>> getEnchantNames() {
		return enchantNames;
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

package me.mcgamer00000.customenchants.listeners;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.mcgamer00000.customenchants.CustomEnchants;
import me.mcgamer00000.customenchants.utils.Enchant;
import me.mcgamer00000.customenchants.utils.EnchantManager;
import me.mcgamer00000.customenchants.utils.Gem;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getCursor().getType() != Material.EMERALD) return;
		Gem gem = Gem.getGem(e.getCursor());
		if(gem == null) return;
		ItemStack item = e.getClickedInventory().getItem(e.getSlot());
		if(item == null) return;
		final EnchantManager enchantManager = CustomEnchants.getInst().getEnchantManager();
		if(!enchantManager.getEnchantNames().get(gem.getEnchant().getName()).contains(item.getType()))
			return;
		List<Enchant> enchants = enchantManager.getEnchants(item.getItemMeta().getLore());
		for(Enchant enchant: enchants) {
			if(enchant.getName().equals(gem.getEnchant().getName())) {
				e.getWhoClicked().sendMessage(ChatColor.RED + "The item already has this enchant.");
				e.setCancelled(true);
				return;
			}
		}
		e.setCancelled(true);
		e.getCursor().setAmount(e.getCursor().getAmount()-1);
		if(gem.getChance() < Math.random()*100) {
			e.getWhoClicked().sendMessage(ChatColor.RED + "The enchant failed and was destroyed in the process.");
			e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getEyeLocation(), Sound.ENTITY_ITEM_BREAK, 0, 0);
			return;
		} else {
			enchantManager.addEnchant(gem.getEnchant(), item);
			e.getWhoClicked().sendMessage(ChatColor.GREEN + gem.getEnchant().getName() + " " + gem.getEnchant().getLevel() + " has been successfully placed on your item.");
			e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getEyeLocation(), Sound.BLOCK_ANVIL_USE, 0, 0);
		}
	}
	
}

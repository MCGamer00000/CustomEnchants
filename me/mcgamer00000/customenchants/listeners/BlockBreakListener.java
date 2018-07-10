package me.mcgamer00000.customenchants.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.mcgamer00000.customenchants.CustomEnchants;
import me.mcgamer00000.customenchants.utils.BlockBreakEnchant;
import me.mcgamer00000.customenchants.utils.Enchant;

public class BlockBreakListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.getPlayer().getMainHand() == null)
			return;
		ItemStack item = e.getPlayer().getItemInHand();

		List<Enchant> enchants = CustomEnchants.getInst().getEnchantManager().getEnchants(item.getItemMeta().getLore());
		Map<String,BlockBreakEnchant> blockBreakEnchants = CustomEnchants.getInst().getEnchantManager().getBlockBreakEnchants();
		for(String s: blockBreakEnchants.keySet()) {
			for(Enchant enchant: enchants) {
				if(enchant.getName().equalsIgnoreCase(s)) {
					blockBreakEnchants.get(s).run(e, enchants, enchant.getLevel());
					break;
				}
			}
		}
	}
	
}

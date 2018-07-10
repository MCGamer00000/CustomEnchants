	package me.mcgamer00000.customenchants.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.mcgamer00000.customenchants.CustomEnchants;
import me.mcgamer00000.customenchants.utils.Enchant;
import me.mcgamer00000.customenchants.utils.PlayerAttackEnchant;

public class PlayerAttackListener implements Listener {

	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;
		Player p = (Player) e.getDamager();
		ItemStack item = p.getInventory().getItemInMainHand();
		if(item == null || item.getType() == Material.AIR) return;
		List<Enchant> enchants = CustomEnchants.getInst().getEnchantManager().getEnchants(item.getItemMeta().getLore());
		if(enchants.size() < 1) return;
		Map<String, PlayerAttackEnchant> playerAttackEnchants = CustomEnchants.getInst().getEnchantManager().getPlayerAttackEnchants();
		for(String s : playerAttackEnchants.keySet()) {
			for(Enchant enchant : enchants) {
				if(enchant.getName().equalsIgnoreCase(s)) {
					playerAttackEnchants.get(s).run(e, enchants, enchant.getLevel());
					break;
				}
			}
		}
	}
	
}

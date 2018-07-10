package me.mcgamer00000.customenchants.utils;

import java.util.List;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

@FunctionalInterface
public interface PlayerAttackEnchant {

	public void run(EntityDamageByEntityEvent e, List<Enchant> enchants, int lvl);
	
}

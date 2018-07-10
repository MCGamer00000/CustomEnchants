package me.mcgamer00000.customenchants.utils;

import java.util.List;

import org.bukkit.event.block.BlockBreakEvent;

@FunctionalInterface
public interface BlockBreakEnchant {

	public void run(BlockBreakEvent e, List<Enchant> enchants, int lvl);

}

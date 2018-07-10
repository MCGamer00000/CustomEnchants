package me.mcgamer00000.customenchants.utils;

import java.util.List;

import org.bukkit.event.block.BlockBreakEvent;

public abstract class BlockBreakEnchant {
	
	public abstract void run(BlockBreakEvent e, List<Enchant> enchants, int lvl);
	
}

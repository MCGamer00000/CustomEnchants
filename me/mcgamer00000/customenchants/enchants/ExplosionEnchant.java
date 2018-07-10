package me.mcgamer00000.customenchants.enchants;

import java.util.List;
import java.util.Random;

import me.mcgamer00000.customenchants.utils.BlockBreakEnchant;
import me.mcgamer00000.customenchants.utils.Enchant;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

public class ExplosionEnchant implements BlockBreakEnchant {
	
	private static final Random RAN = new Random();
	
	@Override
	public void run(BlockBreakEvent e, List<Enchant> enchants, int level) {
		e.setDropItems(false);
		for(int i = 0; i < level*2; i++) {
			Location l = new Location(null, 0, 0, 0, map(RAN.nextFloat(), 1), map(RAN.nextFloat(), 1));
			double max = RAN.nextDouble()*Math.sqrt(level/2);
			for(int i2 = 0; i2 < max; i2++) {
				Vector v = l.getDirection().multiply(i2);
				Location l2 = e.getBlock().getLocation().clone().add(0.5, 0.5, 0.5).add(v);
				Block b = l2.getBlock();
				Material m = b.getType();
				if(m != Material.AIR && m != Material.BEDROCK && m.isSolid()) {
					if(e.getPlayer().getGameMode()!=GameMode.CREATIVE) 
						b.getDrops(e.getPlayer().getInventory().getItemInMainHand()).forEach(item -> e.getPlayer().getInventory().addItem(item));
					b.setType(Material.AIR);
					// Prevents item drop lag
				}
			}
		}
	}

	public float map(float x, float max) {
		return (x/max*360-180);
	}
	
}

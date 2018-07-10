package me.mcgamer00000.customenchants.enchants;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

import me.mcgamer00000.customenchants.utils.BlockBreakEnchant;
import me.mcgamer00000.customenchants.utils.Enchant;

public class ExplosionEnchant extends BlockBreakEnchant {
	
	@SuppressWarnings("deprecation")
	@Override
	public void run(BlockBreakEvent e, List<Enchant> enchants, int level) {
		Random ran = new Random();
		for(int i = 0; i < level*2; i++) {
			Location l = new Location(null, 0, 0, 0, map(ran.nextFloat(), 1), map(ran.nextFloat(), 1));
			double max = ran.nextDouble()*Math.sqrt(level/2);
			for(int i2 = 0; i2 < max; i2++) {
				Vector v = l.getDirection().multiply(i2);
				Location l2 = e.getBlock().getLocation().clone().add(0.5, 0.5, 0.5).add(v);
				if(l2.getBlock().getType() != Material.AIR && l2.getBlock().getType() != Material.BEDROCK) 
					l2.getBlock().breakNaturally(e.getPlayer().getItemInHand());
			}
		}
	}

	public float map(float x, float max) {
		return (x/max*360-180);
	}
	
}
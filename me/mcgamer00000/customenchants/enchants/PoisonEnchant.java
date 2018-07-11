package me.mcgamer00000.customenchants.enchants;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mcgamer00000.customenchants.utils.Enchant;
import me.mcgamer00000.customenchants.utils.PlayerAttackEnchant;

public class PoisonEnchant implements PlayerAttackEnchant {

	private static final Random RAN = new Random();
	
	@Override
	public void run(EntityDamageByEntityEvent e, List<Enchant> enchants, int lvl) {
		if(!(e.getEntity() instanceof LivingEntity)) return;
		if(RAN.nextInt(10)>1) return;
		LivingEntity entity = (LivingEntity) e.getEntity();
		entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 4*20, lvl));
	}

}

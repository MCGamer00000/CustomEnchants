package me.mcgamer00000.customenchants;

import java.util.Arrays;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.mcgamer00000.customenchants.enchants.ExplosionEnchant;
import me.mcgamer00000.customenchants.enchants.PoisonEnchant;
import me.mcgamer00000.customenchants.listeners.BlockBreakListener;
import me.mcgamer00000.customenchants.listeners.InventoryClickListener;
import me.mcgamer00000.customenchants.listeners.PlayerAttackListener;
import me.mcgamer00000.customenchants.utils.EnchantManager;

public class CustomEnchants extends JavaPlugin {

	private static CustomEnchants inst;
	private EnchantManager enchantManager;
	
	@Override
	public void onEnable() {
		inst = this;
		enchantManager = new EnchantManager();
		this.enchantManager.registerBlockBreakEnchant("Explosion", new ExplosionEnchant());
		this.enchantManager.registerPlayerAttackEnchant("Poison", new PoisonEnchant());
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerAttackListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
	}
	
	// This is just a debug command for creating an enchanted item.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 3) {
			sender.sendMessage("Usage: /createitem <player> <enchant> <lvl> [chance]");
			return true;
		}
		Player p = Bukkit.getPlayer(args[0]);
		if(p == null || !p.isOnline()) {
			sender.sendMessage("Invalid player or player isn't online.");
			return true;
		}
		if(!getEnchantManager().getEnchantNames().containsKey(args[1])) {
			sender.sendMessage("Invalid Enchantment: " + args[1]);
			return true;
		}
		if(NumberUtils.toInt(args[2],-1) <= 0) {
			sender.sendMessage("Invalid Level: " + args[2]);
			return true;
		}
		String chance = null;
		if(args.length < 4) {
			chance = Math.ceil(Math.random()*100)+"";
		} else {
			if(NumberUtils.toInt(args[3],-1) <= 0) {
				sender.sendMessage("Invalid Chance: " + args[3]);
				return true;
			}
			chance = args[3];
		}
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Enchanting Gem");
		meta.setLore(Arrays.asList("",ChatColor.AQUA + args[1] + " " + args[2], ChatColor.AQUA + "Chance: " + ChatColor.DARK_AQUA + chance + "%"));
		item.setItemMeta(meta);
		p.getInventory().addItem(item);
		p.sendMessage(ChatColor.GREEN + "You have been given a gem with the chance of " + chance + "%.");
		return true;
	}
	
	public static CustomEnchants getInst() {
		return inst;
	}

	public EnchantManager getEnchantManager() {
		return enchantManager;
	}

}

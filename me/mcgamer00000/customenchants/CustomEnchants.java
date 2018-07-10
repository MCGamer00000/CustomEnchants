package me.mcgamer00000.customenchants;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.mcgamer00000.customenchants.listeners.BlockBreakListener;
import me.mcgamer00000.customenchants.utils.EnchantManager;

public class CustomEnchants extends JavaPlugin {

	private static CustomEnchants inst;
	private EnchantManager enchantManager;
	
	public void onEnable() {
		inst = this;
		enchantManager = new EnchantManager();
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
	}

	
	// This is just a debug command for creating an enchanted item.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 2) {
			if(args.length >= 1) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("You aren't a player!");
					return true;
				}
				Player p = (Player) sender;
				ItemStack item = p.getInventory().getItemInMainHand();
				item.addUnsafeEnchantment(Enchantment.LUCK, 0);
			}
			sender.sendMessage("Usage: /createitem <material> <lore>");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage("You aren't a player!");
			return true;
		}
		Material mat = null;
		try {
			mat = Material.getMaterial(args[0].toUpperCase());
		} catch(Exception e) {
			sender.sendMessage("Invalid material " + args[0]);
			return true;
		}
		String lore = "";
		for(int i = 1; i < args.length; i++ ) {
			lore += args[i] + " ";
		}
		lore = lore.substring(0, lore.length()-1);
		lore = lore.replace("&", ""+ChatColor.COLOR_CHAR);
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		Player p = (Player) sender;
		p.getInventory().addItem(item);
		return true;
	}
	
	public static CustomEnchants getInst() {
		return inst;
	}

	public EnchantManager getEnchantManager() {
		return enchantManager;
	}

	public void setEnchantManager(EnchantManager enchantManager) {
		this.enchantManager = enchantManager;
	}

	
	
}

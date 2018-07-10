package me.mcgamer00000.customenchants;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
			sender.sendMessage("Usage: /createitem <material> <lore>");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage("You aren't a player!");
			return true;
		}
		Material mat = Material.getMaterial(args[0].toUpperCase());
		if(mat == null) {
			sender.sendMessage("Invalid material " + args[0]);
			return true;
		}
		StringBuilder loreBuilder = new StringBuilder();
		for(int i = 1; i < args.length; i++ ) {
			loreBuilder.append(args[i]+" ");
		}
		String lore = loreBuilder.toString();
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

}

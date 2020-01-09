package com.starfluxgames.joinleaveeditor;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmd_jlereload implements CommandExecutor{

	private Main plugin;
	
	public cmd_jlereload(Main plugin)
	{
		this.plugin = plugin;
		plugin.getCommand("jlereload").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("joinleaveeditor.reload")  || sender.hasPermission("jle.reload") || sender.isOp())
		{
			plugin.cfgm.reloadConfig();
			plugin.loadConfig();
			sender.sendMessage(ChatColor.GREEN + "Config Reloaded");	
		}
		
		return false;
	}
	
}
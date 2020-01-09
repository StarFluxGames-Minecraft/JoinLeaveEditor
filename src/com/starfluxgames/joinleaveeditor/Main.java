package com.starfluxgames.joinleaveeditor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public ConfigManager cfgm;
	
	public String joinMessage = "";
	public String leaveMessage = "";

	@Override
	public void onEnable() {
		loadConfigManager();
		setupDefaultConfig();
		loadConfig();
		new cmd_jlereload(this);
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void setupDefaultConfig() {
		if (!cfgm.maincfg.contains("join-message")) {
			cfgm.maincfg.set("join-message", "&e%PLAYER% joined the game.");
		}
		if (!cfgm.maincfg.contains("leave-message")) {
			cfgm.maincfg.set("leave-message", "&e%PLAYER% left the game.");
		}
		cfgm.saveConfig();
		cfgm.reloadConfig();
	}

	public void loadConfig() {
		joinMessage = cfgm.maincfg.getString("join-message");
		leaveMessage = cfgm.maincfg.getString("leave-message");
	}

	public void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
		cfgm.saveConfig();
		cfgm.reloadConfig();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage.replace("%PLAYER%", e.getPlayer().getName())));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', leaveMessage.replace("%PLAYER%", e.getPlayer().getName())));
	}
}

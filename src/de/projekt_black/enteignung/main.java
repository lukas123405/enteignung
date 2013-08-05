package de.projekt_black.enteignung;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	@Override
    public void onDisable() {
    }
	@Override
    public void onEnable() {
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("enteignen")){
			
		}
		return false;
	}
		
}

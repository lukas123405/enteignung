package de.projekt_black.enteignung;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

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
		if(args.length != 1) {
			sender.sendMessage( ChatColor.RED+"Du darfst nur eine Region eingeben!");
			return true;
		}
		String rg=args[0];
		Plugin wgplugin=this.getServer().getPluginManager().getPlugin("WorldGuard");
		if(wgplugin == null) {
			sender.sendMessage( ChatColor.RED+"Es ist einen Fehler aufgetreten bitte einen Admin bescheid sagen!");
			System.err.println("WorldGuard wurde nicht auf dem Server gefunden.");
			return true;
		}
		if(!(wgplugin instanceof WorldGuardPlugin))	{
			sender.sendMessage( ChatColor.RED+"Es ist einen Fehler aufgetreten bitte einen Admin bescheid sagen!");
			System.err.println("WorldGuard wurde nicht auf dem Server gefunden.");
			return true;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage( ChatColor.RED+"Diesen Behfel kann nur in-game ausgeführt werden.");
			return true;
		}
		Player p =(Player)sender;
		WorldGuardPlugin worldGuard=(WorldGuardPlugin)wgplugin;
		RegionManager mgr =worldGuard.getGlobalRegionManager().get(p.getWorld());
		ProtectedRegion region = mgr.getRegion(rg);
		
		int maxx = region.getMaximumPoint().getBlockX();
		int maxz = region.getMaximumPoint().getBlockZ();
		int maxy = region.getMaximumPoint().getBlockY();
		
		int minx = region.getMinimumPoint().getBlockX();
		int minz = region.getMinimumPoint().getBlockZ();
		int miny = region.getMinimumPoint().getBlockY();
		
		region.getOwners().removaAll();
		for (int x = minx;x<=maxx;x++){
			for (int y = miny;y<=maxy;y++){
				for (int z = minz;z<=maxz;z++){
					Location loc = new Location (p.getWorld(),x,y,z);
					Block b = loc.getBlock();
					Material m = b.getType();
					Material materials[] = {Material.DIAMOND_BLOCK,Material.DIAMOND_ORE,Material.EMERALD_BLOCK,Material.EMERALD_ORE,Material.CHEST,Material.TRAPPED_CHEST,Material.FURNACE,Material.DISPENSER,Material.HOPPER,Material.WORKBENCH};
						for (Material mat: materials){					
							if (m.equals(mat)){
								b.setType(Material.AIR);
							}
						}
					}
 				}
			}
		}
	
		return false;
	}
		
}

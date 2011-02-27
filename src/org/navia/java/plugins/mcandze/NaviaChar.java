package org.navia.java.plugins.mcandze;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class NaviaChar extends JavaPlugin {
	
	public static Server server;
	public static Logger log;
	public static PermissionHandler permissions;
	private NCPlayerListener playerListener;

	public NaviaChar(){
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDisable() {
		CharHandler.saveCharacters();

	}

	@Override
	public void onEnable() {
		enablePermissions();
		server = getServer();
		log = Logger.getLogger("Minecraft");
		CharHandler.initialize();
		NewCharacterSessionHandler.initialize();
		playerListener = new NCPlayerListener(this);
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
		
		for (Player p: getServer().getOnlinePlayers()){
			if (!CharHandler.playerHasACharacter(p)){
				NewCharacterSession newSession = new NewCharacterSession(p);
				NewCharacterSessionHandler.sessions.add(newSession);
			}
		}
		
	}
	
	public void enablePermissions(){
		Plugin test = getServer().getPluginManager().getPlugin("Permissions");
		if (test != null){
			this.permissions = ((Permissions)test).Security;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		
		String commandName = cmd.getName();
		
		Player player;
		
		if (!sender.isPlayer()){
			return true;
		}
		
		player = (Player)sender;
		
		if (commandName.equalsIgnoreCase("char")){
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("next")){
					if (NewCharacterSessionHandler.playerIsInSession(player)){
						NewCharacterSessionHandler.getSessionByPlayer(player).nextGuide();
					}
					return true;
				}
				return false;
			}
			if (args.length >= 2){
				if (args[0].equalsIgnoreCase("name")){
					if (!(this).permissions.has(player, "naviachar.name"))
						return true;
					if (NewCharacterSessionHandler.playerIsInSession(player)){
						String name = "";
						for (int i = 1; i < args.length; i++){
							name += args[i] + " ";
						}
						name = name.substring(0, name.length() - 1);
						NewCharacterSessionHandler.getSessionByPlayer(player).nameCharacter(name);
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("age")){
					if (!(this).permissions.has(player, "naviachar.age"))
						return true;
					if (NewCharacterSessionHandler.playerIsInSession(player)){
						NewCharacterSessionHandler.getSessionByPlayer(player).ageCharacter(args[1]);
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("gender")){
					if (!(this).permissions.has(player, "naviachar.gender"))
						return true;
					if (NewCharacterSessionHandler.playerIsInSession(player)){
						NewCharacterSessionHandler.getSessionByPlayer(player).genderCharacter(args[1]);
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("show")){
					if (!(this).permissions.has(player, "naviachar.show"))
						return true;
					if (args.length < 2){
						return false;
					}
					if (!CharHandler.nameHasACharacter(args[1])){
						player.sendMessage(ChatColor.RED + "Player doesn't even have a character.");
						return true;
					}
					CharHandler.getCharacterByPlayerName(args[1]).showProfileTo(player);
					return true;
				}
				if (args[0].equalsIgnoreCase("admin")){
					if (args.length < 2){
						return false;
					}
					if (args[1].equalsIgnoreCase("name")){
						if (!(this).permissions.has(player, "naviachar.admin.name"))
							return true;
						if (args.length < 4){
							return false;
						}
						if (!CharHandler.nameHasACharacter(args[2])){
							player.sendMessage(ChatColor.RED + "Player doesn't even have a character.");
							return true;
						}
						String name = "";
						for (int i = 3; i < args.length; i++){
							name += args[i] + " ";
						}
						name = name.substring(0, name.length() - 1);
						Character c = CharHandler.getCharacterByPlayerName(args[2]);
						c.setCharacterName(name);
						player.sendMessage(ChatColor.RED + "The character profile now looks like this:");
						c.showProfileTo(player);
						return true;
					}
					if (args[1].equalsIgnoreCase("age")){
						if (!(this).permissions.has(player, "naviachar.admin.age"))
							return true;
						if (args.length < 4){
							return false;
						}
						if (!CharHandler.nameHasACharacter(args[2])){
							player.sendMessage(ChatColor.RED + "Player doesn't even have a character.");
							return true;
						}
						int i;
						try {
							i = Integer.parseInt(args[3]);
						} catch (NumberFormatException e){
							player.sendMessage("You did not type an integer.");
							return true;
						}
						Character c = CharHandler.getCharacterByPlayerName(args[2]);
						c.setAge(i);
						player.sendMessage(ChatColor.RED + "The character profile now looks like this:");
						c.showProfileTo(player);
						return true;
					}
					if (args[1].equalsIgnoreCase("gender")){
						if (!(this).permissions.has(player, "naviachar.admin.gender"))
							return true;
						if (args.length < 4){
							return false;
						}
						if (!CharHandler.nameHasACharacter(args[2])){
							player.sendMessage(ChatColor.RED + "Player doesn't even have a character.");
							return true;
						}
						Character c = CharHandler.getCharacterByPlayerName(args[2]);
						Gender gGender;
						try {
							gGender = Gender.valueOf(args[3].toUpperCase());
						} catch (Exception e){
							gGender = Gender.UNKNOWN;
						}
						c.setGender(gGender);
						player.sendMessage(ChatColor.RED + "The character profile now looks like this:");
						c.showProfileTo(player);
						return true;
					}
					
				}
			}
		}
		
		return false;
	}

}

package org.navia.java.plugins.mcandze;

import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NewCharacterSession {
	
	private Player player;
	private boolean isNaming = false;
	private boolean isAging = false;
	private boolean isGendering = false;
	private List<Method> sessions;
	public Character character;
	
	public NewCharacterSession(Player player){
		this.player = player;
		this.character = new Character(player.getName());
		startSession();
	}
	
	private void startSession(){
		player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Hi there! Seems like you are new, or just haven't set up your character yet. I will guide you through the process!");
		player.sendMessage("I suggest you /leavechannel g, plus any other channels you might be in. The chat might interrupt your setup.  Type \"/char next\" When you're ready!");		
		isNaming = true;
	}
	
	public void nextGuide(){
		if (isNaming){
			player.sendMessage(ChatColor.RED + "--------------------------------");
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "The first thing to do, is to name your character. Assuming you already know the name, go ahead and type: \"/char name <Character's name>\"");
			player.sendMessage("\"/char name <Character's name>\"");
			player.sendMessage(ChatColor.GOLD + "Example: " + ChatColor.RED + "/char name Marcus Clorance");
			player.sendMessage("Once you've done this, go to the next guide. (/char next)");
			return;
		}
		
		if (isAging){
			player.sendMessage(ChatColor.RED + "--------------------------------");
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "If you've read the lore, you should know your");
			player.sendMessage("approximate age of your character.");
			player.sendMessage("Please type it in, by using: \"/char age <Character's age>\"");
			player.sendMessage(ChatColor.GOLD + "Example: " + ChatColor.RED + "/char age 48");
			player.sendMessage("Once you've done this, go to the next guide. (/char next)");
			return;
		}
		
		if (isGendering){
			player.sendMessage(ChatColor.RED + "--------------------------------");
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Now you have to set your character's gender.");
			player.sendMessage("You have these choices: male, female, unknown. - Type them in correctly, or you will be set as \"unknown\"");
			player.sendMessage("Set the gender of your character, with this command: /char gender <Character's gender>");
			player.sendMessage(ChatColor.GOLD + "Example: " + ChatColor.RED + "/char gender female");
			player.sendMessage("Once you've done this, go to the next guide.");
			return;
		}
	}
	
	public void nameCharacter(String name){
		if (!isNaming){
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "You might already have named your character or have not gotten to this part yet.");
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "If you typed in the wrong name, ask a moderator/admin to rename your character for you.");
			return;
		}
		character.setCharacterName(name);
		player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Your character profile now looks like this:");
		character.showProfileTo(player);
		isNaming = false;
		isAging = true;
	}
	
	public void ageCharacter(String age){
		if (!isAging){
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "You might already have set your character's age or have not gotten to this part yet.");
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "If you typed in the wrong age, ask a moderator/admin to reset your character's age for you.");
			return;
		}
		int intAge;
		try {
			intAge = Integer.parseInt(age);
		} catch (NumberFormatException e){
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Please use a valid integer.");
			return;
		}
		
		character.setAge(intAge);
		player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Your character profile now looks like this:");
		character.showProfileTo(player);
		isAging = false;	
		isGendering = true;
	}
	
	public void genderCharacter(String gender){
		if (!isGendering){
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "You might already have set your character's gender or have not gotten to this part yet.");
			player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "If you typed in the wrong age, ask a moderator/admin to reset your character's gender for you.");
			return;
		}
		Gender gGender;
		try {
			gGender = Gender.valueOf(gender.toUpperCase());
		} catch (Exception e){
			gGender = Gender.UNKNOWN;
		}
		character.setGender(gGender);
		player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Your character profile now looks like this:");
		character.showProfileTo(player);
		isGendering = false;
		player.sendMessage(ChatColor.BLUE + "[CharGuide]: " + ChatColor.WHITE + "Congratulations! You have now made your character. Thank you for your time!");
		CharHandler.addCharacter(this.character);
		CharHandler.initialize();
	}
	
}

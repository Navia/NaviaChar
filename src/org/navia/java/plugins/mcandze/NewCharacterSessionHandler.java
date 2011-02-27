package org.navia.java.plugins.mcandze;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class NewCharacterSessionHandler {
	public static ArrayList<NewCharacterSession> sessions;
	
	public static void initialize(){
		sessions = new ArrayList<NewCharacterSession>();
	}
	
	public static boolean playerIsInSession(Player player){
		for (NewCharacterSession n: sessions){
			if (n.character.getPlayer().equals(player.getName())){
				return true;
			}
		}
		return false;
	}
	
	public static NewCharacterSession getSessionByPlayer(Player player){
		for (NewCharacterSession n: sessions){
			if (n.character.getPlayer().equals(player.getName())){
				return n;
			}
		}
		return null;
	}
}

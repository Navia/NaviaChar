package org.navia.java.plugins.mcandze;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class CharHandler {
	
	public static ArrayList<Character> characters;
	
	public static void initialize(){
		loadCharacters();
	}
	
	public static Character getCharacterByPlayerName(String name){
		for (Character c: characters){
			if (c.getPlayer().equals(name)){
				return c;
			}
		}
		
		return null;
	}
	
	public static void loadCharacters(){
		characters = FlatfileSource.getCharacters();
		NaviaChar.log.info("[NaviaChar] Loaded " + characters.size() + " characters.");
	}
	
	public static boolean playerHasACharacter(Player player){
		for (Character c: characters){
			if (c.getPlayer().equals(player.getName()))
				return true;
		}
		
		return false;
	}
	
	public static boolean nameHasACharacter(String name){
		for (Character c: characters){
			if (c.getPlayer().equals(name)){
				return true;
			}
		}
		
		return false;
	}
	
	public static void saveCharacters(){
		for(Character c: characters){
			FlatfileSource.updateCharacter(c);
		}
	}
	
	public static void addCharacter(Character character){
		characters.add(character);
		FlatfileSource.updateCharacter(character);
	}
	
	public static boolean characterExistsWithFrontname(String characterName){
		for (Character c: characters){
			if (c.getCharacterName().startsWith(characterName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean characterExistsWithName(String characterName){
		for (Character c: characters){
			if (c.getCharacterName().equals(characterName)){
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Character> getCharacterByCharacterFrontname(String name){
		ArrayList<Character> characters = new ArrayList<Character>();
		for (Character c: characters){
			if (c.getCharacterName().startsWith(name)){
				characters.add(c);
			}
		}
		return characters;
	}
	
	public static Character getCharacterByCharacterName(String name){
		for (Character c: characters){
			if (c.getCharacterName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	
}

package org.navia.java.plugins.mcandze;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Character {
	private String player = null;
	private String characterName = null;
	private int age = -1;
	private Gender gender = null;
	
	public Character(String player, String characterName, int age, Gender gender) {
		super();
		this.player = player;
		this.characterName = characterName;
		this.age = age;
		this.gender = gender;
	}
	
	public Character(String player){
		this.player = player;
	}
	
	

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void showProfileTo(Player target){
		if (player != null){
			target.sendMessage(ChatColor.GOLD + "Player: " + ChatColor.WHITE + player);
		}
		
		if (characterName != null){
			target.sendMessage(ChatColor.GOLD + "Character: " + ChatColor.WHITE + characterName);
		}
		
		if (age != -1){
			target.sendMessage(ChatColor.GOLD + "Age: " + ChatColor.WHITE + String.valueOf(age));
		}
		
		if (gender != null){
			target.sendMessage(ChatColor.GOLD + "Gender: " + ChatColor.WHITE + gender.getNiceName());
		}
	}
	
}

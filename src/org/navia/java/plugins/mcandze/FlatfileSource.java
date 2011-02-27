package org.navia.java.plugins.mcandze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FlatfileSource {
	public static final String characterDirectory = "plugins" + File.separator + "NaviaChar" + File.separator + "Data" + File.separator + "Characters";
	
	public static ArrayList<Character> getCharacters(){
		File file = new File(characterDirectory);
		if (!file.exists() || !file.isDirectory()){
			file.mkdirs();
		}
		
		ArrayList<Character> characters = new ArrayList<Character>();
		File txtFile = new File(characterDirectory, "characters.txt");
		if (!txtFile.exists()){
			try {
				txtFile.createNewFile();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(txtFile));
			String curLine;
			
			while ((curLine = br.readLine()) != null){
				String[] split = curLine.split(":");
				Character character = new Character(split[0], split[1], Integer.parseInt(split[2]), Gender.valueOf(split[3].toUpperCase()));
				characters.add(character);
			}
			br.close();
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return characters;
	}
	
	public static void addCharacter(Character c){
		File file = new File(characterDirectory);
		if (!file.exists() || !file.isDirectory()){
			file.mkdirs();
		}
		
		File txtFile = new File(characterDirectory, "characters.txt");
		if (!txtFile.exists()){
			try {
				txtFile.createNewFile();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(txtFile, true));
			bw.write(c.getPlayer() + ":" + c.getCharacterName() + ":" + c.getAge() + ":" + c.getGender().toString());
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void deleteCharacter(Character c){
		File file = new File(characterDirectory);
		if (!file.exists() || !file.isDirectory()){
			file.mkdirs();
		}
		
		File txtFile = new File(characterDirectory, "characters.txt");
		if (!txtFile.exists()){
			try {
				txtFile.createNewFile();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(txtFile));
			String curLine;
			
			StringBuilder toKeep = new StringBuilder();
			
			while ((curLine = br.readLine()) != null){
				String split[] = curLine.split(":");
				if (!split[0].equalsIgnoreCase(c.getPlayer()) 
						&& !split[1].equalsIgnoreCase(c.getCharacterName()) 
						&& !split[2].equalsIgnoreCase(String.valueOf(c.getAge()))
						&& !split[3].equalsIgnoreCase(c.getGender().toString())){
					toKeep.append(curLine).append("\r\n");
				}
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(characterDirectory, "characters.txt")));
			bw.write(toKeep.toString());
			bw.close();
			
		} catch (Exception e){
			NaviaChar.log.warning("[NaviaChar] Error while deleting character.");
			e.printStackTrace();
		}
	}
	
	public static void updateCharacter(Character c){
		deleteCharacter(c);
		addCharacter(c);
	}
}

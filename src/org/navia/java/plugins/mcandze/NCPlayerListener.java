package org.navia.java.plugins.mcandze;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class NCPlayerListener extends PlayerListener {
	
	NaviaChar plugin;
	
	public NCPlayerListener(NaviaChar instance){
		this.plugin = instance;
	}
	
	public void onPlayerJoin(PlayerEvent event){
		if (!CharHandler.playerHasACharacter(event.getPlayer())){
			NewCharacterSession newSession = new NewCharacterSession(event.getPlayer());
			NewCharacterSessionHandler.sessions.add(newSession);
		}
	}

}

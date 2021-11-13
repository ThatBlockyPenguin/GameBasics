package com.blockypenguin.gamebasicslib.events;

import com.blockypenguin.gamebasicslib.base.GameBase;

public class Events {
	public static void sendEvent(GameBase game, EventType type, String... data) {
		if(type == EventType.SCREEN) game.getScreen().ifPresent(s -> s.receiveEvent(data));
		if(type == EventType.GAME) game.receiveEvent(data);
		if(type == EventType.BOTH) {
			game.receiveEvent(data);
			game.getScreen().ifPresent(s -> s.receiveEvent(data));
		}
	}
	
	public static enum EventType {
		SCREEN,
		GAME,
		BOTH
	}
}

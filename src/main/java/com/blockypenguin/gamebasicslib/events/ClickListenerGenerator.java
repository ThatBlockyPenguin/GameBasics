package com.blockypenguin.gamebasicslib.events;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.blockypenguin.gamebasicslib.function.TriConsumer;

public class ClickListenerGenerator {
	public static ClickListener generate(TriConsumer<InputEvent, Float, Float> con) {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				con.accept(event, x, y);
			}
		};
	}
}

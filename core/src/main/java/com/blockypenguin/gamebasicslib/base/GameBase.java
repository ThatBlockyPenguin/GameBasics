package com.blockypenguin.gamebasicslib.base;

import java.util.Optional;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class GameBase extends ApplicationAdapter {
	public final Random random;
	
	protected Optional<ScreenBase> screen;
	public final int minWidth;
	public final int minHeight;
	private static GameBase instance;
	
	protected Color clearColour = Color.BLACK;

	protected GameBase(int minWidth, int minHeight) {
		instance = this;
		random = new Random();
		screen = Optional.empty();
		
		this.minWidth = minWidth;
		this.minHeight = minHeight;
	}

	@Override
	public void render() {
		ScreenUtils.clear(clearColour);
		float delta = Gdx.graphics.getDeltaTime();
		
		preRender(delta);
		screen.ifPresent(s -> s.render(Gdx.graphics.getDeltaTime()));
		postRender(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		if(width < minWidth) width = minWidth;
		if(height < minHeight) height = minHeight;
		
		Gdx.graphics.setWindowedMode(width, height);
		if(screen.isPresent()) screen.get().resize(width, height);
	}

	@Override
	public void pause() {
		screen.ifPresent(s -> s.pause());
	}

	@Override
	public void resume() {
		screen.ifPresent(s -> s.resume());
	}
	
	@Override
	public void dispose() {
		screen.ifPresent(s -> s.hide());
	}


	/** Sets the current screen. {@link ScreenBase#hide()} is called on any old screen,
	 * and {@link ScreenBase#show()} is called on the new screen, if any.
	 * @param screen may be {@code null} */
	public void setScreen(ScreenBase screen) {
		this.screen.ifPresent(s -> s.hide());
		
		this.screen = Optional.of(screen);
		
		this.screen.ifPresent(s -> {
			s.show();
			s.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		});
	}

	/** @return the currently active {@link ScreenBase}. */
	public Optional<ScreenBase> getScreen() {
		return screen;
	}
	
	public void preRender(float delta) {
		screen.ifPresent(s -> s.preRender(delta));
	}
	
	public void postRender(float delta) {
		screen.ifPresent(s -> s.postRender(delta));
	}
	
	public void setClearColour(Color clearColour) {
		this.clearColour = clearColour;
	}
	
	public static GameBase getInstance() {
		return instance;
	}
	
	public void receiveEvent(String... data) {};
}

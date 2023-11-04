package com.blockypenguin.gamebasicslib.base;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Optional;
import java.util.Random;

public abstract class GameBase extends ApplicationAdapter {
	protected Optional<ScreenBase> screen;
	private static GameBase instance;
	
	protected Color clearColour = Color.BLACK;

	protected GameBase() {
		instance = this;
		screen = Optional.empty();
	}

	@Override
	public void render() {
		ScreenUtils.clear(clearColour);
		float delta = Gdx.graphics.getDeltaTime();
		
		preRender(delta);
		screen.ifPresent(s -> s.render(delta));
		postRender(delta);
	}
	
	@Override
	public void resize(int width, int height) { screen.ifPresent(s ->  s.resize(width, height)); }

	@Override
	public void pause() { screen.ifPresent(ScreenBase::pause); }

	@Override
	public void resume() { screen.ifPresent(ScreenBase::resume); }
	
	@Override
	public void dispose() { screen.ifPresent(ScreenBase::hide); }


	/**
	 * Sets the current screen. {@link ScreenBase#hide()} is called on any old screen,
	 * and {@link ScreenBase#show()} is called on the new screen, if any.
	 * @param screen may be {@code null}
	 */
	public void setScreen(ScreenBase screen) {
		this.screen.ifPresent(ScreenBase::hide);
		
		this.screen = Optional.of(screen);
		
		this.screen.ifPresent(s -> {
			s.show();
			s.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		});
	}

	/** @return the currently active {@link ScreenBase}. */
	public Optional<ScreenBase> getScreen() { return screen; }
	
	public void preRender(float delta) { screen.ifPresent(s -> s.preRender(delta)); }
	public void postRender(float delta) { screen.ifPresent(s -> s.postRender(delta)); }
	
	public void setClearColour(Color clearColour) { this.clearColour = clearColour; }
	
	/**
	 * @return The current instance, or null if none exists.
	 */
	@Null
	public static GameBase getInstance() { return instance; }
	
	public void receiveEvent(String... data) {}
}

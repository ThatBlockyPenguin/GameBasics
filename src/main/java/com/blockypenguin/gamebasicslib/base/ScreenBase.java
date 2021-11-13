package com.blockypenguin.gamebasicslib.base;

import com.badlogic.gdx.Screen;

public abstract class ScreenBase implements Screen {
	
	protected final GameBase instance;
	
	public ScreenBase(GameBase instance) {
		this.instance = instance;
	}
	
	@Override
	public void show() {}
	
	public void preRender(float delta) {}

	@Override
	public void render(float delta) {}
	
	public void postRender(float delta) {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
	/**
	 * super.hide() calls dispose()
	 */
	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {}

	public void receiveEvent(String... data) {}
}

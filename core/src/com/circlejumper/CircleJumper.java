package com.circlejumper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.circlejumper.screens.GameScreen;
import com.circlejumper.screens.LoadingScreen;

public class CircleJumper extends Game {

	private final Logger log = new Logger(CircleJumper.class.getName() , Logger.DEBUG);
	private AssetManager assetManager;
	private SpriteBatch batch;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		this.assetManager = new AssetManager();
		this.batch = new SpriteBatch();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		log.debug("Disposed batch");
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}

package com.circlejumper.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.circlejumper.CircleJumper;
import com.circlejumper.assets.AssetDescriptors;
import com.circlejumper.config.WorldConfig;
import com.circlejumper.util.GdxUtils;

/**
 * Created by RAJ DIGHE on 10/11/2020
 **/


public class LoadingScreen extends ScreenAdapter {

    private final float PROGRESS_BAR_WIDTH = WorldConfig.HUD_WIDTH / 2;
    private final float PROGRESS_BAR_HEIGHT = 60f;

    public LoadingScreen(CircleJumper game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    private final CircleJumper game;
    private final AssetManager assetManager;

    private Viewport HUDViewport;
    private ShapeRenderer renderer;
    private boolean changeScreen;

    private float progress;
    private float waitTime = 1f;

    @Override
    public void show() {
        super.show();
        HUDViewport = new FitViewport(WorldConfig.HUD_WIDTH, WorldConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();

        assetManager.load(AssetDescriptors.FONT);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        GdxUtils.clearScreen();
        HUDViewport.apply();
        renderer.setProjectionMatrix(HUDViewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        update(delta);
        draw();

        renderer.end();

        if (changeScreen) {
            game.setScreen(new GameScreen());
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        HUDViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
    }

    private void update(float delta) {

        progress = assetManager.getProgress();

        if (assetManager.update()) {
            waitTime -= delta;
            if (waitTime <= 0) {
                changeScreen = true;
            }

        }
    }

    private void draw() {
        float progressBarX = (WorldConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (WorldConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2F;
        renderer.rect(
                progressBarX, progressBarY,
                progress * PROGRESS_BAR_WIDTH , PROGRESS_BAR_HEIGHT
                );
    }
}

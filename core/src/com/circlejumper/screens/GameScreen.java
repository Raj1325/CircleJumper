package com.circlejumper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.circlejumper.config.GameObjectConfig;
import com.circlejumper.config.WorldConfig;
import com.circlejumper.entity.Coin;
import com.circlejumper.entity.Planet;
import com.circlejumper.entity.Player;
import com.circlejumper.util.GdxUtils;

/**
 * Created by RAJ DIGHE on 6/11/2020
 **/


public class GameScreen extends ScreenAdapter  {

    private Planet planet;
    private Player player;
    private Array<Coin> coins;
    private Pool<Coin> coinPool;

    private ShapeRenderer renderer;
    private Viewport viewport;

    private float coinSpawnTime = 0;
    private int coinsCount = 0;

    @Override
    public void show() {
        super.show();

        coins = new Array<Coin>();
        coinPool = Pools.get(Coin.class, 10);
        planet = new Planet(GameObjectConfig.PLANET_RADIUS);
        planet.setPosition(WorldConfig.WORLD_CENTER_X, WorldConfig.WORLD_CENTER_Y);
        player = new Player(planet);
        player.setPosition(
                WorldConfig.WORLD_CENTER_X,
                WorldConfig.WORLD_CENTER_Y + planet.getRadius()
        );
        player.setDimension(GameObjectConfig.PLAYER_WIDTH, GameObjectConfig.PLAYER_HEIGHT);

        renderer = new ShapeRenderer();
        viewport = new FitViewport(WorldConfig.WORLD_WIDTH, WorldConfig.WORLD_HEIGHT);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        handleInput();
        update(delta);
        draw(delta);


    }


    private void update(float delta) {
        planet.update(delta);
        if(coinsCount < GameObjectConfig.MAX_COINS) {
            spawnCoins(delta);
        }
        player.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, true);
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
        coins.clear();
        coinPool.clear();
    }

    private void draw(float delta) {
        GdxUtils.clearScreen();
        viewport.apply();
        Color oldColor = renderer.getColor();
        renderer.setColor(Color.RED);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        planet.render(delta, renderer);
        for (Coin coin : coins) {
            if(coin.isAvailable()){
                coin.render(delta, renderer);
            }else{
                coins.removeValue(coin , true);
                coinPool.free(coin);
                coinsCount--;
            }

        }
        player.render(delta, renderer);

        renderer.end();

        renderer.setColor(oldColor);
    }

    private void spawnCoins(float delta) {
        coinSpawnTime += delta;
        if (coinSpawnTime > GameObjectConfig.COIN_SPAWN_TIMER) {
            coinSpawnTime = 0;
            coinsCount++;
            Coin coin = coinPool.obtain();
            float angle = MathUtils.random(360);
            coin.setAngle(angle);
            coin.setAvailable(true);
            coin.setPosition(
                    planet.getX() + planet.getRadius() * MathUtils.cosDeg(angle),
                    planet.getY() + planet.getRadius() * MathUtils.sinDeg(angle)
            );
            coin.setDimension(GameObjectConfig.COIN_WIDTH, GameObjectConfig.COIN_HEIGHT);
            coins.add(coin);
        }
    }

    private void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.upPressed();
        }

    }
}

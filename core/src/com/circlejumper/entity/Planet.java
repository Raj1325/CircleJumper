package com.circlejumper.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.circlejumper.config.GameObjectConfig;
import com.circlejumper.config.WorldConfig;

/**
 * Created by RAJ DIGHE on 6/11/2020
 **/


public class Planet {

    private Vector2 position;
    private Circle bounds;
    private float RADIUS;

    public Planet(float radius) {
        RADIUS = radius;
        this.position = new Vector2(WorldConfig.WORLD_CENTER_X , WorldConfig.WORLD_CENTER_Y);
        this.bounds = new Circle(position.x , position.y , RADIUS);
    }

    public void update(float delta){

    }

    public void render(float delta , ShapeRenderer renderer){
        renderer.circle(position.x , position.y , RADIUS , GameObjectConfig.PLANET_CIRCLE_SEGMENTS);
    }

    public void setPosition(float x , float y){
        this.position.set(x,y);
        updateBounds();
    }

    public void setRadius(float radius){
        this.RADIUS = radius;
        updateBounds();
    }
    public float getRadius(){
        return this.RADIUS;
    }

    public float getX(){
        return this.position.x;
    }

    public float getY(){
        return this.position.y;
    }

    private void updateBounds(){
        bounds.setPosition(position.x , position.y);
        bounds.setRadius(RADIUS);
    }
}

package com.circlejumper.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by RAJ DIGHE on 6/11/2020
 **/


public class EntityBase {

    protected Vector2 position;
    protected Rectangle bounds;
    protected Vector2 dimension;

    public EntityBase() {
        this.position = new Vector2();
        this.bounds = new Rectangle();
        this.dimension = new Vector2();
    }

    public void setPosition(float x , float y){
        this.position.set(x , y);
    }

    public void setDimension(float width , float height){
        this.dimension.set(width , height);
    }

    public void render(float delta , ShapeRenderer renderer , float angle){
        renderer.rect(this.position.x , this.position.y ,
                0,0,
                this.dimension.x , this.dimension.y,
                1,1 ,
                angle);
    }

    protected void updateBounds(){
        this.bounds.setPosition(this.position.x , this.position.y);
        this.bounds.setSize(this.dimension.x , this.dimension.y);
    }
}

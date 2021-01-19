package com.circlejumper.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by RAJ DIGHE on 6/11/2020
 **/


public class Coin extends EntityBase implements Pool.Poolable {

    private boolean isAvailable;
    private float angle;

    public boolean isAvailable(){
        return isAvailable;
    }

    public void setAvailable(boolean available){
        this.isAvailable = available;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public void render(float delta, ShapeRenderer renderer) {
        super.render(delta, renderer, angle);
    }

    @Override
    public void reset() {
        isAvailable = true;
    }
}

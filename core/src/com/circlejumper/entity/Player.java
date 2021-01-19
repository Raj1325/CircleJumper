package com.circlejumper.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.circlejumper.config.GameObjectConfig;
import com.circlejumper.config.PlayerState;

/**
 * Created by RAJ DIGHE on 6/11/2020
 **/


public class Player extends EntityBase{

    private float angle = 0;
    private final Planet planet;
    private float jumpLength = 0;
    private float stationaryTime = 0;
    private PlayerState state;

    public Player(Planet planet) {
        super();
        this.planet = planet;
        this.jumpLength = 0;
        this.state = PlayerState.WALKING;
    }

    public void update(float delta){

        angle += 60*delta;
        angle %= 360;

        float newX , newY;

        if(this.state.isJumping()){

            if(jumpLength <= GameObjectConfig.PLAYER_MAX_HEIGHT){
                jumpLength += GameObjectConfig.PLAYER_JUMP_SPEED * delta;
            }else{
                state = PlayerState.STATIONARY;
            }
        }else if(this.state.isStattionary()){
            stationaryTime += delta;
            if(stationaryTime >= GameObjectConfig.PLAYER_STATIONARY_TIME){
                stationaryTime = 0;
                state = PlayerState.FALLING;
            }

        }else if(this.state.isFalling()){
            if(jumpLength > 0){
                jumpLength -= GameObjectConfig.PLAYER_JUMP_SPEED * delta;
            }else{
                state = PlayerState.WALKING;
                jumpLength = 0;
            }
        }

        newX = planet.getX() + ((planet.getRadius() + jumpLength) * MathUtils.cosDeg(angle));
        newY = planet.getY() + ((planet.getRadius() + jumpLength) * MathUtils.sinDeg(angle));

        this.position.set(newX , newY);
        updateBounds();

    }

    public void render(float delta, ShapeRenderer renderer) {
        super.render(delta, renderer, angle);
    }

    public void upPressed(){
        if(this.state.isWalking()){
            this.state = PlayerState.JUMPING;
        }
    }
}

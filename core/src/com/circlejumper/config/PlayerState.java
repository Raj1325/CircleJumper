package com.circlejumper.config;

/**
 * Created by RAJ DIGHE on 6/11/2020
 **/


public enum PlayerState {

    WALKING,JUMPING,FALLING , STATIONARY;

    public boolean isWalking(){
        return this == WALKING;
    }

    public boolean isJumping(){
        return this == JUMPING;
    }

    public boolean isFalling(){
        return this == FALLING;
    }

    public boolean isStattionary(){
        return this == STATIONARY;
    }

}

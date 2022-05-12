package com.mygdx.game.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.GameScreen;

public class Player extends PlayerPaddle{

    public Player(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }

    public void Update(){
        super.Update();

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            velY = 1;

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            velY = -1;

        body.setLinearVelocity(0, velY * speed);
    }
}

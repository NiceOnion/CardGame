package com.mygdx.game.Object;

import com.mygdx.game.GameScreen;

public class PlayerAI extends PlayerPaddle{
    public PlayerAI(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }

    @Override
    public void Update(){
        super.Update();

        Ball ball = gamescreen.getBall();
        if(ball.getY() + 10 > this.y && ball.getY() - 10 > this.y)
            velY=1;
        if(ball.getY() + 10 < this.y && ball.getY() - 10 < this.y)
            velY=-1;

        body.setLinearVelocity(0, velY * speed);
    }
}

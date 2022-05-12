package com.mygdx.game.Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Helper.BodyHelper;
import com.mygdx.game.Helper.Const;
import com.mygdx.game.Helper.ContactType;
import com.mygdx.game.MyGdxGame;

public class Ball {

    private Body body;
    private float x, y , speed, velX, velY;
    private int width, height;
    private GameScreen gameScreen;
    private Texture texture;

    public Ball(GameScreen gameScreen){
        this.x = (float)MyGdxGame.INSTANCE.getScreenWidth() / 2;
        this.y = (float)MyGdxGame.INSTANCE.getScreenHeight() / 2;
        this.speed = 5;

        this.texture = new Texture("White.png");
        this.gameScreen = gameScreen;
        this.width = 32;
        this.height = 32;
        this.velX = getRandomDirection();
        this.velY = getRandomDirection();
        this.body = BodyHelper.createbody(x,y,width, height,false,0,gameScreen.getWorld(), ContactType.BALL);

    }

    private float getRandomDirection(){
        return(Math.random() < 0.5) ? 1 : -1;
    }

    public void Update() {
        x = body.getPosition().x * Const.PPM - ((float)width / 2);
        y = body.getPosition().y * Const.PPM - ((float)height / 2);

        this.body.setLinearVelocity(velX * speed, velY * speed);

        //score
        if (x < 0) {
        gameScreen.getPlayerAI().score();
        Reset();
        }

        if (x > MyGdxGame.INSTANCE.getScreenWidth()){
            gameScreen.getPlayer().score();
            Reset();
        }

    }

    public void Reset(){
        this.velX = this.getRandomDirection();
        this.velY = this.getRandomDirection();
        this.speed = 5;
        this.body.setTransform((float)MyGdxGame.INSTANCE.getScreenWidth() / 2 / Const.PPM,(float)MyGdxGame.INSTANCE.getScreenWidth()/2/ Const.PPM, 0);
    }

    public void Render(SpriteBatch sBatch){
        sBatch.draw(texture, x, y, width, height);
    }

    public void reverseX(){
        this.velX *= -1;
    }

    public void reverseY(){
        this.velY *= -1;
    }

    public void incSpeed(){
        this.speed = this.speed + this.speed/10;
    }

    public float getY() {
        return y;
    }
}

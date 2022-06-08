package com.mygdx.game.Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Helper.BodyHelper;
import com.mygdx.game.Helper.Const;
import com.mygdx.game.Helper.ContactType;

public abstract class PlayerPaddle extends Sprite {

    protected Body body;
    Vector2 PastPosition;
    protected float x, y ,speed, velY;
    protected int width, height, score;
    protected Texture texture;
    protected GameScreen gamescreen;


    public PlayerPaddle(float x, float y, GameScreen gameScreen){
        this.x = x;
        this.y = y;
        PastPosition = new Vector2(getX(), getY());
        this.gamescreen = gameScreen;
        this.speed = 6;
        this.width = 16;
        this.height = 64;
        this.texture = new Texture("White.png");
        this.body = BodyHelper.createbody(x,y,width,height,false,10000, gamescreen.getWorld(), ContactType.PLAYER );
    }

    public void Update(){
        x = body.getPosition().x * Const.PPM - ((float)width / 2);
        y = body.getPosition().y * Const.PPM - ((float)height / 2);
        velY = 0;
    }

    public boolean hasMoved(){
        if (PastPosition.y != getY()){
            PastPosition.y = getY();
            return true;
        }
        return false;
    }

    public void Render(SpriteBatch batch){
        batch.draw(texture, x, y ,width, height);
    }

    public void score(){
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

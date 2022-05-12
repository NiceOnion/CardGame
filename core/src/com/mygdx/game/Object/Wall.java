package com.mygdx.game.Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Helper.BodyHelper;
import com.mygdx.game.Helper.ContactType;
import com.mygdx.game.MyGdxGame;

public class Wall {

    private Body body;
    float x,y;
    private int width, height;
    private Texture texture;

    public Wall(float y, GameScreen gamescreen){
        this.x = (float)MyGdxGame.INSTANCE.getScreenWidth()/2;
        this.y = y;
        this.width = MyGdxGame.INSTANCE.getScreenWidth();
        this.height = 64;

        this.texture = new Texture("White.png");
        this.body = BodyHelper.createbody(x,y,width, height, true, 0, gamescreen.getWorld(), ContactType.WALL);
    }

    public void Render(SpriteBatch sBatch){
        sBatch.draw(texture, x - ((float)width / 2), y - ((float)height / 2), width, height);
    }
}

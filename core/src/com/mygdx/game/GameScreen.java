package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final SpriteBatch sBatch;
    private final World world;
    private Box2DDebugRenderer boxRenderer;

    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        this.camera.position.set(new Vector3(MyGdxGame.INSTANCE.getScreenWidth() / 2, MyGdxGame.INSTANCE.getScreenHeight() / 2, 0));
        sBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.boxRenderer = new Box2DDebugRenderer();
    }

    public void update(){
        world.step(1/60f,6,2);
        sBatch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta){
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sBatch.begin();

        sBatch.end();
    }
}

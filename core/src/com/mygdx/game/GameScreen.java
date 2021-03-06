package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Object.*;

import java.util.HashMap;

import static com.mygdx.game.MyGdxGame.INSTANCE;
import static java.lang.Float.parseFloat;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final SpriteBatch sBatch;
    private final World world;
    //Player objects
    private final Player player;
    public HashMap<String, PlayerPaddle> opponent;
    private final PlayerAI playerAI;
    private final Ball ball;
    private final Wall wallTop, wallBottem;

    private final TextureRegion[] numbers;


    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        this.camera.position.set(new Vector3((float) INSTANCE.getScreenWidth() / 2, (float) INSTANCE.getScreenHeight() / 2, 0));
        sBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        //this.boxRenderer = new Box2DDebugRenderer();
        //private Box2DDebugRenderer boxRenderer;
        GameContactListener gameContactListener = new GameContactListener(this);
        this.world.setContactListener(gameContactListener);

        this.player = new Player(16, (float) INSTANCE.getScreenHeight()/2, this);
        this.playerAI = new PlayerAI( INSTANCE.getScreenWidth() - 16, (float) INSTANCE.getScreenHeight()/2, this);
        opponent = new HashMap<String, PlayerPaddle>();
        this.ball = new Ball(this);
        this.wallTop = new Wall(32, this);
        this.wallBottem = new Wall(INSTANCE.getScreenHeight() - 32, this);
        this.numbers = LoadTextureSprite("numbers.png", 10);
    }

    public void update(){
        world.step(1/60f,6,2);


        this.player.Update();
        this.playerAI.Update();
        this.ball.Update();
        this.camera.update();

        sBatch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            this.ball.Reset();
            this.player.setScore(0);
            this.playerAI.setScore(0);
        }
    }



    @Override
    public void render(float delta){
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        INSTANCE.UpdateServer(Gdx.graphics.getDeltaTime());

        sBatch.begin();

        this.player.Render(sBatch);
        this.ball.Render(sBatch);
        this.wallBottem.Render(sBatch);
        this.wallTop.Render(sBatch);
        for (HashMap.Entry<String, PlayerPaddle> entry: opponent.entrySet()){
            entry.getValue().draw(sBatch);
        }
        this.playerAI.Render(sBatch);

        drawNumbers(sBatch, player.getScore(), 64, INSTANCE.getScreenHeight() - 55 );
        drawNumbers(sBatch, playerAI.getScore(), INSTANCE.getScreenWidth() - 96, INSTANCE.getScreenHeight() - 55);

        sBatch.end();

        //this.boxRenderer.render(world, camera.combined.scl(Const.PPM));
    }

    private void drawNumbers(SpriteBatch sBatch, int number, float x, float y){
        int width = 30;
        int height = 42;
        if(number < 10)
        {
            sBatch.draw(numbers[number], x, y , width, height);
        }
        else
        {
            sBatch.draw(numbers[Integer.parseInt((""+number).substring(0,1))], x, y, width, height);
            sBatch.draw(numbers[Integer.parseInt((""+number).substring(1,2))], x + 20, y, width, height);
        }
    }

    private TextureRegion[] LoadTextureSprite (String filename, int columns){
        Texture texture = new Texture(filename);
        return TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight())[0];

    }

    public World getWorld() {
        return world;
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerAI getPlayerAI() {
        return playerAI;
    }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

final MyGdxGame game;

    private Texture backGroundImage;
    private Texture ArcherImage;
    private Texture WarriorImage;
    private OrthographicCamera camera;


    public GameScreen(final MyGdxGame game){
        this.game = game;

        backGroundImage = new Texture(Gdx.files.internal("CGBattleground.jpg"));
        ArcherImage = new Texture(Gdx.files.internal("CGArcher01.jpg"));
        WarriorImage = new Texture(Gdx.files.internal("CGWarrior01.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

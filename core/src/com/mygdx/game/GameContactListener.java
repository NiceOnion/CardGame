package com.mygdx.game;

import com.mygdx.game.Helper.ContactType;
import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {

    private GameScreen gamescreen;

    public GameContactListener(GameScreen gameScreen){
        this.gamescreen = gameScreen;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if(a == null || b == null) return;
        if(a.getUserData() == null || b.getUserData() == null) return;
        if(a.getUserData() == ContactType.BALL || b.getUserData() == ContactType.BALL){
            if (a.getUserData() == ContactType.PLAYER || b.getUserData() == ContactType.PLAYER)
            {
                gamescreen.getBall().reverseX();
                gamescreen.getBall().incSpeed();
            }
            if(a.getUserData() == ContactType.WALL || b.getUserData() == ContactType.WALL)
                gamescreen.getBall().reverseY();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

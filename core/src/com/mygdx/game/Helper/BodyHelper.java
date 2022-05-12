package com.mygdx.game.Helper;

import com.badlogic.gdx.physics.box2d.*;

public class BodyHelper {

    public static Body createbody(float x, float y, int width, int height, boolean isStatic, float density, World world, ContactType type){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = !isStatic ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;
        bodyDef.position.set( x / Const.PPM, y/Const.PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox((float)width / 2 / Const.PPM, (float)height/ 2 / Const.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = density;
        body.createFixture(fixtureDef).setUserData(type);

        polygonShape.dispose();

        return body;
    }
}

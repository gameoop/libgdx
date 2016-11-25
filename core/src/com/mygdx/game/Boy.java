package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by com on 24/11/2559.
 */
public class Boy extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion boystand;

    public Boy(World world){
       // super(screen.getAtlas().findRegion("little boy"));
        this.world = world;
        defineBoy();
        /*boystand = new TextureRegion(getTexture(),0,0,16,16);
        setBounds(0,0,16/MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(boystand);*/

    }

    public void defineBoy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MyGdxGame.PPM,32/MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12/MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}

package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Playscreen.MyGdxGame;
import com.mygdx.game.Playscreen.PlayScreen;

/**
 * Created by com on 28/11/2559.
 */
public class
candy extends enemy {

    private float stateTime;
    private Animation walk;
    private Array<TextureRegion> frame;



    public candy(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frame = new Array<TextureRegion>();

        for(int i =0;i<2;i++){
            frame.add(new TextureRegion(screen.getAtlas().findRegion("eneylw1"),i*60,0,60,60));
            walk = new Animation(0.4f,frame);
        }
        stateTime = 0;
        setBounds(getX(),getY(),60/ MyGdxGame.PPM,60/MyGdxGame.PPM);


    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getWidth()/2);
        setRegion(walk.getKeyFrame(stateTime));
        b2body.setLinearVelocity(velocity);
    }



    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(25/MyGdxGame.PPM);

        //ชน
        fdef.filter.categoryBits = MyGdxGame.ENEMY_BIT;
        fdef.filter.maskBits = MyGdxGame.GROUND_BIT |
                MyGdxGame.BRICK_BIT |
                MyGdxGame.ENEMY_BIT |
                MyGdxGame.OBJECT_BIT |
                MyGdxGame.BOY_BIT |
                MyGdxGame.ITEM_BIT;

       fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

    }



}

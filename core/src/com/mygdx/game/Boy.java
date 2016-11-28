package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.Boy.State.JUMPING;

/**
 * Created by com on 24/11/2559.
 */
public class Boy extends Sprite {
    public enum State { FALLING,JUMPING,STANDING,RUNNING};
    public State currentState;
    public  State prevState;
    public World world;
    public Body b2body;
    private TextureRegion boystand;



    private Animation boyRun;
    private Animation boyjump;
    private float stateTimer;
    private boolean runningRight;

    public Boy(PlayScreen screen){
       super(screen.getAtlas().findRegion("playerjump"));
        this.world = screen.getWorld();

        currentState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        Array<TextureRegion> frames = new Array<TextureRegion>();

        //เลือกแอนนิเมชั่น
        for(int i =0;i<4;i++){
            frames.add(new TextureRegion(getTexture(),i*57,143,57,98));
            boyjump = new Animation(0.5f,frames);
            frames.clear();
        }
        for(int i=6;i<10;i++){
            frames.add(new TextureRegion(getTexture(),i*57,143,57,98));
            boyRun = new Animation(0.1f,frames);
            frames.clear();
        }



        boystand = new TextureRegion(getTexture(),0,143,57,98);

        defineBoy();
        boystand = new TextureRegion(getTexture(),0,143,57,98);
        setBounds(0,0,57/MyGdxGame.PPM,98/MyGdxGame.PPM);
        setRegion(boystand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case JUMPING:
                    region = boyjump.getKeyFrame(stateTimer);
                    break;
            case RUNNING:
                    region = boyRun.getKeyFrame(stateTimer,true);
                    break;
            case FALLING:
            case STANDING:
            default:
                region = boystand;
                break;
        }

            if((b2body.getLinearVelocity().x <0 || !runningRight)&& !region.isFlipX()){
                region.flip(true,false);
                runningRight = false;
            }else if((b2body.getLinearVelocity().x>0 || runningRight)&& region.isFlipX()){
                region.flip(true,false);
                runningRight = true;
            }
        stateTimer = currentState == prevState ? stateTimer+dt : 0;
        prevState = currentState;
        return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y>0 || (b2body.getLinearVelocity().y <0 && prevState == State.JUMPING)){
            return JUMPING;
        }else if(b2body.getLinearVelocity().y<0){
            return State.FALLING;
        }else if(b2body.getLinearVelocity().x !=0){
            return State.RUNNING;
        }else
            return State.STANDING;
    }

    public void defineBoy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MyGdxGame.PPM,32/MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(25/MyGdxGame.PPM);

        //ชน
        fdef.filter.categoryBits = MyGdxGame.BOY_BIT;
        fdef.filter.maskBits = MyGdxGame.GROUND_BIT |
         MyGdxGame.BRICK_BIT |
         MyGdxGame.OBJECT_BIT|
         MyGdxGame.BOY_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/MyGdxGame.PPM , 6/MyGdxGame.PPM), new Vector2(2/MyGdxGame.PPM,6/MyGdxGame.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

       b2body.createFixture(fdef).setUserData("head");
    }
}

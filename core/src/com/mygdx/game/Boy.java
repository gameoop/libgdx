package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.awt.*;
import java.awt.Rectangle;

import static com.mygdx.game.Boy.State.JUMPING;

/**
 * Created by com on 24/11/2559.
 */
public class Boy extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING, DEAD}



    public State currentState;
    public State prevState;
    public World world;
    public Body b2body;

    private TextureRegion boystand;

    private Animation boyRun;
    private Animation boyjump;


    private float stateTimer;
    private boolean runningRight;

    private boolean boyisdead;
    private TextureRegion boydead;



    public Boy(PlayScreen screen) {
        super(screen.getAtlas().findRegion("playerjump"));
        this.world = screen.getWorld();


        currentState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        Array<TextureRegion> frames = new Array<TextureRegion>();

        //เลือกแอนนิเมชั่น
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * 57, 143, 57, 98));
            boyjump = new Animation(0.6f, frames);
            frames.clear();
        }
        for (int i = 6; i < 10; i++) {
            frames.add(new TextureRegion(getTexture(), i * 57, 143, 57, 98));
            boyRun = new Animation(0.1f, frames);
            frames.clear();
        }

        defineBoy();
        boystand = new TextureRegion(getTexture(), 0, 143, 57, 98);
        setBounds(0, 0, 57 / MyGdxGame.PPM, 98 / MyGdxGame.PPM);
        setRegion(boystand);



      //boydead = new TextureRegion(screen.getAtlas().findRegion("playerjump"), 0, 143, 57, 98);



    }

    public void update(float dt) {

       /* if (!isDead()) {
            die();
        }*/


        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 3);
        setRegion(getFrame(dt));
    }


    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
            case DEAD:
                region = boydead;
                break;
            case JUMPING:
                region = boyjump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = boyRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = boystand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == prevState ? stateTimer + dt : 0;
        prevState = currentState;
        return region;
    }

    public State getState() {
        if (boyisdead)
            return State.DEAD;
        else if (b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && prevState == State.JUMPING))
            return JUMPING;
        else if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
         else
            return State.STANDING;
    }

    public void defineBoy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MyGdxGame.PPM, 32 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / MyGdxGame.PPM);

        //ชน
        fdef.filter.categoryBits = MyGdxGame.BOY_BIT;
        fdef.filter.maskBits = MyGdxGame.GROUND_BIT |
                MyGdxGame.BRICK_BIT |
                MyGdxGame.OBJECT_BIT |
                MyGdxGame.BOY_BIT |
                MyGdxGame.ITEM_BIT |
                MyGdxGame.ENEMY_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);



    }
    public float getStateTimer() {
        return stateTimer;
    }

   /* public boolean isDead() {
        return boyisdead;
    }

   public void die() {
        if (!isDead()) {
            boyisdead = true;

        }
    }



    public void hit(enemy enemy) {
        die();

    }*/
}









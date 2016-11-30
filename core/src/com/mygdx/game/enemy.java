package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Vector;

/**
 * Created by com on 28/11/2559.
 */
public abstract class enemy extends Sprite{
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
   public Vector2 velocity;


    public enemy(PlayScreen screen,float x,float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
       velocity = new Vector2(1,0);

    }
    protected abstract void defineEnemy();
    public abstract void update(float at);

    public void reverseVelocity(boolean x,boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}

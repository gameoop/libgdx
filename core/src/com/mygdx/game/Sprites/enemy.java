package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Playscreen.PlayScreen;

/**
 * Created by com on 28/11/2559.
 */
public abstract class enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    private Rectangle boundsenemy;



    public enemy(PlayScreen screen,float x,float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocity = new Vector2(0.6f,0);
        boundsenemy = new Rectangle(x,y,60,60);


    }
    protected abstract void defineEnemy();
    public abstract void update(float dt);





    public void reverseVelocity(boolean x,boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }






}

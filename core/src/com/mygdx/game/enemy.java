package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by com on 28/11/2559.
 */
public abstract class enemy extends Sprite{
    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public enemy(PlayScreen screen,float x,float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();

    }
    protected abstract void defineEnemy();
}

package com.mygdx.game.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Playscreen.MyGdxGame;
import com.mygdx.game.Playscreen.PlayScreen;
import com.mygdx.game.Sprites.Boy;

/**
 * Created by com on 1/12/2559.
 */
public abstract  class Item extends Sprite {
    protected PlayScreen screen;
    protected World world;
    protected Vector2 velocity;
    protected Body body;
    boolean todestroy;
    boolean destroy;


    public Item(PlayScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        defineItem();
        velocity = new Vector2(0,0);
        todestroy = false;
        destroy = false;
    }

    protected abstract void defineItem();
    protected abstract void use(Boy boy);

    public void update(float dt){
        if(todestroy && !destroy){
            world.destroyBody(body);
            destroy = true;
            MyGdxGame.manager.get("audio/sound/242857__plasterbrain__coin-get.ogg", Sound.class).play();
        }
    }
    public void draw(Batch batch){
        if(!destroy)
            super.draw(batch);
    }
    public void destroy(){
        todestroy = true;
    }


}


package com.mygdx.game.Playscreen;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by com on 24/11/2559.
 */
public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, Rectangle bounds){
        super(screen,bounds);
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.BRICK_BIT);
    }

}

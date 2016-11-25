package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by com on 24/11/2559.
 */
public abstract class InteractiveTileObject {
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;

    public InteractiveTileObject(World world,TiledMap map,Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/MyGdxGame.PPM,(bounds.getY()+bounds.getHeight()/2)/MyGdxGame.PPM);

        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2/MyGdxGame.PPM,bounds.getHeight()/2/MyGdxGame.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);


    }
}

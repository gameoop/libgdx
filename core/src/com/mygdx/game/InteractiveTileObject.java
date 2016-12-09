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
    protected PlayScreen screen;

    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen screen,Rectangle bounds){
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/MyGdxGame.PPM,(bounds.getY()+bounds.getHeight()/2)/MyGdxGame.PPM);

        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2/MyGdxGame.PPM,bounds.getHeight()/2/MyGdxGame.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }
    public  abstract void onHeadHit();
   //ชน
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}

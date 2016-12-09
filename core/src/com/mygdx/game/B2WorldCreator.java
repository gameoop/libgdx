package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by com on 24/11/2559.
 */
public class B2WorldCreator {
    private Array<candy> candies;
    private Array<item1> item1s;
    private Array<item2> item2s;
   // private Array<Heart> hearts;

    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MyGdxGame.PPM, (rect.getY() + rect.getHeight() / 2) / MyGdxGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / MyGdxGame.PPM, rect.getHeight() / 2 / MyGdxGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MyGdxGame.OBJECT_BIT;
            body.createFixture(fdef);

            //ENEMY

        }
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rect);
        }
        //สร้างศัตรู
        candies = new Array<candy>();
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            candies.add(new candy(screen, rect.getX() / MyGdxGame.PPM, rect.getY() / MyGdxGame.PPM));
        }
       item1s = new Array<item1>();
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            item1s.add(new item1(screen, rect.getX() / MyGdxGame.PPM, rect.getY() / MyGdxGame.PPM));
        }
        item2s = new Array<item2>();
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            item2s.add(new item2(screen, rect.getX() / MyGdxGame.PPM, rect.getY() / MyGdxGame.PPM));
        }
      /* hearts = new Array<Heart>();
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            hearts.add(new Heart(screen, rect.getX() / MyGdxGame.PPM, rect.getY() / MyGdxGame.PPM));
        }*/



    }

    public Array<candy> getCandies() {
        return candies;
    }
    public Array<item1> getItem1s(){

        return item1s;
    }
    public Array<item2> getItem2s(){
        return item2s;
    }
    /*public Array<Heart> getHearts(){
        return hearts;
    }*/
}
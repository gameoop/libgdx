package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by com on 28/11/2559.
 */
public class WorldContactListener implements ContactListener {

    //เช็คการชน
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA:fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
        switch (cDef){
            case MyGdxGame.ENEMY_BIT | MyGdxGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == MyGdxGame.ENEMY_BIT)
                    ((enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((enemy)fixB.getUserData()).reverseVelocity(true,false);
                break;
            case MyGdxGame.BOY_BIT | MyGdxGame.ENEMY_BIT:
                Gdx.app.log("BOY","DEAD");
                break;
            case MyGdxGame.ENEMY_BIT | MyGdxGame.ENEMY_BIT:
                ((enemy) fixA.getUserData()).reverseVelocity(true, false);
                ((enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;

        }




    }

    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

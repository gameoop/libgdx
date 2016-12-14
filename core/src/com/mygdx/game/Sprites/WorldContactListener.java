package com.mygdx.game.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Playscreen.Hud;
import com.mygdx.game.Playscreen.MyGdxGame;

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


        switch (cDef){


            case MyGdxGame.ENEMY_BIT | MyGdxGame.OBJECT_BIT :
                if(fixA.getFilterData().categoryBits == MyGdxGame.ENEMY_BIT)
                    ((enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((enemy)fixB.getUserData()).reverseVelocity(true,false);
                break;

           case MyGdxGame.BOY_BIT | MyGdxGame.ENEMY_BIT:
               if(fixA.getFilterData().categoryBits == MyGdxGame.ENEMY_BIT)
                   ((enemy) fixA.getUserData()).reverseVelocity(true, false);
               else
                   ((enemy)fixB.getUserData()).reverseVelocity(true,false);
               Hud.Lift(-1);
               //Boy.die();
               MyGdxGame.manager.get("audio/sound/350925__cabled-mess__hurt-c-08.wav", Sound.class).play();
               break;


            case MyGdxGame.ENEMY_BIT | MyGdxGame.ENEMY_BIT :
                ((enemy) fixA.getUserData()).reverseVelocity(true, false);
                ((enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MyGdxGame.ENEMY_BIT | MyGdxGame.ITEM_BIT:
                if(fixA.getFilterData().categoryBits == MyGdxGame.ENEMY_BIT)
                    ((enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((enemy)fixB.getUserData()).reverseVelocity(true,false);
                break;
            case MyGdxGame.BOY_BIT | MyGdxGame.ITEM_BIT:

                if(fixA.getFilterData().categoryBits == MyGdxGame.ITEM_BIT)

                    ((Item) fixA.getUserData()).use((Boy) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Boy) fixA.getUserData());

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

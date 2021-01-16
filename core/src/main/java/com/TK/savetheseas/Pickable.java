package com.TK.savetheseas;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Pickable extends Collision{

    protected int ranNum = SaveTheSeaGame.getRanNum();
    protected float pickable_speed;
    protected float xpos,ypos,
                    g_width = SaveTheSeaGame.getGAME_WIDTH();

    public Pickable(){
        pickable_speed = ranNum%12 + 10;
        xpos = g_width;
        ypos = (ranNum%10*100) + 300;
        box = new CollisionBox();
    }

    @Override
    void Touched() {
        this.xpos = -500;
        super.Touched();
    }

    void setPos(){
        ranNum = SaveTheSeaGame.getRanNum();
        pickable_speed = ranNum%10 + 10;
        ypos = (ranNum%7*100) + 300;
        xpos = g_width;
    }

    void changeSpeed(){
        xpos -= pickable_speed;
    }

    void update(){
        changeSpeed();
        //if touch player
        if(box.intersects(player.box) && !isTouched){
            Touched();
            isTouched = true;
        }else if(isTouched){
            isTouched = false;
        }
        //if pass end of map
        if(xpos<-160){
            setPos();
        }
        super.update(xpos,ypos);
    }

    void render(Graphics g) {
        super.render(g);
    }

    //GETTER
    public float getXpos() {        return xpos;    }
}

package com.TK.savetheseas;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Collision {
    CollisionBox box;
    Player player = SaveTheSeaGame.getPlayer();
    boolean isTouched = false;

    void Touched(){
    }

    void update(float xpos,float ypos){
        box.preUpdate();
        box.forceTo(xpos,ypos);
    }

    void render(Graphics g){
        if(SaveTheSeaGame.isTesting)
            box.draw(g);
    }
}

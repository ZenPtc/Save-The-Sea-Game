package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

public class Fish extends Pickable{

    private Texture fish = new Texture("fish"+(ranNum%3+1)+".png"); //random picture

    @Override
    void Touched() {
        SaveTheSeaGame.setScore(SaveTheSeaGame.getScore()-1);
        super.Touched();
    }

    @Override
    void update() {
        fish = new Texture("fish"+(ranNum%3+1)+".png"); //new fish
        super.update();
        box.set(xpos,ypos,fish.getWidth(),fish.getHeight());
    }

    void render(Graphics g) {
        g.drawTexture(fish, xpos, ypos);
        super.render(g);
    }
}

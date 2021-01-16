package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

import java.util.Random;

public class Garbage extends Pickable{

    private Texture garbage = new Texture("gar"+(ranNum%5+1)+".png"); //random picture

    private int ranNumG = new Random().nextInt();
    private static int countUnPick = 0;

    public Garbage(){
        if(ranNumG<=0) ranNumG = (ranNumG*-1)+1;
        pickable_speed = ranNumG%25 + 10;
        xpos = g_width;
        ypos = (ranNumG%10*100) + 300;
        countUnPick = 0;
        box.set(xpos,ypos,garbage.getWidth(),garbage.getHeight());
    }

    @Override
    void Touched() {
        SaveTheSeaGame.setScore(SaveTheSeaGame.getScore()+1);
        super.Touched();
    }

    @Override
    void setPos() {
        ranNumG = new Random().nextInt();
        if(ranNumG<=0) ranNumG = (ranNumG*-1)+1;
        pickable_speed = ranNumG%10 + 10;
        ypos = (ranNumG%7*100) + 300;
        xpos = g_width;
    }

    @Override
    void update() {
        if(xpos<-150){
            countUnPick++;
            if(countUnPick==10){
                SaveTheSeaGame.setIsNormal(false);
                Player.setTimeMinus(1.25f);
            }
        }
        super.update();
        garbage = new Texture("gar"+(ranNumG%5+1)+".png"); //new garbage
    }

    void render(Graphics g) {
        g.drawTexture(garbage, xpos, ypos);
        super.render(g);
    }
}

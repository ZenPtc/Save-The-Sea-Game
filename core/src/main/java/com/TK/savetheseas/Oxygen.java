package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

public class Oxygen extends ItemHeal{

    private Texture oxygen = new Texture("oxygen.png");
    private Texture oxyweed = new Texture("seaweed1.png");

    private int type;

    public Oxygen(int type){ //type 0:at water surface ,1:at underwater
        this.type = type;
        if(type==0){
            xpos = g_width*2;
            ypos = 140; //above water surface
            box.set(xpos,ypos,oxygen.getWidth(),oxygen.getHeight());
        }else if(type==1){
            xpos = g_width*3;
            ypos = g_height - oxygen.getHeight() - 100;
            box.set(xpos,ypos,oxygen.getWidth(),oxygen.getHeight());
        }
    }

    @Override
    void Touched() {
        Player.setOxygen(Player.getOxygen()+350);
        super.Touched();
    }

    @Override
    void setPos() {
        if(type==0){
            xpos = g_width*4;
        }else if(type==1){
            xpos = g_width*3;
        }
    }

    @Override
    void render(Graphics g) {
        g.drawTexture(oxygen,xpos,ypos);
        if(type==1)
            g.drawTexture(oxyweed,xpos,ypos-15);
        super.render(g);
    }
}

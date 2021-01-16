package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

public class HealPack extends ItemHeal{

    private Texture medicine = new Texture("heal.png");
    private Texture medweed = new Texture("seaweed2.png");

    public HealPack(){
        xpos = g_width*4;
        ypos = g_height - medicine.getHeight() - 100;
        box.set(xpos,ypos,medicine.getWidth(),medicine.getHeight());
    }

    @Override
    void Touched() {
        Player.setHp(Player.getHp()+350);
        super.Touched();
    }

    @Override
    void setPos() {
        xpos = g_width*4;
    }

    @Override
    void render(Graphics g) {
        g.drawTexture(medicine,xpos,ypos);
        g.drawTexture(medweed,xpos,ypos-15);
        super.render(g);
    }
}

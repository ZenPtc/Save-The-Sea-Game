package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

public class DangerFish extends Pickable {

    private Texture Dfish= new Texture("Dfish.png");

    private int ranNumD = SaveTheSeaGame.getRanNum();

    public DangerFish(){
        if(ranNumD<=0) ranNumD = (ranNumD*-1)+1;
        pickable_speed = ranNumD%25 + 10;
        xpos = g_width*3;
        ypos = (ranNumD%10*100) + 300;
        box.set(xpos,ypos,Dfish.getWidth(),Dfish.getHeight());
    }

    @Override
    void Touched() {
        float hp = Player.getHp()-30; //damage 30
        Player.setHp(hp);
        super.Touched();
    }

    @Override
    void setPos() {
        super.setPos();
        xpos = g_width*3;
    }

    @Override
    void update() {
        super.update();
    }

    @Override
    void render(Graphics g) {
        g.drawTexture(Dfish, xpos, ypos);
        super.render(g);
    }
}

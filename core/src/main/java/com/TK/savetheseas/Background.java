package com.TK.savetheseas;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class Background{

    Texture background;

    private static float BG_speed = 10;
    private float width,xpos,ypos;

    public Background(float xpos,String filename){
        background = new Texture(filename);
        this.width = background.getWidth();
        this.xpos = xpos;
        ypos = 0.0f;
    }

    void update() {
        //loop background
        xpos -= BG_speed;
        if(xpos<=-width){
            xpos = width;
        }
    }

    void render(Graphics g) {
        g.drawTexture(background, xpos, ypos);
    }

    //GETTER
    public static float getBG_speed() {        return BG_speed;    }
}

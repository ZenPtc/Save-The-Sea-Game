package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

import java.util.Random;

public class Barrier extends Collision{

    private int ranNum = SaveTheSeaGame.getRanNum(),type;
    private int ranNumB = new Random().nextInt();

    private Texture barrierLow = new Texture("bar"+(ranNum%3+1)+".png"); //random picture
    private Texture barrierUp = new Texture("barUp"+(ranNum%2+1)+".png"); //random picture

    private float bar_speed = Background.getBG_speed(),
                  xpos,ypos,
                  g_width = SaveTheSeaGame.getGAME_WIDTH(),
                  g_height = SaveTheSeaGame.getGAME_HEIGHT();

    public Barrier(int type){ // 0:Low ,1:Up
        this.type = type;
        box = new CollisionBox();
        if(type==0){
            xpos = g_width;
            ypos = g_height - barrierLow.getHeight();
            box.set(xpos,ypos,barrierLow.getWidth(),barrierLow.getHeight());
        }else if(type==1){
            xpos = g_width + 1100;
            ypos = 175;     //sea surface y = 175
            box.set(xpos,ypos,barrierUp.getWidth(),barrierUp.getHeight());
        }
        if(ranNumB<=0) ranNumB = (ranNumB*-1)+1;

    }

    @Override
    void Touched() {
        float hp = Player.getHp()-5;
        Player.setHp(hp);
        super.Touched();
    }

    void update(){
        ranNum = SaveTheSeaGame.getRanNum();
        xpos -= bar_speed;
        if(box.intersects(player.box)){
            Touched();
        }
        if(xpos<-700){
            xpos = g_width*1.2f;
            type = ranNum%2;
            barrierLow = new Texture("bar"+(ranNum%3+1)+".png");
            barrierUp = new Texture("barUp"+((ranNum+ranNumB)%2+1)+".png");
            if(type==0){
                ypos = g_height - barrierLow.getHeight();
                box.forceTo(xpos,ypos,barrierLow.getWidth(),barrierLow.getHeight());
            }else if(type==1){
                ypos = 250;     //sea surface y = 250
                box.forceTo(xpos,ypos,barrierUp.getWidth(),barrierUp.getHeight());
            }
        }
        super.update(xpos,ypos);
    }

    void render(Graphics g){
        if(this.type==0){
            g.drawTexture(barrierLow,xpos,ypos);
        }else if(this.type==1){
            g.drawTexture(barrierUp,xpos,ypos);
        }
        super.render(g);
    }

    void checkRange(Barrier bar){
        if(this.box.getDistanceTo(bar.xpos,bar.ypos)<500){
            xpos += 500;
        }
    }

    //GETTER
    public float getXpos() {        return xpos;    }
    public float getYpos() {        return ypos;    }
    public Texture getBarrierLow() {        return barrierLow;    }
}

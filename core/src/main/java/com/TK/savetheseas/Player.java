package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Player extends Collision{

    private Texture player = new Texture("player.png");

    private float jump_accel = -12.0f,maxOxygen,maxHP,
                  gravity = SaveTheSeaGame.getGAME_GRAVITY(),
                  g_height = SaveTheSeaGame.getGAME_HEIGHT();
    private float xpos,ypos,yAccel = 0.0f;
    private float G_height = SaveTheSeaGame.getGAME_HEIGHT();

    private static float oxygen,hp,timeMinus = 0.75f;

    public Player(float maxOxygen,float maxHP){
        xpos = 120;
        ypos = g_height/2;
        oxygen = maxOxygen;
        this.maxOxygen = maxOxygen;
        hp = maxHP;
        this.maxHP = maxHP;
        box = new CollisionBox(xpos,ypos,player.getWidth(),player.getHeight());
    }

    void update(){
        calcLifeBar();
        move(Input.spacePressed());
        super.update(xpos,ypos);

    }

    void render(Graphics g) {
        g.drawTexture(player, xpos, ypos);
        super.render(g);
        g.setColor(Color.LIME);
        g.fillRect(35f,G_height-150, oxygen,40f);
        g.setColor(Color.RED);
        g.fillRect(35f,G_height-80, hp,40f);

    }

    void move(boolean isJump){
        if(ypos<200){               //if above water
            yAccel += gravity;
        }else{
            if(isJump){
                yAccel = jump_accel;
            }else{
                yAccel += gravity;
            }
        }
        ypos += yAccel;
        if(ypos>=g_height-150){     //if touch ground
            ypos = g_height-150;
        }
    }

    void calcLifeBar(){
        if(oxygen>maxOxygen){
            oxygen = maxOxygen;
        }else if(oxygen>0){
            oxygen -= timeMinus;
        }else oxygen=-1;
        if(hp>maxHP){
            hp = maxHP;
        }else if(hp>0){
            if(oxygen>maxOxygen*2/3){
                hp -= 0.5;
            }else if(oxygen>maxOxygen*1/3){
                hp -= 0.75;
            }else   hp -= 1;
        }else {
            SaveTheSeaGame.setIsDead(true);
        }
    }

    //GETTER
    public static float getHp() {        return hp;    }
    public static float getOxygen() {        return oxygen;    }

    //SETTER
    public static void setHp(float hp) {        Player.hp = hp;    }
    public static void setOxygen(float oxygen) {        Player.oxygen = oxygen;    }
    public static void setTimeMinus(float timeMinus) {        Player.timeMinus = timeMinus;    }
}

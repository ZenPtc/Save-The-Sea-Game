package com.TK.savetheseas;

public class ItemHeal extends Pickable{

    protected float item_speed = Background.getBG_speed(),
            g_height = SaveTheSeaGame.getGAME_HEIGHT();

    @Override
    void changeSpeed() {
        xpos -= item_speed;
    }

    void checkRange(Barrier bar){
        if(this.box.getDistanceTo(bar.getXpos()+bar.getBarrierLow().getWidth()/2
                ,bar.getYpos())<bar.getBarrierLow().getWidth()/2+150){
            xpos += bar.getBarrierLow().getWidth()/2+70;
        }
    }
}

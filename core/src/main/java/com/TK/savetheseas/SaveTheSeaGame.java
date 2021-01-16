package com.TK.savetheseas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.font.BitmapFont;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.viewport.FitViewport;
import org.mini2Dx.core.graphics.viewport.Viewport;

import java.util.Random;

public class SaveTheSeaGame extends BasicGame{
	public static final String GAME_IDENTIFIER = "com.TK.savetheseas";
	static boolean isTesting = false;

    private static final float GAME_WIDTH = 1920;
    private static final float GAME_HEIGHT = 1300;
    private static final float GAME_GRAVITY = 0.7f;

    private static int score=0,highscore;
    private static int ranNum = new Random().nextInt();
    private static boolean isNormal = true, start = true,
                           isDead = false;
    private float maxOxygen=600,maxHp=1000;

    static Player player;
    Texture startPage;
    Texture restartPage;
	Viewport fitviewport;
    Background background1,background2;
    Pickable garbage1,garbage2,fish,dFish;
    Barrier barrier1,barrier2;
    String text = "score:",text1="score:0";
    BitmapFont font;
    ItemHeal ox1,ox2,hp;
    PlayerData playerData;
	
	@Override
    public void initialise() {
	    isDead = false;
	    score = 0;

        startPage = new Texture("startPage.png");
        restartPage = new Texture("restartPage.png");
	    fitviewport = new FitViewport(GAME_WIDTH,GAME_HEIGHT);
        background1 = new Background(0.0f,"bg1.jpg");
        background2 = new Background(background1.background.getWidth(),"bg2.jpg");
        player = new Player(maxOxygen,maxHp);
        garbage1 = new Garbage();
        garbage2 = new Garbage();
        fish = new Fish();
        dFish = new DangerFish();
        barrier1 = new Barrier(ranNum%2);
        barrier2 = new Barrier(ranNum%2+1);
        font = new BitmapFont();
        font.getData().setScale(5);
        ox1 = new Oxygen(0);
        ox2 = new Oxygen(1);
        hp = new HealPack();
        playerData = new PlayerData();
        playerData.loadPlayerData();
        highscore = playerData.getHighScore();
    }
    
    @Override
    public void update(float delta) {
	    if(isDead || start){
	        if(Input.spacePressed()){
	            start = false;
	            initialise();
            }
        }else{
            if(!isNormal){
                background1 = new Background(0.0f,"SWbg1.jpg");
                background2 = new Background(background1.background.getWidth(),"SWbg2.jpg");
                isNormal = true;
            }
            setScore();
            calcHighscore();
            ranNum = new Random().nextInt(15);
            background1.update();
            background2.update();
            garbage1.update();
            garbage2.update();
            fish.update();
            dFish.update();
            barrier1.update();
            barrier2.update();
            barrier2.checkRange(barrier1);
            ox1.update();
            ox2.update();
            ox1.checkRange(barrier1);
            ox1.checkRange(barrier2);
            ox2.checkRange(barrier1);
            ox2.checkRange(barrier2);
            hp.update();
            hp.checkRange(barrier1);
            hp.checkRange(barrier2);
            player.update();
        }
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
	    fitviewport.apply(g);
        background1.render(g);
        background2.render(g);
        garbage1.render(g);
        garbage2.render(g);
        fish.render(g);
        dFish.render(g);
        barrier1.render(g);
        barrier2.render(g);
        ox1.render(g);
        ox2.render(g);
        hp.render(g);
        player.render(g);

        //draw score
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(text1,GAME_WIDTH*5/6,35,700);

        //Page
        if(start){
            g.drawTexture(startPage,0,0);
        }else if(isDead){
            g.drawTexture(restartPage,0,0);
            g.setColor(Color.ROYAL); //63-86-154
            //render score,highscore
            g.drawString(""+highscore,GAME_WIDTH/2+225,GAME_HEIGHT/2-65);
            g.drawString(""+score,GAME_WIDTH/2+225,GAME_HEIGHT/2+65);
        }
    }

    void setScore(){
	    text1 = text + score;
    }

    void calcHighscore(){
	    if(score>highscore){
	        highscore = score;
	        playerData.savePlayerData(highscore);
        }
    }

    //GETTER
    public static float getGAME_WIDTH()    {  return GAME_WIDTH;      }
    public static float getGAME_HEIGHT()   {  return GAME_HEIGHT;     }
    public static float getGAME_GRAVITY()  {  return GAME_GRAVITY;    }
    public static int getScore()        {        return score;     }
    public static Player getPlayer()    {        return player;    }
    public static int getRanNum() {
        if(ranNum<=0) ranNum = (ranNum*-1)+1;
	    return ranNum;
	}

	//SETTER
    public static void setScore(int score) {        SaveTheSeaGame.score = score;    }
    public static void setIsNormal(boolean isNormal) {        SaveTheSeaGame.isNormal = isNormal;    }
    public static void setIsDead(boolean isDead) {        SaveTheSeaGame.isDead = isDead;    }
}

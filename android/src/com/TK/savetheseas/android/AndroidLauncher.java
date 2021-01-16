package com.TK.savetheseas.android;

import org.mini2Dx.android.AndroidMini2DxConfig;

import com.badlogic.gdx.backends.android.AndroidMini2DxGame;

import android.os.Bundle;

import com.TK.savetheseas.SaveTheSeaGame;

public class AndroidLauncher extends AndroidMini2DxGame {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidMini2DxConfig config = new AndroidMini2DxConfig(SaveTheSeaGame.GAME_IDENTIFIER);
        initialize(new SaveTheSeaGame(), config);
    }
}

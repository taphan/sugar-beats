package com.sugarbeats.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
<<<<<<< HEAD
import com.sugarbeats.game.SugarBeats;
=======
import com.sugarbeats.SugarBeats;

>>>>>>> ashley
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SugarBeats(), config);
	}
}

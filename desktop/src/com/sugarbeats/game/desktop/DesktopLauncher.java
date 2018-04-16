package com.sugarbeats.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.service.INet;

public class DesktopLauncher {

	private static INet logIn;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SugarBeats.VIRTUAL_WIDTH;
		config.height = SugarBeats.VIRTUAL_HEIGHT;
		config.title = SugarBeats.TITLE;
		new LwjglApplication(new SugarBeats(logIn), config);
	}
}

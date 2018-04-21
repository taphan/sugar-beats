package com.sugarbeats.game;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.example.games.basegameutils.GameHelper;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.model.PlayerData;
import com.sugarbeats.service.IPlayService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static com.google.android.gms.games.GamesActivityResultCodes.RESULT_INVALID_ROOM;
import static com.google.android.gms.games.GamesActivityResultCodes.RESULT_LEFT_ROOM;
import static com.google.android.gms.games.GamesStatusCodes.STATUS_CLIENT_RECONNECT_REQUIRED;
import static com.google.android.gms.games.GamesStatusCodes.STATUS_OK;


public class AndroidLauncher extends AndroidApplication  {

	final static String TAG = "SUGAR BEATS";
	private GameHelper gameHelper;
	private AndroidNetwork playService;






	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		playService = new AndroidNetwork(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass = false;
		config.useAccelerometer = false;
		config.useImmersiveMode = true;
		config.useWakelock = true;
		initialize(new SugarBeats(playService), config);
		playService.setup();



	}

	@Override
	protected void onResume() {
		super.onResume();


	}

	@Override
	protected void onStart() {
		super.onStart();
		playService.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		playService.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		playService.onActivityResult(requestCode, resultCode, data);

	}




















}



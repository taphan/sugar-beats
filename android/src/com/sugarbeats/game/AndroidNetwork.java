package com.sugarbeats.game;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.example.games.basegameutils.GameHelper;
import com.sugarbeats.model.PlayerData;
import com.sugarbeats.service.IPlayService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.android.gms.games.GamesActivityResultCodes.RESULT_INVALID_ROOM;
import static com.google.android.gms.games.GamesActivityResultCodes.RESULT_LEFT_ROOM;
import static com.google.android.gms.games.GamesStatusCodes.STATUS_CLIENT_RECONNECT_REQUIRED;
import static com.google.android.gms.games.GamesStatusCodes.STATUS_OK;

public class AndroidNetwork implements IPlayService, RoomUpdateListener, RealTimeMessageReceivedListener, RoomStatusUpdateListener, OnInvitationReceivedListener, GameHelper.GameHelperListener{
    final static String TAG = "SUGAR BEATS";
    private GameHelper gameHelper;
    Activity activity;

    private final static int requestCode = 1;
    public static final int RC_ACHIEVEMENTS = 1;
    private static final int RC_SELECT_PLAYERS = 100;
    private static final int MIN_PLAYERS = 1;
    private static final int MAX_PLAYERS = 7;
    private static final int RC_LEADERBOARD = 2;
    private static final int RC_INVITATION_INBOX = 10001;
    private final static int RC_WAITING_ROOM = 10002;
    private String currentRoomId = null;
    private String incomingInvitationId;

    private IPlayService.IGameListener gameListener;
    private IPlayService.INetworkListener networkListener;
    private IPlayService playService;

    public AndroidNetwork(AndroidLauncher activity){
        this.activity = activity;
        gameHelper = new GameHelper(activity, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(true);
        gameHelper.setShowErrorDialogs(true);
    }

    public GameHelper getGameHelper() {
        return gameHelper;
    }

    public  void setup(){
        gameHelper.setup(this);
    }
    public void onStart(Activity activity) {
        gameHelper.onStart(activity);
    }

    public void onStop() {
        gameHelper.onStop();
    }



    public void keepScreenOn() {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void acceptInviteToRoom(String invId) {
        Log.d(TAG, "Accepting invitation: " + invId);
        RoomConfig.Builder roomConfigBuilder = RoomConfig.builder(this);
        roomConfigBuilder.setInvitationIdToAccept(invId)
                .setMessageReceivedListener(this)
                .setRoomStatusUpdateListener(this);
        keepScreenOn();
        Games.RealTimeMultiplayer.join(gameHelper.getApiClient(), roomConfigBuilder.build());
    }


    @Override
    public void onSignInFailed() {

    }
    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

    @Override
    public void onSignInSucceeded() {
        Log.d(TAG, "onSignInSucceeded: ");
        if (gameHelper.hasInvitation()) {
            acceptInviteToRoom(gameHelper.getInvitationId());
        }
    }

    @Override
    public void signIn() {
        try {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case RC_ACHIEVEMENTS:
                Log.d(TAG, "onActivityResult: RC_ACHIEVEMENTS");
                break;
            case RC_LEADERBOARD:
                Log.d(TAG, "onActivityResult: RC_LEADERBOARD");
                break;
            case RC_SELECT_PLAYERS:
                Log.d(TAG, "onActivityResult: RC_SELECT_PLAYERS");
                handleSelectPlayersResult(resultCode, data);
                break;
            case RC_INVITATION_INBOX:
                handleInvitationInboxResult(resultCode, data);
                break;
            case RC_WAITING_ROOM:
                handleWaitingRoomResult(resultCode, data);
                break;
            default:
                gameHelper.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void handleInvitationInboxResult(int resultCode, Intent data) {
        Log.d(TAG, "handleInvitationInboxResult: ");
    }

    private void handleSelectPlayersResult(int response, Intent data) {
        Log.d(TAG, "handleSelectPlayersResult: ");
        if (response != Activity.RESULT_OK) {
            Log.w(TAG, "*** select players UI cancelled, " + response);
            return;
        }

        Log.d(TAG, "Select players UI succeeded.");

        // get the invitee list
        final ArrayList<String> invitees = data.getStringArrayListExtra(Games.EXTRA_PLAYER_IDS);
        Log.d(TAG, "Invitee count: " + invitees.size());

        // get the automatch criteria
        Bundle autoMatchCriteria = null;
        int minAutoMatchPlayers = data.getIntExtra(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
        int maxAutoMatchPlayers = data.getIntExtra(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);
        if (minAutoMatchPlayers > 0 || maxAutoMatchPlayers > 0) {
            autoMatchCriteria = RoomConfig.createAutoMatchCriteria(
                    minAutoMatchPlayers, maxAutoMatchPlayers, 0);
            Log.d(TAG, "Automatch criteria: " + autoMatchCriteria);
        }
        // create the room
        Log.d(TAG, "Creating room...");
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(this);
        rtmConfigBuilder.addPlayersToInvite(invitees);
        rtmConfigBuilder.setMessageReceivedListener(this);
        rtmConfigBuilder.setRoomStatusUpdateListener(this);
        if (autoMatchCriteria != null) {
            rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        }
        keepScreenOn();
        Games.RealTimeMultiplayer.create(gameHelper.getApiClient(), rtmConfigBuilder.build());
        Log.d(TAG, "Room created, waiting for it to be ready...");
    }

    private void handleWaitingRoomResult(int resultCode, Intent intent) {
        Room room = intent.getParcelableExtra(Multiplayer.EXTRA_ROOM);
        Log.d(TAG, "handleWaitingRoomResult: ");
        switch (resultCode) {
            case Activity.RESULT_OK:
                Log.d(TAG, "handleWaitingRoomResult: OK");
                gameListener.onMultiplayerGameStarting();
                List<PlayerData> playerList = new ArrayList<>();
                String currentPlayerId = Games.Players.getCurrentPlayerId(gameHelper.getApiClient());
                for (Participant participant : room.getParticipants()) {
                    String playerId = participant.getPlayer().getPlayerId();
                    PlayerData playerData = new PlayerData(playerId, participant.getParticipantId(), participant.getDisplayName());
                    if (Objects.equals(currentPlayerId, playerId)) {
                        playerData.isSelf = true;
                    }
                    Log.d(TAG,playerData.displayName);
                    playerList.add(playerData);
                }
                networkListener.onRoomReady(playerList);
                break;
            case Activity.RESULT_CANCELED:
                Log.d(TAG, "handleWaitingRoomResult: CANCEL");
                // TODO: 02-Apr-17 leave room
                break;
            case RESULT_LEFT_ROOM:
                Log.d(TAG, "handleWaitingRoomResult: RESULT_LEFT_ROOM");
                Games.RealTimeMultiplayer.leave(gameHelper.getApiClient(), this, room.getRoomId());
                break;
            case RESULT_INVALID_ROOM:
                // TODO: 02-Apr-17 handle invalid room
                Log.d(TAG, "handleWaitingRoomResult: INVALID");
                break;
        }
    }

    @Override
    public void rateGame() {
        String str = "Your PlayStore Link";
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void unlockAchievement() {

    }


    @Override
    public void submitScore(int highScore) {
//        if (isSignedIn() == true) {
//            Games.Leaderboards.submitScore(gameHelper.getApiClient(),
//                    getString(R.string.leaderboard_leaderboard), highScore);
//        }
    }

    @Override
    public void showAchievement() {
        if (isSignedIn() == true) {
            activity.startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), RC_ACHIEVEMENTS);

        } else {
            signIn();
        }
    }
    @Override
    public void showScore() {
        if (isSignedIn()) {
            activity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                    activity.getString(R.string.leaderboard_leaderboard)), RC_LEADERBOARD);
        } else {
            signIn();
        }
    }


    @Override
    public void setGameListener(IGameListener gameListener) {
        Log.d("AndroidNetworkService", "SETGAMELISTENER BLE KALT");
        this.gameListener = gameListener;
    }
    @Override
    public void setNetworkListener(INetworkListener networkListener) {
        Log.d("AndroidNetworkService", "SETNETWORKKLISTENER BLE KALT");
        this.networkListener = networkListener;
    }


    @Override
    public void startSelectOpponents(boolean autoMatch) {
        Intent intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(gameHelper.getApiClient(), MIN_PLAYERS, MAX_PLAYERS, autoMatch);
        activity.startActivityForResult(intent, RC_SELECT_PLAYERS);
        Log.d(TAG, "StartSelectOpponents finished");
    }

    @Override
    public void onInvitationReceived(Invitation invitation) {
        // We got an invitation to play a game! So, store it in
        // incomingInvitationId
        // and show the popup on the screen.
        Log.d(TAG, "onInvitationReceived: ");
        incomingInvitationId = invitation.getInvitationId();
        Toast.makeText(activity, invitation.getInviter().getDisplayName() + " has invited you. ", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onInvitationRemoved(String s) {

    }

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {

    }

    @Override
    public void onRoomConnecting(Room room) {
        Log.d(TAG, "onRoomConnecting: ");
    }

    @Override
    public void onRoomAutoMatching(Room room) {

    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> list) {

    }

    @Override
    public void onPeerDeclined(Room room, List<String> list) {

    }

    @Override
    public void onPeerJoined(Room room, List<String> list) {
        Log.d(TAG, "onPeerJoined: ");

    }

    @Override
    public void onPeerLeft(Room room, List<String> list) {

    }



    @Override
    public void onDisconnectedFromRoom(Room room) {

    }

    void stopKeepingScreenOn() {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onPeersConnected(Room room, List<String> list) {
        Log.d(TAG, "onPeersConnected: ");
    }
    @Override
    public void onP2PConnected(String s) {
        Log.d(TAG, "onP2PConnected: ");
    }
    @Override
    public void onJoinedRoom(int statusCode, Room room) {
        Log.d(TAG, "onJoinedRoom: ");
        switch (statusCode) {
            case STATUS_OK:
                currentRoomId = room.getRoomId();
                showWaitingRoom(room);
                break;
            case STATUS_CLIENT_RECONNECT_REQUIRED:
                signIn();
                break;
            default:
        }
    }


    @Override
    public void onConnectedToRoom(Room room) {
        Log.d(TAG, "onConnectedToRoom: ");
        stopKeepingScreenOn();
        if (currentRoomId == null) currentRoomId = room.getRoomId();
    }

    @Override
    public void onRoomConnected(int statusCode, Room room) {
        Log.d(TAG, "onRoomConnected(" + statusCode + ", " + room + ")");

        if (statusCode != STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomConnected, status " + statusCode);
            showGameError();
            return;
        }
    }

    @Override
    public void onRoomCreated(int statusCode, Room room) {
        Log.d(TAG, "onRoomCreated: ");
        switch (statusCode) {
            case STATUS_OK:
                currentRoomId = room.getRoomId();
                showWaitingRoom(room);
                break;
            case STATUS_CLIENT_RECONNECT_REQUIRED:
                signIn();
                break;
            default:
        }
    }


    // Show the waiting room UI to track the progress of other players as they enter the
    // room and get connected.
    private void showWaitingRoom(Room room) {
        Log.d(TAG, "showWaitingRoom: " + room);
        if (room == null) {
            Log.w(TAG, "showWaitingRoom: room is null, returning");
            return;
        }
        // minimum number of players required for our game
        // For simplicity, we require everyone to join the game before we start it
        // (this is signaled by Integer.MAX_VALUE).
        final int MIN_PLAYERS = Integer.MAX_VALUE;
        Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(gameHelper.getApiClient(), room, MIN_PLAYERS);
        // show waiting room UI
        activity.startActivityForResult(i, RC_WAITING_ROOM);
    }

    @Override
    public void onPeersDisconnected(Room room, List<String> list) {

    }



    @Override
    public void onP2PDisconnected(String s) {

    }





    @Override
    public void onLeftRoom(int i, String s) {

    }


    public void showGameError() {
        // TODO: 03-Apr-17 improve error message
        BaseGameUtils.makeSimpleDialog(activity, "ERROR");
    }





}

package com.sugarbeats.service;

/*Now that everything is set up, we are ready to implement play services. Since all
our game classes are inside the libGDX core project, we can’t directly call these
methods because these are Android methods. So we create an interface inside our
core project and implement this interface inside the Android Project. Makes sense ?

So inside the core Project, create a new interface and call it PlayServices. */

import com.sugarbeats.model.PlayerData;

import java.util.List;

public interface IPlayService {


        public void  onSignInSucceeded();


        public void signIn();

        public void signOut();

        public void rateGame();

        public void unlockAchievement();

        public void submitScore(int highScore);

        public void showAchievement();

        public void showScore();
        public void onSignInFailed();


        void setGameListener(IGameListener gameListener);


        public boolean isSignedIn();

        public void startSelectOpponents(boolean autoMatch);


        interface IGameListener {

                void onMultiplayerGameStarting();
        }
        void setNetworkListener(INetworkListener networkListener);

        interface INetworkListener {

                void onReliableMessageReceived(String senderParticipantId, int describeContents, byte[] messageData);

                void onUnreliableMessageReceived(String senderParticipantId, int describeContents, byte[] messageData);

                void onRoomReady(List<PlayerData> players);
        }
}

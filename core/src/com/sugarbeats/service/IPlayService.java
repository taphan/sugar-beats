package com.sugarbeats.service;

/*Now that everything is set up, we are ready to implement play services. Since all
our game classes are inside the libGDX core project, we can’t directly call these
methods because these are Android methods. So we create an interface inside our
core project and implement this interface inside the Android Project. Makes sense ?

So inside the core Project, create a new interface and call it PlayServices. */

public interface IPlayService {

        public void signIn();
        public void signOut();
        public void rateGame();
        public void unlockAchievement();
        public void submitScore(int highScore);
        public void showAchievement();
        public void showScore();
        public boolean isSignedIn();
        public void invitePlayers(boolean autoMatch);
    }

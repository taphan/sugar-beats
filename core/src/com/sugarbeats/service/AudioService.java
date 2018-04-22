package com.sugarbeats.service;

/**
 * Created by taphan on 11.04.2018.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioService {
    //Music
    public static Music menuMusic;
    public static Music gameMusic;
    public static Music victoryMusic;
    public static Music defeatMusic;

    //Sound effects
    public static Sound buttonPressSound;
    public static Sound sliderSound;
    public static Sound walkSound;
    public static Sound shootSound;
    public static Sound projectileSound;
    public static Sound impactSound;
    public static Sound damageSound;
    public static Sound deathSound;

    public static void load () {
        //Sound logic
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuSong.mp3"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gameSong.mp3"));
        victoryMusic = Gdx.audio.newMusic(Gdx.files.internal("victorySong.mp3"));
        defeatMusic = Gdx.audio.newMusic(Gdx.files.internal("defeatSong.mp3"));
        menuMusic.setLooping(true);
        gameMusic.setLooping(true);
        victoryMusic.setLooping(true);
        defeatMusic.setLooping(true);
        menuMusic.setVolume(0.5f);
        gameMusic.setVolume(0.5f);
        victoryMusic.setVolume(0.5f);
        defeatMusic.setVolume(0.5f);

        buttonPressSound = Gdx.audio.newSound(Gdx.files.internal("btnSound.mp3"));
        sliderSound = Gdx.audio.newSound(Gdx.files.internal("sliderSound.wav"));
        walkSound = Gdx.audio.newSound(Gdx.files.internal("walkSound.wav"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("throwSound.mp3"));
        projectileSound = Gdx.audio.newSound(Gdx.files.internal("projectileSound.wav"));
        impactSound = Gdx.audio.newSound(Gdx.files.internal("impactSound.wav"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("damageSound.wav"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("deathSound.wav"));
    }

    public static void playSound (Sound sound) {
        //if (Settings.soundEnabled)
        sound.play(1);
    }

    public static void playMusic (Music music) {
        //if (Settings.soundEnabled)
        menuMusic.stop();
        gameMusic.stop();
        victoryMusic.stop();
        defeatMusic.stop();
        music.play();
    }
}

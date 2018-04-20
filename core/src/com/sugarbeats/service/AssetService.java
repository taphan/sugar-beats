package com.sugarbeats.service;

/**
 * Created by taphan on 11.04.2018.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sugarbeats.SugarBeats;

import org.w3c.dom.css.Rect;

import java.awt.Rectangle;

public class AssetService {
    //Size of the screen (Should these be public or private?)
    public static final int WIDTH = SugarBeats.WIDTH;
    public static final int HEIGHT = SugarBeats.HEIGHT;

    //Backgrounds for menu logic
    public static Texture mainMenu;
    public static Texture settingsMenu;
    public static Texture pauseMenu;
    public static Texture helpMenu;

    //Buttons for menu, with hitboxes
    public static Texture playBtn;
    public static Texture settingBtn;
    public static Texture helpBtn;
    public static Texture hsBtn;
    public static Texture acBtn;
    public static Texture mBtn;

    //TODO: Add logic for music/soundeffects on/off? Good for usability
    //TODO: Add a 'back'-button?


    //Backgrounds within the game
    public static TextureRegion background1;
    public static TextureRegion background2;
    public static TextureRegion background3;

    //Map within the game
    public static TextureRegion map1;
    public static TextureRegion map2;
    public static TextureRegion map3;

    //Characters
    //public static TextureRegion character1;
    public static Animation<TextureRegion> character2;
    public static Animation<TextureRegion> character1;
    public static Animation<TextureRegion> bullet;

    //Buttons within the game, with hitboxes
    public static Texture rightBtn;
    public static Texture leftBtn;
    public static Texture upBtn;
    public static Texture downBtn;
    public static Texture powerUp1Btn;
    public static Texture powerUp2Btn;
    public static Texture powerBar;
    public static Texture powerBarBtn;
    public static Texture fireBtn;
    public static Texture pauseBtn;

    public static Rectangle rightBounds;
    public static Rectangle leftBounds;
    public static Rectangle downBounds;
    public static Rectangle powerUp1Bounds;
    public static Rectangle powerUp2Bounds; //These may not get implemented
    public static Rectangle powerBarBounds;
    public static Rectangle powerBarBtnBounds;
    public static Rectangle fireBounds;
    public static Rectangle pauseBounds;

    //Animations

    public static Animation walkAnim1;
    public static Animation shootAnim1;
    public static Animation projectileAnim1;
    //public static Animation explosionAnim1;
    public static Animation getHitAnim1;
    public static Animation deathAnim1;

    public static Animation walkAnim2;
    public static Animation shootAnim2;
    public static Animation projectileAnim2;
    public static Animation getHitAnim2;
    public static Animation deathAnim2;

    //public static Animation walkAnim3;
    //public static Animation shootAnim3;
    //public static Animation projectileAnim3;
    //public static Animation getHitAnim3;
    //public static Animation deathAnim3;



    public static Texture loadTexture (String file) { //For loading multiple textures for text regions
        return new Texture(Gdx.files.internal(file));
    }

    public static void load () {
        //Graphics logic
        mainMenu = new Texture("main_menu1.png");

        settingsMenu = new Texture("main_menu1.png");
        pauseMenu = new Texture("main_menu1.png");
        helpMenu = new Texture("help_menu1.png");

        playBtn = new Texture("button_play1.png");
        settingBtn = new Texture("button_settings1.png");
        helpBtn = new Texture("button_help1.png");
        hsBtn = new Texture("button_highscore1.png");
        acBtn = new Texture("button_achievements1.png");
        mBtn = new Texture("button_multiplayer1.png");

        map1 = new TextureRegion(loadTexture("ground2.png")); //Chocolate
        map2 = new TextureRegion(loadTexture("ground3.png")); //Cotton candy
        map3 = new TextureRegion(loadTexture("ground4.png")); //Ice cream
        background1 = new TextureRegion(loadTexture("map4.png")); //Chocolate
        background2 = new TextureRegion(loadTexture("map5.png")); //Cotton candy
        background3 = new TextureRegion(loadTexture("map2.png")); //Ice cream

        //TODO: add graphics and bounds for gameplay buttons
        upBtn = new Texture("button_up.png");
        downBtn = new Texture("button_up.png");
        leftBtn = new Texture("button_up.png");
        rightBtn = new Texture("button_up.png");
        //These can be rotated when drawn: https://stackoverflow.com/questions/24748350/libgdx-rotate-a-texture-when-drawing-it-with-spritebatch
        fireBtn = new Texture("button_shoot.png");
        powerBar = new Texture("slider.png");
        powerBarBtn = new Texture("button_slider.png");
        pauseBtn = new Texture("bullet.png"); //TODO: are we sure we want a pause functionality?


        //character2 = new TextureRegion(new Texture("heliregion.png"),0,0,162,65);
        //character1 = new Animation<TextureRegion>(0.2f,new TextureRegion(wl,0,65),new TextureRegion(heliLoad,162,65));
        //character2 = new TextureRegion(walk, 1500, 0, 500, 500);
        /*
        walk = loadTexture("char1ani.png");
        character2 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(walk, 0, 0, 500, 500),
                new TextureRegion(walk, 500, 0, 500, 500),
                new TextureRegion(walk, 1000, 0, 500, 500),
                new TextureRegion(walk, 1500, 0, 500, 500));
                */

        Texture walk = loadTexture("projectile1_anim.png");
        character2 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(walk, 0, 0, 200, 200),
                new TextureRegion(walk, 200, 0, 200, 200),
                new TextureRegion(walk, 400, 0, 200, 200),
                new TextureRegion(walk, 600, 0, 200, 200),
                new TextureRegion(walk, 800, 0, 200, 200),
                new TextureRegion(walk, 1000, 0, 200, 200),
                new TextureRegion(walk, 1200, 0, 200, 200),
                new TextureRegion(walk, 1400, 0, 200, 200));


        Texture bullet = loadTexture("bullet.png");

        //Animation logic
        //TODO: use the link below on how to make animations
        // https://github.com/saltares/ashley-superjumper/blob/master/core/src/com/siondream/superjumper/Assets.java
        //We might have to do it differently from them though, I suspect these animations run as an infinite loop regardless of user input

       character2.setPlayMode(Animation.PlayMode.LOOP);
    }
}
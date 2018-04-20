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

import javax.xml.soap.Text;


public class AssetService {
    //Size of the screen (Should these be public or private?)
    public static final int WIDTH = SugarBeats.WIDTH;
    public static final int HEIGHT = SugarBeats.HEIGHT;

    //Backgrounds for menu logic
    public static Texture mainMenu;
    public static Texture settingsMenu;
    public static Texture pauseMenu;
    public static Texture helpMenu;

    //Title for setting and helo
    public static Texture title_Setting;
    public static Texture title_Help;

    //Buttons for menu, with hitboxes
    public static Texture playBtn;
    public static Texture settingBtn;
    public static Texture helpBtn;
    public static Texture hsBtn;
    public static Texture acBtn;
    public static Texture mBtn;

    //TODO: Add logic for music/soundeffects on/off? Good for usability
    //TODO: Add a 'back'-button?
    public static Texture unmute_btn;
    public static Texture mute_btn;


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
    public static Texture walk1;
    public static Texture projectile1;
    public static Texture shoot1;
    public static Texture getHit1;
    public static Texture death1;

    public static Texture walk2;
    public static Texture projectile2;
    public static Texture shoot2;
    public static Texture getHit2;
    public static Texture death2;


    public static Animation walkAnim1;
    public static Animation shootAnim1;
    public static Animation projectileAnim1;
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
        settingsMenu = new Texture("settingsMenu.png"); //TODO: make more menus?

        pauseMenu = new Texture("main_menu1.png");
        helpMenu = new Texture("help_menu1.png");

        title_Setting = new Texture("title_Setting.png");
        title_Help = new Texture("title_help.png");

        unmute_btn = new Texture("unmute.png");
        mute_btn = new Texture("mute.png");


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

        //Animation logic
        //TODO: use the link below on how to make animations
        // https://github.com/saltares/ashley-superjumper/blob/master/core/src/com/siondream/superjumper/Assets.java
        //We might have to do it differently from them though, I suspect these animations run as an infinite loop regardless of user input

        walk1 = loadTexture("sprite1_walkanim.png");
        shoot1 = loadTexture("sprite1_throwanim.png");
        projectile1 = loadTexture("projectile1_anim.png");
        getHit1 = loadTexture("sprite1_hitanim.png");
        death1 = loadTexture("sprite1_deathanim.png");

        walk2 = loadTexture("sprite2_walkanim.png");
        shoot2 = loadTexture("sprite2_throwanim.png");
        projectile2 = loadTexture("projectile2_anim.png");
        getHit2 = loadTexture("sprite2_hitanim.png");
        death2 = loadTexture("sprite2_deathanim.png");

        // Animation for character 1
        character1 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(walk1, 0, 0, 500, 500));

        walkAnim1 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(walk1, 0, 0, 500, 500),
                new TextureRegion(walk1, 500, 0, 500, 500),
                new TextureRegion(walk1, 1000, 0, 500, 500),
                new TextureRegion(walk1, 1500, 0, 500, 500));

        shootAnim1 =  new Animation<TextureRegion>(0.2f,
                new TextureRegion(shoot1, 0, 0, 500, 500),
                new TextureRegion(shoot1, 500, 0, 500, 500),
                new TextureRegion(shoot1, 1000, 0, 500, 500),
                new TextureRegion(shoot1, 1500, 0, 500, 500),
                new TextureRegion(shoot1, 2000, 0, 500, 500),
                new TextureRegion(shoot1, 2500, 0, 500, 500),
                new TextureRegion(shoot1, 3000, 0, 500, 500),
                new TextureRegion(shoot1, 3500, 0, 500, 500),
                new TextureRegion(shoot1, 4000, 0, 500, 500),
                new TextureRegion(shoot1, 4500, 0, 500, 500),
                new TextureRegion(shoot1, 5000, 0, 500, 500),
                new TextureRegion(shoot1, 5500, 0, 500, 500));

        projectileAnim1 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(projectile1, 0, 0, 200, 200),
                new TextureRegion(projectile1, 200, 0, 200, 200),
                new TextureRegion(projectile1, 400, 0, 200, 200),
                new TextureRegion(projectile1, 600, 0, 200, 200),
                new TextureRegion(projectile1, 800, 0, 200, 200),
                new TextureRegion(projectile1, 1000, 0, 200, 200),
                new TextureRegion(projectile1, 1200, 0, 200, 200),
                new TextureRegion(projectile1, 1400, 0, 200, 200));

        getHitAnim1 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(getHit1, 0, 0, 500, 500),
                new TextureRegion(getHit1, 500, 0, 500, 500),
                new TextureRegion(getHit1, 1000, 0, 500, 500),
                new TextureRegion(getHit1, 1500, 0, 500, 500));

        deathAnim1 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(death1, 0, 0, 500, 500),
                new TextureRegion(death1, 500, 0, 500, 500),
                new TextureRegion(death1, 1000, 0, 500, 500),
                new TextureRegion(death1, 1500, 0, 500, 500),
                new TextureRegion(death1, 2000, 0, 500, 500),
                new TextureRegion(death1, 2500, 0, 500, 500),
                new TextureRegion(death1, 3000, 0, 500, 500),
                new TextureRegion(death1, 3500, 0, 500, 500),
                new TextureRegion(death1, 4000, 0, 500, 500),
                new TextureRegion(death1, 4500, 0, 500, 500));

        // Animation for character 2
        walkAnim2 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(walk2, 0, 0, 500, 500),
                new TextureRegion(walk2, 500, 0, 500, 500),
                new TextureRegion(walk2, 1000, 0, 500, 500),
                new TextureRegion(walk2, 1500, 0, 500, 500));

        shootAnim2 =  new Animation<TextureRegion>(0.2f,
                new TextureRegion(shoot2, 0, 0, 500, 500),
                new TextureRegion(shoot2, 500, 0, 500, 500),
                new TextureRegion(shoot2, 1000, 0, 500, 500),
                new TextureRegion(shoot2, 1500, 0, 500, 500),
                new TextureRegion(shoot2, 2000, 0, 500, 500),
                new TextureRegion(shoot2, 2500, 0, 500, 500),
                new TextureRegion(shoot2, 3000, 0, 500, 500),
                new TextureRegion(shoot2, 3500, 0, 500, 500),
                new TextureRegion(shoot2, 4000, 0, 500, 500),
                new TextureRegion(shoot2, 4500, 0, 500, 500),
                new TextureRegion(shoot2, 5000, 0, 500, 500),
                new TextureRegion(shoot2, 5500, 0, 500, 500));

        projectileAnim2 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(projectile2, 0, 0, 200, 200),
                new TextureRegion(projectile2, 200, 0, 200, 200),
                new TextureRegion(projectile2, 400, 0, 200, 200),
                new TextureRegion(projectile2, 600, 0, 200, 200),
                new TextureRegion(projectile2, 800, 0, 200, 200),
                new TextureRegion(projectile2, 1000, 0, 200, 200),
                new TextureRegion(projectile2, 1200, 0, 200, 200),
                new TextureRegion(projectile2, 1400, 0, 200, 200));

        getHitAnim2 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(getHit2, 0, 0, 500, 500),
                new TextureRegion(getHit2, 500, 0, 500, 500),
                new TextureRegion(getHit2, 1000, 0, 500, 500),
                new TextureRegion(getHit2, 1500, 0, 500, 500));

        deathAnim2 = new Animation<TextureRegion>(0.2f,
                new TextureRegion(death2, 0, 0, 500, 500),
                new TextureRegion(death2, 500, 0, 500, 500),
                new TextureRegion(death2, 1000, 0, 500, 500),
                new TextureRegion(death2, 1500, 0, 500, 500),
                new TextureRegion(death2, 2000, 0, 500, 500),
                new TextureRegion(death2, 2500, 0, 500, 500),
                new TextureRegion(death2, 3000, 0, 500, 500),
                new TextureRegion(death2, 3500, 0, 500, 500),
                new TextureRegion(death2, 4000, 0, 500, 500),
                new TextureRegion(death2, 4500, 0, 500, 500));


        //TODO: add graphics and bounds for gameplay buttons
        fireBtn = new Texture("button_shoot.png");
        leftBtn = new Texture("back_button.png");
        rightBtn = new Texture("forward_button.png");
        Texture bullet = loadTexture("bullet.png");


        walkAnim1.setPlayMode(Animation.PlayMode.LOOP);
        projectileAnim1.setPlayMode(Animation.PlayMode.LOOP);

        walkAnim2.setPlayMode(Animation.PlayMode.LOOP);
        projectileAnim2.setPlayMode(Animation.PlayMode.LOOP);

    }
}
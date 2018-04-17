package com.sugarbeats.game.entity.system;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sugarbeats.game.entity.component.ProjectileEquationComponent;

/**
 * Created by Kv1402 on 17.04.2018.
 */

public class TrajectorySystem extends Actor {
    public static class Controller  {

        public float power = 50f;
        public float angle = 0f;

    }

    private Controller controller;
    private ProjectileEquationComponent projectileEquation;
    private Sprite trajectorySprite;

    public int trajectoryPointCount = 30;
    public float timeSeparation = 1f;

    public TrajectorySystem(Controller controller, float gravity, Sprite trajectorySprite) {
           this.controller = controller;
           this.trajectorySprite = trajectorySprite;
           this.projectileEquation = new ProjectileEquationComponent();
           this.projectileEquation.gravity = gravity;
    }

        @Override
    public void act(float delta) {
           super.act(delta);
           projectileEquation.startVelocity.set(controller.power, 0f);
           projectileEquation.startVelocity.rotate(controller.angle);
    }


    public void draw(SpriteBatch batch, float parentAlpha) {
           float t = 0f;
           float width = this.getWidth();
           float height = this.getHeight();

           float timeSeparation = this.timeSeparation;

           for (int i = 0; i < trajectoryPointCount; i++) {
                float x = this.getX() + projectileEquation.getX(t);
                float y = this.getY() + projectileEquation.getY(t);

               // batch.setColor(this.color);
                batch.draw(trajectorySprite, x, y, width, height);

                t += timeSeparation;
            }
        }


        public Actor hit(float x, float y) {
            return null;
        }

    }



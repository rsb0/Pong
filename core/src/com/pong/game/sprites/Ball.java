package com.pong.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {

    //balls speed
    private static final int SPEED = 300;

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private Texture ballTexture;
    private Vector2 position;
    private boolean upHeading = true;
    private boolean rightHeading = true;
    private Rectangle bounds;   //Rectangle class used to detect collision with paddles

    /*int values used to keep track of how many times the ball has crossed the left or right edge
    of screen*/
    private int leftScore;
    private int rightScore;


    public Ball(int width, int height){
        ballTexture = new Texture("ball.png");
        position = new Vector2(width / 2, height / 2);
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
        bounds = new Rectangle(position.x, position.y, ballTexture.getWidth(),ballTexture.getHeight());
        rightScore = 0;
        leftScore = 0;
    }


    public void upDate(float dt){

        //If the ball is heading up, add to the y-coordinate of the balls position
        if(upHeading){
            position.add(0, SPEED * dt);
            //Check if outside top edge of screen, and if so, change heading
            if(position.y >= SCREEN_HEIGHT - ballTexture.getHeight()){
                position.y = SCREEN_HEIGHT - ballTexture.getHeight();
                upHeading = false;
            }
        }
        //If the ball is heading down, subtract from the y-coordinate of the balls position
        else{
            position.sub(0, SPEED * dt);
            //Check if outside bottom edge of screen, and if so, change heading:
            if(position.y <= 0){
                position.y = 0;
                upHeading = true;
            }
        }
        //If the ball is heading right, add to the x-coordinate of the balls position
        if(rightHeading){
            position.add(SPEED * dt, 0);
            //Check if outside right edge of screen
            if(position.x >= SCREEN_WIDTH - ballTexture.getWidth()){
                position.set(300, 300);     //reset ball to center of screen
                leftScore += 1;             //add 1 to left side's score
                upHeading = true;
            }
        }
        //If the ball is heading left, subtract from the x-coordinate of the balls position
        else{
            position.sub(SPEED * dt, 0);
            //check if outside left edge of screen
            if(position.x <= 0){
                position.set(300, 300);     //reset ball to center of screen
                rightScore += 1;            //add 1 to right side's score
                rightHeading = true;
                upHeading = true;
            }
        }

        //set Rectangle to balls current position
        bounds.setPosition(position.x, position.y);

    }

    /*check for collision with right or left paddle. If collision is detected, change heading
    in x-direction*/
    public void collision(Rectangle rightPaddleBounds, Rectangle leftPaddleBounds){
        if(bounds.overlaps(rightPaddleBounds)){
            position.set(position.x - 10, position.y);
            rightHeading = !rightHeading;
        }
        if(bounds.overlaps(leftPaddleBounds)){
            position.set(position.x + 10, position.y);
            rightHeading = !rightHeading;
        }
    }

    public int getLeftScore(){ return leftScore; }

    public int getRightScore(){ return rightScore; }

    public Texture getBallTexture(){ return ballTexture; }

    public Vector2 getPosition(){ return position; }

    public void dispose(){ ballTexture.dispose();}
}

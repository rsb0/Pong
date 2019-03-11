package com.pong.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class LeftPaddle {
    private Texture paddleTexture;
    private Vector2 position;

    //speed at which the paddle will move up or down. Slightly less than the ball's speed
    private static final int SPEED = 200;
    private int SCREEN_HEIGHT;
    private Rectangle bounds;   //Rectangle class used to detect collision with ball
    private com.pong.game.sprites.Ball ball;


    public LeftPaddle(int screenHeight, com.pong.game.sprites.Ball ball){
        paddleTexture = new Texture("paddle.png");
        position = new Vector2(0, screenHeight / 2 - paddleTexture.getHeight() / 2);
        SCREEN_HEIGHT = screenHeight;
        bounds = new Rectangle(position.x, position.y, paddleTexture.getWidth(), paddleTexture.getHeight());
        this.ball = ball;
    }

    public void manualUpdate(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.add(0, SPEED * dt);
        }
        if(position.y + paddleTexture.getHeight() >= SCREEN_HEIGHT) {
            position.y = SCREEN_HEIGHT - paddleTexture.getHeight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.sub(0, SPEED * dt);
        }
        if(position.y <= 0){
            position.y = 0;
        }
        bounds.setY(position.y);
    }

    /*automatically plays the game by keeping track of the balls position and moving up or down
    to keep the centre of the paddle aligned with the current y-position of the ball*/
    public void autoUpdate(float dt){
        /*if the ball's y-coordinate greater than the y-coordinate of the center of the paddle,
        the paddle is moved up*/
        if(ball.getPosition().y < position.y + paddleTexture.getHeight() / 2){
            position.sub(0,SPEED * dt);
            if(position.y <= 0){
                position.y = 0;
            }
        }
        /*if the ball's y-coordinate less than the y-coordinate of the center of the paddle,
        the paddle is moved down*/
        if(ball.getPosition().y > position.y + paddleTexture.getHeight() / 2) {
            position.add(0, SPEED * dt);
            if(position.y >= SCREEN_HEIGHT - paddleTexture.getHeight()){
                position.y = SCREEN_HEIGHT - paddleTexture.getHeight();
            }
        }
        //reset the Rectangle bounds according to the new coordinates.
        bounds.setY(position.y);
    }

    public Texture getTexture(){
        return paddleTexture;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){ paddleTexture.dispose();}
}


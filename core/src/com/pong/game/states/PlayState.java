package com.pong.game.states;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.sprites.Ball;
import com.pong.game.sprites.LeftPaddle;
import com.pong.game.sprites.RightPaddle;

public class PlayState extends State {
    private RightPaddle rightPaddle;
    private LeftPaddle leftPaddle;
    private Ball ball;
    private BitmapFont scoreOutput;
    private Texture medianDevider;

    public PlayState(com.pong.game.states.GameStateManager gsm){
        super(gsm);
        ball = new Ball(600,620);
        rightPaddle = new RightPaddle(600,620,ball);
        leftPaddle = new LeftPaddle(620, ball);
        scoreOutput = new BitmapFont();
        medianDevider = new Texture("Drawing.png");
    }

    @Override
    public void upDate(float dt) {
        ball.upDate(dt);

        //Test to see if either side has a winning score
        if(ball.getLeftScore() == 21 || ball.getRightScore() == 21){
            //pop current state and push gameOver-state on top of GameStateManager's state-stack
            gsm.set(new com.pong.game.states.EndState(gsm, ball.getRightScore(), ball.getLeftScore()));
        }
        //update paddles and ball
        rightPaddle.manualUpdate(dt);
        leftPaddle.autoUpdate(dt);
        ball.collision(rightPaddle.getBounds(), leftPaddle.getBounds());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        //Draw vertical line in middle of screen
        sb.draw(medianDevider,290,0,10, 575);

        //draw current score
        scoreOutput.draw(sb,
                "       score:\n" + ball.getLeftScore() + "                   " + ball.getRightScore(),
                250,590);

        //draw ball and paddles
        sb.draw(ball.getBallTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(rightPaddle.getTexture(), rightPaddle.getPosition().x, rightPaddle.getPosition().y);
        sb.draw(leftPaddle.getTexture(), leftPaddle.getPosition().x, leftPaddle.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        medianDevider.dispose();
        ball.dispose();
        leftPaddle.dispose();
        rightPaddle.dispose();
    }
}

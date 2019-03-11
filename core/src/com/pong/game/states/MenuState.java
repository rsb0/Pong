package com.pong.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{
    private Texture startScreen;
    private BitmapFont font;

    public MenuState(com.pong.game.states.GameStateManager gsm){
        super(gsm);
        startScreen = new Texture("MenuScreen.png");
        font = new BitmapFont();
    }

    //check for input, pop current state and push a PlayState to top of GameStateManager's state-stack
    public void upDate(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            gsm.set(new PlayState(gsm));
        }
    }
    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(startScreen, 0,0, 600, 620);
        font.draw(sb, "Press ENTER to start", 225,250);
        sb.end();
    }
    public void dispose(){
        startScreen.dispose();
    }
}

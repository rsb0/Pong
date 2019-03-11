package com.pong.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//abstract class the different states of the game will extend
public abstract class State {
    protected com.pong.game.states.GameStateManager gsm;
    public State(com.pong.game.states.GameStateManager gsm){
        this.gsm = gsm;
    }
    public abstract void upDate(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}

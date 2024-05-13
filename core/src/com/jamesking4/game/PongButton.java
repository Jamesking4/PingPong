package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_WIDTH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class PongButton {
    BitmapFont font;
    String text;
    float x, y;
    float size;
    private float width, height;
    private boolean isCentered;

    public PongButton(String text, float x, float y, float size, BitmapFont font) {
        this.font = font;
        this.text = text;
        this.size = size;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
        height = size;
        this.x = x;
        this.y = y;
    }

    public PongButton(String text, float y, float size, BitmapFont font) {
        this.font = font;
        this.text = text;
        this.size = size;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
        height = size;
        this.x = SCR_WIDTH/2-width/2;
        this.y = y;
        isCentered = true;
    }

    void setText(String text) {
        this.text = text;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
        if(isCentered){
            this.x = SCR_WIDTH/2-width/2;
        }
    }

    boolean hit(float tx, float ty) {
        return x<tx & tx<x+width & y>ty & ty>y-height;
    }
}


package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Target {
  private Texture texture;
  private float x;
  private float y;
  private float scale;
  private static int TARGET_SIZE = 32;
  private boolean active;

  public Target() {
    this.texture = new Texture("block.png");
    this.x = (float) (Math.random() * Gdx.graphics.getWidth());
    this.y = (float) (Math.random() * Gdx.graphics.getHeight());
    checkBorber();
    this.scale = 1.0f;
    this.active = true;
  }

  public boolean isActive() {
    return active;
  }

  public void deactivate() {
    active = false;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  private void checkBorber() {
    if (x < TARGET_SIZE) x = TARGET_SIZE;
    if (x > Gdx.graphics.getWidth() - TARGET_SIZE) x = Gdx.graphics.getWidth() - TARGET_SIZE;
    if (y < TARGET_SIZE) y = TARGET_SIZE;
    if (y > Gdx.graphics.getHeight() - TARGET_SIZE) y = Gdx.graphics.getHeight() - TARGET_SIZE;
  }

  public void render(SpriteBatch batch) {
    batch.draw(texture, x - TARGET_SIZE /2, y - TARGET_SIZE /2);
  }

  public void dispose() {
    texture.dispose();
  }
}

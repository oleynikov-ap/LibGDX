package ru.geekbrains.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private TextureRegion texture;
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;
    private int mode;
    private float deltaTime;
    private float TIME_OUT = 1;

    public boolean isActive() {
        return active;
    }

    public Projectile(TextureRegion texture) {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.texture = texture;
        this.active = false;
        this.mode = 0;
    }

    public void deactivate() {
        active = false;
    }

    public void activate(float x, float y, float vx, float vy, int mode) {
        if (mode == 1) active = true;
        position.set(x, y);
        velocity.set(vx, vy);
        this.mode = mode;
        this.deltaTime = TIME_OUT;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0 || position.x > 1280 || position.y < 0 || position.y > 720) {
            deactivate();
            setMode(0);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 8, position.y - 8);
    }
    public int getMode() {
        return mode;
    }
    public float getDeltaTime() {
        return deltaTime;
    }
    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}

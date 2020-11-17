package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Tank {
    private Texture texture;
    private Texture textureWeapon;
    private float x;
    private float y;
    private float speed;
    private float angle;
    private float angleWeapon;
    private Projectile projectile;
    private float scale;
    private static int TANK_SIZE = 40;

    public Tank(Projectile projectile) {
        this.texture = new Texture("tank.png");
        this.textureWeapon = new Texture("weapon.png");
        this.projectile = projectile;
        this.x = 100.0f;
        this.y = 100.0f;
        this.speed = 240.0f;
        this.scale = 3.0f;
    }

    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            x += speed * MathUtils.cosDeg(angle) * dt;
            y += speed * MathUtils.sinDeg(angle) * dt;
            checkBorber();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            x -= speed * MathUtils.cosDeg(angle) * dt * 0.2f;
            y -= speed * MathUtils.sinDeg(angle) * dt * 0.2f;
            checkBorber();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            angleWeapon -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            angleWeapon += 90.0f * dt;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !projectile.isActive()) {
            projectile.shoot(x + 16 * scale * MathUtils.cosDeg(angle + angleWeapon), y + 16* scale * MathUtils.sinDeg(angle + angleWeapon), angle + angleWeapon);
        }
        if (projectile.isActive()) {
            projectile.update(dt);
        }
    }

    private void checkBorber() {
        if (x < TANK_SIZE) x = TANK_SIZE;
        if (x > Gdx.graphics.getWidth() - TANK_SIZE) x = Gdx.graphics.getWidth() - TANK_SIZE;
        if (y < TANK_SIZE) y = TANK_SIZE;
        if (y > Gdx.graphics.getHeight() - TANK_SIZE) y = Gdx.graphics.getHeight() - TANK_SIZE;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - TANK_SIZE/2, y - TANK_SIZE/2, TANK_SIZE/2, TANK_SIZE/2, TANK_SIZE, TANK_SIZE, scale, scale, angle, 0, 0, TANK_SIZE, TANK_SIZE, false, false);
        batch.draw(textureWeapon, x - TANK_SIZE/2, y - TANK_SIZE/2, TANK_SIZE/2, TANK_SIZE/2, TANK_SIZE, TANK_SIZE, scale, scale, angle + angleWeapon, 0, 0, TANK_SIZE, TANK_SIZE, false, false);
        if (projectile.isActive()) {
            projectile.render(batch);
        }
    }

    public void dispose() {
        texture.dispose();
        projectile.dispose();
    }
}

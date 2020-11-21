package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ProjectileController {
    private static final int MAX_PROJECTILES = 100;
    private Projectile[] items;
    private int mode;
    private int TIME_OUT = 1;
    private Hero hero;

    public ProjectileController(TextureAtlas atlas) {
        this.items = new Projectile[MAX_PROJECTILES];
        TextureRegion region = atlas.findRegion("projectile");
        for (int i = 0; i < items.length; i++) {
            items[i] = new Projectile(region);
        }
        this.mode = 1;
    }

    public void activate() {
        int j = this.mode;
        for (Projectile p : items) {
            if (p.getMode() == 0) {
                p.activate(hero.position.x * 40 + 20, hero.position.y * 40 + 20, hero.velocity.x, hero.velocity.y, j--);
                if (j == 0) return;
            }
        }
    }

    public void update(float dt) {
        for (Projectile p : items) {
            int j = p.getMode();
            if (j == 1) {
                p.update(dt);
            }
            else {
                if (j > 1) {
                    p.setDeltaTime(p.getDeltaTime() - dt);
                    if (p.getDeltaTime() < 0){
                            p.activate(hero.position.x * 40 + 20, hero.position.y * 40 + 20, hero.velocity.x, hero.velocity.y, --j);
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            incMode();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            decMode();
        }
    }

    public void render(SpriteBatch batch) {
        for (Projectile p : items) {
            if (p.getMode() == 1) {
                p.render(batch);
            }
        }
    }
    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void incMode() {
        this.mode++;
    }

    public void decMode() {
        if (this.mode > 1) this.mode--;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
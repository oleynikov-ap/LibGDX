package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.security.Key;

public class Hero {
    private ProjectileController projectileController;
    public Vector2 position;
    public Vector2 velocity;
    private TextureRegion texture;

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(0, 0);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.velocity = new Vector2(200,0);
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            projectileController.activate();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (this.position.y < 19) this.position.y++;
            this.velocity.x = 0;
            this.velocity.y = 200;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (this.position.y > 0) this.position.y--;
            this.velocity.x = 0;
            this.velocity.y = -200;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (this.position.x < 19) this.position.x++;
            this.velocity.x = 200;
            this.velocity.y = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (this.position.x > 0) this.position.x--;
            this.velocity.x = -200;
            this.velocity.y = 0;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x * 40, position.y * 40);
    }
}

package ru.geekbrains.dungeon.units;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.dungeon.GameController;

public class Monster extends Unit {
    private float aiBrainsImplseTime;
    private Unit target;
    private float distance;

    public Monster(TextureAtlas atlas, GameController gc) {
        super(gc, 5, 2, 10);
        this.texture = atlas.findRegion("monster");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.targetX = cellX;
        this.targetY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
        this.target = gc.getUnitController().getHero();
        this.distance = 5.0f;
    }

    public void update(float dt) {
        super.update(dt);
        if (canIMakeAction()) {
            if (isStayStill()) {
                aiBrainsImplseTime += dt;
            }
            if (aiBrainsImplseTime > 0.4f) {
                aiBrainsImplseTime = 0.0f;
                if (canIAttackThisTarget(target)) {
                    attack(target);
                } else {
                    tryToMove();
                }
            }
        }
    }

    public float calcDistance(int targetCellX, int targetCellY, int attacerCellX, int attacerCellY){
        return (float) Math.sqrt((targetCellX - attacerCellX) * (targetCellX - attacerCellX) + (targetCellY - attacerCellY) * (targetCellY - attacerCellY));
    }

    public void tryToMove() {
        int targetCellX = target.getCellX();
        int targetCellY = target.getCellY();
        int bestX = -1, bestY = -1;
        if (calcDistance(cellX, cellY, targetCellX, targetCellY) <= distance) {
            float bestDst = 10000;
            for (int i = cellX - 1; i <= cellX + 1; i++) {
                for (int j = cellY - 1; j <= cellY + 1; j++) {
                    if (Math.abs(cellX - i) + Math.abs(cellY - j) == 1 && gc.getGameMap().isCellPassable(i, j) && gc.getUnitController().isCellFree(i, j)) {
                        float dst = calcDistance(i, j, target.getCellX(), target.getCellY());
                        if (dst < bestDst) {
                            bestDst = dst;
                            bestX = i;
                            bestY = j;
                        }
                    }
                }
            }
        }
        else {
            while (bestX == -1 && bestY == -1) {
                float move = (float) Math.random();
                if (move >= 0.25f && gc.getGameMap().isCellPassable(cellX - 1, cellY) && gc.getUnitController().isCellFree(cellX - 1, cellY)) {
                    bestX = cellX - 1;
                    bestY = cellY;
                } else if (move >= 0.5f && gc.getGameMap().isCellPassable(cellX + 1, cellY) && gc.getUnitController().isCellFree(cellX + 1, cellY)) {
                    bestX = cellX + 1;
                    bestY = cellY;
                } else if (move >= 0.75f && gc.getGameMap().isCellPassable(cellX, cellY - 1) && gc.getUnitController().isCellFree(cellX, cellY - 1)) {
                    bestX = cellX;
                    bestY = cellY - 1;
                } else if (gc.getGameMap().isCellPassable(cellX, cellY + 1) && gc.getUnitController().isCellFree(cellX, cellY + 1)) {
                    bestX = cellX;
                    bestY = cellY + 1;
                }

            }
        }
        goTo(bestX, bestY);
    }
}

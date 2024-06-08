package com.mygdx.game.Strength;
import com.mygdx.game.BaseUnit;
import java.util.ArrayList;

public class Strength extends BaseUnit {
    protected int strength;
    protected int stamina;
    protected int maxStamina;
    public boolean flag;

    public Strength(String name, int HP, int maxHP, int attack, int attackRange, int defense, int initiative,
                    int level, int experience, int speed, int x, int y) {
        super(name, HP, maxHP, attack, attackRange, defense, initiative, level, experience, speed, x, y);
        this.strength = strength;
        this.maxStamina = this.stamina = stamina;
    }

    @Override
    public void Healing(BaseUnit target) {
    }
    @Override
    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend) {
    }
    @Override
    public boolean isDead() {
        return super.isDead();
    }
}

package com.mygdx.game;
import java.util.List;


public abstract class BaseUnit implements MyInterface {
    protected String name;
    public double HP;
    protected double maxHP;
    protected int attack;
    protected int attackRange;
    protected int defend;
    protected int initiative;
    protected int level;
    protected int experience;
    public Position position;
    public static int speed;
    protected String ClassName;
    public List deadlist;

    public BaseUnit(String name, double HP, double maxHP, int attack, int attackRange, int defend,
                int initiative, int level, int experience, int speed, int x, int y){
        this.name = name;
        this.maxHP = this.HP = HP;
        this.attack = attack;
        this.attackRange = attackRange;
        this.defend = defend;
        this.initiative = initiative;
        this.level = level;
        this.experience = experience;
        this.position = new Position(x, y);
        this.speed = speed;

    }

//    Определяем кто совершает ход
    public int getInitiative() {
        return initiative;
    }

    public double getHP() {
        return HP;
    }

    public double setHP(double HP) {
        this.HP = HP;
        return HP;
    }

    public String getName() { return name; }
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + name + ", \u2665: " + HP + ",  ⚔ : " + attack + ", \uD83D\uDEE1\uFE0F :" + defend;
    }

    /*    Метод получения урона
     *   @param damage урон
    */
    public void getDamage(double damage) {
        HP -= damage;
        if (HP < 0) {
            HP = 0;
        }
        if (HP >= maxHP) HP = maxHP;
    }

//    Метод нанесения урона
    public void hitEnemy (BaseUnit target) {
        if (target != null) {
            double damage = this.attack - target.defend;
            target.getDamage(damage);
        } else return;
    }

//    Лечить цель
    public void Healing (BaseUnit target) {
        int heal = this.attack;
        target.HP += heal;
        if (target.HP > target.maxHP) {
            target.HP = target.maxHP;
        }
    }
    public static int getSpeed() {
        return speed;
    }
//    Определяем состояние смерти

    public void death() {
        if (getHP() <= 0) {
            System.out.println("Персонаж умер...");
        }

    }
    public boolean isDead() {
        if (getHP() <= 0) {
            return true;
        }
        return false;
    }

/**
*   Метод атаки противника
*   @param target - list enemies
*   @return nearby Enemy
*/

    /**
     * Method searching nearby enemy
     * @param targets list of enemies
     * @return nearby enemy
    */
    public BaseUnit findNearestTarget(List<BaseUnit> targets) {
        if (targets == null || targets.isEmpty()) {
            throw new IllegalArgumentException("Enemies was not found!");
        }
        BaseUnit nearestTarget = null;
        double minDistanse = Double.MAX_VALUE;
        for (BaseUnit target : targets) {
            double distance = this.position.getDistance(target.position);
            if (distance < minDistanse) {
                minDistanse = distance;
                nearestTarget = target;
            }
        }
        return nearestTarget;
    }
//    public abstract void healing(BaseUnit target);
    public String getInfo() {
        return "";
    }
}


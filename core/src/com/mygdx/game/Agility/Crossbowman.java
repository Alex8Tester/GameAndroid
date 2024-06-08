package com.mygdx.game.Agility;
import com.mygdx.game.BaseUnit;
import java.util.ArrayList;
import com.mygdx.game.Strength.Peasant;

public class Crossbowman extends Agility {
    protected int bolt;
    protected int maxBolt;
    protected int accuracy;

    public Crossbowman(String name, int x, int y) {
        super(name, 45, 60, 100, 1, 7, 2, 1, 0, 20,5, x, y);
        this.maxBolt = this.bolt = 20;
        this.accuracy = 3;
    }
    @Override
    public String toString(){
        return super.toString() + ", \u27b6 : " + bolt;
    }

    public String getInfo() {
        return "А";
    }

    @Override
    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend) {
        if (getHP() <= 0 || bolt == 0) return;
        hitEnemy(findNearestTarget(enemy));
        bolt--;
            if (bolt < maxBolt){
                for (BaseUnit BaseUnit : friend) {
                    if (BaseUnit.getInfo().equals("Ф") && !((Peasant) BaseUnit).flag) {
                        ((Peasant) BaseUnit).flag = true;
                        bolt++;
                        return;
                }
            }
        }
    }
}
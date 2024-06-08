package com.mygdx.game.Intellect;
import com.mygdx.game.BaseUnit;
import java.util.ArrayList;


public class Magician extends Intellect {
    protected int mana;
    protected int maxMana;
    public Magician(String name, int x, int y) {
        super(name, 45, 60, 40, 5, 5, 1, 1, 15,45,4, x, y);
        this.maxMana = this.mana = 10;
        flag = false;
    }

    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend) {
        if ((getHP() <= 0) || (mana < 3)) {
            mana += 1;
        } else {
            flag = true;
            hitEnemy(findNearestTarget(enemy));
            mana -= 3;
        }
    }
    public String getInfo(){
        return "В";
    }
}

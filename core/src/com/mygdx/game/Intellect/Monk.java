package com.mygdx.game.Intellect;
import com.mygdx.game.BaseUnit;
import java.util.ArrayList;
import java.util.*;

public class Monk extends Intellect {
    protected int mana;
    protected int maxMana;

    public Monk(String name, int x, int y) {
        super(name, 40, 60, 25, 5, 3, 1, 1, 0, 20, 4, x, y);
        this.maxMana = this.mana = 10;
        flag = false;
    }
    public String getInfo() {
        return "М";
    }

    @Override
    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend) {
        if (getHP() <= 0) return;
        List<BaseUnit> sortList = new ArrayList<>(friend);
        List<BaseUnit> deadList = new ArrayList<>();
        sortList.sort((o1, o2) -> (int) o1.getHP() - (int) o2.getHP());

        Iterator<BaseUnit> iterator = sortList.iterator();
        while (iterator.hasNext()) {
            BaseUnit BaseUnit = iterator.next();
            if (BaseUnit.getHP() == 0) {
                deadList.add(BaseUnit);
                iterator.remove();
            }

        }
        if (deadList.size() > 3) {
            flag = true;
            System.out.println("Флаг установлен");
        }

        if (flag && mana == 10) {
            deadList.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());
            deadList.get(0).setHP(maxHP);
            mana = 0;
            System.out.println("Воскресил: " + deadList.get(0).getName());
            flag = false;
            return;
        }

        if (flag) {
            mana++;
            return;
        }

        if (mana < 2) {
            mana++;
            return;
        }

        Healing(sortList.get(0));
        mana -= 2;
    }
}
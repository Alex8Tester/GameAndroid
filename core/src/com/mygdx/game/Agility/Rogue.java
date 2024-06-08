package com.mygdx.game.Agility;
import com.mygdx.game.BaseUnit;
import com.mygdx.game.Position;
import java.util.ArrayList;

public class Rogue extends Agility {
    protected int venom;
    protected double stealth;
    public Rogue(String name, int x, int y) {
        super(name, 50, 60, 20, 1, 5, 2, 1, 0, 25,5, x, y);
        this.venom = venom;
        this.stealth = stealth;
    }
    public String getInfo(){
        return "Р";
    }

    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend) {
        if (HP<=0) return;
        if(findNearestTarget(enemy) == null){
            return;
        }

        BaseUnit target = super.findNearestTarget(enemy);
        if (position.getDistance(target.position) < 2){
            hitEnemy(target);
            return;
        }
        Position diff = position.getDiffPos(target.position);

        Position newposition = new Position(position.x, position.y);

        if (Math.abs(diff.x) > Math.abs(diff.y))

            newposition.x += diff.x < 0 ? 1 : -1;
        else
            newposition.y += diff.y < 0 ? 1 : -1;

        boolean flag = false;
        for (BaseUnit BaseUnit : friend) {
            flag = true;
            if (BaseUnit.position.equals(newposition) && BaseUnit.getHP() > 0) {
                flag = true;
                break;
            }
            if (flag) {
                this.position = newposition;
            }
        }
    }
}

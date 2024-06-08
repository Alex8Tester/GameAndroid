package com.mygdx.game.Strength;
import com.mygdx.game.BaseUnit;
import com.mygdx.game.Position;
import java.util.ArrayList;


public class Piciner extends Strength {
    protected int armor;
    public Piciner(String name, int x, int y) {
        super(name, 60, 75, 35, 3, 10, 3, 1, 0, 3, x, y);
    this.armor = 10;
    }
    public String getInfo(){
        return "П";
    }

    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend) {
        if (HP <= 0) return;
        if(findNearestTarget(enemy) == null){
            return;
        }
        BaseUnit target = super.findNearestTarget(enemy);
        if (position.getDistance(target.position) < 2) {
            hitEnemy(findNearestTarget(enemy));
            return;
        }
        Position diff = position.getDiffPos(target.position);
        Position newposition = new Position(position.getX(), position.getY());
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

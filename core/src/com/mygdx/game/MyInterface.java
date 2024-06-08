package com.mygdx.game;

import java.util.ArrayList;
import com.mygdx.game.BaseUnit;
public interface MyInterface {
    /**
     * Method play on step
     * @param enemy - 1st team
     * @param friend - 2st team
     */
    public void step(ArrayList<BaseUnit> enemy, ArrayList<BaseUnit> friend);
}

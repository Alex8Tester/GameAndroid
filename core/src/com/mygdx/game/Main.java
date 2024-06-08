package com.mygdx.game;


import com.mygdx.game.Agility.Crossbowman;
import com.mygdx.game.Agility.Rogue;
import com.mygdx.game.Agility.Sniper;
import com.mygdx.game.Intellect.Magician;
import com.mygdx.game.Intellect.Monk;
import com.mygdx.game.Strength.Peasant;
import com.mygdx.game.Strength.Piciner;
import com.mygdx.game.Strength.Spearman;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static ArrayList<BaseUnit> holyTeam = new ArrayList<>();
    public static ArrayList<BaseUnit> darkTeam = new ArrayList<>();
    public static ArrayList<BaseUnit> allTeam = new ArrayList<>();

    private static String getName() {
        return String.valueOf(Names.values()[new Random().nextInt(Names.values().length - 1)]);
    }

    public static void main(String[] args) {
        Random random = new Random();
        initTeam();
        allTeam.addAll(holyTeam);
        allTeam.addAll(darkTeam);
        allTeam.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (true) {
            View.view();
            scanner.nextLine();
            int summ1HP = 0;
            int summ2HP = 0;
            for (BaseUnit BaseUnit : holyTeam){
                summ1HP += BaseUnit.getHP();
            }
            for (BaseUnit BaseUnit : darkTeam){
                summ2HP += BaseUnit.getHP();
            }
            if (summ1HP == 0){
                System.out.println("Winner team darkTeam");
                flag = false;
                break;
            }
            if (summ2HP == 0){
                System.out.println("Winner team holyTeam");
                flag = false;
                break;
            }
            for (BaseUnit BaseUnit : allTeam) {
                if (holyTeam.contains(BaseUnit)) BaseUnit.step(darkTeam, holyTeam);
                else BaseUnit.step(holyTeam, darkTeam);
            }
        }
    }

    public static void initTeam() {
        int teamCount = 10;
        Random rand = new Random();
        for (int i = 1; i < teamCount+1; i++) {
            int val = rand.nextInt(8);
            int coordX1 = rand.nextInt(10);
            int coordX2 = rand.nextInt(10);
            Position xy1 = new Position(coordX1, 1);
            Position xy2 = new Position(coordX2, 10);
            switch (val) {
                case 0:
                    holyTeam.add(new Crossbowman(getName(), i, 1));
                    break;
                case 1:
                    holyTeam.add(new Rogue(getName(), i, 1));
                    break;
                case 2:
                    holyTeam.add(new Sniper(getName(), i, 1));
                    break;
                case 3:
                    holyTeam.add(new Magician(getName(), i, 1));
                    break;
                case 4:
                    holyTeam.add(new Monk(getName(), i, 1));
                    break;
                case 5:
                    holyTeam.add(new Peasant(getName(), i, 1));
                    break;
                case 6:
                    holyTeam.add(new Spearman(getName(), i, 1));
                    break;
                case 7:
                    holyTeam.add(new Piciner(getName(), i, 1));
                    break;
                default:
                    break;
            }
        }
        for (int i = 1; i < teamCount+1; i++) {
            int val = rand.nextInt(8);
            int coordX1 = rand.nextInt(10);
            int coordX2 = rand.nextInt(10);
            Position xy1 = new Position(coordX1, 1);
            Position xy2 = new Position(coordX2, 10);
            switch (val) {
                case 0:
                    darkTeam.add(new Crossbowman(getName(), i, 10));
                    break;
                case 1:
                    darkTeam.add(new Rogue(getName(), i, 10));
                    break;
                case 2:
                    darkTeam.add(new Sniper(getName(), i, 10));
                    break;
                case 3:
                    darkTeam.add(new Magician(getName(), i, 10));
                    break;
                case 4:
                    darkTeam.add(new Monk(getName(), i, 10));
                    break;
                case 5:
                    darkTeam.add(new Peasant(getName(), i, 10));
                    break;
                case 6:
                    darkTeam.add(new Spearman(getName(), i, 10));
                    break;
                case 7:
                    darkTeam.add(new Piciner(getName(), i, 10));
                    break;
                default:
                    break;
            }
        }
    }
}
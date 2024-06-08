package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Agility.Crossbowman;
import com.mygdx.game.Agility.Rogue;
import com.mygdx.game.Agility.Sniper;
import com.mygdx.game.Intellect.Magician;
import com.mygdx.game.Intellect.Monk;
import com.mygdx.game.Strength.Peasant;
import com.mygdx.game.Strength.Piciner;
import com.mygdx.game.Strength.Spearman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, Crossbowman, Mage, Monk, Peasant, Piciner, Rogue, Sniper, Spearman;
	Music music;

	public ArrayList<BaseUnit> holyTeam;
	public ArrayList<BaseUnit> darkTeam;
	public ArrayList<BaseUnit> allTeam;

	private String getName(){
		return String.valueOf(Names.values()[new Random().nextInt(Names.values().length-1)]);
	}
	
	@Override
	public void create () {
		holyTeam = new ArrayList<>();
		darkTeam = new ArrayList<>();
		allTeam = new ArrayList<>();

		initTeam();
		allTeam.addAll(holyTeam);
		allTeam.addAll(darkTeam);
		allTeam.sort((o1, o2) -> o2.position.getX() - o1.position.getX());

		batch = new SpriteBatch();
		int rnd = MathUtils.random(0, 4);
		fon = new Texture("fon/CmBk"+rnd+".png");

		music = Gdx.audio.newMusic(Gdx.files.internal("music/paul-romero-rob-king-combat-theme-0"+MathUtils.random(1, 4)+".mp3"));
		music.setVolume(.5f);
		music.play();

		this.Sniper = new Texture("units/Sniper.png");
		this.Mage = new Texture("units/Mage.png");
		this.Monk = new Texture("units/Monk.png");
		this.Peasant = new Texture("units/Peasant.png");
		this.Crossbowman = new Texture("units/CrossBowMan.png");
		this.Rogue = new Texture("units/Rogue.png");
		this.Spearman = new Texture("units/SpearMan.png");
		this.Piciner = new Texture("units/Piciner.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (BaseUnit BaseUnit : allTeam) {
			if (BaseUnit.getHP()==0) continue;
			int y = BaseUnit.position.getX() * Gdx.graphics.getWidth() / 20;
			int x = BaseUnit.position.getY() * Gdx.graphics.getHeight() / 15;
			int k = 1;
			if (darkTeam.contains(BaseUnit)) k = -1;
			switch (BaseUnit.getInfo()) {
				case "С":
					batch.draw(Sniper, x, y, Sniper.getWidth()*k, Sniper.getHeight());
					break;
				case "Ф":
					batch.draw(Peasant, x, y, Peasant.getWidth()*k, Peasant.getHeight());
					break;
				case "К":
					batch.draw(Spearman, x, y, Spearman.getWidth()*k, Spearman.getHeight());
					break;
				case "П":
					batch.draw(Piciner, x, y, Piciner.getWidth()*k, Piciner.getHeight());
					break;
				case "Р":
					batch.draw(Rogue, x, y, Rogue.getWidth()*k, Rogue.getHeight());
					break;
				case "М":
					batch.draw(Monk, x, y, Monk.getWidth()*k, Monk.getHeight());
					break;
				case "А":
					batch.draw(Crossbowman, x, y, Crossbowman.getWidth()*k, Crossbowman.getHeight());
					break;
				case "В":
					batch.draw(Mage, x, y, Mage.getWidth()*k, Mage.getHeight());
					break;
			}
		}

		batch.end();

		boolean flag = true;
		for (BaseUnit BaseUnit : darkTeam) {
			if (BaseUnit.getHP()>0) flag = false;
		}
		if (flag) {
			Gdx.graphics.setTitle("Команда темных победила!");
			return;
		}

		flag = true;
		for (BaseUnit BaseUnit : holyTeam) {
			if (BaseUnit.getHP()>0) flag = false;
		}
		if (flag) {
			Gdx.graphics.setTitle("Команда светлых победила!");
			return;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()) {
			for (BaseUnit BaseUnit : allTeam)
				if (holyTeam.contains(BaseUnit)) BaseUnit.step(darkTeam, holyTeam);
				else BaseUnit.step(holyTeam, darkTeam);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
		Peasant.dispose();
		Crossbowman.dispose();
		Sniper.dispose();
		Rogue.dispose();
		Spearman.dispose();
		Piciner.dispose();
		Mage.dispose();
		Monk.dispose();
		music.dispose();
	}
	public void initTeam() {
		int teamCount = 10;
		Random rand = new Random();
		for (int i = 1; i < teamCount+1; i++) {
			int val = MathUtils.random(6);
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
			int val = MathUtils.random(6);
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

package project.igpe.GUI;

import javafx.beans.property.SimpleStringProperty;

public class ScoreBoardData {

	private SimpleStringProperty nameHero;
	private SimpleStringProperty sex;
	private SimpleStringProperty kill;
	private SimpleStringProperty shoot;
	private SimpleStringProperty timePlayed;
	
	public ScoreBoardData(String nameHero, String sex, String kill, String shoot, String timePlayed) {
		super();
		this.nameHero = new SimpleStringProperty(nameHero);
		this.sex = new SimpleStringProperty(sex);
		this.kill = new SimpleStringProperty(kill);
		this.shoot = new SimpleStringProperty(shoot);
		this.timePlayed = new SimpleStringProperty(timePlayed);
	}
	/*
	public String toString() {
        return getNameHero() + ";" + getSex()  + ";" + getKill() + ";" + getShoot() + ";" + getTimePlayed();
    }*/

	public ScoreBoardData() {}

	public String getNameHero() {
		return nameHero.get();
	}

	public String getSex() {
		return sex.getValue();
	}

	public String getKill() {
		return kill.get();
	}

	public String getShoot() {
		return shoot.get();
	}

	public String getTimePlayed() {
		return timePlayed.get();
	}

	public void setNameHero(String nameHero) {
		this.nameHero = new SimpleStringProperty(nameHero);
	}

	public void setSex(String sex) {
		this.sex = new SimpleStringProperty(sex);

	}

	public void setKill(String kill) {
		this.kill = new SimpleStringProperty(kill);
	}

	public void setShoot(String shoot) {
		this.shoot = new SimpleStringProperty(shoot);
	}

	public void setTimePlayed(String timePlayed) {
		this.timePlayed = new SimpleStringProperty(timePlayed);
	}

	
}

package project.igpe.GUI;

public class ScoreBoardData {

	private String nameHero;
	private Boolean sex;
	private int kill;
	private int shoot;
	private int timePlayed;
	
	public ScoreBoardData(String nameHero, Boolean sex, int kill, int shoot, int timePlayed) {
		super();
		this.nameHero = nameHero;
		this.sex = sex;
		this.kill = kill;
		this.shoot = shoot;
		this.timePlayed = timePlayed;
	}

	public String getNameHero() {
		return nameHero;
	}

	public void setNameHero(String nameHero) {
		this.nameHero = nameHero;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getShoot() {
		return shoot;
	}

	public void setShoot(int shoot) {
		this.shoot = shoot;
	}

	public int getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(int timePlayed) {
		this.timePlayed = timePlayed;
	}
	
	
	
}

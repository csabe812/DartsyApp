package hu.csfulop.model;

public class Player {

	private int id;
	private String name;
	private int wins;
	private int loses;
	private float average;

	public Player(int id, String name, int wins, int loses, float average) {
		super();
		this.id = id;
		this.name = name;
		this.wins = wins;
		this.loses = loses;
		this.average = average;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

}

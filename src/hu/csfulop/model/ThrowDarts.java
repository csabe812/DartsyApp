package hu.csfulop.model;

public class ThrowDarts {
	private int id;
	private int matchId;
	private int userId;
	private int score;

	public ThrowDarts(int id, int matchId, int userId, int score) {
		this.id = id;
		this.matchId = matchId;
		this.userId = userId;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}

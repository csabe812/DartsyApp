package hu.csfulop.javaswing.config;

public interface DataClass {

	String appName = "Dartsy";
	
	String fileMenu = "File";
	String[] fileMenuItem = {"New", "Exit"};
	
	String newGameDialog = "New game";
	String playerOne = "Player 1";
	String playerTwo = "Player 2";
	String newPlayer = "New player";
	String newPlayerButton = "Add new player";
	String okButton = "Ok";
	String cancelButton = "Cancel";
	String playerName = "Name";
	
//	String frameIconPath = "src\\resources\\arrow.png";
	String frameIconPath = "src\\resources\\darts2.jpg";
	
	//Database
    String jdbcUrl = "jdbc:sqlite:src\\resources\\dartsy_db.sqlite";
    String selectName = "SELECT name FROM users";
    String insertName = "INSERT INTO users(name) VALUES(?)";
    String selectThrows = "SELECT users.name, throws.score FROM throws, users WHERE users.id = throws.userid and throws.matchid = (SELECT id FROM matches ORDER BY ID DESC LIMIT 1)";
    String selectPlayerOne = "select users.name, matches.playerone, matches.playertwo from users, matches where matches.playerone = users.id";
    String selectPlayerTwo = "select users.name, matches.playerone, matches.playertwo from users, matches where matches.playertwo = users.id";
    
    //JOptionPane
    String userExists = "User already exists!";
    String cannotAdd = "Cannot add user";
    
    String valueError = "Value cannot be less than zero or more than 180";
    String valueErrorTitle = "Value error";
    
    String startedGame = "The game has been started";
    String youTurn = " you turn";
    String enterScore = "Enter score";
    String modifyPreviousScore = "Modifiy previous score";
	String modify = "Modify";

	String[] tableColumnName = { "ID", "NAME", "THREW" };

	String followingUserTurn = "Following turns";
	String scoresLeft = "Scores left";
}

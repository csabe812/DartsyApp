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
    String jdbcUrl = "jdbc:sqlite:d:\\Programming\\eclipse_projects\\Dartsy\\src\\resources\\dartsy_db.sqlite";
    String selectName = "SELECT name FROM users";
    String insertName = "INSERT INTO users(name) VALUES(?)";
    //JOptionPane
    String userExists = "User already exists!";
    String cannotAdd = "Cannot add user";
    
    String startedGame = "The game has been started";
}

package users;

public class DataUsersNode{
	private byte[] hash;
	private byte[] salt;
	private int highScore;

	public DataUsersNode(){
		this.hash = null;
		this.salt = null;
		this.highScore = 0;
	}
	
	// Constructor for a new user
	public DataUsersNode(byte[] hash, byte[] salt){
		this.hash = hash;
		this.salt = salt;
		this.highScore = 0;
	}
	
	public DataUsersNode(byte[] hash, byte[] salt, int highScore){
		this.hash = hash;
		this.salt = salt;
		this.highScore = highScore;
	}

	public void setHash(byte[] hash){
		this.hash = hash;
	}

	public byte[] getHash(){
		return hash;
	}

	public void setSalt(byte[] salt){
		this.salt = salt;
	}

	public byte[] getSalt(){
		return salt;
	}

	public void setHighScore(int highScore){
		this.highScore = highScore;
	}

	public int getHighScore(){
		return highScore;
	}
}

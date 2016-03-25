package users;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.io.*;
import java.util.*;

public class DataUsers{
	private Random random;
	private Hashtable<String, DataUsersNode> usersTable;
	private final String databaseAddress = "users/database.in";
	private final int lenMaxUser = 20;

	public DataUsers() throws FileNotFoundException, IOException {
		random = new Random();
		usersTable = new Hashtable<String, DataUsersNode>();
        readDatabase();
	}

    public int getHighScore(String user) {
        return usersTable.get(user).getHighScore();
    }

    public void setHighScore(String user, int score) {
        if (score > getHighScore(user)) 
            usersTable.get(user).setHighScore(score);
    }

	private void readDatabase() throws FileNotFoundException, IOException{
		BufferedReader database = new BufferedReader(new FileReader(databaseAddress));	
		String currentLine;
		String[] aux;
		byte[] salt;
		byte[] hash;
		int highScore;
		while ((currentLine = database.readLine()) != null) {
			aux = currentLine.split(" ");
			Base64.Decoder dec = Base64.getDecoder();
			hash = dec.decode(aux[1]);
			salt = dec.decode(aux[2]);
			highScore = Integer.parseInt(aux[3]);
			usersTable.put(aux[0], new DataUsersNode(hash, salt, highScore));
		}
		database.close();
	}

	public void printDatabase() throws IOException{	
		BufferedWriter database = new BufferedWriter(new FileWriter(databaseAddress));
		Base64.Encoder enc = Base64.getEncoder();
		for (String key: usersTable.keySet()){
			DataUsersNode aux = usersTable.get(key);
			database.write(key+" ");
			database.write(enc.encodeToString(aux.getHash())+" ");
			database.write(enc.encodeToString(aux.getSalt())+" ");
			database.write(Integer.toString(aux.getHighScore()));
			database.newLine();
		}
		database.close();		
	}

	public boolean verifyCredentials(String user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		if (usersTable.containsKey(user)){
			DataUsersNode credentials = usersTable.get(user);
			byte[] salt = credentials.getSalt();
			byte[] hash = credentials.getHash();
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] newHash = f.generateSecret(spec).getEncoded();
			if (Arrays.equals(hash, newHash))
				return true;
			return false;
		}
		return newUser(user, password);
	}	

	public boolean newUser(String user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		if (user.length() > lenMaxUser || password.equals(""))
			return false;
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		usersTable.put(user, new DataUsersNode(hash, salt, 0));
		return true;
	}
}

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Random;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataUsers{
	Random random;
	HashMap<String, DataUsersNode> users;
	final BufferedReader database = new BufferedReader(new FileReader("database.in"));

	public DataUsers(){
		random = new Random();
		database = new HashMap<String, DataUserNode>();
	}

	public void readDatabase(){
		String currentLine;
		String[] aux;
		while ((currentLine = database.readLine()) != null) {
			aux = currentLine.split(" ");
			// Decode things from strings
			users.put(aux[0], new DataUsersNode(aux[1], aux[2], Integer.parseInt(aux[3])));
		}
	}	

	public bool verifyCredentials(String user, String password){
		return false;		
	}	

	public void newUser(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		System.out.println("salt: "+enc.encodeToString(salt));
		System.out.println("hash: "+enc.encodeToString(hash));
	}
}

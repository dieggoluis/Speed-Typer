package dict;

import java.io.*;

class Dictionary{
	PrefixTree tree;
	final String inputFile = "dict/words";

	public Dictionary(){
		tree = new PrefixTree();
	}

	public void loadDictionary() throws FileNotFoundException, IOException{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));	
		String currentLine;
		while ((currentLine = input.readLine()) != null)
			tree.insert(currentLine.toLowerCase());	
		input.close();
	}
}

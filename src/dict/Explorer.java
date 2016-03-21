package dict;

import java.io.*;
import java.util.*;

class Explorer{
	private PrefixTree tree;
	private TreeNode explorerNode;
	private Deque<TreeNode> stackOfNodes; // Preferable over stack in the java documentation
	private String word;
	private boolean possibleWord;
	private int wrongLetters;
	private final String inputFile = "dict/words";

	public boolean getPossibleWord(){
		return possibleWord;
	}

	public String getString(){
		return word;
	}

	public int getWrongLetters(){
		return wrongLetters;
	}

	public Explorer(){
		tree = new PrefixTree();
		loadDictionary();
		explorerNode = tree.getRoot();
		stackOfNodes = new ArrayDeque<TreeNode>(); // Not thread-safe, but, probably, this won't be a problem
		word = "";
		possibleWord = true;
		wrongLetters = 0;
	}

	private void loadDictionary() throws FileNotFoundException, IOException{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));	
		String currentLine;
		while ((currentLine = input.readLine()) != null)
			tree.insert(currentLine.toLowerCase());	
		input.close();
	}

	public void returnToRoot(){
		explorerNode = tree.getRoot();
		stackOfNodes.clear();
		word = "";
		isWord = true;
		wrongLetters = 0;
	}
	
	// Returns Integer.MAX_VALUE when the word has not finished yet
	public int explore(char c){
		// Deal with enter key later (I hope that the user don't use it)
		if (c == ' '){	
			// int out = computePoints();
			explorerNode.increaseFrequency();
			returnToRoot();
			// return out;
		}
		else if (c == '\b'){
			if (wrongLetters == 0)
				explorerNode = stackOfNodes.pop();
			else if (wrongLetters == 1){
				wrongLetters--;
				possibleWord = true;
			}
	    		    else
				wrongLetters--;
		}
		else{
			if (possibleWord){
				stackOfNodes.push(explorerNode);
				if (c == '\''){
					word += c;
					if (explorerNode.getChild(26) == null)
						possibleWord = false;
					explorerNode = explorerNode.getChild(26);	
				}
				else if (c >= 'a' && c <= 'z'){
					word += c;
					if (explorerNode.getChild(c-'a') == null)
						possibleWord = false;
					explorerNode = explorerNode.getChild(c-'a');
				}
				else{
					wrongLetters++;
					possibleWord = false;
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}

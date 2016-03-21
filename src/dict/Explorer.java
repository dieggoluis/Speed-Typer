package dict;

import java.io.*;
import java.util.*;

class Explorer{
	private PrefixTree tree;
	private TreeNode explorerNode;
	private Deque<TreeNode> stackOfNodes; // Preferable over stack in the java documentation
	private boolean possibleWord;
	final String inputFile = "dict/words";

	// To do: implement setters and getters

	public Explorer(){
		tree = new PrefixTree();
		loadDictionary();
		explorerNode = tree.getRoot();
		stackOfNodes = new ArrayDeque<TreeNode>(); // Not thread-safe, but, probably, this won't be a problem
		possibleWord = true; 
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
		isWord = true;
	}
	
	public void explore(char c){
		if (possibleWord){
			if (c == '\''){
				if (explorerNode.getChild(26) == null)
					possibleWord = false;
				else{
					stackOfNodes.push(explorerNode.getChild(26));
					explorerNode = explorerNode.getChild(26);	
				}
			}
			else{ // Verify backspace and ASCII encoding	
				if (cur.getChild(c-'a') == null)
					return false;
				cur = cur.getChild(c-'a');
			}
		}
	}
}

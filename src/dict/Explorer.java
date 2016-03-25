package dict;

import java.io.*;
import java.util.*;

public class Explorer{
	private PrefixTree tree;
	private TreeNode explorerNode;
	private Deque<TreeNode> stackOfNodes;
	private String word;
	private boolean possibleWord;
	private int wrongLetters;
	private final String inputFile = "dict/words";

	public boolean isPossibleWord(){
		return possibleWord;
	}

	public String getWord(){
		return word;
	}

	public int getWrongLetters(){
		return wrongLetters;
	}

	public Explorer() throws FileNotFoundException, IOException{
		tree = new PrefixTree();
		loadDictionary();
		explorerNode = tree.getRoot();
		stackOfNodes = new ArrayDeque<TreeNode>(); 
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

	private void returnToRoot(){
		explorerNode = tree.getRoot();
		stackOfNodes.clear();
		word = "";
		possibleWord = true;
		wrongLetters = 0;
	}

	private int computePoints(){
		if (word.equals("luizinha"))
            return 1000;
        if (word.equals("easteregg"))
            // There are no easter eggs in this game
            return -1;
        if (possibleWord && explorerNode.getIsWord())
			return Integer.max(0, 2*word.length()-3*explorerNode.getFrequency());
		return -word.length();
	}
	
	// Returns Integer.MAX_VALUE if the word has not finished yet
	public int explore(char c){
		if (c == ' ' || c == '\n'){	
			int out = computePoints();
            if (possibleWord) {
                explorerNode.increaseFrequency();
            }
			returnToRoot();
			return out;
		}
		else if (c == '\b'){
			if (wrongLetters > 1){
				if (stackOfNodes.size() > 0){
					explorerNode = stackOfNodes.pop();
					word = word.substring(0, word.length()-1);
				}
			}
			else if (wrongLetters == 1){
				wrongLetters--;
				possibleWord = true;
			}
            else
				wrongLetters--;
		}
		else{
			word = word + Character.toString(c);
			if (possibleWord){
                if (c == '\''){
					if (explorerNode.getChild(26) == null){
						wrongLetters++;
						possibleWord = false;
					}
					else{
						stackOfNodes.push(explorerNode);
						explorerNode = explorerNode.getChild(26);	
					}
				}
				else if (c >= 'a' && c <= 'z'){
					if (explorerNode.getChild(c-'a') == null){
						wrongLetters++;
						possibleWord = false;
					}
					else{
						stackOfNodes.push(explorerNode);
						explorerNode = explorerNode.getChild(c-'a');
					}
				}
				else{
					wrongLetters++;
					possibleWord = false;
				}
			}
			else
				wrongLetters++;
		}
		return Integer.MAX_VALUE;
	}
}

package dict;

class TreeNode{
	private boolean isWord;
	private TreeNode[] children;
	private int frequency;

	public TreeNode(){
		this.isWord = false;
		this.frequency = 0;
		this.children = new TreeNode[27];
	}
	
	public boolean getIsWord(){
		return isWord;
	}
	
	public void setIsWord(boolean isWord){
		this.isWord = isWord;
	}

	public int getFrequency(){
		return frequency;
	}

	public void increaseFrequency(){
		frequency++;
	}

	public TreeNode getChild(int i){
		return children[i];
	}	

	public void setChild(int i, TreeNode child){
		this.children[i] = child;
	}
}

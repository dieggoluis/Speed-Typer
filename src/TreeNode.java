class TreeNode{
	private boolean isWord;
	private TreeNode[] children;
	private int frequency;

	public TreeNode(){
		this.isWord = false;
		this.frequency = 0;
		this.children = new TreeNode[26];
	}
	
	public TreeNode(boolean isWord){
		this.isWord = isWord;
		this.frequency = 0;
		this.children = new TreeNode[26];
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

	public void setFrequency(int frequency){
		this.frequency = frequency;
	}

	public void increaseFrequency(){
		frequency++;
	}

	public TreeNode[] getChildren(){
		return children;
	}

	public void setChildren(TreeNode[] children){
		this.children = children;	
	}

	public TreeNode getChild(int i){
		return children[i];
	}	

	public void setChild(int i, TreeNode child){
		this.children[i] = child;
	}
}

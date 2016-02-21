class TreeNode{
	private boolean isWord;
	private TreeNode[] children;

	public TreeNode(){
		this.isWord = false;
		this.children = new TreeNode[26];
	}
	
	public TreeNode(boolean isWord){
		this.isWord = isWord;
		this.children = new TreeNode[26];
	}
	
	public boolean getIsWord(){
		return isWord;
	}
	
	public void setIsWord(boolean isWord){
		this.isWord = isWord;
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

class PrefixTree{	
	private TreeNode root;
	
	public PrefixTree(){
		this.root = new TreeNode();
	}

	public PrefixTree(TreeNode root){
		this.root = root;
	}

	public void insert(String word){
		TreeNode cur = root;
		for (char c: word.toCharArray()){
			if (cur.getChild(c-'a') == null)
				cur.setChild(c-'a', new TreeNode());
			cur = cur.getChild(c-'a');	
		}
		cur.setIsWord(true);	
	}

	public boolean search(String word){
		TreeNode cur = root;
		for (char c: word.toCharArray()){
			if (cur.getChild(c-'a') == null)
				return false;
			cur = cur.getChild(c-'a');
		}
		return cur.getIsWord();
	}

	public boolean prefixSearch(String prefix){
		TreeNode cur = root;
		for (char c: prefix.toCharArray()){
			if (cur.getChild(c-'a') == null)
				return false;
			cur = cur.getChild(c-'a');
		}
		return true;
	}
}

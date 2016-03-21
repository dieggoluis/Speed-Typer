package dict;

class PrefixTree{	
	private TreeNode root;
	
	public PrefixTree(){
		this.root = new TreeNode();
	}

	public PrefixTree(TreeNode root){
		this.root = root;
	}
	
	public TreeNode getRoot(){
		return root;
	}

	public void insert(String word){
		TreeNode cur = root;
		for (char c: word.toCharArray()){
			if (c == '\''){
				if (cur.getChild(26) == null)
					cur.setChild(26, new TreeNode());
				cur = cur.getChild(26);	
			}	
			else{
				if(cur.getChild(c-'a') == null)
					cur.setChild(c-'a', new TreeNode());
				cur = cur.getChild(c-'a');
			}	
		}
		cur.setIsWord(true);	
	}


	// The next two functions won't be used since the search is done always from the beginning of the tree
	// Can't deal with non-ASCII chars, so, a lot of exceptions
	// They stay in this code anyway only for being used as example for the next code versions
	public boolean search(String word){
		TreeNode cur = root;
		for (char c: word.toCharArray()){
			if (c == '\''){
				if (cur.getChild(26) == null)
					return false;
				cur = cur.getChild(26);	
			}
			else{	
				if (cur.getChild(c-'a') == null)
					return false;
				cur = cur.getChild(c-'a');
			}
		}
		return cur.getIsWord();
	}

	public boolean prefixSearch(String prefix){
		TreeNode cur = root;
		for (char c: prefix.toCharArray()){
			if (c == '\''){
				if (cur.getChild(26) == null)
					return false;
				cur = cur.getChild(26);	
			}	
			else{
				if (cur.getChild(c-'a') == null)
					return false;
				cur = cur.getChild(c-'a');
			}
		}
		return true;
	}
}

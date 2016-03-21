package dict;

class PrefixTree{	
	private TreeNode root;
	
	public PrefixTree(){
		this.root = new TreeNode();
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
}

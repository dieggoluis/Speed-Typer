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
			if (cur.getChild(c-'a') == null)
				cur.setChild(c-'a', new TreeNode());
			cur = cur.getChild(c-'a');	
		}
		cur.setIsWord(true);	
	}

	public boolean search(String word){
		TreeNode cur = root;
		for (char c: word.toCharArray()){
			if (c == '\''){
				if (cur.getChild(26) == null)
					return false;
				cur = cur.getChild(26);	
			}	
			if (cur.getChild(c-'a') == null)
				return false;
			cur = cur.getChild(c-'a');
		}
		return cur.getIsWord();
	}

	// Não muito útil pois procura do começo sempre
	// Implementar função que faz a procura conforme a palavra é digitada
	public boolean prefixSearch(String prefix){
		TreeNode cur = root;
		for (char c: prefix.toCharArray()){
			if (c == '\''){
				if (cur.getChild(26) == null)
					return false;
				cur = cur.getChild(26);	
			}	
			if (cur.getChild(c-'a') == null)
				return false;
			cur = cur.getChild(c-'a');
		}
		return true;
	}
}

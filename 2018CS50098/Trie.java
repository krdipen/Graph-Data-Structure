public class Trie<T> {
	
	TrieNode<T>[] root=new TrieNode[13];
    
    public TrieNode<T> search(String word) {
    	int length=word.length();
        TrieNode<T>[] current;
        current=root;
        for(int i=0;i<length;i++) {
        	if(current[word.charAt(i)-'-']==null) {
        		return null;
        	}
        	else {
        		if(i==length-1) {
        			if(current[word.charAt(i)-'-'].obj!=null) {
        				return current[word.charAt(i)-'-'];
        			}
        			else {
        				return null;
        			}
        		}
        		current=current[word.charAt(i)-'-'].next;
        	}
        }
        return null;
    }
    
    public boolean insert(String word,T value) {
    	int length=word.length();
        TrieNode<T>[] current;
        current=root;
        for(int i=0;i<length;i++) {
        	if(current[word.charAt(i)-'-']==null) {
        		current[word.charAt(i)-'-']=new TrieNode<T>();
        	}
        	if(i==length-1) {
        		if(current[word.charAt(i)-'-'].obj!=null) {
        			return false;
        		}
        		else {
        			current[word.charAt(i)-'-'].obj=(T)value;
        			return true;
        		}
        	}
        	current=current[word.charAt(i)-'-'].next;
        }
        return false;
    }
    
}
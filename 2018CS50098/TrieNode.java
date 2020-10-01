public class TrieNode<T> {
	public T obj;
	public TrieNode<T>[] next;
	public TrieNode() {
		this.obj=null;
		next=new TrieNode[13];
	}
    public T getValue() {
        return obj;
    }
}
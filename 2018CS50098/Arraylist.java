public class Arraylist<T> {
	
	public Arraynode<T>[] array;
	public Integer size;
	
	public Arraylist() {
		this.array=new Arraynode[1000];
		this.size=0;
	}
	
	public Integer size() {
		return size;
	}
	
	public void add(T value) {
		Arraynode<T> arraynode=new Arraynode<T>(value);
		if(size<array.length) {
			array[size]=arraynode;
		}
		else {
			Arraynode<T>[] arraynew=new Arraynode[2*array.length];
			for(int i=0;i<size;i++) {
				arraynew[i]=array[i];
			}
			arraynew[size]=arraynode;
			array=arraynew;
		}
		size++;
	}
	
	public T get(int i) {
		return array[i].value();
	}
	
	public T remove(int i) {
		T value=array[i].value();
		for(int j=i;j<size-1;j++) {
			array[j]=array[j+1];
		}
		array[size-1]=null;
		size--;
		return value;
	}
	
	public void set(int i,T value) {
		Arraynode<T> arraynode=new Arraynode<T>(value);
		array[i]=arraynode;
	}
}
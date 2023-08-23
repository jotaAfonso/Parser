package tests;

public class Tuple2<K, V> {

    private K first;
    private V second;
  
    public Tuple2(K first, V second){
        this.setFirst(first);
        this.setSecond(second);
    }

	public K getFirst() {
		return first;
	}

	public void setFirst(K first) {
		this.first = first;
	}

	public V getSecond() {
		return second;
	}

	public void setSecond(V second) {
		this.second = second;
	}
}
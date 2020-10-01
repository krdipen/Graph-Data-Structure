public class Edge implements Comparable<Edge>,EdgeInterface {
	public Point[] edge=new Point[2];
	public float length_sq;
	public Arraylist<Triangle> tri_neighbor=new Arraylist<Triangle>();
	public Edge(Point a,Point b) {
		this.edge[0]=a;
		this.edge[1]=b;
		this.length_sq=(a.point[0]-b.point[0])*(a.point[0]-b.point[0])+(a.point[1]-b.point[1])*(a.point[1]-b.point[1])+(a.point[2]-b.point[2])*(a.point[2]-b.point[2]);
	}
	@Override
	public PointInterface[] edgeEndPoints() {
		return edge;
	}
	@Override
	public int compareTo(Edge e) {
		if(this.length_sq-e.length_sq>0) {
			return -1;
		}
		else if(this.length_sq-e.length_sq<0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public String getKey() {
		if(edge[0].compareTo(edge[1])==1) {
			return edge[0].getKey()+"/"+edge[1].getKey();
		}
		else {
			return edge[1].getKey()+"/"+edge[0].getKey();
		} 
	}
}
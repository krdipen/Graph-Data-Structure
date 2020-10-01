public class Triangle implements Comparable<Triangle>,TriangleInterface {
	public Point[] triangle=new Point[3];
	public Integer time;
	public Arraylist<Point> pt_neighbor=new Arraylist<Point>();
	public Arraylist<Edge> ed_neighbor=new Arraylist<Edge>();
	public Arraylist<Triangle> tri_neighbor=new Arraylist<Triangle>();
	public Arraylist<Triangle> ext_neighbor=new Arraylist<Triangle>();
	public Integer index;
	public Triangle(Point a,Point b,Point c,int time) {
		this.triangle[0]=a;
		this.triangle[1]=b;
		this.triangle[2]=c;
		this.time=time;
	}
	@Override
	public PointInterface[] triangle_coord() {
		return triangle;
	}
	@Override
	public int compareTo(Triangle t) {
		return t.time-this.time;
	}
	public String getKey() {
		if((triangle[0].compareTo(triangle[1])>=0)&&(triangle[1].compareTo(triangle[2])>=0)) {
			return triangle[0].getKey()+"/"+triangle[1].getKey()+"/"+triangle[2].getKey();
		}
		else if((triangle[0].compareTo(triangle[2])>=0)&&(triangle[2].compareTo(triangle[1])>=0)) {
			return triangle[0].getKey()+"/"+triangle[2].getKey()+"/"+triangle[1].getKey();
		}
		else if((triangle[1].compareTo(triangle[0])>=0)&&(triangle[0].compareTo(triangle[2])>=0)) {
			return triangle[1].getKey()+"/"+triangle[0].getKey()+"/"+triangle[2].getKey();
		}
		else if((triangle[1].compareTo(triangle[2])>=0)&&(triangle[2].compareTo(triangle[0])>=0)) {
			return triangle[1].getKey()+"/"+triangle[2].getKey()+"/"+triangle[0].getKey();
		}
		else if((triangle[2].compareTo(triangle[0])>=0)&&(triangle[0].compareTo(triangle[1])>=0)) {
			return triangle[2].getKey()+"/"+triangle[0].getKey()+"/"+triangle[1].getKey();
		}
		else {
			return triangle[2].getKey()+"/"+triangle[1].getKey()+"/"+triangle[0].getKey();
		}
	}
}
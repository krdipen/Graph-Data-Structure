public class Point implements Comparable<Point>,PointInterface {
	public float[] point=new float[3];
	public Arraylist<Point> pt_neighbor=new Arraylist<Point>();
	public Arraylist<Edge> ed_neighbor=new Arraylist<Edge>();
	public Arraylist<Triangle> tri_neighbor=new Arraylist<Triangle>();
	public Integer index;
	public Point(float x,float y,float z) {
		this.point[0]=x;
		this.point[1]=y;
		this.point[2]=z;
	}
	@Override
	public float getX() {
		return point[0];
	}
	@Override
	public float getY() {
		return point[1];
	}
	@Override
	public float getZ() {
		return point[2];
	}
	@Override
	public float[] getXYZcoordinate() {
		return point;
	}
	@Override
	public int compareTo(Point p) {
		if(this.point[0]<p.point[0]) {
			return 1;
		}
		else if(this.point[0]>p.point[0]) {
			return -1;
		}
		else {
			if(this.point[1]<p.point[1]) {
				return 1;
			}
			else if(this.point[1]>p.point[1]) {
				return -1;
			}
			else {
				if(this.point[2]<p.point[2]) {
					return 1;
				}
				else if(this.point[2]>p.point[2]) {
					return -1;
				}
				else {
					return 0;
				}
			}
		}
	}
	public String getKey() {
		return point[0]+"/"+point[1]+"/"+point[2];
	}
}
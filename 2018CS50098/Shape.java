public class Shape implements ShapeInterface {
	
	int time=1;
	Trie<Point> trie1=new Trie<Point>();
	Trie<Edge> trie2=new Trie<Edge>();
	Trie<Triangle> trie3=new Trie<Triangle>();
	Arraylist<Point> alist1=new Arraylist<Point>();
	Arraylist<Edge> alist2=new Arraylist<Edge>();
	Arraylist<Triangle> alist3=new Arraylist<Triangle>();
	
	@Override
	public boolean ADD_TRIANGLE(float[] triangle_coord) {
		float x1,y1,z1,x2,y2,z2,x3,y3,z3,area_x,area_y,area_z;
		x1=triangle_coord[0];
		y1=triangle_coord[1];
		z1=triangle_coord[2];
		x2=triangle_coord[3];
		y2=triangle_coord[4];
		z2=triangle_coord[5];
		x3=triangle_coord[6];
		y3=triangle_coord[7];
		z3=triangle_coord[8];
		area_x=(y2-y1)*(z3-z1)-(y3-y1)*(z2-z1);
		area_y=(z2-z1)*(x3-x1)-(x2-x1)*(z3-z1);
		area_z=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1);
		if((area_x==0)&&(area_y==0)&&(area_z==0)) {
			return false;
		}
		else {
			Point a=new Point(x1,y1,z1);
			Point b=new Point(x2,y2,z2);
			Point c=new Point(x3,y3,z3);
			if(trie1.insert(a.getKey(),a)) {
				a.index=alist1.size();
				alist1.add(a);
			}
			else {
				a=trie1.search(a.getKey()).obj;
			}
			if(trie1.insert(b.getKey(),b)) {
				b.index=alist1.size();
				alist1.add(b);
			}
			else {
				b=trie1.search(b.getKey()).obj;
			}
			if(trie1.insert(c.getKey(),c)) {
				c.index=alist1.size();
				alist1.add(c);
			}
			else {
				c=trie1.search(c.getKey()).obj;
			}
			Edge ab=new Edge(a,b);
			Edge bc=new Edge(b,c);
			Edge ca=new Edge(c,a);
			if(trie2.insert(ab.getKey(),ab)) {
				alist2.add(ab);
				a.pt_neighbor.add(b);
				b.pt_neighbor.add(a);
				a.ed_neighbor.add(ab);
				b.ed_neighbor.add(ab);
			}
			else {
				ab=trie2.search(ab.getKey()).obj;
			}
			if(trie2.insert(bc.getKey(),bc)) {
				alist2.add(bc);
				b.pt_neighbor.add(c);
				c.pt_neighbor.add(b);
				b.ed_neighbor.add(bc);
				c.ed_neighbor.add(bc);
			}
			else {
				bc=trie2.search(bc.getKey()).obj;
			}
			if(trie2.insert(ca.getKey(),ca)) {
				alist2.add(ca);
				c.pt_neighbor.add(a);
				a.pt_neighbor.add(c);
				c.ed_neighbor.add(ca);
				a.ed_neighbor.add(ca);
			}
			else {
				ca=trie2.search(ca.getKey()).obj;
			}
			Triangle t=new Triangle(a,b,c,time);
			if(trie3.insert(t.getKey(),t)) {
				t.index=alist3.size();
				alist3.add(t);
				t.pt_neighbor.add(a);
				t.pt_neighbor.add(b);
				t.pt_neighbor.add(c);
				t.ed_neighbor.add(ab);
				t.ed_neighbor.add(bc);
				t.ed_neighbor.add(ca);
				MaxHeap<Triangle> temp_maxheap=new MaxHeap<Triangle>();
				Trie<Triangle> temp_trie=new Trie<Triangle>();
				int size;
				for(int i=0;i<a.tri_neighbor.size();i++) {
					if(temp_trie.insert(a.tri_neighbor.get(i).getKey(),a.tri_neighbor.get(i))) {
						temp_maxheap.insert(a.tri_neighbor.get(i));
						a.tri_neighbor.get(i).ext_neighbor.add(t);
					}
				}
				for(int i=0;i<b.tri_neighbor.size();i++) {
					if(temp_trie.insert(b.tri_neighbor.get(i).getKey(),b.tri_neighbor.get(i))) {
						temp_maxheap.insert(b.tri_neighbor.get(i));
						b.tri_neighbor.get(i).ext_neighbor.add(t);
					}
				}
				for(int i=0;i<c.tri_neighbor.size();i++) {
					if(temp_trie.insert(c.tri_neighbor.get(i).getKey(),c.tri_neighbor.get(i))) {
						temp_maxheap.insert(c.tri_neighbor.get(i));
						c.tri_neighbor.get(i).ext_neighbor.add(t);
					}
				}
				size=temp_maxheap.list.size();
				for(int i=0;i<size;i++) {
					t.ext_neighbor.add(temp_maxheap.extractMax());
				}
				a.tri_neighbor.add(t);
				b.tri_neighbor.add(t);
				c.tri_neighbor.add(t);
				for(int i=0;i<ab.tri_neighbor.size();i++) {
					temp_maxheap.insert(ab.tri_neighbor.get(i));
					ab.tri_neighbor.get(i).tri_neighbor.add(t);
				}
				for(int i=0;i<bc.tri_neighbor.size();i++) {
					temp_maxheap.insert(bc.tri_neighbor.get(i));
					bc.tri_neighbor.get(i).tri_neighbor.add(t);
				}
				for(int i=0;i<ca.tri_neighbor.size();i++) {
					temp_maxheap.insert(ca.tri_neighbor.get(i));
					ca.tri_neighbor.get(i).tri_neighbor.add(t);
				}
				size=temp_maxheap.list.size();
				for(int i=0;i<size;i++) {
					t.tri_neighbor.add(temp_maxheap.extractMax());
				}
				ab.tri_neighbor.add(t);
				bc.tri_neighbor.add(t);
				ca.tri_neighbor.add(t);
			}
			else {
				return false;
			}
			time++;
			return true;
		}
	}
	
	@Override
	public int TYPE_MESH() {
		boolean b=true;
		for(int i=0;i<alist2.size();i++) {
			if(alist2.get(i).tri_neighbor.size()>2) {
				return 3;
			}
			if(alist2.get(i).tri_neighbor.size()<2) {
				b=false;
			}
		}
		if(b) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	@Override
	public EdgeInterface[] BOUNDARY_EDGES() {
		MaxHeap<Edge> temp_maxheap=new MaxHeap<Edge>();
		for(int i=0;i<alist2.size();i++) {
			if(alist2.get(i).tri_neighbor.size()==1) {
				temp_maxheap.insert(alist2.get(i));
			}
		}
		int size=temp_maxheap.list.size();
		if(size==0) {
			return null;
		}
		EdgeInterface[] boundary=new EdgeInterface[size];
		for(int i=0;i<size;i++) {
			boundary[i]=temp_maxheap.extractMax();
		}
		return boundary;
	}
	
	@Override
	public int COUNT_CONNECTED_COMPONENTS() {
		int count=0;
		Arraylist<Integer> marked=new Arraylist<Integer>();
		for(int i=0;i<alist3.size();i++) {
			marked.add(0);
		}
		for(int i=0;i<alist3.size();i++) {
			if(marked.get(i)==0) {
				count++;
				Arraylist<Triangle> queue=new Arraylist<Triangle>();
				queue.add(alist3.get(i));
				marked.set(i,1);
				while(queue.size()!=0) {
					Triangle t=queue.remove(0);
					for(int j=0;j<t.tri_neighbor.size();j++) {
						if(marked.get(t.tri_neighbor.get(j).index)==0) {
							queue.add(t.tri_neighbor.get(j));
							marked.set(t.tri_neighbor.get(j).index,1);
						}
					}
				}
			}
		}
		return count;
	}
	
	public String getKey(float[] triangle_coord) {
		float x1,y1,z1,x2,y2,z2,x3,y3,z3;
		x1=triangle_coord[0];
		y1=triangle_coord[1];
		z1=triangle_coord[2];
		x2=triangle_coord[3];
		y2=triangle_coord[4];
		z2=triangle_coord[5];
		x3=triangle_coord[6];
		y3=triangle_coord[7];
		z3=triangle_coord[8];
		Point a=new Point(x1,y1,z1);
		Point b=new Point(x2,y2,z2);
		Point c=new Point(x3,y3,z3);
		if((a.compareTo(b)>=0)&&(b.compareTo(c)>=0)) {
			return a.getKey()+"/"+b.getKey()+"/"+c.getKey();
		}
		else if((a.compareTo(c)>=0)&&(c.compareTo(b)>=0)) {
			return a.getKey()+"/"+c.getKey()+"/"+b.getKey();
		}
		else if((b.compareTo(a)>=0)&&(a.compareTo(c)>=0)) {
			return b.getKey()+"/"+a.getKey()+"/"+c.getKey();
		}
		else if((b.compareTo(c)>=0)&&(c.compareTo(a)>=0)) {
			return b.getKey()+"/"+c.getKey()+"/"+a.getKey();
		}
		else if((c.compareTo(a)>=0)&&(a.compareTo(b)>=0)) {
			return c.getKey()+"/"+a.getKey()+"/"+b.getKey();
		}
		else {
			return c.getKey()+"/"+b.getKey()+"/"+a.getKey();
		}
	}
	
	@Override
	public TriangleInterface[] NEIGHBORS_OF_TRIANGLE(float[] triangle_coord) {
		String word=getKey(triangle_coord);
		if(trie3.search(word)!=null) {
			Triangle t=trie3.search(word).obj;
			int size=t.tri_neighbor.size();
			if(size==0) {
				return null;
			}
			TriangleInterface[] tri_neighbor=new TriangleInterface[size];
			for(int i=0;i<size;i++) {
				tri_neighbor[i]=t.tri_neighbor.get(i);
			}
			return tri_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public EdgeInterface[] EDGE_NEIGHBOR_TRIANGLE(float[] triangle_coord) {
		String word=getKey(triangle_coord);
		if(trie3.search(word)!=null) {
			Triangle t=trie3.search(word).obj;
			int size=t.ed_neighbor.size();
			if(size==0) {
				return null;
			}
			EdgeInterface[] ed_neighbor=new EdgeInterface[size];
			for(int i=0;i<size;i++) {
				ed_neighbor[i]=t.ed_neighbor.get(i);
			}
			return ed_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public PointInterface[] VERTEX_NEIGHBOR_TRIANGLE(float[] triangle_coord) {
		String word=getKey(triangle_coord);
		if(trie3.search(word)!=null) {
			Triangle t=trie3.search(word).obj;
			int size=t.pt_neighbor.size();
			if(size==0) {
				return null;
			}
			PointInterface[] pt_neighbor=new PointInterface[size];
			for(int i=0;i<size;i++) {
				pt_neighbor[i]=t.pt_neighbor.get(i);
			}
			return pt_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public TriangleInterface[] EXTENDED_NEIGHBOR_TRIANGLE(float[] triangle_coord) {
		String word=getKey(triangle_coord);
		if(trie3.search(word)!=null) {
			Triangle t=trie3.search(word).obj;
			int size=t.ext_neighbor.size();
			if(size==0) {
				return null;
			}
			TriangleInterface[] ext_neighbor=new TriangleInterface[size];
			for(int i=0;i<size;i++) {
				ext_neighbor[i]=t.ext_neighbor.get(i);
			}
			return ext_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public TriangleInterface[] INCIDENT_TRIANGLES(float[] point_coordinates) {
		String word=point_coordinates[0]+"/"+point_coordinates[1]+"/"+point_coordinates[2];
		if(trie1.search(word)!=null) {
			Point p=trie1.search(word).obj;
			int size=p.tri_neighbor.size();
			if(size==0) {
				return null;
			}
			TriangleInterface[] tri_neighbor=new TriangleInterface[size];
			for(int i=0;i<size;i++) {
				tri_neighbor[i]=p.tri_neighbor.get(i);
			}
			return tri_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public PointInterface[] NEIGHBORS_OF_POINT(float[] point_coordinates) {
		String word=point_coordinates[0]+"/"+point_coordinates[1]+"/"+point_coordinates[2];
		if(trie1.search(word)!=null) {
			Point p=trie1.search(word).obj;
			int size=p.pt_neighbor.size();
			if(size==0) {
				return null;
			}
			PointInterface[] pt_neighbor=new PointInterface[size];
			for(int i=0;i<size;i++) {
				pt_neighbor[i]=p.pt_neighbor.get(i);
			}
			return pt_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public EdgeInterface[] EDGE_NEIGHBORS_OF_POINT(float[] point_coordinates) {
		String word=point_coordinates[0]+"/"+point_coordinates[1]+"/"+point_coordinates[2];
		if(trie1.search(word)!=null) {
			Point p=trie1.search(word).obj;
			int size=p.ed_neighbor.size();
			if(size==0) {
				return null;
			}
			EdgeInterface[] ed_neighbor=new EdgeInterface[size];
			for(int i=0;i<size;i++) {
				ed_neighbor[i]=p.ed_neighbor.get(i);
			}
			return ed_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public TriangleInterface[] FACE_NEIGHBORS_OF_POINT(float[] point_coordinates) {
		String word=point_coordinates[0]+"/"+point_coordinates[1]+"/"+point_coordinates[2];
		if(trie1.search(word)!=null) {
			Point p=trie1.search(word).obj;
			int size=p.tri_neighbor.size();
			if(size==0) {
				return null;
			}
			TriangleInterface[] tri_neighbor=new TriangleInterface[size];
			for(int i=0;i<size;i++) {
				tri_neighbor[i]=p.tri_neighbor.get(i);
			}
			return tri_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean IS_CONNECTED(float[] triangle_coord_1, float[] triangle_coord_2) {
		String word1=getKey(triangle_coord_1);
		String word2=getKey(triangle_coord_2);
		Triangle req_t;
		if(trie3.search(word2)!=null) {
			req_t=trie3.search(word2).obj;
		}
		else {
			return false;
		}
		Arraylist<Integer> marked=new Arraylist<Integer>();
		for(int i=0;i<alist3.size();i++) {
			marked.add(0);
		}
		Arraylist<Triangle> queue=new Arraylist<Triangle>();
		if(trie3.search(word1)!=null) {
			queue.add(trie3.search(word1).obj);
		}
		else {
			return false;
		}
		marked.set(trie3.search(word1).obj.index,1);
		while(queue.size()!=0) {
			Triangle t=queue.remove(0);
			for(int j=0;j<t.tri_neighbor.size();j++) {
				if(marked.get(t.tri_neighbor.get(j).index)==0) {
					queue.add(t.tri_neighbor.get(j));
					marked.set(t.tri_neighbor.get(j).index,1);
					if(t.tri_neighbor.get(j)==req_t) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public TriangleInterface[] TRIANGLE_NEIGHBOR_OF_EDGE(float[] edge_coordinates) {
		String word;
		float x1,y1,z1,x2,y2,z2;
		x1=edge_coordinates[0];
		y1=edge_coordinates[1];
		z1=edge_coordinates[2];
		x2=edge_coordinates[3];
		y2=edge_coordinates[4];
		z2=edge_coordinates[5];
		Point a=new Point(x1,y1,z1);
		Point b=new Point(x2,y2,z2);
		if(a.compareTo(b)==1) {
			word=a.getKey()+"/"+b.getKey();
		}
		else {
			word=b.getKey()+"/"+a.getKey();
		}
		if(trie2.search(word)!=null) {
			Edge e=trie2.search(word).obj;
			int size=e.tri_neighbor.size();
			if(size==0) {
				return null;
			}
			TriangleInterface[] tri_neighbor=new TriangleInterface[size];
			for(int i=0;i<size;i++) {
				tri_neighbor[i]=e.tri_neighbor.get(i);
			}
			return tri_neighbor;
		}
		else {
			return null;
		}
	}
	
	@Override
	public int MAXIMUM_DIAMETER() {
		Arraylist<Triangle> component=new Arraylist<Triangle>();
		int size=0;
		Arraylist<Integer> marked=new Arraylist<Integer>();
		for(int i=0;i<alist3.size();i++) {
			marked.add(0);
		}
		for(int i=0;i<alist3.size();i++) {
			if(marked.get(i)==0) {
				Arraylist<Triangle> temp_alist=new Arraylist<Triangle>();
				Arraylist<Triangle> queue=new Arraylist<Triangle>();
				temp_alist.add(alist3.get(i));
				queue.add(alist3.get(i));
				marked.set(i,1);
				while(queue.size()!=0) {
					Triangle t=queue.remove(0);
					for(int j=0;j<t.tri_neighbor.size();j++) {
						if(marked.get(t.tri_neighbor.get(j).index)==0) {
							temp_alist.add(t.tri_neighbor.get(j));
							queue.add(t.tri_neighbor.get(j));
							marked.set(t.tri_neighbor.get(j).index,1);
						}
					}
				}
				if(temp_alist.size()>size) {
					size=temp_alist.size();
					component=temp_alist;
				}
			}
		}
		int diameter=0;
		for(int i=0;i<component.size();i++) {
			Arraylist<Integer> marked2=new Arraylist<Integer>();
			for(int j=0;j<component.size();j++) {
				marked2.add(-1);
			}
			Arraylist<Triangle> queue=new Arraylist<Triangle>();
			queue.add(component.get(i));
			marked2.set(i,0);
			int current=0;
			while(queue.size()!=0) {
				Triangle t=queue.remove(0);
				for(int j=0;j<t.tri_neighbor.size();j++) {
					if(marked2.get(t.tri_neighbor.get(j).index)==-1) {
						queue.add(t.tri_neighbor.get(j));
						marked2.set(t.tri_neighbor.get(j).index,marked.get(t.index)+1);
						current=marked.get(t.index)+1;
					}
				}
			}
			if(current>diameter) {
				diameter=current;
			}
		}
		return diameter;	
	}
	
	@Override
	public PointInterface[] CENTROID() {
		MaxHeap<Point> centroid_maxheap=new MaxHeap<Point>();
		Arraylist<Integer> marked=new Arraylist<Integer>();
		for(int i=0;i<alist3.size();i++) {
			marked.add(0);
		}
		for(int i=0;i<alist3.size();i++) {
			if(marked.get(i)==0) {
				Point centroid=new Point(0,0,0);
				Arraylist<Triangle> queue=new Arraylist<Triangle>();
				queue.add(alist3.get(i));
				marked.set(i,1);
				Arraylist<Integer> marked_pt=new Arraylist<Integer>();
				for(int j=0;j<alist1.size();j++) {
					marked_pt.add(0);
				}
				int number=0;
				if(marked_pt.get(alist3.get(i).triangle[0].index)==0) {
					marked_pt.set(alist3.get(i).triangle[0].index,1);
					centroid.point[0]=centroid.point[0]+alist3.get(i).triangle[0].point[0];
					centroid.point[1]=centroid.point[1]+alist3.get(i).triangle[0].point[1];
					centroid.point[2]=centroid.point[2]+alist3.get(i).triangle[0].point[2];
					number++;
				}
				if(marked_pt.get(alist3.get(i).triangle[1].index)==0) {
					marked_pt.set(alist3.get(i).triangle[1].index,1);
					centroid.point[0]=centroid.point[0]+alist3.get(i).triangle[1].point[0];
					centroid.point[1]=centroid.point[1]+alist3.get(i).triangle[1].point[1];
					centroid.point[2]=centroid.point[2]+alist3.get(i).triangle[1].point[2];
					number++;
				}
				if(marked_pt.get(alist3.get(i).triangle[2].index)==0) {
					marked_pt.set(alist3.get(i).triangle[2].index,1);
					centroid.point[0]=centroid.point[0]+alist3.get(i).triangle[2].point[0];
					centroid.point[1]=centroid.point[1]+alist3.get(i).triangle[2].point[1];
					centroid.point[2]=centroid.point[2]+alist3.get(i).triangle[2].point[2];
					number++;
				}
				while(queue.size()!=0) {
					Triangle t=queue.remove(0);
					for(int j=0;j<t.tri_neighbor.size();j++) {
						if(marked.get(t.tri_neighbor.get(j).index)==0) {
							queue.add(t.tri_neighbor.get(j));
							marked.set(t.tri_neighbor.get(j).index,1);
							if(marked_pt.get(t.tri_neighbor.get(j).triangle[0].index)==0) {
								marked_pt.set(t.tri_neighbor.get(j).triangle[0].index,1);
								centroid.point[0]=centroid.point[0]+t.tri_neighbor.get(j).triangle[0].point[0];
								centroid.point[1]=centroid.point[1]+t.tri_neighbor.get(j).triangle[0].point[1];
								centroid.point[2]=centroid.point[2]+t.tri_neighbor.get(j).triangle[0].point[2];
								number++;
							}
							if(marked_pt.get(t.tri_neighbor.get(j).triangle[1].index)==0) {
								marked_pt.set(t.tri_neighbor.get(j).triangle[1].index,1);
								centroid.point[0]=centroid.point[0]+t.tri_neighbor.get(j).triangle[1].point[0];
								centroid.point[1]=centroid.point[1]+t.tri_neighbor.get(j).triangle[1].point[1];
								centroid.point[2]=centroid.point[2]+t.tri_neighbor.get(j).triangle[1].point[2];
								number++;
							}
							if(marked_pt.get(t.tri_neighbor.get(j).triangle[2].index)==0) {
								marked_pt.set(t.tri_neighbor.get(j).triangle[2].index,1);
								centroid.point[0]=centroid.point[0]+t.tri_neighbor.get(j).triangle[2].point[0];
								centroid.point[1]=centroid.point[1]+t.tri_neighbor.get(j).triangle[2].point[1];
								centroid.point[2]=centroid.point[2]+t.tri_neighbor.get(j).triangle[2].point[2];
								number++;
							}
						}
					}
				}
				centroid.point[0]=centroid.point[0]/number;
				centroid.point[1]=centroid.point[1]/number;
				centroid.point[2]=centroid.point[2]/number;
				centroid_maxheap.insert(centroid);
			}
		}
		int size=centroid_maxheap.list.size();
		PointInterface[] centroids=new PointInterface[size];
		for(int i=0;i<size;i++) {
			centroids[i]=centroid_maxheap.extractMax();
		}
		if(size==0) {
			return null;
		}
		else {
			return centroids;
		}
	}
	
	@Override
	public PointInterface CENTROID_OF_COMPONENT(float[] point_coordinates) {
		String word=point_coordinates[0]+"/"+point_coordinates[1]+"/"+point_coordinates[2];
		if(trie1.search(word)==null) {
			return null;
		}
		Arraylist<Integer> marked=new Arraylist<Integer>();
		for(int i=0;i<alist3.size();i++) {
			marked.add(0);
		}
		Point centroid=new Point(0,0,0);
		Arraylist<Triangle> queue=new Arraylist<Triangle>();
		Triangle temp_t=trie1.search(word).obj.tri_neighbor.get(0);
		queue.add(temp_t);
		marked.set(temp_t.index,1);
		Arraylist<Integer> marked_pt=new Arraylist<Integer>();
		for(int i=0;i<alist1.size();i++) {
			marked_pt.add(0);
		}
		int number=0;
		if(marked_pt.get(temp_t.triangle[0].index)==0) {
			marked_pt.set(temp_t.triangle[0].index,1);
			centroid.point[0]=centroid.point[0]+temp_t.triangle[0].point[0];
			centroid.point[1]=centroid.point[1]+temp_t.triangle[0].point[1];
			centroid.point[2]=centroid.point[2]+temp_t.triangle[0].point[2];
			number++;
		}
		if(marked_pt.get(temp_t.triangle[1].index)==0) {
			marked_pt.set(temp_t.triangle[1].index,1);
			centroid.point[0]=centroid.point[0]+temp_t.triangle[1].point[0];
			centroid.point[1]=centroid.point[1]+temp_t.triangle[1].point[1];
			centroid.point[2]=centroid.point[2]+temp_t.triangle[1].point[2];
			number++;
		}
		if(marked_pt.get(temp_t.triangle[2].index)==0) {
			marked_pt.set(temp_t.triangle[2].index,1);
			centroid.point[0]=centroid.point[0]+temp_t.triangle[2].point[0];
			centroid.point[1]=centroid.point[1]+temp_t.triangle[2].point[1];
			centroid.point[2]=centroid.point[2]+temp_t.triangle[2].point[2];
			number++;
		}
		while(queue.size()!=0) {
			Triangle t=queue.remove(0);
			for(int j=0;j<t.tri_neighbor.size();j++) {
				if(marked.get(t.tri_neighbor.get(j).index)==0) {
					queue.add(t.tri_neighbor.get(j));
					marked.set(t.tri_neighbor.get(j).index,1);
					if(marked_pt.get(t.tri_neighbor.get(j).triangle[0].index)==0) {
						marked_pt.set(t.tri_neighbor.get(j).triangle[0].index,1);
						centroid.point[0]=centroid.point[0]+t.tri_neighbor.get(j).triangle[0].point[0];
						centroid.point[1]=centroid.point[1]+t.tri_neighbor.get(j).triangle[0].point[1];
						centroid.point[2]=centroid.point[2]+t.tri_neighbor.get(j).triangle[0].point[2];
						number++;
					}
					if(marked_pt.get(t.tri_neighbor.get(j).triangle[1].index)==0) {
						marked_pt.set(t.tri_neighbor.get(j).triangle[1].index,1);
						centroid.point[0]=centroid.point[0]+t.tri_neighbor.get(j).triangle[1].point[0];
						centroid.point[1]=centroid.point[1]+t.tri_neighbor.get(j).triangle[1].point[1];
						centroid.point[2]=centroid.point[2]+t.tri_neighbor.get(j).triangle[1].point[2];
						number++;
					}
					if(marked_pt.get(t.tri_neighbor.get(j).triangle[2].index)==0) {
						marked_pt.set(t.tri_neighbor.get(j).triangle[2].index,1);
						centroid.point[0]=centroid.point[0]+t.tri_neighbor.get(j).triangle[2].point[0];
						centroid.point[1]=centroid.point[1]+t.tri_neighbor.get(j).triangle[2].point[1];
						centroid.point[2]=centroid.point[2]+t.tri_neighbor.get(j).triangle[2].point[2];
						number++;
					}
				}
			}
		}
		centroid.point[0]=centroid.point[0]/number;
		centroid.point[1]=centroid.point[1]/number;
		centroid.point[2]=centroid.point[2]/number;
		return centroid;
	}
	
	@Override
	public PointInterface[] CLOSEST_COMPONENTS() {
		Arraylist<Arraylist<Point>> component=new Arraylist<Arraylist<Point>>();
		Arraylist<Integer> marked=new Arraylist<Integer>();
		for(int i=0;i<alist3.size();i++) {
			marked.add(0);
		}
		for(int i=0;i<alist3.size();i++) {
			if(marked.get(i)==0) {
				Arraylist<Point> temp_alist=new Arraylist<Point>();
				component.add(temp_alist);
				Arraylist<Triangle> queue=new Arraylist<Triangle>();
				queue.add(alist3.get(i));
				marked.set(i,1);
				Arraylist<Integer> marked_pt=new Arraylist<Integer>();
				for(int j=0;j<alist1.size();j++) {
					marked_pt.add(0);
				}
				if(marked_pt.get(alist3.get(i).triangle[0].index)==0) {
					marked_pt.set(alist3.get(i).triangle[0].index,1);
					temp_alist.add(alist3.get(i).triangle[0]);
				}
				if(marked_pt.get(alist3.get(i).triangle[1].index)==0) {
					marked_pt.set(alist3.get(i).triangle[1].index,1);
					temp_alist.add(alist3.get(i).triangle[1]);
				}
				if(marked_pt.get(alist3.get(i).triangle[2].index)==0) {
					marked_pt.set(alist3.get(i).triangle[2].index,1);
					temp_alist.add(alist3.get(i).triangle[2]);
				}
				while(queue.size()!=0) {
					Triangle t=queue.remove(0);
					for(int j=0;j<t.tri_neighbor.size();j++) {
						if(marked.get(t.tri_neighbor.get(j).index)==0) {
							queue.add(t.tri_neighbor.get(j));
							marked.set(t.tri_neighbor.get(j).index,1);
							if(marked_pt.get(t.tri_neighbor.get(j).triangle[0].index)==0) {
								marked_pt.set(t.tri_neighbor.get(j).triangle[0].index,1);
								temp_alist.add(t.tri_neighbor.get(j).triangle[0]);
							}
							if(marked_pt.get(t.tri_neighbor.get(j).triangle[1].index)==0) {
								marked_pt.set(t.tri_neighbor.get(j).triangle[1].index,1);
								temp_alist.add(t.tri_neighbor.get(j).triangle[1]);
							}
							if(marked_pt.get(t.tri_neighbor.get(j).triangle[2].index)==0) {
								marked_pt.set(t.tri_neighbor.get(j).triangle[2].index,1);
								temp_alist.add(t.tri_neighbor.get(j).triangle[2]);
							}
						}
					}
				}
			}
		}
		if(component.size()<2) {
			return null;
		}
		Edge closest=new Edge(component.get(0).get(0),component.get(1).get(0));
		for(int i=0;i<component.size()-1;i++) {
			for(int j=i+1;j<component.size();j++) {
				for(int x=0;x<component.get(i).size();x++) {
					for(int y=0;y<component.get(j).size();y++) {
						Edge temp_edge=new Edge(component.get(i).get(x),component.get(j).get(y));
						if(closest.length_sq>temp_edge.length_sq) {
							closest=temp_edge;
						}
					}
				}
			}
		}
		return closest.edge;
	}

}
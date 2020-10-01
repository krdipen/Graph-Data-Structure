public interface ShapeInterface
{
	default public boolean ADD_TRIANGLE(float [] triangle_coord){return false;}                                    //done//
	default public int TYPE_MESH(){return -1;}                                                                     //done//
	default public EdgeInterface [] BOUNDARY_EDGES(){return null;}                                                 //done//
	default public int COUNT_CONNECTED_COMPONENTS(){return 0;}                                                     //done//
	default public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){return null;}               //done//
	default public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){return null;}                  //done//
	default public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){return null;}               //done//
	default public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){return null;}          //done//
	default public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){return null;}               //done//
	default public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){return null;}                  //done//
	default public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){return null;}              //done//
	default public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){return null;}          //done//
	default public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){return false;}       //done//
	default public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){return null;}         //done//
	default public int MAXIMUM_DIAMETER(){return 0;}                                                               //done//
	default public PointInterface [] CENTROID (){return null;}                                                     //done//
	default public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){return null;}                 //done//
	default public PointInterface [] CLOSEST_COMPONENTS(){return null;}                                            //done//
}
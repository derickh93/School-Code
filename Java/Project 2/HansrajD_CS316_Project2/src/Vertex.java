import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Vertex Class</p>
 *
 * <p>Description: Defines a Vertex class that will be used to perform depth first search along with the DFS class.
 * This class defines a Vertex object which is a node in the graph.
 *
 * @author Derick Hansraj
 */
public class Vertex {
	//instance variables
	private String name;
	private boolean visited;
	private List<Vertex> neighborList;

    /**
     * parameterized constructor - constructor that allows you to pass a name to the name variable
     */
	public Vertex(String name) {
		this.name = name;
		this.neighborList = new ArrayList<>();
	}
	
    /**
     * addNeighbor method - method that adds a Vertex object to the neighborList of the current Vertex
     * @param vertex - Vertex that is to be added as a neighbor
     * @return void
     */
	public void addNeighbor(Vertex vertex) {
		this.neighborList.add(vertex);
	}
	
    /**
     * getName method - method that adds a Vertex object to the neighborList of the current Vertex
     * @return - a string representing the name
     */
	public String getName() {
		return this.name;
	}
	
    /**
     * setName method - method that changes the name variable
     * @param name - name to be passed to the name variable
     * @return - void
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * isVisited method - returns true if the vertex has been visited and false if it has not
     * @return - a boolean indicating whether the Vertex has been visited.
     */
	public boolean isVisited() {
		return visited;
	}
    /**
     * setVisited method - method that sets whether or not the Vertex is visited.
     * @param visited - boolean indicating value of visited
     * @return - void
     */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
    /**
     * getNeighborList method - method that returns the neighborList
     * @return - returns the neighborList
     */
	public List<Vertex> getNeighborList(){
		return neighborList;
	}
    /**
     * setNeighborList method - sets the neighborList as passed by the user
     * @param neighborList - a list that contains the neighbors of the current Vertex
     */
	public void setNeighborList(List<Vertex> neighborList) {
		this.neighborList = neighborList;
	}
    /**
     * toString method - method that returns the state of the object as a string
     * @return - a string representing the Vertex
     */
	public String toString() {
		return this.name;
	}
}

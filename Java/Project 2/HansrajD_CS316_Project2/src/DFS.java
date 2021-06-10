import java.util.List;
import java.util.Stack;

/**
 * <p>Title: DFS Class</p>
 *
 * <p>Description: Defines a DFS class that will be used to perform depth first search along with the Vertex class.
 *
 * @author Derick Hansraj
 */
public class DFS {
	//instance variables
	private Stack<Vertex> stack;

	/**
	 * default constructor - the constructor which defines a new stack
	 */
	public DFS() {
		this.stack = new Stack<>();
	}
	/**
	 * dfs - method that will loop through the List of Vertex objects and if 
	 * it is visited.
	 */
	public void dfs(List<Vertex> vertexList) {
		for(Vertex v: vertexList) {
			if(!v.isVisited()) {
				v.setVisited(true);
				dfsWithStack(v);
			}
		}
	}

	/**
	 * dfsWithStack - method that will perform dfs with the root vertex
	 */
	private void dfsWithStack(Vertex rootVertex) {
		this.stack.add(rootVertex);
		rootVertex.setVisited(true);

		while(!stack.isEmpty()) {
			Vertex actualVertex = this.stack.pop();
			System.out.println(actualVertex + " ");

			for(Vertex v : actualVertex.getNeighborList()) {
				if(!v.isVisited()) {
					v.setVisited(true);
					this.stack.push(v);
				}
			}
		}
	}

}

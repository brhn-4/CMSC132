package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	public HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;
	
	public Graph() //Constructor
	{
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<>();
	}
	
	public void addVertex(String vertexName, E data) throws IllegalArgumentException
	{
		if(dataMap.containsKey(vertexName))
		{
			throw new IllegalArgumentException("Vertex alread exists");
		}
		dataMap.put(vertexName, data);
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) throws IllegalArgumentException
	{
		if(!(dataMap.containsKey(startVertexName)) || !(dataMap.containsKey(endVertexName)))
		{
			throw new IllegalArgumentException("Vertex does not exists");
		}
		adjacencyMap.get(startVertexName).put(endVertexName, cost);
	}
	
	public String toString()
	{
		TreeMap<String,E> dataMapSort = new TreeMap(dataMap);
		TreeMap<String, HashMap<String, Integer>> adjacencyMapSort = new TreeMap(adjacencyMap);
		
		//Vertices: [A, B, C, D, M, ST]
		String ans = "Vertices: [";
		
		for(String vertex : dataMapSort.keySet())
		{
			ans += vertex + ",";
		}
		
		ans += "]\nEdges:\n";
		
		for(String vertex : adjacencyMapSort.keySet())
		{
			TreeMap<String,Integer> connections = new TreeMap<>(adjacencyMap.get(vertex));
			
			ans+= "Vertex(" + vertex + ")--->{";
			
			for(String connection : connections.keySet())
			{
				ans+= connection + "=" + adjacencyMapSort.get(vertex).get(connection) + ", ";
			}
			
			ans+= "}\n";
		}
			
		ans = ans.replaceAll(",]","]");
		ans = ans.replaceAll(", }","}");
		
		return ans;
	}
	
	public Map<String,Integer> getAdjacentVertices(String vertexName)
	{
		TreeMap<String,Integer> adjacentVertices = new TreeMap<>();
		
		for(String connection : adjacencyMap.get(vertexName).keySet())
		{
			adjacentVertices.put(connection, adjacencyMap.get(connection).get(connection));
		}
		
		return adjacentVertices;
	}
	
	public int getCost(String startVertexName, String endVertexName)
	{
		if(!(dataMap.containsKey(startVertexName)) || !(dataMap.containsKey(endVertexName)))
		{
			throw new IllegalArgumentException("Vertex does not exists");
		}
		
		return getAdjacentVertices(startVertexName).get(endVertexName);
	}
	
	public E getData(String vertex)
	{
		if(!dataMap.containsKey(vertex))
		{
			throw new IllegalArgumentException("Vertex not within graph");
		}
		
		return dataMap.get(vertex);
	}
	
	public Set<String> getVertices()
	{
		
		Set<String> vertices = new TreeSet<>();
		
		for(String vertex : dataMap.keySet())
		{
			vertices.add(vertex);
		}
		
		return vertices;
	}
	
	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback)
	{
		if(!dataMap.containsKey(startVertexName))
		{
			throw new IllegalArgumentException("Vertex not within graph");
		}
		
		Stack<String> stack = new Stack<>();
		Set<String> visited = new HashSet<String>();
		
		callback.processVertex(startVertexName, dataMap.get(startVertexName));
		visited.add(startVertexName);
		
		for(String neighbor : adjacencyMap.get(startVertexName).keySet())
		{
			stack.add(neighbor);
		}
		
		while(!stack.isEmpty())
		{
			String curr = stack.pop();
			
			if(!visited.contains(curr))
			{
				callback.processVertex(curr, dataMap.get(curr));
				
				for(String neighbor : adjacencyMap.get(curr).keySet())
				{
					stack.add(neighbor);
				}
			}
			
			visited.add(curr);
		}
		// add all neighbors of start vertex to stack
		// while stack is not empty:
		// 		pop first thing off stack
		//		if the vertex is not visited:
		//			process that vertex and add all neighbors to stack
		//			add vertex to list of visited
	}
	
	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback)
	{
		if(!dataMap.containsKey(startVertexName))
		{
			throw new IllegalArgumentException("Vertex not within graph");
		}
		
		LinkedList<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<String>();
		
		callback.processVertex(startVertexName, dataMap.get(startVertexName));
		visited.add(startVertexName);
		
		for(String neighbor : adjacencyMap.get(startVertexName).keySet())
		{
			queue.add(neighbor);
		}
			
		while(!queue.isEmpty())
		{
			String curr = queue.pop();
				
			if(!visited.contains(curr))
			{
				callback.processVertex(curr, dataMap.get(curr));
					
				for(String neighbor : adjacencyMap.get(curr).keySet())
				{
					queue.add(neighbor);
				}
			}	
			
			visited.add(curr);
		}
	}
	
	private class Node implements Comparable<Node>{
		String name;
		int distance;
		Node predecessor;
		
		public Node(String name, int distance, Node predecessor)
		{
			this.name = name;
			this.distance = distance;
			this.predecessor = predecessor;
		}
		
		public int compareTo(Node other)
		{
			return this.distance - other.distance;
		}
	}
	
	public int doDijkstras(String startVertexName, String endVertexName, ArrayList<String> shortestPath)
	{
		if(!(dataMap.containsKey(startVertexName)) || !(dataMap.containsKey(endVertexName)))
		{
			throw new IllegalArgumentException("Vertex does not exists");
		}
		if(startVertexName == endVertexName)
		{
			shortestPath.add(startVertexName);
			return 0;
		}
		
		int time = -1;
		TreeMap<String, Node> visited = new TreeMap<String, Node>();
		Queue<Node> queue = new PriorityQueue<>();
		
		queue.add(new Node(startVertexName,0,null));
		
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			TreeMap<String,Integer> adjacenctVertexSorted = new TreeMap<>(adjacencyMap.get(curr.name));
			
			for(String vertex : adjacenctVertexSorted.keySet())
			{
				if(!visited.containsKey(vertex))
				{
					Node successor = null;
					for(Node node : queue)
					{
						if(node.name.equals(vertex))
						{
							successor = node;
							break;
						}
					}
					
					if (successor == null) 
					{
						successor = new Node(vertex, curr.distance + adjacenctVertexSorted.get(vertex),curr);
						queue.add(successor);
					} 
					
					else 
					{
						int oldDistance = successor.distance;
						int newDistance = curr.distance + adjacenctVertexSorted.get(vertex);
						
						if(newDistance<oldDistance)
						{
							successor.distance = newDistance;
							successor.predecessor = curr;
						}		
					}
					
				queue.add(successor);
				}
		}
			
		visited.put(curr.name, curr);
		}
		
		if(visited.containsKey(endVertexName))
		{
			time = visited.get(endVertexName).distance;
			Node predecessor = visited.get(endVertexName);
			
			while(predecessor.name != startVertexName)
			{
				shortestPath.add(0,predecessor.name);
				predecessor = predecessor.predecessor;
			}
			
			shortestPath.add(0,startVertexName);
		}
		else
		{
			shortestPath.add("None");
		}

		return time;
	}
}
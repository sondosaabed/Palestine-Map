import java.util.ArrayList;

public class Table {
	boolean isKnown[];
	double distance[];
	City path[];
	City vertices[];
	int NOV; //vertices
	
	public Table() {
	}

	public Table(ArrayList<City> cities, int start) {
		//Initialize the table
		this.NOV = cities.size();
		this.isKnown = new boolean[this.NOV];
		this.distance = new double[this.NOV];
		this.path = new City[this.NOV];
		this.vertices = new City[this.NOV];

		for (int i = 0; i < this.NOV; i++) {
			isKnown[i] = false;
			path[i] = null;
			vertices[i] = cities.get(i);
			if (i == start) {
				distance[start] = 0;
				continue;
			}
			distance[i] = Integer.MAX_VALUE;
		}
		
		//Calculate the table
		for (int i = 0; i <= NOV; i++) {
			//Find the next Minimum Adjacent
			double Max = Integer.MAX_VALUE;
			int ind = -1;
			for (int k = 0; k < NOV; k++)
				if (isKnown[k] == false) {
					if (distance[k] < Max) {
						Max = distance[k];
						ind = k;
					}
				}
			i =  ind;
			
			if (i == -1)
				break;
			isKnown[i] = true;
			for (int j = 0; j < vertices[i].getAdjacents().size(); j++) {  // for each adjacent
				int index = Find(vertices[i].getAdjacents().get(j).getCity(), vertices);

				if (isKnown[index] == false)
					if (vertices[i].getAdjacents().get(j).getDistance() + distance[i] < distance[index]) {
						distance[index] = vertices[i].getAdjacents().get(j).getDistance() + distance[i];
						path[index] = vertices[i];
					}
			}
		}	
	}

	private int Find(City c, City[] g) {
		int index = -1;
		for (int i = 0; i < g.length; i++) {
			if (c.getName().equals(g[i].getName())) {
				index = i;
				break;
			}
		}
		return index;
	}
}


public class Adjacent {
	private City city = new City();
	private double distance;
	
	public Adjacent() {
	}
	
	public Adjacent(City city, double distance) {
		this.city = city;
		this.distance = distance;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Adjacent [city=" + city.getName() + ", distance=" + distance + "]";
	}
}

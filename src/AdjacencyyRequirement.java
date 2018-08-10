
public class AdjacencyyRequirement implements Requirement{

	private int north, east, west, south;
	public final static int NA = -1;
	
	public AdjacencyyRequirement(int north, int east, int south, int west){

		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;

		
	}
	
	public boolean complies(Piece p){
		
		if(this.north != NA && this.north!= p.north){
			return false;
		}
		if(this.east != NA && this.east!= p.east){
			return false;
		}
		if(this.south != NA && this.south!= p.south){
			return false;
		}
		if(this.west != NA && this.west!= p.west){
			return false;
		}
		return true;
		
	}

	public void setNorth(int north) {
		this.north = north;
	}

	public void setEast(int east) {
		this.east = east;
	}

	public void setWest(int west) {
		this.west = west;
	}

	public void setSouth(int south) {
		this.south = south;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Should be: North: " + north + " East: " + east + " South: " + south + " West: " + west;
	}
	
	
}

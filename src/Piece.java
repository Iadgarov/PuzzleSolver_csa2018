import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Piece {
	
	
	private int id;
	protected int north, east, west, south;
	private int original_north;
	private int flip_count;
	private boolean is_set;
	
	
	String format = "------------\n|  \\ %02d /  |\n| %02d\\  / %02d|\n|   /  \\   |\n|  / %02d \\  |\n------------";
	
	private static Pattern p = Pattern.compile("\\d+");
	private static Matcher m;
	
	public Piece(String args){
		m = p.matcher(args);
		m.find();
		this.id = Integer.parseInt(m.group());
		
		m.find();
		this.north = Integer.parseInt(m.group());
		this.original_north = Integer.parseInt(m.group());
		
		m.find();
		this.east = Integer.parseInt(m.group());
		m.find();
		this.south = Integer.parseInt(m.group());
		m.find();
		this.west = Integer.parseInt(m.group());
		
		this.is_set = false;
		this.flip_count = 0;
		
	}
	
	private void rotate() {
		int temp;
		temp = this.north;
		this.north = this.west;
		this.west = this.south;
		this.south = this.east;
		this.east = temp;
		this.flip_count = (this.flip_count + 1) % 4;
	}
	
	public boolean meetsRequirement(Requirement r){
		
		if (!r.complies(this) && !this.is_set){
			this.rotate();
			if (!r.complies(this)){
				this.rotate();
				if (!r.complies(this)){
					this.rotate();
					if (!r.complies(this)){
						this.rotate(); // back to original form, nothing fits
						return false;
					}
				}
			}
			
		}
			
			
		
		return true;
		
		
	}
	
	public boolean meetsRequirements(List<Requirement> reqs){
		
		boolean result = true;
		for (Requirement r : reqs){
			result = result && this.meetsRequirement(r);
		}
		return result;
		
		
	}
	
	
	@Override
	public String toString() {
		String r = String.format(format, this.north, this.west, this.east, this.south);
		return r;
	}
	
	@Override
	public boolean equals(Object obj) {
		Piece other = (Piece)obj;
		if (this.north == other.north && 
			this.east == other.east &&
			this.south == other.south &&
			this.west == other.west){
				
				return true;
			}
		return false;
	}

	public int getNorth() {
		return north;
	}

	public boolean get_Is_set() {
		return is_set;
	}

	public void set_Is_set(boolean is_set) {
		this.is_set = is_set;
	}

	public int getEast() {
		return east;
	}

	public int getWest() {
		return west;
	}

	public int getSouth() {
		return south;
	}
	
	public int getID(){
		return id;
	}
	
	public int getRotation(){
		return this.flip_count;
	}
	
	

}

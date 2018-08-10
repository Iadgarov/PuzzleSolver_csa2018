
public class ExclusionRequirement implements Requirement {
	
	private Piece p;
	
	public ExclusionRequirement(Piece p){
		this.p = p;
	}
	
	@Override
	public boolean complies(Piece p) {
		if (this.p.equals(p))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Integer.toString(this.p.getID());
	}

}

import java.util.ArrayList;
import java.util.List;

public class Slot {
	
	private AdjacencyyRequirement req;
	private List<Requirement> bad_choices;
	private Piece chosen;
	private boolean isEdge;
	
	
	
	public Slot(boolean isEdge){
		this.req = new AdjacencyyRequirement(AdjacencyyRequirement.NA, AdjacencyyRequirement.NA, AdjacencyyRequirement.NA, AdjacencyyRequirement.NA);
		
		bad_choices = new ArrayList<>();
		chosen = null;
		this.isEdge = isEdge;
	}
	
	public AdjacencyyRequirement getRequirement(){
		return req;
	}
	
	public Piece getChosen(){
		return chosen;
	}
	
	public void setChosen(Piece chosen){
		if (this.getChosen() != null){
			this.getChosen().set_Is_set(false);
		}
		this.chosen = chosen;
		if (this.chosen != null)
			this.chosen.set_Is_set(true);
	}
	
	public boolean isEdge(){
		return isEdge;
	}

	public List<Requirement> getBad_choices() {
		return bad_choices;
	}
	


}

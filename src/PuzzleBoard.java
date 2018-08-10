import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PuzzleBoard {
	
	final static String input = "0,[3, 11, 14, 3]; 1,[14, 7, 0, 18]; 2,[5, 19, 0, 8]; 3,[0, 10, 4, 1]; 4,[17, 8, 6, 3]; 5,[12, 19, 20, 9]; 6,[6, 9, 18, 4]; 7,[8, 8, 8, 16]; 8,[5, 4, 16, 7]; 9,[1, 14, 8, 0]; 10,[9, 0, 1, 19]; 11,[13, 2, 0, 10]; 12,[1, 5, 4, 5]; 13,[18, 0, 15, 14]; 14,[9, 6, 10, 0]; 15,[3, 7, 6, 12]; 16,[5, 5, 7, 1]; 17,[15, 18, 0, 0]; 18,[19, 6, 11, 3]; 19,[12, 3, 19, 2]; 20,[11, 8, 2, 14]; 21,[0, 16, 1, 18]; 22,[16, 0, 15, 2]; 23,[13, 14, 1, 16]; 24,[9, 6, 11, 6]; 25,[0, 20, 7, 14]; 26,[20, 8, 17, 16]; 27,[8, 9, 6, 5]; 28,[9, 10, 16, 0]; 29,[20, 8, 11, 5]; 30,[6, 15, 0, 6]; 31,[14, 1, 17, 15]; 32,[4, 0, 3, 20]; 33,[16, 6, 1, 4]; 34,[14, 8, 15, 6]; 35,[6, 14, 8, 1]; 36,[16, 7, 20, 16]; 37,[0, 19, 12, 1]; 38,[6, 14, 17, 20]; 39,[8, 6, 11, 11]; 40,[19, 8, 8, 20]; 41,[11, 1, 6, 2]; 42,[6, 11, 20, 5]; 43,[10, 7, 1, 5]; 44,[14, 20, 8, 9]; 45,[16, 13, 3, 8]; 46,[1, 19, 19, 5]; 47,[16, 19, 3, 0]; 48,[7, 5, 13, 0]; 49,[1, 9, 5, 20]; 50,[18, 7, 10, 11]; 51,[17, 12, 4, 3]; 52,[4, 17, 6, 9]; 53,[14, 7, 9, 6]; 54,[13, 0, 3, 13]; 55,[18, 6, 9, 7]; 56,[7, 8, 1, 0]; 57,[7, 0, 0, 18]; 58,[0, 7, 4, 20]; 59,[16, 10, 19, 3]; 60,[16, 3, 7, 9]; 61,[17, 6, 2, 17]; 62,[15, 7, 1, 8]; 63,[0, 6, 7, 0]; 64,[0, 8, 8, 15]; 65,[7, 1, 8, 6]; 66,[0, 4, 5, 6]; 67,[5, 1, 12, 13]; 68,[5, 2, 16, 13]; 69,[3, 10, 9, 5]; 70,[12, 6, 0, 7]; 71,[3, 7, 17, 11]; 72,[7, 6, 16, 14]; 73,[2, 18, 15, 0]; 74,[0, 0, 3, 10]; 75,[11, 16, 6, 4]; 76,[11, 7, 0, 18]; 77,[8, 3, 0, 1]; 78,[19, 3, 0, 14]; 79,[7, 12, 1, 4]; 80,[9, 5, 3, 3]; 81,[0, 13, 3, 1]; 82,[6, 3, 5, 16]; 83,[1, 11, 9, 0]; 84,[20, 10, 1, 8]; 85,[11, 6, 0, 13]; 86,[14, 8, 0, 20]; 87,[20, 0, 6, 11]; 88,[1, 11, 6, 8]; 89,[11, 19, 11, 16]; 90,[16, 14, 15, 17]; 91,[19, 13, 6, 9]; 92,[1, 14, 11, 8]; 93,[16, 12, 8, 7]; 94,[9, 18, 13, 11]; 95,[19, 7, 6, 13]; 96,[9, 16, 12, 6]; 97,[3, 3, 20, 6]; 98,[18, 7, 13, 5]; 99,[19, 9, 6, 11]";

	static List<Piece> puzzle_parts;
	
	private Slot board[][];
	
	public PuzzleBoard(){
		board = new Slot[12][12];
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				
				if (i != 0 && i != 11 && j != 0 && j != 11)
					board[i][j] = new Slot(false);
				else {
					//System.out.println("slot " + i +"," + j+ " is an edge!" );
					board[i][j] = new Slot(true);
				}
			}
		}
	}
	
	public void setBoardRequirements(){
		
		for (int i = 1; i < board.length - 1; i++){
			for (int j = 1; j < board[0].length - 1; j++){
			
				setSlotRequirements(i, j);
			}
		}
	}
	
	public void setSlotRequirements(int i, int j){
		Slot slot = board[i][j];
		AdjacencyyRequirement r = slot.getRequirement();
		

		
		// edges
		if (board[i - 1][j].isEdge())
			r.setNorth(0);
		if (board[i + 1][j].isEdge())
			r.setSouth(0);
		if (board[i][j - 1].isEdge())
			r.setWest(0);
		if (board[i][j + 1].isEdge())
			r.setEast(0);
		

		
		// neighbors
		// above me
		if (i > 1 && board[i - 1][j].getChosen() != null)
			r.setNorth(board[i - 1][j].getChosen().getSouth());
		// below me
		if (i < 10 && board[i + 1][j].getChosen() != null)
			r.setSouth(board[i + 1][j].getChosen().getNorth());
		// left of me
		if (j > 1 && board[i][j - 1].getChosen() != null)
			r.setWest(board[i][j - 1].getChosen().getEast());
		// right of me
		if (j < 10 && board[i][j + 1].getChosen() != null)
			r.setEast(board[i][j + 1].getChosen().getWest());
	}
	
	
	public Piece choose(Slot s){
		for (Piece p : puzzle_parts){
			if (p.meetsRequirement(s.getRequirement()) && p.meetsRequirements(s.getBad_choices()) && !p.get_Is_set()){
				//System.out.println("Returning:\n" + p);
				return p;
				
				
			}
		}
		return null;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		List<String> puzzle_parts_strings = new ArrayList<String>(Arrays.asList(input.split(";")));
		puzzle_parts = new ArrayList<>();
		for (String s : puzzle_parts_strings){
			puzzle_parts.add(new Piece(s));
			
			
		}
		
		PuzzleBoard puzzle = new PuzzleBoard();
		puzzle.setBoardRequirements();
		
		for (int i = 1; i < puzzle.board.length - 1;){

				
			for (int j = 1; j < puzzle.board[0].length - 1;){

				//puzzle.setBoardRequirements();
				puzzle.setSlotRequirements(i, j);
				Slot s = puzzle.board[i][j];
				
				if (s.isEdge()) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
					if (j + 1 < 11)
						j++;
					else if (i + 1 < 11){
						i++;
						j = 1;
					}
					continue;
					
				}

				System.out.println("Req = " + s.getRequirement());
				
				Piece chosen = puzzle.choose(s);
				if (chosen != null){
					s.setChosen(chosen);
					System.out.println("Choice for i="+i+" and j="+j+" :\n " + chosen );
					if (j + 1 < 11)
						j++;
					else if (i + 1 < 11){
						i++;
						j = 1;
					}
					else {
						System.out.println("Done!");
						
						for (i = 1; i < puzzle.board.length - 1; i++){
							for (j = 1; j < puzzle.board[0].length - 1; j++){
								s = puzzle.board[i][j];
								chosen = s.getChosen();
								System.out.print(chosen.getID() + "," + chosen.getRotation() + "; ");
							}
						}

						return;
					}
					
//					try {
//						System.in.read();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					continue;
				}
				else {
					// nothing fits, go back and revert previous choice, add to list of bad choices
					// first remove choice from this slot and empty this slots bad choice list, state is changing
					s.getBad_choices().removeAll(s.getBad_choices());
					s.setChosen(null);
					if (j - 1 > 0)
						j--;
					else if (i - 1 > 0){
						i--;
						j = 10;
					}
					else{
						System.out.println("BAD!!!!!!!");
						for (Piece p : puzzle_parts){
							if (p.get_Is_set()){
								System.out.println(p.getID());
							}
						}
						return;
					}
					
					
					Slot prev_slot = puzzle.board[i][j];
					// revert previous choice and document it as bad. 
					
					Piece bad_choice = prev_slot.getChosen();
					prev_slot.getBad_choices().add(new ExclusionRequirement(bad_choice));
					prev_slot.setChosen(null);
					System.out.println("Bad choice for i="+i+" and j="+j+" => ");
					for (Requirement pp : prev_slot.getBad_choices()){
							 System.out.print(pp + ", " );
					}
					System.out.println();
					
						
					continue;
					
				}
				
				
			}
		}
		
	
		
		
		
		
		
//		for (Piece p : puzzle_parts){
//			System.out.println(p);
//		}


	}

}

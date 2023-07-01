import java.util.Random;
import java.util.Scanner;

class TicTao{
	static char board [][];
	public TicTao() {
		board=new char[3][3];
		initBoard();
	}
	void initBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=' ';
			}
		}
	}
	static void displayBoard() {
		System.out.println("----------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("----------------");
		}
	}
	static void placeMark(int row,int column,char alp) {
		if(row>=0 && row<=2 && column>=0 && column<=2) {
			board[row][column]=alp;
		}
		else {
			System.out.println("Invalid Input");
		}
	}
	static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0] !=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkColumnWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j] !=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkDiagnoal() {
		if((board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] )|| board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
			return true;
		}
		return false;
	}
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ')
					return false;
			}
		}
		return true;
	}
}


abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row,int column) {
		if(row>=0 && row<=2 && column>=0 && column<=2) {
			if(TicTao.board[row][column] ==' ') {
					return true;
		}
		}
		return false;
	}
}

class HumanPlayer extends Player{
	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter row & column ");
		int row,column;
		do {
			row=sc.nextInt();
			column=sc.nextInt();
		}while(!isValidMove(row,column));
		TicTao.placeMark(row, column, mark);
	}
	
}

class AIPlayer extends Player{
	AIPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Random random = new Random();
		System.out.println("Enter row & column ");
		int row,column;
		do {
			row=random.nextInt(3);
			column=random.nextInt(3);
		}while(!isValidMove(row,column));
		TicTao.placeMark(row, column, mark);
	}
}

public class LaunchGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTao ticTao = new TicTao();
		HumanPlayer p1=new HumanPlayer("Bob", 'X');
		AIPlayer p2=new AIPlayer("Jim", 'O');
		Player cp;
		cp=p1;
		while(true) {
			System.out.println(cp.name +" turn");
			cp.makeMove();
			TicTao.displayBoard();
			if(TicTao.checkRowWin() || TicTao.checkColumnWin() || TicTao.checkDiagnoal()) {
				System.out.println(cp.name +" has won");
				break;
			}
			else if(ticTao.checkDraw()) {
				System.out.println("Game is a Draw");
				break;
			}
			else {
				if(cp==p1) {
					cp=p2;
				}else {
					cp=p1;
				}
				
			}
		}
	}
}
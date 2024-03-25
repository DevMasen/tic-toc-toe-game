import java.util.Objects;
import java.util.Scanner;


public class XO {//Main Class for XO Board
    ////////Attributes//////////
    private final char free=' ';
    private final char[][] board={ {free,free,free},  //Array for board values
                                   {free,free,free},
                                   {free,free,free}};
    private final String error ="Something Wrong! Try again."; //Error massage

    ////////Methods//////////
    public void display(){ //Method for display board
        System.out.format( "    A       B       C   \n"+
                           "        |       |       \n"+
                           "1   %c   |   %c   |   %c   \n"+
                           "        |       |       \n"+
                           " -----------------------\n"+
                           "        |       |       \n"+
                           "2   %c   |   %c   |   %c   \n"+
                           "        |       |       \n"+
                           " -----------------------\n"+
                           "        |       |       \n"+
                           "3   %c   |   %c   |   %c   \n"+
                           "        |       |       \n",board[0][0],board[0][1],board[0][2],
                                                        board[1][0],board[1][1],board[1][2],
                                                        board[2][0],board[2][1],board[2][2]);
    }
    public boolean is_free(){ //Method for checking board for empty house
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j]==free) return true;
            }
        }
        return false;
    }
    public int winner(){//Method for specifying the winner
        for (int row = 0; row<3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'X')
                    return +1;
                else if (board[row][0] == 'O')
                    return -1;
            }
        }
        for (int col = 0; col<3; col++)
        {
            if (board[0][col]==board[1][col] && board[1][col]==board[2][col])
            {
                if (board[0][col]=='X')
                    return +1;
                else if (board[0][col]=='O')
                    return -1;
            }
        }
        if (board[0][0]==board[1][1] && board[1][1]==board[2][2])
        {
            if (board[0][0]=='X')
                return +1;
            else if (board[0][0]=='O')
                return -1;
        }
        if (board[0][2]==board[1][1] && board[1][1]==board[2][0])
        {
            if (board[0][2]=='X')
                return +1;
            else if (board[0][2]=='O')
                return -1;
        }
        return 0;
    }
    public Index check_best(char xo){//Method for option 1 player that checks for best move on rows, columns and diameters.
        Index ind=null;
        for (byte i = 0; i < 3; i++) {
            if(board[i][0]==xo && board[i][1]==xo && board[i][2]==free){
                ind =new Index();
                ind.i_ind=i;
                ind.j_ind=2;
                break;
            }
            else if(board[i][0]==xo && board[i][1]==free && board[i][2]==xo){
                ind=new Index();
                ind.i_ind=i;
                ind.j_ind=1;
                break;
            }
            else if (board[i][0]==free && board[i][1]==xo && board[i][2]==xo) {
                ind=new Index();
                ind.i_ind=i;
                ind.j_ind=0;
                break;
            }
        }
        if(ind==null){
            for (byte j = 0; j < 3; j++) {
                if(board[0][j]==xo && board[1][j]==xo && board[2][j]==free){
                    ind =new Index();
                    ind.i_ind=2;
                    ind.j_ind=j;
                    break;
                }
                else if(board[0][j]==xo && board[1][j]==free && board[2][j]==xo){
                    ind=new Index();
                    ind.i_ind=1;
                    ind.j_ind=j;
                    break;
                }
                else if (board[0][j]==free && board[1][j]==xo && board[2][j]==xo) {
                    ind=new Index();
                    ind.i_ind=0;
                    ind.j_ind=j;
                    break;
                }
            }
        }
        if(ind==null){
            if(board[0][0]==xo && board[1][1]==xo && board[2][2]==free){
                ind=new Index();
                ind.i_ind=2;
                ind.j_ind=2;
            }
            if(board[0][0]==xo && board[1][1]==free && board[2][2]==xo){
                ind=new Index();
                ind.i_ind=1;
                ind.j_ind=1;
            }
            if(board[0][0]==free && board[1][1]==xo && board[2][2]==xo){
                ind=new Index();
                ind.i_ind=0;
                ind.j_ind=0;
            }
            if(board[0][2]==xo && board[1][1]==xo && board[2][0]==free){
                ind=new Index();
                ind.i_ind=2;
                ind.j_ind=0;
            }
            if(board[0][2]==xo && board[1][1]==free && board[2][0]==xo){
                ind=new Index();
                ind.i_ind=1;
                ind.j_ind=1;
            }
            if(board[0][2]==free && board[1][1]==xo && board[2][0]==xo){
                ind=new Index();
                ind.i_ind=0;
                ind.j_ind=2;
            }
        }
        return ind;
    }
    public Index best_move(){//Method that returns the index for best move through method check_best().
        Index ind= this.check_best('O');
        if(ind==null) ind= this.check_best('X');
        if(ind==null) {
            for (byte i = 0; i < 3 ; i++) {
                for (byte j = 0; j < 3; j++) {
                    if(board[i][j]==free) {
                        ind =new Index();
                        ind.i_ind=i;
                        ind.j_ind=j;
                        break;
                    }
                }
                if(ind!=null) break;
            }
        }
        return ind;
    }
    public void insert(Index ind,char xo){//Method that insert an X or O character in an index
        board[ind.i_ind][ind.j_ind]=xo;
    }
    public boolean player_move(Byte r, String c, char x_o){//Method for inserting user input in board through method insert()
        byte i,j;
        if(Objects.equals(c, "A") || Objects.equals(c, "a")) j=0;
        else if(Objects.equals(c, "B")|| Objects.equals(c, "b")) j=1;
        else if (Objects.equals(c, "C")|| Objects.equals(c, "c")) j=2;
        else {
            return false;
        }
        if(r==1) i=0;
        else if (r==2) i=1;
        else if(r==3) i=2;
        else{
            return false;
        }
        Index in=new Index();
        in.i_ind=i;
        in.j_ind=j;
        if(board[i][j]==' ') this.insert(in, x_o);
        else {
            return false;
        }
        return true;
    }
    public boolean x_compact(byte r, String c){//Method for inserting X character in board through method player_move()
        boolean result=this.player_move(r, c, 'X');
        if(!result){
            System.out.println(error);
            return false;
        }
        return this.winner() == 0;
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);//Define new object of class Scanner
        System.out.format("Welcome to XO Game!\nInput Shape: row(1, 2 or 3) column(A, B or C) eg:2 B\n" +
                          "1-One Player\n2-Two Players\n");//Show Information about user input
        byte opt=input.nextByte();//Creating option 1 player and 2 players
        XO BB=new XO();
        if(opt==1){//One player
            System.out.print("So, let's play!\nI am O and you are X.\n");
            while(BB.is_free() && BB.winner()==0){
                BB.display();
                System.out.print("Choose a position in : ");
                byte row=input.nextByte();
                String col=input.next();
                boolean R=BB.x_compact(row, col);
                if(!R) continue;
                if(BB.is_free()) {
                    Index in2 = BB.best_move();
                    BB.insert(in2, 'O');
                }
            }
            ///Choosing Winner///
            BB.display();
            if(BB.winner()==1) System.out.println("You Won!");
            else if (BB.winner()==-1) System.out.println("I Won!");
            else System.out.println("Even!");
        }
        else if(opt==2){//Two player
            System.out.println("Ok!, Player 1 (X) and Player 2 (O).");
            while (BB.is_free() && BB.winner()==0){
                BB.display();
                System.out.print("Player 1 (X) tern: ");
                byte row=input.nextByte();
                String col=input.next();
                boolean R=BB.x_compact(row, col);
                if(!R) continue;
                if(BB.is_free()){
                    while(true) {
                        BB.display();
                        System.out.print("Player 2 (O) tern: ");
                        row = input.nextByte();
                        col = input.next();
                        boolean Re = BB.player_move(row, col, 'O');
                        if (!Re) {
                            System.out.println(BB.error);
                        }
                        else break;
                    }
                }
            }
            ///Choosing Winner///
            BB.display();
            if(BB.winner()==1) System.out.println("Player 1 (X) Won!");
            else if(BB.winner()==-1) System.out.println("Player 2 (O) Won!");
            else System.out.println("Even!");
        }
        else System.out.println(BB.error);//Error Handling for option input
    }
}
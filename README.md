# Chess-Game
New version of chess game with additional set of instructions.

- Game page
The game board is a 5 x 5 table. At the beginning of the game, the pieces are placed as follows:

![image](https://user-images.githubusercontent.com/118456195/211197304-0151d2ff-0c3b-4424-8b8a-7adee2ae21e8.png)

(names of white pieces from right to left: spear, elephant, silver general, golden general, king)
Black pieces are on top, and facing each king is a pawn.

Types of beads:
●Soldier piece: It can only move forward one space and if there is a piece in its way, it hits. 

●Spear: Moves forward as much as desired. 

●King's Piece: Moves one move away in any desired house. 

●Elephant's neck: it moves diagonally (crosswise) as much as desired, but only in the front direction. 

●Silver General: Moves in any direction of one house, except side and back. 
![image](https://user-images.githubusercontent.com/118456195/211197487-94e557f2-8b38-48c5-b081-da168131790f.png)

●Golden General: Moves a house in any desired direction except the rear right and rear left directions.

![image](https://user-images.githubusercontent.com/118456195/211197498-8ce7501e-69a2-42b0-b8b2-6a9b2b97414e.png)

Rules of the game
1. The general rules of the game are similar to chess, but differ in some details. You can't jump over the pieces and you can hit the opponent's piece by going to that house. Each player makes a move in each turn. Black starts first.
2. Win mode: The first player to hit the opponent's king wins. It is also possible to beat the opponent's king with the king.
3. Promotion: some pieces will be promoted when they reach each of the last two rows of the table and their movement pattern will change:

Soldier: Moves like the Golden General.

Spear: It becomes a face and can move in vertical directions (front, back, left and right) as much as you want.

Elephant: It can either move diagonally as much as desired or vertically up to one house.

Silver General: You can take a maximum of two steps in a desired direction. (But it does not jump from a bead).

4. Placing a checker: The checkers that are hit by the opponent are added to the hand of the player who hit them and turn the checker into its own color.
He can plant that piece in an empty square on the next turns.
5. If the hit piece was promoted, it is added to the hand of the player who hit it in normal form (not promoted).
6. By planting a bead in the last two rows, the bead will not be promoted.

Input and output format in the console:
The name of the bead and the corresponding Latin letter: the elephant (B), the soldier (P), the silver general (S), the golden general (G),
king (K), spear (L)

The game starts from the initial layout. Each game move is input in the following format:
PieceLetter GridFrom GridTo

That pieceLetter corresponds to the table above. If the bead is white, this letter should be printed in Capital letter and otherwise in Small letter.
The coordinates of the houses are written in the form of a two-digit number, where the first digit indicates the column and the second digit indicates the row. Each digit is between 1 and 5. The bottom left house is page 11.

EXAPLE:
K 21 22
This means that the white king should go from column two row one to column two row two.

If the player is going to plant a piece from his hand, he declares the starting house as 00.

The code is required to make a move every time by taking each input line and output the new state of the game, which includes the state of the board and the players' hands, in the following format:
In the first line, it prints the expanded page, that is, starting from the first row, it stacks the rows together.

The output of each row is as follows: if the house is empty, the dash character is printed; otherwise, the Latin letter of the checker (in accordance with the given table) is printed. If a checker belongs to the white player, with a capital letter, and otherwise The face should be printed in small letters.
In the second and third line, we output the pieces that each of the black and white players have hit from the opponent. 
The pieces in each player's hand are of the same color as that player. If there is no piece in a player's hand, we print an empty line. 
The order of the printed beads is the same as the order in which they were added.


EXAMPLE:
-GSB-K---L---------p--sgk
pb
L
Note that some movements may be illegal. In this case, the same game state should be output without change.
If a player wins, the program should declare the winning player as follows and terminate itself:
"black wins!"

Also, the program terminates with input 0.

Graphical interface:
In the graphical interface, we show the game board and the pieces on the board with the right arrangement, and we also display the pieces of each player's hand next to the game board.
The player must be able to move the pieces with the help of the mouse pointer.
At the end of the game, the player must receive a message to announce the winner.



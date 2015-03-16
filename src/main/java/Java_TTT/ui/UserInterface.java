package Java_TTT.ui;

import Java_TTT.boards.Board;
import Java_TTT.participants.GameParticipants;

public interface UserInterface {

    public void printMessage(String string);

    public String captureChoice();

    public void chooseGameConfiguration();

    public void promptForBoardSize();

    public void printChoice(GameParticipants player, String choice);

    public void printWelcomeMessage(int boardSize);

    public void chooseStartingPlayer(String player1, String player2);

    public void printGamePieceAssignment(GameParticipants player1, GameParticipants player2);

    public void printBoard(Board board);

    public void printError(String choice);

    public void printWinner(String gamePiece);

    public void printCatsGame();
}
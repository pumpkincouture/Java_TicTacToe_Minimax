package Java_TTT.games.setup;

import Java_TTT.boards.Board;
import Java_TTT.rules.FourByFourBoardRules;
import Java_TTT.rules.TTTBoardRules;
import Java_TTT.rules.ThreeByThreeBoardRules;
import Java_TTT.ui.CommandLineInterface;

public class BoardSetUp implements Configurable {
    private CommandLineInterface ui;
    private TTTBoardRules boardRules;
    private Board board;
    private int boardSize;

    public BoardSetUp(CommandLineInterface ui) {
        this.ui = ui;
    }

    @Override
    public void getConfiguration() {
        ui.promptForBoardSize();
        validateBoardSizeChoice(ui.captureChoice());
    }

    public void validateBoardSizeChoice(String boardSizeChoice) {
        switch(boardSizeChoice) {
            case "3":
                boardSize = convertAnswerToInteger(boardSizeChoice);
                board = new Board(boardSize);
                boardRules = new ThreeByThreeBoardRules(board);
                break;
            case "4":
                boardSize = convertAnswerToInteger(boardSizeChoice);
                board = new Board(boardSize);
                boardRules = new FourByFourBoardRules(board);
                break;
            default:
                ui.printError(boardSizeChoice);
                ui.promptForBoardSize();
                validateBoardSizeChoice(ui.captureChoice());
        }
    }

    private int convertAnswerToInteger(String answer) {
        return Integer.parseInt(answer);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public TTTBoardRules getBoardRules() {
        return boardRules;
    }

    public Board getBoard() {
        return board;
    }
}

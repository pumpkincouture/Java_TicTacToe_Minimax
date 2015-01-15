package Java_TTT;

import java.util.*;

public class AIComputerPlayer extends Player {
    private GameScorer gameScorer;
    private int choice;

    public AIComputerPlayer(String gamePiece) {
        super(gamePiece);
    }

    @Override
    public String getMove(Board board) {
        minimax(board, 0, this.getGamePiece());
        return convertChosenIndexToString(choice);
    }

    private int minimax(Board board, int depth, String gamePiece) {
        gameScorer = new GameScorer(board);
        ArrayList<Integer> scores = new ArrayList();
        ArrayList<Integer> moves = new ArrayList();
        depth += 1;

        if (isGameOver(board)) {
            return getScores(board, depth);
        }

        for (Integer openSpace: board.getOpenSpaces()) {
            board.placeMove(convertChosenIndexToString(openSpace), gamePiece);
            scores.add(minimax(board, depth, switchPlayers(board, gamePiece)));
            moves.add(openSpace);
            board.clearBoard(openSpace);
        }

        if (gamePiece == this.getGamePiece()) {
            int max_score = maxIndex(scores);
            choice = moves.get(max_score);
            return scores.get(max_score);
        }

        else {
            int min_score = minIndex(scores);
            choice = moves.get(min_score);
            return scores.get(min_score);
        }
    }

    private static int getMaxValue(ArrayList<Integer> scoresList) {
        int maxValue = Collections.max(scoresList);
        return maxValue;
    }

    private static int getMinValue(ArrayList<Integer> scoresList) {
        int minValue = Collections.min(scoresList);
        return minValue;
    }

    private static Integer maxIndex(ArrayList<Integer> list) {
        List<Integer> index = new ArrayList();
        int max =  getMaxValue(list);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == max) {
                index.add(i);
            }
        }
        return index.get(0);
    }

    private static Integer minIndex(ArrayList<Integer> list) {
        List<Integer> index = new ArrayList();
        int min = getMinValue(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == min) {
                index.add(i);
            }
        }
        return index.get(0);
    }

    private int getScores(Board board, int depth) {
        if (isComputerWinner(board)) {
            return 10 - depth;
        } else if (isOpponentWinner(board)) {
            return depth - 10;
        }
        return 0;
    }

    private boolean isOpponentWinner(Board board) {
        if (getGameWinner(board) == "") {
            return false;
        } else if (getGameWinner(board) != this.getGamePiece()) {
            return true;
        }
        return false;
    }

    private boolean isComputerWinner(Board board) {
        return getGameWinner(board) == this.getGamePiece();
    }

    private boolean isGameOver(Board board) {
        return gameScorer.isGameOver(board.getOpponentPiece(this.getGamePiece()), this.getGamePiece());
    }

    private String switchPlayers(Board board, String gamePiece) {
        return gamePiece == this.getGamePiece() ? board.getOpponentPiece(gamePiece) : this.getGamePiece();
    }

    private String getGameWinner(Board board) {
        return gameScorer.getWinningPlayer(board.getOpponentPiece(this.getGamePiece()), this.getGamePiece());
    }

    private String convertChosenIndexToString(int chosenSpace) {
        return Integer.toString(chosenSpace + 1);
    }
}

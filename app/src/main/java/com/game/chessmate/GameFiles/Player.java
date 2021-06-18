package com.game.chessmate.GameFiles;

import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceColour;

import java.util.ArrayList;

/**
 * The type Player.
 */
public class Player {

    private ArrayList<ChessPiece> chessPiecesAlive;
    private ArrayList<ChessPiece> chessPiecesCaptured;
    private ArrayList<Field> legalMovesForCheat;
    private ArrayList<Field> legalMovesSelected;
    private Field lastSelectedField;
    private ChessPieceColour color;
    private Card[] playerCards;
    private boolean cheatOn;
    private float lightValue;
    private boolean wasLeganMove;
    private int timesCheatFunktionUsedWrongly;

    /**
     * Instantiates a new Player.
     *
     * @param color the color of the Player's ChessPieces
     */
    public Player(ChessPieceColour color) {
        this.chessPiecesAlive = new ArrayList<>();
        this.chessPiecesCaptured = new ArrayList<>();
        this.legalMovesSelected = new ArrayList<>();
        this.lastSelectedField = null;
        this.color = color;
        this.cheatOn = false;
        this.wasLeganMove = true;
        this.timesCheatFunktionUsedWrongly = 0;
    }


    /**
     * Gets cheat on.
     *
     * @return the cheat on
     */
    public boolean getCheatOn() {
        return cheatOn;
    }

    /**
     * Sets cheat on.
     *
     * @param cheatOn the cheat on
     */
    public void setCheatOn(boolean cheatOn) {
        this.cheatOn = cheatOn;
    }

    /**
     * Add chess pieces alive.
     *
     * @param piece the piece
     */
    public void addChessPiecesAlive(ChessPiece piece) {
        this.chessPiecesAlive.add(piece);
    }

    /**
     * Remove chess pieces alive.
     *
     * @param piece the piece
     */
    public void removeChessPiecesAlive(ChessPiece piece) {
        this.chessPiecesAlive.remove(piece);
    }

    /**
     * Add chess pieces captured.
     *
     * @param piece the piece
     */
    public void addChessPiecesCaptured(ChessPiece piece) {
        this.chessPiecesCaptured.add(piece);
    }

    /**
     * Remove chess pieces captured.
     *
     * @param piece the piece
     */
    public void removeChessPiecesCaptured(ChessPiece piece) {
        this.chessPiecesCaptured.remove(piece);
    }

    /**
     * Gets chess pieces alive.
     *
     * @return the chess pieces alive
     */
    public ArrayList<ChessPiece> getChessPiecesAlive() {
        return chessPiecesAlive;
    }

    /**
     * Gets chess pieces captured.
     *
     * @return the chess pieces captured
     */
    public ArrayList<ChessPiece> getChessPiecesCaptured() {
        return chessPiecesCaptured;
    }

    /**
     * Gets legal moves selected.
     *
     * @return the legal moves selected
     */
    public ArrayList<Field> getLegalMovesSelected() {
        return legalMovesSelected;
    }

    /**
     * Gets last selected field.
     *
     * @return the last selected field
     */
    public Field getLastSelectedField() {
        return lastSelectedField;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public ChessPieceColour getColor() {
        return color;
    }

    /**
     * Sets legal moves selected.
     *
     * @param legalMovesSelected the legal moves selected
     */
    public void setLegalMovesSelected(ArrayList<Field> legalMovesSelected) {
        this.legalMovesSelected = legalMovesSelected;
    }

    /**
     * Sets last selected field.
     *
     * @param lastSelectedField the last selected field
     */
    public void setLastSelectedField(Field lastSelectedField) {
        this.lastSelectedField = lastSelectedField;
    }

    /**
     * Gets legal moves for cheat.
     *
     * @return the legal moves for cheat
     */
    public ArrayList<Field> getLegalMovesForCheat() {
        return legalMovesForCheat;
    }

    /**
     * Sets legal moves for cheat.
     *
     * @param legalMovesForCheat the legal moves for cheat
     */
    public void setLegalMovesForCheat(ArrayList<Field> legalMovesForCheat) {
        this.legalMovesForCheat = legalMovesForCheat;
    }

    /**
     * Set cards.
     *
     * @param cards the cards
     */
    public void setCards(Card[]cards){playerCards= cards;}

    /**
     * Get current cards card [ ].
     *
     * @return the card [ ]
     */
    public Card[] getCurrentCards(){return playerCards;}

    /**
     * Gets light value.
     *
     * @return the light value
     */
    public float getLightValue() {
        return lightValue;
    }


    /**
     * Sets light value.
     *
     * @param lightValue the light value
     */
    public void setLightValue(float lightValue) {
        this.lightValue = lightValue;
    }

    /**
     * Gets was legan move.
     *
     * @return the was legan move
     */
    public boolean getWasLeganMove() {
        return wasLeganMove;
    }

    /**
     * Sets was legan move.
     *
     * @param wasLeganMove the was legan move
     */
    public void setWasLeganMove(boolean wasLeganMove) {
        this.wasLeganMove = wasLeganMove;
    }

    /**
     * Gets times cheat funktion used wrongly.
     *
     * @return the times cheat funktion used wrongly
     */
    public int getTimesCheatFunktionUsedWrongly() {
        return timesCheatFunktionUsedWrongly;
    }

    /**
     * Sets times cheat funktion used wrongly.
     *
     * @param timesCheatFunktionUsedWrongly the times cheat funktion used wrongly
     */
    public void setTimesCheatFunktionUsedWrongly(int timesCheatFunktionUsedWrongly) {
        this.timesCheatFunktionUsedWrongly = timesCheatFunktionUsedWrongly;
    }
}

package com.game.chessmate.GameFiles;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.game.chessmate.GameActivity;
import com.game.chessmate.GameFiles.Networking.NetworkTasks;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;
import com.game.chessmate.R;

import java.util.ArrayList;

/**
 * The type Card.
 */
public class Card {

    //Characteristics of card
    private final int id;
    private final String name;
    private final String desc;
    private final String useCase;
    private boolean continuingEffectUntilEnd=false;
    private boolean continuingEffectUntilCaptured=false;
    private boolean owned;

    /**
     * The Context.
     */
//View and Sound
    Context context;
    private final int drawableId;
    private MediaPlayer fieldManipulationSound;

    /**
     * The Legal moves.
     */
    ArrayList<Field> legalMoves;
    /**
     * The Current fields.
     */
    Field [][] currentFields;

    /**
     * Instantiates a new Card.
     *
     * @param id      the id
     * @param context the context
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Card (int id, Context context) throws IllegalArgumentException{
        this.id=id;
        this.context=context;

        //create card depending on id
        switch (id){
            case 0:
                name="Cowardice";//geht
                desc="Move one of your opponent's pawns one or two squares backward. It may not enter or cross an occupied square.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.cowardice;
                break;

            case 1:
                name="Dark Mirror";//geht
                desc="On this move, one of your pawns can capture by moving diagonally backward instead of forward.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.dark_mirror;
                break;

            case 2:
                name="Death Dance";//geht
                desc="Exchange the position of any of your pieces with any adjacent enemy piece.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.death_dance;
                break;

            case 3: //? Bauer ist nicht wirklich weg?
                name="Disintegration";
                desc="Remove one of your own pawns from the chessboard, and set it aside. It is now dead, and cannot be brought back into play with another card.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.disintegration;
                break;

            case 4:
                name="Champion";//geht
                desc="Any one knight in play becomes a Champion. Place a marker underneath it. Instead of jumping like a knight, to the opposite corner of a 2 by 3 rectangle, a Champion jumps to the opposite corner of a 3 by 4 rectangle.";
                useCase="[i] Play this card instead of your move. Continuing Effect until piece is captured.";
                continuingEffectUntilCaptured=true;
                drawableId=R.drawable.champion;
                break;

            case 5:
                name="Rebirth"; //? Wenn ich auf dem Feld stehe, wird Figur nicht entfernt? geht
                desc="Move one enemy piece to any square it could have occupied at the beginning of the game. The square must be empty or contain one of your pieces. If one of your pieces is in the square, it is captured.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.rebirth;
                break;

            case 6:
                name="Revelation";
                desc="Replace one of your knights or one of your opponent's knights by a bishop owned by the same player.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.revelation;
                break;

            case 7:
                name="Long Jump";
                desc="Move one of your knights to any square whose color is different from the one it currently occupies. You cannot capture a piece with this move.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.long_jump;
                break;

            case 8:
                name="Lost Castle";//geht
                desc="Swap the positions of one of your rooks and one of your opponent's rooks.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.lost_castle;
                break;

            case 9:
                name="Mystic Shield";
                desc="The piece you just moved cannot be captured by your opponent on his next turn. If you moved more than one piece, you must designate only one to be protected.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.mystic_shield;
                break;

            case 10:
                name="Forbidden City";
                desc="Place a marker in any unoccupied square. No piece can enter this square, or pass through it, for the rest of the game. Knights and other \"jumping\" pieces may still pass over it.";
                useCase="[i] Play this card instead of your move. Continuing Effect until the end of game.";
                continuingEffectUntilEnd=true;
                drawableId=R.drawable.forbidden_city;
                break;

            case 11:
                name="Holy Quest";
                desc="Swap the positions of a bishop and a knight belonging to your opponent.";
                useCase="[i] Play this card instead of your move";
                drawableId=R.drawable.holy_quest;
                break;

            case 12:
                name="Vulture";
                desc="Take the last card played by your opponent and put it in your hand. (If each player has his own deck of cards, you must also discard your top un-drawn card.)";
                useCase="[i] Play this card immediately after your opponent plays a card. You can still make a move";
                drawableId=R.drawable.vulture;
                break;

            case 13:
                name="Crusade";
                desc="Play this card when you move a bishop without capturing a piece. This bishop immediately moves one more time.";
                useCase="[i] Play this card before your bishop's first move.";
                drawableId=R.drawable.crusade;
                break;

            case 14:
                name="Man of Straw";
                desc="Play this card when your king is in check (even checkmate). It swaps positions with any one of your pawns, as long as the new position does not place it in check.";
                useCase="[i] Play this card before your move.";
                drawableId=R.drawable.man_of_straw;
                break;

            case 15:
                name="Bombard";
                desc="On this move, one of your rooks can move in its normal straight line, jump over any piece or one obstruction on the board, and continue in a straight line. The piece or obstruction is not affected by being jumped. At the end of its move, the rook may make a normal capture.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.bombard;
                break;

            case 16:

                /*name="Spoils of War";
                desc="Play this card when you capture one of your opponent's pieces. The capturing piece changes permanently into a piece of the kind it captured. For instance, if one of your pawns captures a knight, it becomes a knight.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.spoils_of_war;
                break;*/

            case 17:

                /*name="Martyr";
                desc="Play this card when one of your bishops has the choice of taking two or more of your opponent's pieces. Capture as many of these pieces as you want (at least two). Your bishop is removed from play and regarded as captured.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.martyr;
                break;*/

            case 18:
                name="Funeral Pyre";
                desc="All captured pieces of both players are now considered dead. They cannot be returned to the chessboard through the play of a card.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.funeral_pyre;
                break;

            case 19:
                name="Hand of Fate";
                desc="Exchange your hand with your opponent's. He must draw another card to replace this one.";
                useCase="[i] Play this card before your move.";
                drawableId=R.drawable.hand_of_fate;
                break;

            case 20:
                name="Abduction";
                desc="Your opponent must look away from the board for ten seconds, as you remove any one of his pieces except the king. He then has ten seconds to look at the board and state what piece you removed and which square it occupied. If he remembers correctly, the piece is put back in its place. If not, it is captured.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.abduction;
                break;

            case 21:
                name="Fog of War";
                desc="This card cancels the effect of any other card. Both cards are discarded. If the opposing card constituted your opponent's whole move, he may make another move, but he may not play another card.";
                useCase="[i] Play this card immediately after your opponent has played the card you want to cancel.";
                drawableId=R.drawable.fog_of_war;
                break;

            case 22:
                name="Think Again";
                desc="Your opponent's move is cancelled. He must make a different move, with the same piece or with another one. If he used a card, he may take it back.";
                useCase="[i] Play this card immediately after your opponent's turn.";
                drawableId=R.drawable.think_again;
                break;

            case 23:
                name="Coup";
                desc="Your king becomes a prince for the remainder of the game. A prince moves like a king but can be captured. Choose one of your pieces, except a rook or a queen, and mark it. This piece keeps its standard move, but is the new king, and your opponent will win if he checkmates it.";
                useCase="[i] Play this card immediately after your move. Continuing Effect until the end of game";
                continuingEffectUntilEnd=true;
                drawableId=R.drawable.coup;
                break;

            case 24:
                name="Confabulation";
                desc="Make an otherwise legal move which puts two of your pieces (other than kings) on the same square. They \"merge\" into a new piece. It can move and capture like either one of them, and is affected by any card that affects either of them. Move the two pieces together. Confabulated pawns cannot promote.";
                useCase="[i] Play this card on your turn, instead of making a regular move. Continuing Effect until piece is captured";
                continuingEffectUntilCaptured=true;
                drawableId=R.drawable.confabulation;
                break;

            default:
                throw new IllegalArgumentException ("Not a valid card!");
        }
    }
    /**
     * Cowardice array list.
     *
     * @param clickNumber  the click number
     * @param oponentPiece the oponent piece
     * @param field        the field
     * @return the array list
     */
//move opponent pawn one or two fields backward (no occupied square)
    public ArrayList<Field> cowardice (int clickNumber, ChessPiece oponentPiece, Field field) {
        legalMoves = new ArrayList<>();
        currentFields = ChessBoard.getInstance().getBoardFields();

        //get legal Moves on first click
        if (clickNumber == 1){
            if (field.getFieldX() - 1 != -1 && field.getFieldX() - 1 != currentFields.length && currentFields[field.getFieldX() - 1][field.getFieldY()].getCurrentPiece() == null && !currentFields[field.getFieldX() - 1][field.getFieldY()].isBlocked()) {
                legalMoves.add(currentFields[field.getFieldX() - 1][field.getFieldY()]);

                if (field.getFieldX() - 2 != -1 && field.getFieldX() - 2 != currentFields.length && currentFields[field.getFieldX() - 2][field.getFieldY()].getCurrentPiece() == null && !currentFields[field.getFieldX() - 2][field.getFieldY()].isBlocked())
                    legalMoves.add(currentFields[field.getFieldX() - 2][field.getFieldY()]);
            }
    }
        //move on second click
    else
            oponentPiece.move(field);

        return legalMoves;
    }

    /**
     * Crusade.
     *
     * @param playingPiece the playing piece
     * @param field        the field
     */
    public void crusade(ChessPiece playingPiece, Field field){
        //bishop moves one more time (when it doesn't capture a piece)
        playingPiece.move(field);
    }

    /**
     * Dark mirror array list.
     *
     * @param clickNumber  the click number
     * @param playingPiece the playing piece
     * @param field        the field
     * @return the array list
     */
    public ArrayList<Field> darkMirror(int clickNumber, ChessPiece playingPiece, Field field){
        //one pawns can capture by moving diagonally backward instead of forward
        legalMoves=new ArrayList<>();
        currentFields=ChessBoard.getInstance().getBoardFields();

        //get legal Moves on first click
        if(clickNumber==1) {
            if (field.getFieldX() + 1 != -1 && field.getFieldX() + 1 != currentFields.length && field.getFieldY() - 1 != -1 && field.getFieldY() - 1 != currentFields.length && currentFields[field.getFieldX() + 1][field.getFieldY() - 1].getCurrentPiece() != null && currentFields[field.getFieldX() + 1][field.getFieldY() - 1].getCurrentPiece().getColour() != playingPiece.getColour() && !currentFields[field.getFieldX() + 1][field.getFieldY() - 1].isProtected())
                legalMoves.add(currentFields[field.getFieldX() + 1][field.getFieldY() - 1]);
            if (field.getFieldX() + 1 != -1 && field.getFieldX() + 1 != currentFields.length && field.getFieldY() + 1 != -1 && field.getFieldY() + 1 != currentFields.length && currentFields[field.getFieldX() + 1][field.getFieldY() + 1].getCurrentPiece() != null && currentFields[field.getFieldX() + 1][field.getFieldY() + 1].getCurrentPiece().getColour() != playingPiece.getColour() && !currentFields[field.getFieldX() + 1][field.getFieldY() + 1].isProtected())
                legalMoves.add(currentFields[field.getFieldX() + 1][field.getFieldY() + 1]);
        }

        //move on second click
        else
            playingPiece.move(field);

        return legalMoves;
    }

    /**
     * Death dance.
     *
     * @param playingPiece the playing piece
     * @param oponentPiece the oponent piece
     */
    public void deathDance(ChessPiece playingPiece, ChessPiece oponentPiece){
        //Exchange the position of any of your pieces with any adjacent enemy piece
        swap(playingPiece,oponentPiece);
    }

    /**
     * Disintegration.
     *
     * @param playingPiece the playing piece
     */
    public void disintegration(ChessPiece playingPiece){
        //Remove one of your own pawns (It is now dead and cannot be brought back into play)

        //start Sound Effect
        createSound();
        fieldManipulationSound.start();

        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            new NetworkTasks.SendCard(ChessBoard.getInstance().getCurrentCard().getId(),playingPiece.getPosition(), ChessBoard.getInstance().getBoardFields()[0][0]);
        }

        //capture Piece
        ChessBoard.getInstance().getLocalPlayer().addChessPiecesCaptured(playingPiece);
        ChessBoard.getInstance().getLocalPlayer().removeChessPiecesAlive(playingPiece);
        playingPiece.getPosition().setCurrentPiece(null);
        playingPiece.setCurrentPosition(null);
        playingPiece.isCaptured=true;
        playingPiece.setUpdateView(true);

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    /**
     * Champion.
     *
     * @param playingPiece the playing piece
     */
    public void champion(ChessPiece playingPiece){
        //Any one knight becomes a Champion. Place a marker underneath it. (a Champion jumps to the opposite corner of a 3 by 4 rectangle).

        //start Sound
        createSound();
        fieldManipulationSound.start();

        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            new NetworkTasks.SendCard(ChessBoard.getInstance().getCurrentCard().getId(),playingPiece.getPosition(), ChessBoard.getInstance().getBoardFields()[0][0]);
        }

        //mark Champion and update view
        playingPiece.setChampion();
        playingPiece.getPosition().markChampion();
        playingPiece.getPosition().invalidate();

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    /**
     * Rebirth array list.
     *
     * @param clickNumber  the click number
     * @param oponentPiece the oponent piece
     * @param field        the field
     * @return the array list
     */
    public ArrayList<Field> rebirth(int clickNumber,ChessPiece oponentPiece,Field field){
//Move one enemy piece to any square it could have occupied at the beginning of the game. The square must be empty or contain one of your pieces. If one of your pieces is in the square, it is captured.

        //get legal Moves on first click
        if (clickNumber==1){
            legalMoves=new ArrayList<>();
            currentFields=ChessBoard.getInstance().getBoardFields();

            //get possible fields for playingPieceType
            switch (oponentPiece.getPlayingPieceType()) {
                case BISHOP:
                    if (!currentFields[0][2].isBlocked()&&(currentFields[0][2].getCurrentPiece() == null || currentFields[0][2].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][2]);
                    if (!currentFields[0][5].isBlocked()&&(currentFields[0][5].getCurrentPiece() == null || currentFields[0][5].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][5]);
                    break;

                case KING:
                    if (!currentFields[0][4].isBlocked()&&(currentFields[0][4].getCurrentPiece() == null || currentFields[0][3].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][4]);
                    break;

                case KNIGHT:
                    if (!currentFields[0][1].isBlocked()&&(currentFields[0][1].getCurrentPiece() == null || currentFields[0][1].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][1]);
                    if (!currentFields[0][6].isBlocked()&&(currentFields[0][6].getCurrentPiece() == null || currentFields[0][6].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][6]);
                    break;

                case PAWN:
                    if (!currentFields[1][0].isBlocked()&&(currentFields[1][0].getCurrentPiece() == null || currentFields[1][0].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][0]);
                    if (!currentFields[1][1].isBlocked()&&(currentFields[1][1].getCurrentPiece() == null || currentFields[1][1].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][1]);
                    if (!currentFields[1][2].isBlocked()&&(currentFields[1][2].getCurrentPiece() == null || currentFields[1][2].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][2]);
                    if (!currentFields[1][3].isBlocked()&&(currentFields[1][3].getCurrentPiece() == null || currentFields[1][3].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][3]);
                    if (!currentFields[1][4].isBlocked()&&(currentFields[1][4].getCurrentPiece() == null || currentFields[1][4].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][4]);
                    if (!currentFields[1][5].isBlocked()&&(currentFields[1][5].getCurrentPiece() == null || currentFields[1][5].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][5]);
                    if (!currentFields[1][6].isBlocked()&&(currentFields[1][6].getCurrentPiece() == null || currentFields[1][6].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][6]);
                    if (!currentFields[1][7].isBlocked()&&(currentFields[1][7].getCurrentPiece() == null || currentFields[1][7].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[1][7]);
                    break;

                case QUEEN:
                    if (!currentFields[0][3].isBlocked()&&(currentFields[0][3].getCurrentPiece() == null || currentFields[0][3].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][3]);
                    break;

                case ROOK:
                    if (!currentFields[0][0].isBlocked()&&(currentFields[0][0].getCurrentPiece() == null || currentFields[0][0].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][0]);
                    if (!currentFields[0][7].isBlocked()&&(currentFields[0][7].getCurrentPiece() == null || currentFields[0][7].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][7]);
                    break;
            }
        }

        //move on second click
        else
            oponentPiece.move(field);

        return legalMoves;
    }

    /**
     * Revelation.
     *
     * @param playingPiece1 the playing piece 1
     * @param playingPiece2 the playing piece 2
     */
    public void revelation(ChessPiece playingPiece1, ChessPiece playingPiece2){
        //Replace one of your knights or one of your opponent's knights by a bishop owned by the same player.
        swap(playingPiece1,playingPiece2);
    }

    /**
     * Long jump array list.
     *
     * @param clickNumber  the click number
     * @param playingPiece the playing piece
     * @param field        the field
     * @return the array list
     */
    public ArrayList<Field> longJump(int clickNumber, ChessPiece playingPiece, Field field) {
        //Move one of your knights to any square whose color is different from the one it currently occupies. You cannot capture a piece with this move.
        legalMoves = new ArrayList<>();
        currentFields = ChessBoard.getInstance().getBoardFields();

        //get legal Moves on first Click
        if (clickNumber == 1) {
            boolean fieldIsEven = true;

            if (playingPiece.getPosition().isEven()) {
                fieldIsEven = false;
            }

            for (Field[] currentField : currentFields) {
                for (Field value : currentField) {
                    if (value.getCurrentPiece() == null && value.isEven() == fieldIsEven && !value.isBlocked()) {
                        legalMoves.add(value);
                    }
                }
            }
        }

        //move on second click
        else {
            playingPiece.move(field);

        }

        return legalMoves;
    }

    /**
     * Lost castle.
     *
     * @param playingPiece the playing piece
     * @param oponentPiece the oponent piece
     */
    public void lostCastle(ChessPiece playingPiece, ChessPiece oponentPiece){
        //Swap your rook with one of your oponents rooks
        swap(playingPiece,oponentPiece);
    }

    /**
     * Mystic shield.
     *
     * @param field the field
     */
    public void mysticShield(Field field){
        //protect 1 of your pieces for the next turn
        currentFields=ChessBoard.getInstance().getBoardFields();

        //start sound
        createSound();
        fieldManipulationSound.start();

        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            new NetworkTasks.SendCard(this.getId(),field,currentFields[5][5]);
        }

        //mark Piece protected and update view
        field.getCurrentPiece().setProtected(true);
        field.setPlayingPieceShield();
        field.invalidate();

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    /**
     * Forbidden city.
     *
     * @param field the field
     */
    public void forbiddenCity(Field field){
        //block field till end of game (cannot be movedTo)

        //start sound
        createSound();
        fieldManipulationSound.start();

        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            new NetworkTasks.SendCard(this.getId(),field,ChessBoard.getInstance().getBoardFields()[0][0]);
        }

        //mark field blocked and update view
        field.setBlocked();
        field.invalidate();

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    /**
     * Holy quest.
     *
     * @param playingPiece1 the playing piece 1
     * @param playingPiece2 the playing piece 2
     */
    public void holyQuest(ChessPiece playingPiece1, ChessPiece playingPiece2){
        //Swap the positions of a bishop and a knight belonging to your opponent.
        swap(playingPiece1,playingPiece2);
    }

    /**
     * Vulture.
     *
     * @param id          the id
     * @param localPlayer the local player
     * @param deck        the deck
     */
    public void vulture(int id ,Player localPlayer,Deck deck){
        //Take the last card played by your opponent and put it in your hand.
        Card temp=localPlayer.getCurrentCards()[id]; //set new Last Card played
        localPlayer.getCurrentCards()[id].setOwned(false); //set current card free again
        localPlayer.getCurrentCards()[id]=deck.getLastCardPlayed(); //get last Card Played
        deck.setLastCardPlayed(temp); //set new last Card Played
        GameActivity.unselectAfterCardActivation(); //mark card "unselected"
    }

    /**
     * Man of straw.
     *
     * @param playingPiece1 the playing piece 1
     * @param playingPiece2 the playing piece 2
     */
    public void manOfStraw(ChessPiece playingPiece1, ChessPiece playingPiece2){
        //Play this card when your king is in check (even checkmate). It swaps positions with any one of your pawns, as long as the new position does not place it in check.
        swap(playingPiece1,playingPiece2);
    }

    /**
     * Bombard.
     *
     * @param playingPiece the playing piece
     * @param field        the field
     */
    public void bombard(ChessPiece playingPiece, Field field){
        //On this move, one of your rooks can move in its normal straight line, jump over any piece or one obstruction on the board, and continue in a straight line. (At the end of its move, the rook may make a normal capture)
        playingPiece.move(field);
    }

    /* Not implemented cards
    public void handOfFate(){
        //Exchange your hand with your opponent's. He must draw another card to replace this one
    }

    public void abduction(){
        //opponent must look away from the board for ten seconds, as you remove any one of his pieces except the king. He then has ten seconds to look at the board If he remembers correctly, the piece is put back in its place.
    }

    public void coup(){
        //Your king becomes a prince (prince moves like a king but can be captured). Choose one of your pieces, except a rook or a queen, and mark it. (This piece keeps its standard move, but is the new king)
    }

    public void confabulation(){
        //Make an otherwise legal move which puts two of your pieces (other than kings) on the same square. They "merge" into a new piece. It can move and capture like either one of them, and is affected by any card that affects either of them. Move the two pieces together. Confabulated pawns cannot promote.

    }

    public void thinkAgain(){
        //Your opponent's move is cancelled. He must make a different move

    }

    public void martyr(){
        //Play this card when one of your bishops has the choice of taking two or more of your opponent's pieces. Capture as many of these pieces as you want (at least two). Your bishop is removed from play and regarded as captured.

    }

    public void fogOfWar(){
        //This card cancels the effect of any other card. Both cards are discarded. If the opposing card constituted your opponent's whole move, he may make another move, but he may not play another card.
    }

    public void funeralPyre(){
        //All captured pieces of both players are now considered dead. They cannot be returned to the chessboard through the play of a card.
    }
*/

    /**
     * Swap.
     *
     * @param playingPiece1 the playing piece 1
     * @param playingPiece2 the playing piece 2
     */
//swap position of two pieces
    public void swap(ChessPiece playingPiece1, ChessPiece playingPiece2){
        //create sound effect
        MediaPlayer.create(context,R.raw.chessmatemove_end).start();

        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            new NetworkTasks.SendCard(ChessBoard.getInstance().getCurrentCard().getId(),playingPiece1.getPosition(),playingPiece2.getPosition());
        }

        //make pieces un-captureable
        playingPiece1.setProtected(true);
        playingPiece2.setProtected(true);

        //get position of pieces
        Field field1=playingPiece1.getPosition();
        Field field2=playingPiece2.getPosition();



        if (playingPiece1.isChampion()) {
            field1.setRectangleDefaultColor();
            field2.markChampion();
        }
        else if (playingPiece2.isChampion()) {
            field2.setRectangleDefaultColor();
            field1.markChampion();
        }


        //swap pieces
        field1.setCurrentPiece(playingPiece2);
        playingPiece2.setCurrentPosition(field1);
        field2.setCurrentPiece(playingPiece1);
        playingPiece1.setCurrentPosition(field2);

        //update view
        playingPiece1.setUpdateView(true);
        playingPiece2.setUpdateView(true);

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    /**
     * Is continuing until end boolean.
     *
     * @return the boolean
     */
    public boolean isContinuingUntilEnd(){
        return continuingEffectUntilEnd;
    }

    /**
     * Is continuing until captured boolean.
     *
     * @return the boolean
     */
    public boolean isContinuingUntilCaptured(){
        return continuingEffectUntilCaptured;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){return name;}

    /**
     * Get desc string.
     *
     * @return the string
     */
    public String getDesc(){return desc;}

    /**
     * Get use case string.
     *
     * @return the string
     */
    public String getUseCase(){return useCase;}

    /**
     * Get drawable id int.
     *
     * @return the int
     */
    public int getDrawableId(){return this.drawableId;}

    /**
     * Is owned boolean.
     *
     * @return the boolean
     */
    public boolean isOwned(){
        return owned;
    }

    /**
     * Set owned.
     *
     * @param owned the owned
     */
    public void setOwned(boolean owned){this.owned=owned;}

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){return this.id;}

    public void createSound(){
        fieldManipulationSound=MediaPlayer.create(context,R.raw.mixkit_field_manipulation);
        fieldManipulationSound.setVolume(10.0f,10.0f);
    }
}

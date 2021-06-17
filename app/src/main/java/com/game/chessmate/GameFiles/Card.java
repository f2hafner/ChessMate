package com.game.chessmate.GameFiles;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.game.chessmate.GameActivity;
import com.game.chessmate.GameFiles.Networking.NetworkManager;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPiece;
import com.game.chessmate.GameFiles.PlayingPieces.ChessPieceType;
import com.game.chessmate.R;

import java.util.ArrayList;

public class Card {
    private String name;
    private String desc;
    private String useCase;
    private boolean continuingEffectUntilEnd=false;
    private boolean continuingEffectUntilCaptured=false;
    private boolean owned;
    private int id;
    private int drawableId;

    ArrayList<Field> legalMoves;
    Field [][] currentFields;

    public Card (int number) throws IllegalArgumentException{
        id=number;
        switch (number){
            case 0: //works
                name="Cowardice";
                desc="Move one of your opponent's pawns one or two squares backward. It may not enter or cross an occupied square.";
                useCase="[i] Play this card immediately after your move";
                drawableId=R.drawable.cowardice;
                break;

            case 1: //works
                name="Dark Mirror";
                desc="On this move, one of your pawns can capture by moving diagonally backward instead of forward.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.dark_mirror;
                break;

            case 2: //bugged, needs sendSwap in Network
                name="Death Dance";
                desc="Exchange the position of any of your pieces with any adjacent enemy piece.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.death_dance;
                break;

            case 3: //bugged, enemy also has to capture
                name="Disintegration";
                desc="Remove one of your own pawns from the chessboard, and set it aside. It is now dead, and cannot be brought back into play with another card.";
                useCase="[i] Play this card immediately before or after your move.";
                drawableId=R.drawable.disintegration;
                break;

            case 4: //bugged; opponents color doesn't get set
                name="Champion";
                desc="Any one knight in play becomes a Champion. Place a marker underneath it. Instead of jumping like a knight, to the opposite corner of a 2 by 3 rectangle, a Champion jumps to the opposite corner of a 3 by 4 rectangle.";
                useCase="[i] Play this card immediately after your move. Continuing Effect until piece is captured.";
                continuingEffectUntilCaptured=true;
                drawableId=R.drawable.champion;
                break;

            case 5: //works
                name="Rebirth";
                desc="Move one enemy piece to any square it could have occupied at the beginning of the game. The square must be empty or contain one of your pieces. If one of your pieces is in the square, it is captured.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.rebirth;
                break;

            case 6: // bugged, needs sendSwap in Network
                name="Revelation";
                desc="Replace one of your knights or one of your opponent's knights by a bishop owned by the same player.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.revelation;
                break;

            case 7: //works
                name="Long Jump";
                desc="Move one of your knights to any square whose color is different from the one it currently occupies. You cannot capture a piece with this move.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.long_jump;
                break;

            case 8: //bugged, needs sendSwap in Network
                name="Lost Castle";
                desc="Swap the positions of one of your rooks and one of your opponent's rooks.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.lost_castle;
                break;

            case 9: // bugged, network
                name="Mystic Shield";
                desc="The piece you just moved cannot be captured by your opponent on his next turn. If you moved more than one piece, you must designate only one to be protected.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.mystic_shield;
                break;

            case 10: //bugged, other player can still move on it
                name="Forbidden City";
                desc="Place a marker in any unoccupied square. No piece can enter this square, or pass through it, for the rest of the game. Knights and other \"jumping\" pieces may still pass over it.";
                useCase="[i] Play this card immediately after your move. Continuing Effect until the end of game.";
                continuingEffectUntilEnd=true;
                drawableId=R.drawable.forbidden_city;
                break;

            case 11: // bugged, needs sendSwap in Network
                name="Holy Quest";
                desc="Swap the positions of a bishop and a knight belonging to your opponent.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.holy_quest;
                break;

            case 12: // bugged, network - hard to do
                name="Vulture";
                desc="Take the last card played by your opponent and put it in your hand. (If each player has his own deck of cards, you must also discard your top undrawn card.)";
                useCase="[i] Play this card immediately after your opponent plays a card.";
                drawableId=R.drawable.vulture;
                break;

            case 13: //change Card
                name="Crusade";
                desc="Play this card when you move a bishop without capturing a piece. This bishop immediately moves one more time.";
                useCase="[i] Play this card before your bishop's first move.";
                drawableId=R.drawable.crusade;
                break;

            case 14: //bugged, network -  hard to do
                name="Hand of Fate";
                desc="Exchange your hand with your opponent's. He must draw another card to replace this one.";
                useCase="[i] Play this card before your move.";
                drawableId=R.drawable.hand_of_fate;
                break;

            case 15:
                name="Spoils of War";
                desc="Play this card when you capture one of your opponent's pieces. The capturing piece changes permanently into a piece of the kind it captured. For instance, if one of your pawns captures a knight, it becomes a knight.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.spoils_of_war;
                break;

            case 16:
                name="Martyr";
                desc="Play this card when one of your bishops has the choice of taking two or more of your opponent's pieces. Capture as many of these pieces as you want (at least two). Your bishop is removed from play and regarded as captured.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.martyr;
                break;

            case 17:
                name="Man of Straw";
                desc="Play this card when your king is in check (even checkmate). It swaps positions with any one of your pawns, as long as the new position does not place it in check.";
                useCase="[i] Play this card before your move.";
                drawableId=R.drawable.man_of_straw;
                break;

            case 18:
                name="Funeral Pyre";
                desc="All captured pieces of both players are now considered dead. They cannot be returned to the chessboard throguh the play of a card.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.funeral_pyre;
                break;

            case 19:
                name="Abduction";
                desc="Your opponent must look away from the board for ten seconds, as you remove any one of his pieces except the king. He then has ten seconds to look at the board and state what piece you removed and which square it occupied. If he remembers correctly, the piece is put back in its place. If not, it is captured.";
                useCase="[i] Play this card immediately after your move.";
                drawableId=R.drawable.abduction;
                break;

            case 20:
                name="Fog of War";
                desc="This card cancels the effect of any other card. Both cards are discarded. If the opposing card constituted your opponent's whole move, he may make another move, but he may not play another card.";
                useCase="[i] Play this card immediately after your opponent has played the card you want to cancel.";
                drawableId=R.drawable.fog_of_war;
                break;

            case 21:
                name="Bombard";
                desc="On this move, one of your rooks can move in its normal straight line, jump over any piece or one obstruction on the board, and continue in a straight line. The piece or obstruction is not affected by being jumped. At the end of its move, the rook may make a normal capture.";
                useCase="[i] Play this card on your turn, instead of making a regular move.";
                drawableId=R.drawable.bombard;
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

    //move opponent pawn one or two fields backward (no occupied square)
    public ArrayList<Field> cowardice (int clickNumber, ChessPiece oponentPiece, Field field) {
        legalMoves = new ArrayList<>();
        currentFields = ChessBoard.getInstance().getBoardFields();

        if (clickNumber == 1){
            if (field.getFieldX() - 1 != -1 && field.getFieldX() - 1 != currentFields.length && currentFields[field.getFieldX() - 1][field.getFieldY()].getCurrentPiece() == null && !currentFields[field.getFieldX() - 1][field.getFieldY()].isBlocked()) {
                legalMoves.add(currentFields[field.getFieldX() - 1][field.getFieldY()]);

                if (field.getFieldX() - 2 != -1 && field.getFieldX() - 2 != currentFields.length && currentFields[field.getFieldX() - 2][field.getFieldY()].getCurrentPiece() == null && !currentFields[field.getFieldX() - 2][field.getFieldY()].isBlocked())
                    legalMoves.add(currentFields[field.getFieldX() - 2][field.getFieldY()]);
            }
    }
    else
            oponentPiece.move(field);
        return legalMoves;
    }

    //bishop moves one more time (when it doesn't capture a piece)
    public void crusade(ChessPiece playingPiece, Field field){
        playingPiece.move(field);
    }

    //one pawns can capture by moving diagonally backward instead of forward
    public ArrayList<Field> darkMirror(int clickNumber, ChessPiece playingPiece, Field field){
        legalMoves=new ArrayList<>();
        currentFields=ChessBoard.getInstance().getBoardFields();

        if(clickNumber==1) {
            if (field.getFieldX() + 1 != -1 && field.getFieldX() + 1 != currentFields.length && field.getFieldY() - 1 != -1 && field.getFieldY() - 1 != currentFields.length && currentFields[field.getFieldX() + 1][field.getFieldY() - 1].getCurrentPiece() != null && currentFields[field.getFieldX() + 1][field.getFieldY() - 1].getCurrentPiece().getColour() != playingPiece.getColour() && !currentFields[field.getFieldX() + 1][field.getFieldY() - 1].isProtected())
                legalMoves.add(currentFields[field.getFieldX() + 1][field.getFieldY() - 1]);
            if (field.getFieldX() + 1 != -1 && field.getFieldX() + 1 != currentFields.length && field.getFieldY() + 1 != -1 && field.getFieldY() + 1 != currentFields.length && currentFields[field.getFieldX() + 1][field.getFieldY() + 1].getCurrentPiece() != null && currentFields[field.getFieldX() + 1][field.getFieldY() + 1].getCurrentPiece().getColour() != playingPiece.getColour() && !currentFields[field.getFieldX() + 1][field.getFieldY() + 1].isProtected())
                legalMoves.add(currentFields[field.getFieldX() + 1][field.getFieldY() + 1]);
        }
        else
            playingPiece.move(field);

        return legalMoves;
    }

    //Exchange the position of any of your pieces with any adjacent enemy piece
    public void deathDance(ChessPiece playingPiece, ChessPiece oponentPiece){

          ChessBoard.getInstance().swap(playingPiece,oponentPiece);
    }

    public ArrayList<Field> getLegalMovesDeathDance(ChessPiece playingPiece){
        legalMoves=new ArrayList<>();
        currentFields=ChessBoard.getInstance().getBoardFields();

            Field field=playingPiece.getPosition();

            //down
            if (field.getFieldX()+1!=-1&&field.getFieldX()+1!=currentFields.length
                    &&currentFields[field.getFieldX()+1][field.getFieldY()].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()+1][field.getFieldY()].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()+1][field.getFieldY()]);
            //up
            if (field.getFieldX()-1!=-1&&field.getFieldX()-1!=currentFields.length
                    &&currentFields[field.getFieldX()-1][field.getFieldY()].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()-1][field.getFieldY()].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()-1][field.getFieldY()]);
            //left
            if (field.getFieldY()-1!=-1&&field.getFieldY()-1!=currentFields.length
                    &&currentFields[field.getFieldX()][field.getFieldY()-1].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()][field.getFieldY()-1].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()][field.getFieldY()-1]);
            //right
            if (field.getFieldY()+1!=-1&&field.getFieldY()+1!=currentFields.length
                    &&currentFields[field.getFieldX()][field.getFieldY()+1].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()][field.getFieldY()+1].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()][field.getFieldY()+1]);
            //down-left
            if (field.getFieldX()+1!=-1&&field.getFieldX()+1!=currentFields.length&&field.getFieldY()-1!=-1&&field.getFieldY()-1!=currentFields.length
                    &&currentFields[field.getFieldX()+1][field.getFieldY()-1].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()+1][field.getFieldY()-1].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()+1][field.getFieldY()-1]);
            //down-right
            if (field.getFieldX()+1!=-1&&field.getFieldX()+1!=currentFields.length&&field.getFieldY()+1!=-1&&field.getFieldY()+1!=currentFields.length
                    &&currentFields[field.getFieldX()+1][field.getFieldY()+1].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()+1][field.getFieldY()+1].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()+1][field.getFieldY()+1]);
            //up-left
            if (field.getFieldX()-1!=-1&&field.getFieldX()-1!=currentFields.length&&field.getFieldY()-1!=-1&&field.getFieldY()-1!=currentFields.length
                    &&currentFields[field.getFieldX()-1][field.getFieldY()-1].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()-1][field.getFieldY()-1].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()-1][field.getFieldY()-1]);
            //up-right
            if (field.getFieldX()-1!=-1&&field.getFieldX()-1!=currentFields.length&&field.getFieldY()+1!=-1&&field.getFieldY()+1!= currentFields.length
                    &&currentFields[field.getFieldX()-1][field.getFieldY()+1].getCurrentPiece()!=null
                    &&currentFields[field.getFieldX()-1][field.getFieldY()+1].getCurrentPiece().getColour()!=playingPiece.getColour())
                legalMoves.add(currentFields[field.getFieldX()-1][field.getFieldY()+1]);

            return legalMoves;
    }

    //Remove one of your own pawns (It is now dead and cannot be brought back into play)
    public void disintegration(ChessPiece playingPiece){
        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            NetworkManager.sendCard(ChessBoard.getInstance().getCurrentCard().getId(),playingPiece.getPosition(), ChessBoard.getInstance().getBoardFields()[0][0]);
        }

        playingPiece.capture();

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    //Any one knight becomes a Champion. Place a marker underneath it. (a Champion jumps to the opposite corner of a 3 by 4 rectangle).
    public void champion(ChessPiece playingPiece){
        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            NetworkManager.sendCard(ChessBoard.getInstance().getCurrentCard().getId(),playingPiece.getPosition(), ChessBoard.getInstance().getBoardFields()[0][0]);
        }

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

    //Move one enemy piece to any square it could have occupied at the beginning of the game. The square must be empty or contain one of your pieces. If one of your pieces is in the square, it is captured.
    public ArrayList<Field> rebirth(int clickNumber,ChessPiece oponentPiece,Field field){

        if (clickNumber==1){
            legalMoves=new ArrayList<>();
            currentFields=ChessBoard.getInstance().getBoardFields();

            switch (oponentPiece.getPlayingPieceType()) {
                case BISHOP:
                    if (!currentFields[0][2].isBlocked()&&(currentFields[0][2].getCurrentPiece() == null || currentFields[0][2].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][2]);
                    if (!currentFields[0][5].isBlocked()&&(currentFields[0][5].getCurrentPiece() == null || currentFields[0][5].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][5]);
                    break;

                case KING:
                    if (!currentFields[0][3].isBlocked()&&(currentFields[0][3].getCurrentPiece() == null || currentFields[0][3].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][3]);
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
                    if (!currentFields[0][4].isBlocked()&&(currentFields[0][4].getCurrentPiece() == null || currentFields[0][4].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][4]);
                    break;

                case ROOK:
                    if (!currentFields[0][0].isBlocked()&&(currentFields[0][0].getCurrentPiece() == null || currentFields[0][0].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][0]);
                    if (!currentFields[0][7].isBlocked()&&(currentFields[0][7].getCurrentPiece() == null || currentFields[0][7].getCurrentPiece().getColour() != oponentPiece.getColour()))
                        legalMoves.add(currentFields[0][7]);
                    break;
            }
        }
        else
            oponentPiece.move(field);

        return legalMoves;
    }

    //Replace one of your knights or one of your opponent's knights by a bishop owned by the same player.
    public void revelation(ChessPiece playingPiece1, ChessPiece playingPiece2){

            ChessBoard.getInstance().swap(playingPiece1,playingPiece2);
    }

    public ArrayList<Field> getLegalMovesRevelation(ChessPiece playingPiece1){
        legalMoves=new ArrayList<>();
        currentFields=ChessBoard.getInstance().getBoardFields();

            for (int i = 0; i < currentFields.length; i++) {
                for (int j = 0; j < currentFields[i].length; j++) {
                    if (currentFields[i][j].getCurrentPiece() != null && currentFields[i][j].getCurrentPiece().getPlayingPieceType() == ChessPieceType.BISHOP && currentFields[i][j].getCurrentPiece().getColour() == playingPiece1.getColour()) {
                        legalMoves.add(currentFields[i][j]);
                    }
                }
            }
        return legalMoves;
    }

    //Move one of your knights to any square whose color is different from the one it currently occupies. You cannot capture a piece with this move.
    public ArrayList<Field> longJump(int clickNumber, ChessPiece playingPiece, Field field) {
        legalMoves = new ArrayList<>();
        currentFields = ChessBoard.getInstance().getBoardFields();

        if (clickNumber == 1) {//first Click
            boolean fieldIsEven = true;

            if (playingPiece.getPosition().isEven()) {
                fieldIsEven = false;
            }

            for (int i = 0; i < currentFields.length; i++) {
                for (int j = 0; j < currentFields[i].length; j++) {
                    if (currentFields[i][j].getCurrentPiece() == null && currentFields[i][j].isEven() == fieldIsEven && !currentFields[i][j].isBlocked()) {
                        legalMoves.add(currentFields[i][j]);
                    }
                }
            }
        } else {
            playingPiece.move(field);

        }

        return legalMoves;
    }

    //Swap your rook with one of your oponents rooks
    public void lostCastle(ChessPiece playingPiece, ChessPiece oponentPiece){

            ChessBoard.getInstance().swap(playingPiece,oponentPiece);

    }

    public ArrayList<Field> getLegalMovesLostCastle(ChessPiece playingPiece){
        legalMoves=new ArrayList<>();
        currentFields=ChessBoard.getInstance().getBoardFields();

            for (int i = 0; i < currentFields.length; i++) {
                for (int j = 0; j < currentFields[i].length; j++) {
                    if (currentFields[i][j].getCurrentPiece() != null && currentFields[i][j].getCurrentPiece().getPlayingPieceType() == ChessPieceType.ROOK && currentFields[i][j].getCurrentPiece().getColour() != playingPiece.getColour()) {
                        legalMoves.add(currentFields[i][j]);
                    }
                }
            }

        return legalMoves;

    }

    //protect 1 of your pieces for the next turn
    public void mysticShield(Field field){

        currentFields=ChessBoard.getInstance().getBoardFields();
        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            NetworkManager.sendCard(this.getId(),field,currentFields[5][5]);
        }

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

    //block field till end of game (cannot be movedTo)
    public void forbiddenCity(Field field){
        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            NetworkManager.sendCard(ChessBoard.getInstance().getCurrentCard().getId(),field, ChessBoard.getInstance().getBoardFields()[0][0]);
        }

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

    //Swap the positions of a bishop and a knight belonging to your opponent.
    public void holyQuest(ChessPiece playingPiece1, ChessPiece playingPiece2){

            ChessBoard.getInstance().swap(playingPiece1,playingPiece2);
    }

    public ArrayList<Field> getLegalMovesHolyQuest(ChessPiece playingPiece1){
        legalMoves=new ArrayList<>();
        currentFields=ChessBoard.getInstance().getBoardFields();

            for (int i = 0; i < currentFields.length; i++) {
                for (int j = 0; j < currentFields[i].length; j++) {
                    if (currentFields[i][j].getCurrentPiece() != null && currentFields[i][j].getCurrentPiece().getPlayingPieceType() == ChessPieceType.KNIGHT && currentFields[i][j].getCurrentPiece().getColour() != playingPiece1.getColour()) {
                        legalMoves.add(currentFields[i][j]);
                    }
                }
            }

        return legalMoves;
    }

    //Exchange your hand with your opponent's. He must draw another card to replace this one.
    public void handOfFate(Player player1,Player player2){
     /*   Card [] temp=player1.getCurrentCards();
        player1.setCards(player2.getCurrentCards());
        player2.setCards(temp);*/
    }

    //Take the last card played by your opponent and put it in your hand.
    public void vulture(int id ,Player localPlayer,Deck deck){
        Log.i("GAMESTATE", "afterCardstart: " + ChessBoard.getInstance().getGameState());
        if (ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            NetworkManager.sendCard(ChessBoard.getInstance().getCurrentCard().getId(),ChessBoard.getInstance().getBoardFields()[0][0], ChessBoard.getInstance().getBoardFields()[0][0]);
        }

        Card temp=localPlayer.getCurrentCards()[id]; //set new Last Card played
        localPlayer.getCurrentCards()[id].setOwned(false); //set current card free again
        localPlayer.getCurrentCards()[id]=deck.getLastCardPlayed(); //get last Card Played
        deck.setLastCardPlayed(temp); //set new last Card Played
        GameActivity.unselectAfterCardActivation(); //mark card "unselected"

        if (ChessBoard.getInstance().getGameState() == GameState.WAITING) {
            ChessBoard.getInstance().setGameState(GameState.ACTIVE);
        }
        else if(ChessBoard.getInstance().getGameState() == GameState.ACTIVE) {
            ChessBoard.getInstance().setGameState(GameState.WAITING);
        }

        Log.i("GAMESTATE", "afterCardend: " + ChessBoard.getInstance().getGameState());
    }

    public void abduction(ChessPiece oponentPiece){
        //opponent must look away from the board for ten seconds, as you remove any one of his pieces except the king. He then has ten seconds to look at the board If he remembers correctly, the piece is put back in its place.

    }

    public void bombard(ChessPiece playingPiece){
        //On this move, one of your rooks can move in its normal straight line, jump over any piece or one obstruction on the board, and continue in a straight line. (At the end of its move, the rook may make a normal capture)

    }

    public void coup(ChessPiece playingPiece){
        //Your king becomes a prince (prince moves like a king but can be captured). Choose one of your pieces, except a rook or a queen, and mark it. (This piece keeps its standard move, but is the new king)

    }

    public void confabulation(){
        //Make an otherwise legal move which puts two of your pieces (other than kings) on the same square. They "merge" into a new piece. It can move and capture like either one of them, and is affected by any card that affects either of them. Move the two pieces together. Confabulated pawns cannot promote.

    }

    public void manOfStraw(ChessPiece playingPiece1, ChessPiece playingPiece2){
        //Play this card when your king is in check (even checkmate). It swaps positions with any one of your pawns, as long as the new position does not place it in check.

        Field temp=playingPiece1.getPosition();
        playingPiece1.setPosition(playingPiece2.getPosition());
        playingPiece2.setPosition(temp);

        // TODO: 31.05.2021 check if new position doesn't place king in check
    }

    public void spoilsOfWar(ChessPiece playingPiece, ChessPiece oponentPiece){
        //when you capture one of your opponent's pieces. The capturing piece changes permanently into a piece of the kind it captured. For instance, if one of your pawns captures a knight, it becomes a knight.

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
        //All captured pieces of both players are now considered dead. They cannot be returned to the chessboard throguh the play of a card.

    }

    public boolean isContinuingUntilEnd(){
        if (continuingEffectUntilEnd)
            return true;
        return false;
    }

    public boolean isContinuingUntilCaptured(){
        if (continuingEffectUntilCaptured)
            return true;
        return false;
    }

    public String getName(){return name;}

    public String getDesc(){return desc;}

    public String getUseCase(){return useCase;}

    public int getDrawableId(){return this.drawableId;}

    public boolean isOwned(){
        if (owned)
            return true;
        return false;
    }

    public void setOwned(boolean owned){this.owned=owned;}

    public int getId(){return this.id;}
}

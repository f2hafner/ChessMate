package com.game.chessmate.GameFiles;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Card {
    private String name;
    private String desc;
    private String useCase;
    private boolean continuingEffectUntilEnd=false;
    private boolean continuingEffectUntilCaptured=false;
    private boolean owned;

    private Bitmap sprite;

    public Card (int number) throws IllegalArgumentException{
        switch (number){
            case 0:
                cowardice();
                break;
            case 1:
                crusade();
                break;
            case 2:
                darkMirror();
                break;
            case 3:
                deathDance();
                break;
            case 4:
                disintegration();
                break;
            case 5:
                abduction();
                break;
            case 6:
                bombard();
                break;
            case 7:
                coup();
                break;
            case 8:
                confabulation();
                break;
            case 9:
                champion();
                break;
            case 10:
                rebirth();
                break;
            case 11:
                revelation();
                break;
            case 12:
                spoilsOfWar();
                break;
            case 13:
                thinkAgain();
                break;
            case 14:
                vulture();
                break;
            case 15:
                longJump();
                break;
            case 16:
                lostCastle();
                break;
            case 17:
                manOfStraw();
                break;
            case 18:
                martyr();
                break;
            case 19:
                mysticShield();
                break;
            case 20:
                fogOfWar();
                break;
            case 21:
                forbiddenCity();
                break;
            case 22:
                holyQuest();
                break;
            case 23:
                funeralPyre();
                break;
            case 24:
                handOfFate();
                break;
            default:
                throw new IllegalArgumentException ("Not a valid card!");
        }
    }

    public void cowardice(){
        name="Cowardice";
        desc="Move one of your opponent's pawns one or two squares backward. It may not enter or cross an occupied square.";
        useCase="[i] Play this card immediately after your move";
    }

    public void crusade(){
        name="Crusade";
        desc="Play this card when you move a bishop without capturing a piece. This bishop immediately moves one more time.";
        useCase="[i] Play this card after your bishop's first move.";
    }

    public void darkMirror(){
        name="Dark Mirror";
        desc="On this move, one of your pawns can capture by moving diagonally backward instead of forward.";
        useCase="[i] Play this card on your turn, instead of making a regular move.";
    }

    public void deathDance(){
        name="Death Dance";
        desc="Exchange the position of any of your pieces with any adjacent enemy piece.";
        useCase="[i] Play this card on your turn, instead of making a regular move.";
    }

    public void disintegration(){
        name="Disintegration";
        desc="Remove one of your own pawns from the chessboard, and set it aside. It is now dead, and cannot be brought back into play with another card.";
        useCase="[i] Play this card immediately before or after your move.";
    }

    public void abduction(){
        name="Abduction";
        desc="Your opponent must look away from the board for ten seconds, as you remove any one of his pieces except the king. He then has ten seconds to look at the board and state what piece you removed and which square it occupied. If he remembers correctly, the piece is put back in its place. If not, it is captured.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void bombard(){
        name="Bombard";
        desc="On this move, one of your rooks can move in its normal straight line, jump over any piece or one obstruction on the board, and continue in a straight line. The piece or obstruction is not affected by being jumped. At the end of its move, the rook may make a normal capture.";
        useCase="[i] Play this card on your turn, instead of making a regular move.";
    }

    public void coup(){
        name="Coup";
        desc="Your king becomes a prince for the remainder of the game. A prince moves like a king but can be captured. Choose one of your pieces, except a rook or a queen, and mark it. This piece keeps its standard move, but is the new king, and your opponent will win if he checkmates it.";
        useCase="[i] Play this card immediately after your move. Continuing Effect until the end of game";
        continuingEffectUntilEnd=true;
    }

    public void confabulation(){
        name="Confabulation";
        desc="Make an otherwise legal move which puts two of your pieces (other than kings) on the same square. They \"merge\" into a new piece. It can move and capture like either one of them, and is affected by any card that affects either of them. Move the two pieces together. Confabulated pawns cannot promote.";
        useCase="[i] Play this card on your turn, instead of making a regular move. Continuing Effect until piece is captured";
        continuingEffectUntilCaptured=true;
    }

    public void champion(){
        name="Champion";
        desc="Any one knight in play becomes a Champion. Place a marker underneath it. Instead of jumping like a knight, to the opposite corner of a 2 by 3 rectangle, a Champion jumps to the opposite corner of a 3 by 4 rectangle.";
        useCase="[i] Play this card immediately after your move. Continuing Effect until piece is captured.";
        continuingEffectUntilCaptured=true;
    }

    public void rebirth(){
        name="Rebirth";
        desc="Move one enemy piece to any square it could have occupied at the beginning of the game. The square must be empty or contain one of your pieces. If one of your pieces is in the square, it is captured.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void revelation(){
        name="Revelation";
        desc="Replace one of your knights or one of your opponent's knights by a bishop owned by the same player.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void spoilsOfWar(){
        name="Spoils of War";
        desc="Play this card when you capture one of your opponent's pieces. The capturing piece changes permanently into a piece of the kind it captured. For instance, if one of your pawns captures a knight, it becomes a knight.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void thinkAgain(){
        name="Think Again";
        desc="Your opponent's move is cancelled. He must make a different move, with the same piece or with another one. If he used a card, he may take it back.";
        useCase="[i] Play this card immediately after your opponent's turn.";
    }

    public void vulture(){
        name="Vulture";
        desc="Take the last card played by your opponent and put it in your hand. (If each player has his own deck of cards, you must also discard your top undrawn card.)";
        useCase="[i] Play this card immediately after your opponent plays a card.";
    }

    public void longJump(){
        name="Long Jump";
        desc="Move one of your knights to any square whose color is different from the one it currently occupies. You cannot capture a piece with this move.";
        useCase="[i] Play this card on your turn, instead of making a regular move.";
    }

    public void lostCastle(){
        name="Lost Castle";
        desc="Swap the positions of one of your rooks and one of your opponent's rooks.";
        useCase="[i] Play this card on your turn, instead of making a regular move.";
    }

    public void manOfStraw(){
        name="Man of Straw";
        desc="Play this card when your king is in check (even checkmate). It swaps positions with any one of your pawns, as long as the new position does not place it in check.";
        useCase="[i] Play this card before your move.";
    }

    public void martyr(){
        name="Martyr";
        desc="Play this card when one of your bishops has the choice of taking two or more of your opponent's pieces. Capture as many of these pieces as you want (at least two). Your bishop is removed from play and regarded as captured.";
        useCase="[i] Play this card on your turn, instead of making a regular move.";
    }

    public void mysticShield(){
        name="Mystic Shield";
        desc="The piece you just moved cannot be captured by your opponent on his next turn. If you moved more than one piece, you must designate only one to be protected.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void fogOfWar(){
        name="Fog of War";
        desc="This card cancels the effect of any other card. Both cards are discarded. If the opposing card constituted your opponent's whole move, he may make another move, but he may not play another card.";
        useCase="[i] Play this card immediately after your opponent has played the card you want to cancel.";
    }

    public void forbiddenCity(){
        name="Forbidden City";
        desc="Place a marker in any unoccupied square. No piece can enter this square, or pass through it, for the rest of the game. Knights and other \"jumping\" pieces may still pass over it.";
        useCase="[i] Play this card immediately after your move. Continuing Effect until the end of game.";
        continuingEffectUntilEnd=true;
    }

    public void holyQuest(){
        name="Holy Quest";
        desc="Swap the positions of a bishop and a knight belonging to your opponent.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void funeralPyre(){
        name="Funeral Pyre";
        desc="All captured pieces of both players are now considered dead. They cannot be returned to the chessboard throguh the play of a card.";
        useCase="[i] Play this card immediately after your move.";
    }

    public void handOfFate(){
        name="Hand of Fate";
        desc="Exchange your hand with your opponent's. He must draw another card to replace this one.";
        useCase="[i] Play this card before your move.";
    }




    public void createBitmap(Resources resources, int drawableId){
        this.sprite = BitmapFactory.decodeResource(resources, drawableId);
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

    public Bitmap getDrawable(){return this.sprite;}

    public boolean isOwned(){
        if (owned)
            return true;
        return false;
    }

    public void setOwned(){owned=true;}
}

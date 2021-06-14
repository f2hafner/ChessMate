# ChessMate
ChessMate is a mobile android game all about Chess and Cards.

## Table of Contents
1. [Description](#Description)

2. [Sequence-Diagram Client Server](#Diagram)

3. [Game Rules](#GameRules)

4. [Detailed info about the Game](#Detailed-Game-Info)

5. [Contributors](#Contributors)

6. [Installation](#Installation)



## Description<a name="Description"></a>
ChessMate is just your standard old chess game. Well it would be, but there is a twist to it. 
In ChessMate you will play a standard game of chess until the cards come into play.
Currently, our plan is to include a Carddeck of 15 Cards from which each player can draw. 
These cards can significantly alter the gameplay. Some will have a minor effect and some will change the rules of chess completely!
A player always has 3 cards on his hand. Though you can only play 1 Card per turn, there is plenty of ways to rig the game and drive your oponent into insanity.
For example: Will your piece get captured in a tradeoff ? Well, one of the cards allows you to shield your piece from any attack for one turn.
For more details on the Cards please refer to [Detailed info about the Game](#Detailed-Game-Info) down below.

This Game is made by CS Students, created for the purpose of learning android development. 




## Sequence-Diagram Client Server<a name="Diagram"></a>
![Sequence-Diagram](https://github.com/f2hafner/ChessMate/blob/master/ProjectResources/Seq_Diagram.svg)




## Game Rules<a name="GameRules"></a>

> ### Turn Sequence
> As in a regular game of chess, the players alternate
> turns. However, on each turn, you may play one card
> from your hand. Each card has a special effect,
> changing the rules of the game. Some cards modify
> your regular move. Others add to it, or replace it
> entirely!
> 
> You may never play more than one card on your turn, or
> on your opponent’s turn.
> When you play a card, it will end up on the discard pile. 
> The card’s effect takes place immediately.
> Exception: If a card has a continuing effect (see
> below), it will get marked to remind both
> players of that effect. Your hand must always
> consist of three cards. Your played card will immediately get 
> replaced by another card.
> 
> If you run out of cards to draw, the discard pile will be shuffled 
> and ready to draw from again.

> ### Continuing Effects
> Most cards change only a single move, but some last
> indefinitely . . . even through the whole game. These are called
> “Continuing Effect” cards. A card does not have a continuing
> effect unless it says so at the bottom! 
>
> #### Transformed Pieces
> Some Continuing Effect cards transform a piece,
> turning it into a new kind of piece with a new name. They will
> get marked by a color.
> Unless the creating card specifies otherwise, a
> transformed piece can be affected by cards that name the
> original piece. If a card returns the piece
> to play later after it got captured, 
> it returns as the original type of piece.
 
> ### Moving Without a Card
> You are never required to play a card. When you
> move without playing a card, you follow all the normal
> rules of chess . . . subject to any continuing effects of cards
> already played!
 
> ### Pawn Rules
> Pawns may promote only to Queen, Rook, Knight or
> Bishop. They may not promote to new types of piece.
 
> ### Variant
> #### Common Deck
> All the cards will be placed in one deck, face down. 
> Both players will get their hand of three cards, and
> all replacements, from this deck. (This is how the original
> French Tempête sur l’Echiquier worked.)
> If you use up all the cards, the discards will be reshuffled.


## Detailed Info about the Game<a name="Detailed-Game-Info"/></a>

### **The Cards**

> * #### Cowardice
>   Move one of your opponent's pawns one or two squares backward. It may not enter or cross an occupied square.  


> * #### Dark Mirror
>   On this move, one of your pawns can capture by moving diagonally backward instead of forward.  


> * #### Death Dance
>   Exchange the position of any of your pieces with any adjacent enemy piece.    


> * #### Disintegration
>   Remove one of your own pawns from the chessboard, and set it aside. It is now [i]dead,
>   and cannot be brought back into play with another card.  


> * #### Champion
>   Any one knight in play becomes a Champion. Place a marker underneath it. Instead of jumping like a knight, to the opposite corner of a 2 by 3 rectangle, a Champion jumps to >   the opposite corner of a 3 by 4 rectangle.  
>
>   Continuing Effect:
>   Until the transformed knight is removed from the board.


> * #### Rebirth
>   Move one enemy piece to any square it could have occupied at the beginning of the game. The square must be empty or contain one of your pieces. If one of your pieces is in > >   the square, it is captured.  


> * #### Revelation
>   Replace one of your knights or one of your opponent's knights by a bishop owned by the same player.  


> * #### Long Jump
>   Move one of your knights to any square whose color is different from the one it currently occupies. You cannot capture a piece with this move.    


> * #### Lost Castle
>   Swap the positions of one of your rooks and one of your opponent's rooks.  


> * #### Mystic Shield
>   The piece you just moved cannot be captured by your opponent on his next turn.  If you moved more than one piece, you must designate only one to be protected.  


> * #### Forbidden City
>   Place a marker in any unoccupied square. No piece can enter this square, or pass through it, for the rest of the game. 
>   Knights and other "jumping" pieces may still pass over it.  
>
>   Continuing Effect:
>   Until the end of the game.


> * #### Holy Quest
>   Swap the positions of a bishop and a knight belonging to your opponent.  


> * #### Hand of Fate
>   Exchange your hand with your opponent's. He must draw another card to replace this one.  


> * #### Vulture
>   Take the last card played by your opponent and put it in your hand. (If each player has his own deck of cards, 
>   you must also discard your top undrawn card.)
 
  
> * #### Crusade
>   Play this card when you move a bishop without capturing a piece. This bishop immediately moves one more time.

  
  
  
  
## Installation<a name="Installation"/></a>




## Contributors<a name="Contributors"/></a>

* [f2hafner](https://github.com/f2hafner) as Architect | UI-Design
* [Bego99](https://github.com/Bego99) as UI-Design | Architect
* [VeronikaSemmelrock](https://github.com/VeronikaSemmelrock) as Developer | Tester
* [KathisGit](https://github.com/KathisGit) as Tester | Documentation
* [ZangerlA](https://github.com/ZangerlA) as Documentation | Developer

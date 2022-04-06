package com.mygdx.game.BusinessLayer;

import com.badlogic.gdx.utils.Array;

public class Battlefield {
    private Card[][] battleField = {};

    public Card[][] getBattleField(){
        return battleField;
    }

    public boolean AddCard(Card newCard, int positionX, int positionY){
        if (battleField[positionX][positionY] == null){
            battleField[positionX][positionY] = newCard;
            return true;
        }
        else
        {
            return false;
        }

    }

    public void Update(){
        for ( Card[] card : battleField)
        {
            //card.Attack();
        }
    }

}

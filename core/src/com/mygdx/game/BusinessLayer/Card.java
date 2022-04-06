package com.mygdx.game.BusinessLayer;

public class Card {
     private String name;
     private int health;
     private int damage;
     private enum ability{}

     public int getHealth(){
         return health;
     }

     public int getDamage(){
         return damage;
     }

     public String getName(){
         return name;
     }

     public void updateHealth(int resultHealth){
         health = resultHealth;
     }

     //public void attack(Card target){
     //    int resultHealth = target.getHealth() - this.damage;
     //    target.updateHealth(resultHealth);
     //}
}

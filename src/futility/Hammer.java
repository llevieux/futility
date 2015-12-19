/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futility;

/**
 *
 * @author Kane
 */
public class Hammer extends Item{
    public Hammer(String name){
        super(name);
        isFlammable = false;
        isMoveable = true;
        description = "The hammer is a piece of steel on the top of a wooden stick, this represents existentialism";
    }
    
    public void Strike(Item toStrike){
        System.out.println(" You hit the " + this.name + " It made a loud noise");
    }
}

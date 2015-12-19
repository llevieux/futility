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
public class Refrigerator extends Item{
    
    public Refrigerator(String name){
        super(name);
        isFlammable = false;
        isMoveable = false;
        description = "Large white maker of Ice, the power to chill makes it entice";
    }
    
    public void refrigerate(Item item){
        System.out.println(" You refrigerated the " + this.name);
    }
    
     public void freeze(Item item){
        System.out.println(" You froze the " + this.name);
    }
}

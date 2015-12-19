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
public class Matchbox extends Item{
    private Match[] matches = new Match[6];
    
    public Matchbox(String name, Match[] p2){
        super(name);
        isFlammable = true;
        isMoveable = true;
        description = "the flame will guide all who seek darkness to the brim of daylight where all shall be calm";
        
        for(int i = 0; i < p2.length; i++){
            matches[i] = p2[i];
        }
    }
    
    
}

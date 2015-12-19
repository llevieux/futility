/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futility;

/**
 *
 * @author Kane
 * 
 * each lever is either on or off.  Otherwise they do nothing.
 */
public class UselessSwitch extends Item{
    private boolean state = false;
    
    public UselessSwitch(String name){
        super(name);
        isFlammable = false;
        isMoveable = false;
    }
    
    public void Switch(){
        if (state)
            state = false;
        else
            state = true;
    }
    
    public boolean getState(){
        return state;
    }
}

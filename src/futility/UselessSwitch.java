/*
 * Copyright (C) 2015 Lucas LeVieux & Kane Mcgrath
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package futility;

/**
 * @author Kane McGrath
 * 
 * each lever is either on or off.  Otherwise they do nothing.
 */
public class UselessSwitch extends Item{
    /**
     * Whether the switch is on or off.
     * 
     *      false -> off
     *      true -> on
     */
    private boolean state = false;
    
    /**
     * Constructor that initializes the switch off, with a name.
     * 
     * @param name the name of the switch
     */
    public UselessSwitch(String name){
        super(name);
        isFlammable = false;
        isMoveable = false;
    }
    
    /**
     * Toggles the state of the switch, and outputs a message.
     */
    public void Switch(){
        if (state == true)
        {
            state = false;
            Output.revealByLine(Output.randomText("you switched a switch.",
                getName() + " is now off",
                "the switch is off.  \n\n you are still in the same room."));
        }
        else
        {
            Output.revealByLine(Output.randomText("you switched a switch.  of course you did.",
                getName() + " is now off.  or is it on?  who can tell.",
                "the switch is on.  \n\n you are still in the same room."));
            state = true;
        }
    }
    
    /**
     * @return the state of the switch. false -> off, true -> on
     */
    public boolean getState(){
        return state;
    }
}

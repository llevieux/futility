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
    private boolean state = false;
    
    public UselessSwitch(String name){
        super(name);
        isFlammable = false;
        isMoveable = false;
    }
    
    public void Switch(){
        System.out.println("\n you switched a switch!\n");
        if (state)
            state = false;
        else
            state = true;
    }
    
    public boolean getState(){
        return state;
    }
}

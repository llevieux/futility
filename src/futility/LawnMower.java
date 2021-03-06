/*
 * Copyright (C) 2015 Kane
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
 *
 * @author Kane
 */
public class LawnMower extends Item{
    private boolean running = false;
    public LawnMower(String name){
        super(name);
        isFlammable = false;
        isMoveable = false;
        description = "the real beauty is underneath";
    }
    
    public void mow(Item item){
        if (running){
            Output.revealByLine("you push the mower on to the " + name + " and it breaks"); 
        }else{
            Output.revealByLine("you push the silent mower on to the " + name + " and it does nothing");
        }
    }
    public void start(){
        if (running){
            Output.revealByLine("the lawnmower is already running, you are losing your sanity."); 
        }else{
            Output.revealByLine("you have started the lawnmower");
            running = true;
        }
    }
    public void stop(){
        if (running){
            Output.revealByLine("you turn off the lawn mower"); 
            running = false;
        }else{
            Output.revealByLine("it is already off");
        }
    }
}

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
 * A thing found in a room. Can be picked up and dropped via the player.
 * 
 * @author Kane McGrath
 */
public class Item 
{
    String name;
    boolean isFlammable;
    boolean isMoveable;
    boolean broken = false;
    String description;
    Match[] matches = null;
    
    public Item(String name, boolean isFlammable, boolean isMoveable)
    {
        this.name = name;
        this.isFlammable = isFlammable;
        this.isMoveable = isMoveable;
        this.description = "";
    }
    public Item(String name){
        this(name, true, true);
    }
    
    public Item(){
        this("");
    }
    
    public void setDescription(String input){
        description = input;
    }
    
    public String getName() {
        return name;
    }
    
    public void light(){
        Output.revealByLine("you can't light the " + name);
    }
    
    public void extinguish(){
        Output.revealByLine("you can't extinguish the " + name);
    }
    
    public void eat(){
        Output.revealByLine("you ate " + name);
    }
    
    public void burn(){
        Output.revealByLine("you burned " + name);
        broken = true;
    }
    
    public void toss(){
        Output.revealByLine("you threw " + name);
        broken = true;
    }
    
    public void examine(){
        Output.revealByLine(description, name + "is also broken.");
    }
    
    public String toString()
    {
        return name + ": " + description;
    }
    
    public void toggle()
    {
        if (broken)
               Output.revealByLine(name + " is broken.", 
                       "plus, it doesn't have a switch on it");
        else
            Output.revealByLine("you can't switch " + name);
    }
    
    public void open()
    {
        Output.revealByLine(name + " doesn't open.");
    }
    
    public void Strike(Item toStrike){
        if (toStrike == this)
            Output.revealByLine("you hit " + toStrike.name + " with itself?!?  how'd you do that?");
        else if (isMoveable)
        {
            Output.revealByLine("you hit " + toStrike.name + ".  hope that's fun for you.");
            toStrike.name = "broken " + toStrike.name;
            broken = true;
        }
        else
            Output.revealByLine("?!?! you can't even lift " + toStrike.name + ".");
    }
}

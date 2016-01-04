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
    String description;
    
    
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
        System.out.println(" you can't light the " + name);
    }
    
    public void extinguish(){
        System.out.println(" you can't extinguish the " + name);
    }
    
    public void eat(){
        System.out.println(" you ate the " + name);
        Futility.game.player.die();
    }
    
    public void burn(){
        System.out.println(" you burned the " + name);
    }
    
    public void toss(){
        System.out.println(" you threw the " + name);
    }
    
    public void examine(){
        System.out.println(" " + description);
    }
    
    public String toString()
    {
        return name + ": " + description;
    }
    
    public void Switch()
    {
        System.out.println(" you can't switch the " + name);
    }
}

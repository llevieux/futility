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
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Item 
{
    String name;
    boolean isFlammable;
    boolean isMoveable;
    
    public Item(String name, boolean isFlammable, boolean isMoveable)
    {
        this.name = name;
        this.isFlammable = isFlammable;
        this.isMoveable = isMoveable;
    }
    public Item(String name){
        this(name, true, true);
    }

    public String getName() {
        return name;
    }
    
    public void eat(){
        System.out.println(" You ate the " + name);
    }
    
    public void burn(){
        System.out.println(" You burned the " + name);
    }
    
    public void toss(){
        System.out.println(" You threw the " + name);
    }
}

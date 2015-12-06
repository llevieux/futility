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
 * The player, AKA the user.  Only one object, created in Game.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Player {
    /**
     * How many items can fit into the inventory.  Determines the size of the
     * array inventory.
     */
    private static final int INVENTORYSIZE = 2;
    
    /**
     * True if the player is still alive.  Toggled to false by die().
     * Accessed by isAlive.
     */
    private boolean isAlive = true;
    
    /**
     * Inventory contains two items.  These are the things that the user can
     * use to make actions with other things.
     */
    private Item[] inventory = new Item[INVENTORYSIZE];
    
    /**
     * A reference to the current room in rooms
     */
    private Room currentRoom;
    
    /**
     * @return string representation of the inventory, using the item.name 
     * separated by a space.
     */
    public String getInventory()
    {
        String result = "";
        for (int i=0; i<INVENTORYSIZE; i++) //go through each index of inventory
            if (inventory [i] != null) //ensure it is filled
                result += inventory[i] + " "; //append to result
        return result;
    }
    
    /**
     * When called the player becomes not alive, AKA isAlive() returns false.
     */
    public void die()
    {
        isAlive = false;
    }
    
    /**
     * @return true if the player is still alive, false if not.  Triggered by 
     * die().
     */
    public boolean isAlive()
    {
        return this.isAlive;
    }
}

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
public class Player 
{
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
     * Constructor that initializes the current room.
     * 
     * @param startingRoom the room that the Player starts in
     */
    public Player(Room startingRoom)
    {
        this.currentRoom = startingRoom;
    }
    
    /**
     * @return string representation of the inventory, using the item.name 
     * separated by a space.
     */
    public String getInventory()
    {
        String result = "";
        for (int i=0; i<INVENTORYSIZE; i++) //go through each index of inventory
            if (inventory [i] != null) //ensure it is filled
                result += inventory[i].getName() + ", "; //append to result
        
        if (result.equals(""))
            return "(none)";
        
        return result;
    }
    
    public Item[] getInventoryArray()
    {
        return inventory;
    }
    
    /**
     * Picks up an item, by adding it to inventory.
     * and removing it from the current room
     * 
     * @param toGet item to pick up.
     */
    public void get(Item toGet)
    {
        for(int i=0; i<inventory.length; i++)
            if (toGet.isMoveable){
                if (inventory[i] == null) //there's an emtpty slot in the inventory
                {
                    inventory[i] = toGet; //add to inventory
                    currentRoom.removeItem(toGet); //remove from room
                    return;
                }
            //else
            System.out.println(" you are a human. you can't really hold more "
                    + "than 2 things.  you'll need to drop something first.");  
            }
    }
    
    /**
     * Removes an Item from your inventory and adds it to the current room
     * 
     * @param toDrop The item to drop
     */
    
    public void drop(Item toDrop)
    {
        for(int i=0; i<inventory.length; i++)
            if (inventory[i] == toDrop){
                inventory[i] = null; //remove from inventory
                currentRoom.addItem(toDrop); //add to room
                return;
            }
        
        System.out.println(" you do not have the item in your inventory");
               
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
    
    /**
     * @return the room the Player is currently in.
     */
    public Room getCurrentRoom() 
    {
        return currentRoom;
    }
    
    /**
     * Moves the player to a specified room.
     * 
     * @param toRoom the room to move to
     */
    public void move(Room toRoom)
    {
        if (toRoom != null && currentRoom.isAccessible(toRoom))
            currentRoom = toRoom;
    }
    
     

}

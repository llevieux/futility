/*
 * Copyright (C) 2015 Lucas LeVieux & Kane McGrath
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

import java.util.Arrays;

/**
 * An object for a room.  Includes a name, an array of items that are contained
 * in it, an array of rooms that are accessible from it.
 * 
 * An array of these things are found in Game.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Room 
{
    /**
     * An array containing all of the rooms accessible from this object.  Sorted
     * alphabetically.  Use addAccessibleRooms, setAccessibleRooms,
     * getAccessibleRooms, and isAccessible(Room).
     */
    private Room[] accessibleRooms = new Room[0];
    
    /**
     * All of the items contained and available in the room, in the order that 
     * they are entered.  Use this.addItems to add to it, and getItems to read
     * from it.
     */
    private Item[] itemsInRoom = new Item[0];
    
    /**
     * The name of the room.
     */
    private String name = "";
    
    /**
     * Text that is printed on look().
     */
    private String lookText;
    
    /**
     * Constructor to initialize a new room with a name, accessible rooms, and
     * arrays with contained items.
     * 
     * @param name the name of the room.
     * @param accessibleRooms  all of the rooms accessible from this object (can
     * be null)
     * @param itemsInRoom the items contained and available inside the room.
     * Arbitrary amount of arrays of items are accepted.  Items will be
     * arranged in the order that they are entered.
     */
    public Room(String name, Room[] accessibleRooms, Item[]... itemsInRoom)
    {
        this.name = name;
        
        setAccessibleRooms(accessibleRooms);
        
        if (itemsInRoom != null)
            for (int i=0; i<itemsInRoom.length; i++) //for each argument
                this.addItems(itemsInRoom[i]);
    }
    
    /**
     * Constructor to initialize a new room with a name, accessible rooms, look 
     * text and arrays with contained items.
     * 
     * @param name the name of the room.
     * @param accessibleRooms  all of the rooms accessible from this object (can
     * be null)
     * @param lookText the text that is displayed by the "look" command
     * @param itemsInRoom the items contained and available inside the room.
     * Arbitrary amount of arrays of items are accepted.  Items will be
     * arranged in the order that they are entered.
     */
    public Room(String name, Room[] accessibleRooms, String lookText, Item[]... itemsInRoom)
    {
        this(name, accessibleRooms, itemsInRoom);
        setLookText(lookText);
    }
    
    /**
     * Constructor to only name the room.
     * 
     * @param name the name of the room.
     */
    public Room(String name)
    {
        this(name, null);
    }
    
    /**
     * @return the name of the room
     */
    public String getName() 
    {
        return this.name;
    }
    
    /**
     * @param input a room to be tested if it is accessible.
     * @return true if input is an accessible room
     */
    public boolean isAccessible(Room input)
    {
        for (int i=0; i<accessibleRooms.length-1; i++) //go through all the accessible rooms
            if (accessibleRooms[i] == input) //if we've found the room
                return true;
        
        //if not found
        return false;
    }
    
    /**
     * @param accessibleRooms an array containing all of the rooms accessible 
     * from this one
     */
    public void setAccessibleRooms(Room[] accessibleRooms) 
    {
        if (accessibleRooms == null)
            return;
        for (int i=0; i<accessibleRooms.length-1; i++) //copy fom argument to instance var
            this.accessibleRooms[i] = accessibleRooms[i]; //referance, not a copy
    }
    
    /**
     * Add rooms that are now accessible from this object.
     * 
     * @param newRooms the newly accessible rooms
     */
    public void addAccessibleRooms(Room[] newRooms)
    {
        if (newRooms == null)
            return;
        
        Room[] output = new Room[this.accessibleRooms.length + newRooms.length];
        
        int i=0, j=0;
        for (; i<this.accessibleRooms.length; i++) //for each in current
            output[i] = this.accessibleRooms[i]; //shallow copy
        
        for (; i<output.length; i++, j++)
            output[i] = newRooms[j]; //output continues where it left off
        
        this.accessibleRooms = output;
    }
    
    public void addAccessibleRoom(Room newRoom)
    {
        addAccessibleRooms(new Room[]{newRoom});
    }
    
    /**
     * @return an array containing all of the rooms accessible from this one
     */
    public Room[] getAccessibleRooms()
    {
        return accessibleRooms;
    }
    
    /**
     * Adds items to itemsInRoom in the order that they are entered.
     * 
     * @param newItems items to be added
     */
    public void addItems (Item[] newItems)
    {
        if (newItems == null)
            return;
        
        Item[] output = new Item[this.itemsInRoom.length + newItems.length];
        
        int i=0, j=0;
        for (; i<this.itemsInRoom.length; i++) //for each in current
            output[i] = this.itemsInRoom[i]; //shallow copy
        
        for (; i<output.length; i++, j++)
            output[i] = newItems[j]; //output continues where it left off
        
        this.itemsInRoom = output;
    }
    
    /**
     * Adds a single item via addItems
     * 
     * @param newItem item to be added to the room
     */
    public void addItem (Item newItem)
    {
        addItems (new Item[]{newItem});
    }

    /**
     * @return an array of all the items contained and available in the room
     */
    public Item[] getItems() 
    {
        return itemsInRoom;
    }
    
    /**
     * Returns a string with all the instance data.  In the format:
     * 
     * room: [name]
     * items in room: [item1.getName()], [item2.getName()], ... [itemn.getName()]
     * accessible rooms: [room1.getName()], [room2.getName()], ... [roomn.getName()]
     * 
     * Outputs (none) in the appropriate area if there are no items or rooms.
     * @return string describing the room
     */
    public String toString()
    {
        String output = "room: " + this.name + "\nitems in room: ";
        if (itemsInRoom.length == 0) //if empty
            output += "(none)";
        for (int i=0; i<this.itemsInRoom.length; i++)
        {
            output += itemsInRoom[i].getName();
            if (i != (itemsInRoom.length - 1))
                output += ", "; //all but last iteration
        }
        
        output += "\naccessible rooms: ";
        if (accessibleRooms.length == 0) //if empty
            output += "(none)";
        for (int i=0; i<this.accessibleRooms.length; i++)
        {
            output += accessibleRooms[i].getName();
            if (i != (accessibleRooms.length - 1))
                output += ", "; //all but last iteration
        }   
        
        return output;
    }
    
    /**
     * Prints a description of the Room.  The text can, and should, be added via
     * setLookText.
     */
    public void look()
    {
        if (lookText == null)
            System.out.println(" the room is just a normal room.");
        else
            System.out.println(" " + lookText);
    }
    
    /**
     * Sets the text that is used by look().
     * 
     * @param lookText a wry description of the room.
     */
    public void setLookText (String lookText)
    {
        this.lookText = lookText;
    }
    /**
     * Removes an item from the room
     * 
     * @param toRemove the item to remove
     */
    public void removeItem (Item toRemove)
    {
        for (int i = 0; i < itemsInRoom.length; i++)
        {
            if (itemsInRoom[i] == toRemove)
            {
                itemsInRoom[i] = null;
            }
        }
    }
}

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
    private Room[] accessibleRooms;
    
    /**
     * All of the items contained and available in the room, sorted
     * alphabetically.  Use this.addItems to add to it.
     */
    private Item[] itemsInRoom = new Item[0];
    
    /**
     * The name of the room.
     */
    private String name = "";
    
    /**
     * A copy constructor to make a duplicate of the passed Room.
     * @param toCopy object to copy
     */
    public Room(Room toCopy)
    {
        for (int i=0; i<toCopy.accessibleRooms.length-1; i++)
            this.accessibleRooms[i] = new Room(toCopy.accessibleRooms[i]); //recursive
    }
    
    /**
     * Constructor to initialize a new room with a name, accessible rooms, and
     * contained items.
     * 
     * @param name the name of the room.
     * @param accessibleRooms all of the rooms accessible from this object
     * @param itemsInRoom the items contained and available inside the room.
     * Arbitrary amount of arrays of items are accepted.  Items will be
     * arranged in alphabetical order, so order doesn't matter.
     */
    public Room(String name, Room[] accessibleRooms, Item[]... itemsInRoom)
    {
        this.name = name;
        
        setAccessibleRooms(accessibleRooms);
        
        for (int i=0; i<itemsInRoom.length-1; i++) //for each argument
            this.addItems(itemsInRoom[i]);
        
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
        //TODO
        return true;
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
        
        java.util.Arrays.sort(output); //sort alphabetically
        
        this.accessibleRooms = output;
    }
    
    /**
     * @return an array containing all of the rooms accessible from this one
     */
    public Room[] getAccessibleRooms()
    {
        return accessibleRooms;
    }
    
    /**
     * Adds items to itemsInRoom and sorts alphabetically.
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
        
        java.util.Arrays.sort(output); //sort alphabetically
        
        this.itemsInRoom = output;
    }

    /**
     * @return an array of all the items contained and available in the room
     */
    public Item[] getItems() {
        return itemsInRoom;
    }
}

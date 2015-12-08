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
     * An array containing all of the rooms accessible from this object.
     */
    private Room[] accessibleRooms;
    
    /**
     * All of the items contained and available in the room.
     */
    private Item[] itemsInRoom;
    
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
     * Arbitrary amount of arrays of items are accepted.
     */
    public Room(String name, Room[] accessibleRooms, Item[]... itemsInRoom)
    {
        this.name = name;
        
        for (int i=0; i<accessibleRooms.length-1; i++) //copy fom argument to instance var
            this.accessibleRooms[i] = accessibleRooms[i]; //referance, not a copy
        
        int k = 0; //a counter for this.itemsInRoom, in order to unify arguments
        for (int i=0; i<itemsInRoom.length-1; i++) //for each argument
            for (int j=0; j<itemsInRoom[i].length-1; j++) //for each in array
            {
                this.itemsInRoom[k] = itemsInRoom[i][j]; //referance, not a copy
                k++; //move to next element in this.itemsInRoom
            }
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
    public void setAccessibleRooms(Room[] accessibleRooms) {
        for (int i=0; i<accessibleRooms.length-1; i++) //copy fom argument to instance var
            this.accessibleRooms[i] = accessibleRooms[i]; //referance, not a copy
    }
    
    /**
     * @return an array containing all of the rooms accessible from this one
     */
    public Room[] getAccessibleRooms()
    {
        return accessibleRooms;
    }
}

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
     * A copy constructor to make a duplicate of the passed object.
     * @param toCopy object to copy
     */
    public Room(Room toCopy)
    {
        for (int i=0; i<toCopy.accessibleRooms.length-1; i++)
            this.accessibleRooms[i] = new Room(toCopy.accessibleRooms[i]); //recursive
    }
    
    /**
     * Constructor.
     * @param accessibleRooms an array containing all of the rooms accessible 
     * from this one
     */
    public Room(Room[] accessibleRooms)
    {
        for (int i=0; i<accessibleRooms.length-1; i++) //copy fom argument to instance var
            this.accessibleRooms[i] = accessibleRooms[i]; //referance, not a copy
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

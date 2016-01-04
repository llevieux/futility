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
 * @author Kane McGrath
 */
public class Refrigerator extends Item{
    
    public Refrigerator(String name){
        super(name);
        isFlammable = false;
        isMoveable = false;
        description = "large white maker of ice, the power to chill makes it entice";
    }
    
    public void refrigerate(Item item){
        Output.revealByLine("you refrigerated " + item.name);
    }
    
     public void freeze(Item item){
        Output.revealByLine("you froze " + item.name);
    }
}

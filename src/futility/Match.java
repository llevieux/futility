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
 *
 * @author Kane
 */
public class Match extends Item{
    private boolean lit = false; //Wether the match is lit true = lit
    public Match(String name){
        super(name);
        isFlammable = true;
        isMoveable = true;
    }
    
    public void light(){
        if (!lit){
            System.out.println(" You have lit the match");
            lit = true;
        } else {
            System.out.println(" The match is already lit");
        }
        
    }
    public void eat(){
        if (lit){
            System.out.println(" You have ate the match and the fire burns your throat");
            player.die();
        } else {
            System.out.println(" You have swallowed the match");
        }
    }
    public void extinguish(){
        if (lit){
            System.out.println(" You have Extinguished the match");
            lit = false;
        } else {
            System.out.println(" you try to extinguish the unlit match");
            System.out.println(" and it does not work");
        }
    }
}

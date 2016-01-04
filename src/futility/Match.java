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
            Output.revealByLine("you have lit the match");
            lit = true;
        } else {
            Output.revealByLine("the match is already lit");
        }
        
    }
    public void eat(){
        if (lit){
            Output.revealByLine("you have ate the match and the fire burns your throat");
            Futility.game.player.die();
        } else {
            Output.revealByLine("you have swallowed the match");
        }
        Futility.game.player.getCurrentRoom().removeItem(this);
    }
    public void extinguish(){
        if (lit){
            Output.revealByLine("you have extinguished the match");
            lit = false;
        } else {
            Output.revealByLine("you try to extinguish the unlit match",
                    "and it does not work");
        }
    }
}

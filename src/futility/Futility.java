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

import java.util.Scanner;

/**
 * The "runme" class for futility.  The only public method, main(), introduces
 * the user, and makes a new game recursively.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public final class Futility
{
    
    /**
     * Object which allows for user input.
     */
    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Introduces the user, then iteratively does a new Game.  Counts the number
     * of games, and supplies it as constructor argument to Game.
     * 
     * @param args the command line arguments (none used)
     */
    public static void main(String[] args) {
        System.out.println(
            "  ████           ██   ██  ██  ██   ██\n" +
            " ██            ██████     ██     ██████\n" +
            " ██    ██   ██   ██   ██  ██  ██   ██   ██   ██\n" +
            "█████  ██   ██   ██   ██  ██  ██   ██    ██ ██\n" +
            " ██    ██   ██   ██   ██  ██  ██   ██     ███\n" +
            " ██     █████    ██   ██  ██  ██   ██    ██\n" +
            "                                        ██\n" +
            "                  the game"); //looks good when fixed-width
        
        
        
        /**
         * User input on whether to do a new game or not.
         */
        String again;
        
        /**
         * The number of games played so far.
         */
        int gameCount = 0;
        
        do
        {
            new Game(gameCount);
            System.out.println("\ntry again?");
            //keyboard.nextLine(); //get rid of \n
            again = keyboard.nextLine();
            System.out.println("\n");
            gameCount++;
        } while (again.charAt(0) == 'y' || again.charAt(0) == 'Y');
    }
    
    /**
     * @param numberOfSpaces number of spaces to output
     * @return a string containing numberOfSpaces spaces
     */
    private static String spaces(int numberOfSpaces)
    {
        String output = "";
        
        for (int i=0; i<numberOfSpaces; i++)
            output += " ";
        
        return output;
    }
}

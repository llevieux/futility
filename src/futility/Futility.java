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
    public static final Scanner keyboard = new Scanner(System.in);

    /**
     * Introduces the user, then iteratively does a new Game.  Counts the number
     * of games, and supplies it as constructor argument to Game.
     * 
     * @param args the command line arguments (none used)
     */
    public static void main(String[] args) {
        int leftSpacing = 16; //number of spaces to insert to the left of the wordmark
        
        System.out.print(newLines(8) +
            spaces(leftSpacing) + "  ████           ██   ██  ██  ██   ██\n" +
            spaces(leftSpacing) + " ██            ██████     ██     ██████\n" +
            spaces(leftSpacing) + " ██    ██   ██   ██   ██  ██  ██   ██   ██   ██\n" +
            spaces(leftSpacing) + "█████  ██   ██   ██   ██  ██  ██   ██    ██ ██\n" +
            spaces(leftSpacing) + " ██    ██   ██   ██   ██  ██  ██   ██     ███\n" +
            spaces(leftSpacing) + " ██     █████    ██   ██  ██  ██   ██    ██\n" +
            spaces(leftSpacing) + "                                        ██\n" +
            spaces(leftSpacing + 18) + "the game" + 
                newLines (8)+
                "press enter to start"
        ); //looks good when fixed-width
        
        keyboard.nextLine(); //proceed on enter
        clearScreen();
        
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
        } while (Character.toLowerCase(again.charAt(0)) == 'y');
    }
    
    /**
     * @param repetitions number of characters to return
     * @param character which character to repeat repetitions times
     * @return a string containing repetitions x character
     */
    public static String repeatedCharactors(int repetitions, char character)
    {
        String output = "";
        
        for (int i=0; i<repetitions; i++)
            output += character;
        
        return output;
    }
    
    /**
     * @param numberOfSpaces number of spaces to return
     * @return numberOfSpaces spaces
     */
    public static String spaces(int numberOfSpaces)
    {
        return repeatedCharactors(numberOfSpaces, ' ');
    }
    
    /**
     * @param numberOfSpaces number of newlines to return
     * @return string containing numberOfNewLines x "\n"s
     */
    public static String newLines(int numberOfLines)
    {
        return repeatedCharactors(numberOfLines, '\n');
    }
    
    /**
     * Clears the screen by adding 50 newLines.
     */
    public static void clearScreen()
    {
        System.out.print(newLines(50));
    }
}

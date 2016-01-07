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
 * Command line arguments
 *      debug mode (doesn't wait): "debug", "--debug" or "-d".
 *      skip intro: "skipintro" , "--skipintro", or "-s"
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
     * The current game being played.  It has default access, which means any 
     * part of the program can access any other part of the program through
     * Futility.game.
     */
    static Game game;
    
    /**
     * Introduces the user, then iteratively does a new Game.  Counts the number
     * of games, and supplies it as constructor argument to Game.
     * 
     * @param args the command line arguments (none used)
     */
    public static void main(String... args) {
        Output.clearScreen();

	int leftSpacing = 16; //number of spaces to insert to the left of the wordmark
        
        System.out.print(Output.newLines(8) +
            Output.spaces(leftSpacing) + " █████           ██   ██  ██  ██   ██\n" +
            Output.spaces(leftSpacing) + " ██            ██████     ██     ██████\n" +
            Output.spaces(leftSpacing) + " ██    ██   ██   ██   ██  ██  ██   ██   ██   ██\n" +
            Output.spaces(leftSpacing) + "█████  ██   ██   ██   ██  ██  ██   ██    ██ ██\n" +
            Output.spaces(leftSpacing) + " ██    ██   ██   ██   ██  ██  ██   ██     ███\n" +
            Output.spaces(leftSpacing) + " ██     █████    ██   ██  ██  ██   ██    ██\n" +
            Output.spaces(leftSpacing) + "                                        ██\n" +
            Output.spaces(leftSpacing + 18) + "the game" + 
                Output.newLines (8)+
                " press enter to start"
        ); //looks good when fixed-width
        
        keyboard.nextLine(); //proceed on enter
        
        /**
         * User input on whether to do a new game or not.
         */
        String again;
        
        /**
         * The number of games played so far.
         */
        int gameCount = 1;
        
        do
        {
            Output.clearScreen();
            game = new Game(gameCount, args);
            Output.revealByLetter("try again? ");
            again = keyboard.nextLine();
            gameCount++;
        } while (Character.toLowerCase(again.charAt(0)) == 'y');

	Output.clearScreen(); //fresh exit!
    }
    
    /**
     * Displays an about + help message.
     */
    public static void about()
    {
        Output.revealByLetter(" futility: the game" + Output.newLines(2)
            + "      by lucas levieux and kane mcgrath" + Output.newLines(5)
            + " github.com/llevieux/futility" + Output.newLines(5)
            + " futility is a text based game.  that means when you type in"
            + " certain commands,"
            + " things happen." + Output.newLines(2) 
            + " good luck I guess." + Output.newLines(5)
            + " press enter to continue", .05);
        keyboard.nextLine();
        Output.clearScreen();
    }
}

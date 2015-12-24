/*
 * Copyright (C) 2015 Lucas LeVieux <contact@lucaslevieux.com>
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
 * Text output and parsing functions.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Output {
    /**
     * Whether debug mode is on or not.
     */
    public static boolean debugMode = false;
    
    /**
     * The maximum amount of characters per row.  Should be consistent with 
     * runme.bat.
     */
    private static final int COLS = 80;

    /**
     * Clears the screen by adding 100 newLines.
     */
    public static void clearScreen() 
    {
        System.out.print(newLines(100));
    }

    /**
     * Pauses execution for seconds seconds.
     *
     * @param seconds the amount of time to pause.
     */
    public static void wait(double seconds) 
    {
        if (debugMode)
            return;
        
        try 
        {
            Thread.sleep((int) (1000 * seconds));
        } catch (InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Returns a random string of those passed.  Useful for messages that you
     * don't want to be the same every time.
     *
     * @param text strings that have an equal chance of being printed to the
     * screen.
     */
    public static String randomText(String... text) 
    {
        return text[Game.random.nextInt(text.length)];
    }

    /**
     * Prints each string entered successively, with 6 lines blank lines in
     * between slowly scrolling at 1 second per line.
     *
     * @param text the text to be revealed, element by element
     */
    public static void revealByLine(String... text) 
    {
        for (int i = 0; i < text.length; i++) 
        {
            revealByLetter(text[i]);
            wait(.8);
            for (int h = 0; h < 4; h++) 
            {
                System.out.println();
                wait(.1);
            }
        }
    }
    
    /**
     * Prints each letter in text, with .1 seconds between.
     * 
     * @param text the text to be printed to the screen
     */
    public static void revealByLetter(String text)
    {
        revealByLetter(text, .08);
    }
    
    /**
     * Prints each letter in text, with waitTime seconds between.
     * 
     * @param text the text to be printed to the screen
     * @param waitTime the amount of time, in seconds, to wait between characters
     */
    public static void revealByLetter(String text, double waitTime)
    {
        text = " " + text; //prepending space
        char[] charArray = text.toCharArray();
        int currentColumn = 1;
        
        for (int i=0; i<charArray.length; i++)
        {
            if (currentColumn >= (COLS - 10) && charArray[i] == ' ')
            {
                System.out.print("\n     "); //word wrap!
                currentColumn = 5;
            }
            else
                System.out.print(charArray[i]);
            wait(waitTime);
            currentColumn++;
        }
    }

    /**
     * @param numberOfSpaces number of spaces to return
     * @return numberOfSpaces spaces
     */
    public static String spaces(int numberOfSpaces) {
        return repeatedCharactors(numberOfSpaces, ' ');
    }

    /**
     * @param repetitions number of characters to return
     * @param character which character to repeat repetitions times
     * @return a string containing repetitions x character
     */
    public static String repeatedCharactors(int repetitions, char character) {
        String output = "";
        for (int i = 0; i < repetitions; i++) {
            output += character;
        }
        return output;
    }

    /**
     * @param numberOfLines number of newlines to return
     * @return string containing numberOfNewLines x "\n"s
     */
    public static String newLines(int numberOfLines) {
        return repeatedCharactors(numberOfLines, '\n');
    }
    
    public static void horizontalLine()
    {
        revealByLetter(repeatedCharactors(COLS-2, '-'), .01);
        System.out.println();
    }
}

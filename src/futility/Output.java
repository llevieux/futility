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
     * Clears the screen by adding 100 newLines.
     */
    public static void clearScreen() 
    {
        System.out.print(Futility.newLines(100));
    }

    /**
     * Pauses execution for seconds seconds.
     *
     * @param seconds the amount of time to pause.
     */
    public static void wait(double seconds) 
    {
        try 
        {
            if (!debugMode)
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
    public static void revealText(String... text) 
    {
        for (int i = 0; i < text.length; i++) 
        {
            System.out.print(" " + text[i]);
            for (int h = 0; h < 4; h++) 
            {
                System.out.println();
                wait(.75);
            }
        }
    }
    
    
}

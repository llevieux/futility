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
import java.util.Random;

/**
 * A game.  The constructors are the only public methods.  Called by 
 * Futility.main().
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Game 
{
    
    /**
     * Object which allows for user input.
     */
    private static final Scanner keyboard = new Scanner(System.in);
    
    /**
     * Count of games played in this session
     */
    private int gameCount = 0;
    
    /**
     * The player.
     */
    private Player player;
    
    /**
     * An array containing all the Rooms in the game.
     */
    private Room[] rooms = new Room[18];
    
    /**
     * A random generator.  nextInt(int max) is very useful.
     */
    private final Random random = new Random();
    
    /**
     * Some names I made up.  see randomName().
     */
    private String[] names = {
            "hollie", "jack",  "rufus", "doriana", "ashby", "lucas", "kane",
            "areman", "bob", "ryan", "jude", "sam", "samuel", "samuindo", "sum",
            "sumuel", "samwise", "toby", "frodo", "santa", "cadby", "sullivan",
            "lacy", "peg", "randy", "rando", "rundy", "ice t", "obama", "trudy",
            "marcos", "brie", "bling", "trevor", "amathyst", "jaden", "irwin",
            "asher", "jesus", "carolina", "chance", "bilbo", "steve", "john",
            "olleh", "bam", "ban", "bun", "orion", "baby", "ice cube", "yolo",
            "doodle", "diddle", "jax", "harry", "cassidy", "light", "flame"
        };
    
    /**
     * Runs a game.
     * @param gameCount Count of games played in this session
     */
    public Game (int gameCount)
    {
        this.gameCount = gameCount;
        main();
    }
    
    /**
     * Runs a game, with gameCount set at 0.
     */
    public Game()
    {
        this(0); //runs Game(int) constructor for 0
    }
    
    /**
     * The main portion of the game.  Called by the constructor.
     */
    private void main()
    {
        /* commented out until the other classes are finished
        
        //--------------------------INITIALIZATION--------------------------
        
        //ITEMS
        Match[] matches = new Match[6];
        for (int i=0; i<matches.length; i++)
            matches[i] = new Match(randomName() + " the match");
        Matchbox matchbox = new Matchbox("matchbox", matches);
        
        Item flare = new Item("flare", true, true);
        Hammer hammer = new Hammer(randomName() + " the hammer");
        Refrigerator refrigerator = new Refrigerator("refrigerator");
        LawnMower lawnMower = new LawnMower("lawnmower");
        AntHill antHill = new AntHill("anthill");
        Box box = new Box("box");
        Box anotherBox = new Box("anouther box");
        
        UselessSwitch[] levers = new UselessSwitch[random.nextInt(4) + 1];
            //random number between 1 and 5
        for (int i=0; i<levers.length; i++)
            levers[i] = new UselessSwitch("lever " + (i+1));
        
        UselessSwitch[] buttons = new UselessSwitch[random.nextInt(3) + 1];
            //random number between 1 and 4
        for (int i=0; i<buttons.length; i++)
            buttons[i] = new UselessSwitch("button " + (i+1));
        
        Item monopolyMoney = new Item("monopoly money", true, true);
        UselessSwitch pressureCooker = new PressureCooker("pressure cooker");
        Chalk chalk = new Chalk("chalk");
        
        Item[] theRoomItems = {matchbox, flare, hammer, refrigerator, lawnMower,
            antHill, box, anotherBox, monopolyMoney, pressureCooker, chalk};
        
        
        //ROOMS
        Room theRoom = new Room("room", 
                new Room[0], //no rooms accessible from theRoom
                theRoomItems, levers, buttons //three lists of items to be added
        );
        
        Room[] rooms = {theRoom};
        
        //PLAYERS
        player = new Player(theRoom);
        */
        System.out.println(randomName() + ", " + randomName() + ", " 
                + randomName() + ", " + randomName() + ", " + randomName());
    }
    
    /**
     * @return a random name from a list of names that I made up.  Each name
     * can only be generated once, so don't worry about duplicate names.
     * If there's no more non-duplicate names left, it returns "loaf".
     */
    private String randomName()
    {
        int randomIndex = 0, i = 0;
        boolean empty = true; //assume emtpy, untill proven false
        do
        {
            if (i == names.length) 
            {
                for (int h=0; h<names.length; h++) //look in each
                {
                    if (names[h] != null)
                    {
                        empty = false;
                        break;
                    }
                }
                if (empty == true)
                    return "loaf"; //no non-nulls left :(
            }
            else
                i++;
            randomIndex = random.nextInt(names.length);
            
        } while(names[randomIndex] == null); //find a name that hasn't been used
                
        String output = new String(names[randomIndex]); //deep copy
        names[randomIndex] = null;
        
        return output;
    }
}

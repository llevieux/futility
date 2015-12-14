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
    private static final Scanner keyboard = Futility.keyboard;
    
    /**
     * The time between lines in revealText.
     */
    private static final double WAITTIME = 0; //1 for normal, 0 for testing
    
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
    private Room[] rooms;
    
    /**
     * A random generator.  nextInt(int max) is very useful.
     */
    private static final Random random = new Random();
    
    /**
     * Some names I made up.  see randomName().
     */
    private String[] availableNames = {
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
        */
        
        //ROOMS
        Room theRoom = new Room("room", 
                new Room[0] //no rooms accessible from theRoom
                //theRoomItems, levers, buttons //three lists of items to be added
        );
        Room billiardsRoom = new Room("billiards room", null,
                new Item[]{new Item("cue stick"), new Item("chair")});
        
        Room[] rooms = {theRoom, billiardsRoom};
        
        
        
        //PLAYERS
        player = new Player(theRoom);
        
        //--------------------------INTRODUCTION--------------------------
        revealText("you are in a small, concrete-reinforced room",
                "your only wish in your humble life is to get out",
                "there are no doors and no windows");
        
        
        //--------------------------MAIN LOOP--------------------------
        while (player.isAlive()/* && player.getCurrentRoom().getName() == "room"*/)
        {            
            //display hints and stats
            String command1;
            do
            {
                System.out.print(" what do you do? ");
                command1 = keyboard.next().toLowerCase();
                if (!isValidCommand(command1))
                    System.out.println("\n nope, that's not something you can do.\n");
            } while (!isValidCommand(command1));
            
            if (!requiresSecondCommand(command1))
            {
                keyboard.nextLine();
                Futility.clearScreen();
            
                switch(command1)
                {
                    case "about":
                    case "help":
                        Futility.about();
                        continue; //no need for a second command
                    case "inventory":
                        System.out.println(player.getInventory());
                        continue;
                    case "look":
                    case "scope":
                        player.getCurrentRoom().look();
                        System.out.print(Futility.newLines(5));
                        continue;
                    case "jump":
                        revealText("you jump in the air", "\"whee, you shout\"");
                        continue;
                    case "die":
                        System.out.println(" how morbid of you.");
                        Futility.wait(3.0);
                        Futility.clearScreen();
                        player.die();
                        continue;
                    case "exit":
                    case "end":
                    case "quit":
                        revealText(
                            "you are still in a small, concrete-reinforced room",
                                "you can't \"" + command1 + "\".");
                        Futility.clearScreen();
                        continue;
                    default:
                        System.out.println(" internal error #1 - sorry bout that");
                        break;
                }
            } else 
            {
                String command2 = keyboard.next().toLowerCase();
                
                switch(command2)
                {
                    default:
                        System.out.println("\n\n you can't do that.");
                }
            }
        } 
        
        //--------------------------GAME OVER--------------------------
        if (player.getCurrentRoom().getName() != "room")
            revealText("wow.", "you won.", "did you cheat?", 
                    "this game was supposed to be unbeatable", "what a hack");
        else if (!player.isAlive())
            revealText("you died in a small, concrete-reinforced room.",
                    "game over");        
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
            if (i == availableNames.length) 
            {
                for (int h=0; h<availableNames.length; h++) //look in each
                {
                    if (availableNames[h] != null)
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
            randomIndex = random.nextInt(availableNames.length);
            
        } while(availableNames[randomIndex] == null); //find a name that hasn't been used
                
        String output = new String(availableNames[randomIndex]); //deep copy
        availableNames[randomIndex] = null;
        
        return output;
    }
    
    /**
     * Prints each string entered successively, with 6 lines blank lines in 
     * between slowly scrolling at 1 second per line.
     * 
     * @param text the text to be revealed, element by element
     */
    public static void revealText(String... text)
    {
        for (int i=0; i<text.length; i++)
        {
            System.out.print(" " + text[i]);
            for (int h=0; h<6; h++)
            {
                System.out.println();
                Futility.wait(WAITTIME);
            }
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
        return text[random.nextInt(text.length)];
    }
    
    /**
     * @param command the command as given in the main loop
     * @return true if the command is the kind of command that needs more info
     */
    private static boolean requiresSecondCommand(String command)
    {
        String[] blacklist = {"about", "help", "inventory", "look", "scope", 
            "exit", "quit", "end", "jump", "die"};
        
        for (int i=0; i<blacklist.length; i++) //look in each blacklist
            if (blacklist[i].equals(command)) //if it's the same as the parameter
                return false;
        
        //else, it requires a second command
        return true;
    }
    
    /**
     * @param command the command as given in the main loop
     * @return true if the command is a valid command
     */
    private static boolean isValidCommand(String command)
    {
        String[] whitelist = {"about", "help", "inventory", "look", "scope", 
            "exit", "quit", "end", "jump", "die", "run"};
        
        for (int i=0; i<whitelist.length; i++) //look in each blacklist
            if (whitelist[i].equals(command)) //if it's the same as the parameter
                return true;
        
        //else
        return false;
    }
}

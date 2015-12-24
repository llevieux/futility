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
     * Count of games played in this session
     */
    private int gameCount = 0;
    
    /**
     * The player.
     */
    public Player player;
    
    /**
     * An array containing all the Rooms in the game.
     */
    private Room[] rooms;
    
    private static Command[] commands = {
        new Command("about", "help", "info"),
        new Command("inventory", "inv"),
        new Command("look", "scope"),
        new Command("exit", "quit", "end"),
        new Command("jump", "hop"),
        new Command("die"),
        
        new Command("switch", true, "switch <lever or button>", "push", "pull" ,"on", "off", "activate"),
        new Command("go", true, "go <room>", "run", "move"),
        new Command("examine", true, "examine <item>", "ex", "watch"),
        new Command("eat", true, "eat <item>"),
        new Command("open", true, "open <matchbox>"),
        new Command("get", true, "get <item>", "pickup", "add", "take"),
        new Command("light", true, "light <match>", "strike", "ignite"),
        new Command("extinguish", true, "extinguish <match>", "putout", "ext"),
        new Command("drop", true, "drop <item>", "put", "remove", "leave")
    };
    
    /**
     * A random generator.  nextInt(int max) is very useful.
     */
    public static final Random random = new Random();
    
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
     * Runs the game.
     * 
     * Allows specification for debug mode, which, as of right now, takes the
     * waiting out of reveal.
     * 
     * @param gameCount Count of games played in this session
     * @param debugMode whether the game is in debug mode/
     */
    public Game (int gameCount, boolean debugMode)
    {
        if (debugMode)
            Output.debugMode = true;
        
        this.gameCount = gameCount;
        main();
    }
    
    /**
     * Runs a game, not in debug mode.
     * 
     * @param gameCount Count of games played in this session
     */
    public Game (int gameCount)
    {
        this(gameCount, false);
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
        //AntHill antHill = new AntHill("anthill");
        //Box box = new Box("box");
        //Box anotherBox = new Box("anouther box");
        
        UselessSwitch[] levers = new UselessSwitch[random.nextInt(4) + 1];
            //random number between 1 and 5
        for (int i=0; i<levers.length; i++)
            levers[i] = new UselessSwitch("lever " + (i+1));
        
        UselessSwitch[] buttons = new UselessSwitch[random.nextInt(3) + 1];
            //random number between 1 and 4
        for (int i=0; i<buttons.length; i++)
            buttons[i] = new UselessSwitch("button " + (i+1));
        
        Item monopolyMoney = new Item("monopoly money");
        monopolyMoney.setDescription("the troubles of money are so foolish "
                + "relative to the current situation");
        UselessSwitch pressureCooker = new UselessSwitch("pressure cooker");
        pressureCooker.setDescription("the cruel irony of having no food, and a "
                + "device to cook it with");
        Item box = new Item("box");
        box.setDescription("you take in all of the deep symbolism of the box, "
                + "like the box you are in, they are both worthless");
        //Chalk chalk = new Chalk("chalk");
        
        Item[] theRoomItems = {matchbox, flare, hammer, refrigerator, lawnMower,
            monopolyMoney, pressureCooker, box
            //chalk, antHill, box, anotherBox
        };
        
        //ROOMS
        Room theRoom = new Room("room", 
                null, //no rooms accessible from theRoom
                "you are in a small, concrete-reinforced room.\n\n\n"
                + " there are no doors and no windows",
                theRoomItems, levers, buttons //three lists of items to be added
        );
        
        Room billiardsRoom = new Room("billiards room", null,
                new Item[]{new Item("cue stick"), new Item("chair")});
        
        Room billiardsAirlock = new Room("billiards airlock",
                new Room[]{billiardsRoom});
        
        Room hallway = new Room("hallway", new Room[]{billiardsRoom});
        
        Room hanger = new Room("hanger", 
                new Room[]{hallway},
                new Item[]{new Item("lucas the airplane"), 
                new Item("kane the airplane")});
        
        hallway.addAccessibleRoom(hanger);
        
        Room satan = new Room("satan.", null,
                new Item[]{new Item("fire")});
        
        hanger.addAccessibleRoom(satan);
        
        Room oilRefinery = new Room("oil refinery", 
                new Room[]{satan}, 
                new Item[]{new Item("oil"), new Item("machines")});
        
        //UNCONNECTED ROOMS
        
        Room untitled = new Room("untitled", new Room[]{satan});
        
        Room dragons1 = new Room("dragons (probably)");
        if (random.nextInt(4) < 3) //75%
            for (int i=0; i<random.nextInt(4); i++)
                dragons1.addItem(new Item("dragon"));
        
        Room dragons2 = new Room("dragons (unlikey)");
        if (random.nextInt(4) == 1) //25%
            for (int i=0; i<random.nextInt(4); i++)
                dragons1.addItem(new Item("dragon"));
        
        Room closet = new Room("closet");
        
        Room sadness = new Room("sadness room");
        
        billiardsRoom.addAccessibleRooms(new Room[]{billiardsAirlock, hallway});
        
        Room[] rooms = {theRoom, billiardsRoom, billiardsAirlock, hallway,
            untitled, satan, oilRefinery, untitled, dragons1, dragons2, closet,
            sadness};
        
        
        //PLAYERS
        player = new Player(theRoom);
        
        
        //--------------------------MAIN GAME--------------------------
        Output.revealText("you are in a small, concrete-reinforced room",
                "your only wish in your humble life is to get out", 
                "there are no doors and no windows");
        
        
        while (player.isAlive() && player.getCurrentRoom() == theRoom)
        {            
            System.out.print(" available commands: ");
            
            for (int i=0; i<commands.length; i++)
            {
                if (commands[i].requiresVerb())
                    System.out.print(commands[i].getDescription());
                else
                    System.out.print(commands[i].getName());
                
                if (i < (commands.length-1))
                    System.out.print(", "); //every time but the last time.
                else
                    System.out.println();
            }
            
            System.out.println(" inventory: " + player.getInventory());
            System.out.println(" accessible rooms: " + player.getCurrentRoom().getStringOfAccessibleRooms());
            System.out.println(" items in room: " + player.getCurrentRoom().getStringOfItemsInRoom());
            System.out.println();
            
            String input = "", command1 = "", command2 = "";
            do
            {
                System.out.print(" what do you do? ");
                input = keyboard.nextLine().toLowerCase().trim();
                
                String[] inputArray = input.split(" ", 2);
                if (inputArray.length > 0)
                    command1 = inputArray[0];
                if (inputArray.length > 1)
                    command2 = inputArray[1];
                
                if (!isValidCommand(command1))
                    System.out.println(Output.randomText("\n nope, that's not something you can do.\n",
                            "\n you can't do that.\n",
                            "\n no.\n",
                            "\n you tried to " + command1 + ", but nothing happened.\n"));
            } while (!isValidCommand(command1));
            
            Command command1Object = getCommandObject (command1);
            
            Output.clearScreen();

            if (command1Object.isNameOrAlias("about"))
                Futility.about();
            else if (command1Object.isNameOrAlias("inventory"))
                Output.revealText(player.getInventory());
            else if (command1Object.isNameOrAlias("look"))
                player.getCurrentRoom().look();
            else if (command1Object.isNameOrAlias("jump"))
                Output.revealText("you jump in the air", "\"whee, you shout\"");
            else if (command1Object.isNameOrAlias("die"))
            {
                System.out.println(" how morbid of you.");
                Output.wait(3.0);
                Output.clearScreen();
                player.die();
            }
            else if (command1Object.isNameOrAlias("exit"))
            {
                Output.revealText("you are still in a small, concrete-reinforced room", 
                            "you can't \"" + command1 + "\".");
                Output.clearScreen();
            }
            else if (command1Object.isNameOrAlias("get"))
                player.get(getItemObject(command2));
            else if (command1Object.isNameOrAlias("drop"))
                player.drop(getItemObject(command2));
            else if (command1Object.isNameOrAlias("go"))
                System.out.println("\n you're stuck inside a small, concrete-"
                + "reinforced room.  you can't just leave.\n");
            else if (command1Object.isNameOrAlias("open"))
            {
                player.getCurrentRoom().addItems(matches);
                System.out.println("\n you've opened the matchbox, now there are "
                        + "matches all over the floor.");
            }
            else if (command1Object.isNameOrAlias("light"))
            {
                if (getItemObject(command2) != null)
                    getItemObject(command2).light();
            }
            else if (command1Object.isNameOrAlias("extinguish"))
            {
                if (getItemObject(command2) != null)
                    getItemObject(command2).extinguish();
            }
            else if (command1Object.isNameOrAlias("examine"))
            {
                if (getItemObject(command2) != null)
                    getItemObject(command2).examine();
            }
            else if (command1Object.isNameOrAlias("eat"))
            {
                if (getItemObject(command2) != null)
                {
                    getItemObject(command2).eat();
                    player.die();
                }
            }
            else if (command1Object.isNameOrAlias("switch"))
            {
                if (getItemObject(command2) != null)
                    getItemObject(command2).Switch();
            }
            else 
                System.out.println(" internal error #1 - sorry bout that");
            
            System.out.println("\n");
        } 
        
        //--------------------------GAME OVER--------------------------
        if (player.getCurrentRoom().getName() != "room")
            Output.revealText("wow.", "you won.", "did you cheat?", 
                    "this game was supposed to be unbeatable", "what a hack");
        else if (!player.isAlive())
            if (gameCount > 4)
                Output.revealText("you died in a small, concrete-reinforced room.",
                        "for the " + Game.ordinal(gameCount) + " time",
                        "game over");
            else
                Output.revealText("you died in a small, concrete-reinforced room.",
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
     * @param command the command as given in the main loop
     * @return true if the command is a valid command
     */
    private boolean isValidCommand(String command)
    {        
        return getCommandObject(command) != null;
    }
    
    /**
     * @param command a string with the name or alias of a command
     * @return a Command object that matches the string
     */
    private Command getCommandObject(String command)
    {
        for (int i=0; i<commands.length; i++) 
            if (commands[i].isNameOrAlias(command))
                return commands[i];
        
        //else
        return null;
    }
    
    /**
     * Finds an item inside of the current room.
     * 
     * @param itemName the name of the item to find
     * @return the first object in the current room whose name matches itemName.
     * null if no such item is found.
     */
    private Item getItemObject (String itemName)
    {
        Item[] itemsInRoom = player.getCurrentRoom().getItems();
        
        for (int i=0; i<itemsInRoom.length; i++)
            if (itemsInRoom[i] != null && itemsInRoom[i].getName().equals(itemName)) //lazy evalutation saves a null pointer exception
                return itemsInRoom[i];
        
        Item[] itemsInInventory = player.getInventoryArray();
        
        for (int i=0; i<itemsInInventory.length; i++)
            if (itemsInInventory[i] != null && itemsInInventory[i].getName().equals(itemName)) //lazy evalutation saves a null pointer exception
                return itemsInInventory[i];
        
        //else
        Output.clearScreen();
        System.out.println(" " + itemName + " is not an object in this room.\n\n");
        return null;
    }
    
    /**
     * Converts an input into an ordinal string.  
     * 
     * eg. 1 => 1st, 2 => 2nd, 3 => 3rd...
     * 
     * @param i a positive integer
     * @return an ordinal representation of the integer
     */
    public static String ordinal(int i) 
    {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) 
        {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];
        }
    }
}

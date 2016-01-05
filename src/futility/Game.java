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
        new Command("light", true, "light <match>", "ignite"),
        new Command("extinguish", true, "extinguish <match>", "putout", "ext"),
        new Command("drop", true, "drop <item>", "put", "remove", "leave"),
            
        new Command("strike", "with", true, "strike <object> with <hammer>", "hit")
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
            "doodle", "diddle", "jax", "harry", "cassidy"
        };
    
    private static boolean skipIntro = false;
    
    
    /**
     * Runs the game.
     * 
     * Allows specification for debug mode, which, as of right now, takes the
     * waiting out of reveal.
     * 
     * @param gameCount Count of games played in this session
     * @param args optional command-line arguments.  see Futility.java for a list.
     */
    public Game (int gameCount, String[] args)
    {
        if (args.length > 0)
        {
            for (String arg:args)
            {
                if (arg.equals("-d") || arg.equals("debug") || arg.equals("--debug"))
                    Output.debugMode = true;
                if (args[0].equals("-s") || args[0].equals("skipintro") || args[0].equals("--skipintro"))
                    skipIntro = true;
            }
        }
        
        
        this.gameCount = gameCount;
        main();
    }
    
    /**
     * Runs a game, with gameCount set at 0.
     */
    public Game()
    {
        this(0, new String[]{}); //runs Game(int) constructor for 0
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
        if (!skipIntro)
        {
            Output.revealByLine("you are in a small, concrete-reinforced room",
                    "your only wish in your humble life is to get out", 
                    "there are no doors",
                    "and no windows");

            Output.wait(3.0);
        }
        
        while (player.isAlive() && player.getCurrentRoom() == theRoom)
        {            
            String availableCommands = "available commands: ";
            
            for (int i=0; i<commands.length; i++)
            {
                if (commands[i].requiresItem())
                    availableCommands += commands[i].getDescription();
                else
                    availableCommands += commands[i].getName();
                
                if (i < (commands.length-1))
                    availableCommands += ", "; //every time but the last time.
            }
            Output.horizontalLine();
            System.out.println();
            Output.revealByLetterln(availableCommands, .001);
            Output.revealByLetterln("inventory: " + player.getInventory(), .001);
            Output.revealByLetterln("accessible rooms: " + player.getCurrentRoom().getStringOfAccessibleRooms(), 
                    .001);
            Output.revealByLetterln("items in room: " + player.getCurrentRoom().getStringOfItemsInRoom(),
                    .001);
            System.out.println();
            
            /**
             * The literal input that is returned by the user, after being 
             * converted to lowercase and trimmed of leading and trailing 
             * whitespace.
             */
            String input = "";
            
            /**
             * The array that holds all the different parts of the input as a
             * array of strings.  
             * 
             * [0] -> the main command.  if it's valid, the corresponding
             *          Command object is copied into mainCommand.  One word long.
             * [1] -> the &lt;object&gt; following the command.  can be as many 
             *          words as are in the &lt;object&gt;.  if it's valid, the 
             *          corresponding Item object is copied into mainItem.
             * [2] -> secondary command, for example "with".  not necessary, is 
             *          usually null.
             * [3] -> if [2] is not null, this will be the object of the 
             *          secondary command.
             * 
             * Example:
             * 
             *      for the input = "strike box with rufus the hammer"
             * 
             * inputArray[0] = "strike"
             * inputArray[1] = "box"
             * inputArray[2] = "with"
             * inputArray[3] = "rufus the hammer"
             */
            String[] inputArray = new String[4];
            
            do //prompt untill valid
            {
                System.out.print(" what do you do? ");
                input = keyboard.nextLine().toLowerCase().trim();
                
                String[] inputWords = input.split(" ");
                int whilePlace = -1;
                
                if (inputWords.length > 0)
                    inputArray[0] = inputWords[0];
                if (inputWords.length > 1)
                {
                    for (int i=1; i<inputWords.length; i++)
                    {
                        if ("with".equals(inputWords[i]))
                        {
                            inputArray[2] = inputWords[i];
                            inputArray[3] = "";
                            for (i++; i<inputWords.length; i++)
                            {
                                if (i<inputWords.length-1) //all except last
                                    inputArray[3] += inputWords[i] + " ";
                                else 
                                    inputArray[3] += inputWords[i];
                            }
                        }
                        else 
                        {
                            if (i==1)
                                inputArray[1] = inputWords[i];
                            else
                                inputArray[1] += " " + inputWords[i];
                        }
                    }
                }
                
                if ("".equals(input))
                    Output.revealByLetterln(Output.randomText("\n hey! you didn't"
                            + " enter anything\n\n",
                            "\n you were supposed to type something there\n\n",
                            "\n oh, come on.\n\n"
                            ));
                else if (getCommandObject(inputArray[0]) == null)
                    Output.revealByLetterln(Output.randomText("\n nope, that's "
                            + "not something you can do.\n\n",
                            "\n you can't do that.\n\n",
                            "\n no.\n\n",
                            "\n oh, come on.\n\n",
                            "\n you tried to " + inputArray[0] + ", but nothing happened.\n\n"
                            ));
                else if (getCommandObject(inputArray[0]).requiresItem()
                        && inputArray[1] == null)
                    Output.revealByLetterln("\n you'll need to enter something "
                            + "to " + inputWords[0] + ".\n\n");
                else if (getCommandObject(inputArray[0]).requiresItem()
                        && getItemObject(inputArray[1]) == null)
                    Output.revealByLetterln("\n " + inputWords[1] + " is not an object "
                            + "in this room.\n\n");
                
            } while (!isValidCommand(inputArray));
            
            Command mainCommand = getCommandObject(inputArray[0]);
            Item mainItem = getItemObject(inputArray[1]); //might be null
            
            Output.clearScreen();

            if (mainCommand.isNameOrAlias("about"))
	    {
                Futility.about();
                continue; //skip the wait, about() already asks for confirmation
	    }
            else if (mainCommand.isNameOrAlias("inventory"))
            {
                if (!"(none)".equals(player.getInventory()))
                    Output.revealByLine(player.getInventory());
                else 
                    Output.revealByLine(
                        Output.randomText("you literally have nothing in your inventory",
                        "inventory: nothing",
                        "you're not carrying anything. pick something up, loser."));
            }
            else if (mainCommand.isNameOrAlias("look"))
                player.getCurrentRoom().look();
            else if (mainCommand.isNameOrAlias("jump"))
                Output.revealByLine("you jump in the air", 
                    Output.randomText("\"whee\", you shout",
                            "well, that was fun",
                            "nothing else happened",
                            "you are still in a small, concrete-reinforced room"));
            else if (mainCommand.isNameOrAlias("die"))
            {
                Output.revealByLine("how morbid of you.");
                player.die();
            }
            else if (mainCommand.isNameOrAlias("exit"))
                Output.revealByLine("you are still in a small, concrete-reinforced room", 
                            "you can't \"" + mainCommand.getName() + "\".");
            else if (mainCommand.isNameOrAlias("get"))
                player.get(mainItem);
            else if (mainCommand.isNameOrAlias("drop"))
                player.drop(mainItem);
            else if (mainCommand.isNameOrAlias("go"))
                Output.revealByLine("you're stuck inside a small, concrete-"
                + "reinforced room.", "you can't just leave.\n");
            else if (mainCommand.isNameOrAlias("open"))
            {
                if (mainItem instanceof Matchbox)
                    player.getCurrentRoom().addItems(matches);
                mainItem.open();
            }
            else if (mainCommand.isNameOrAlias("light"))
                mainItem.light();
            else if (mainCommand.isNameOrAlias("extinguish"))
                mainItem.extinguish();
            else if (mainCommand.isNameOrAlias("examine"))
                mainItem.examine();
            else if (mainCommand.isNameOrAlias("eat"))
            {
                mainItem.eat();
                player.getCurrentRoom().removeItem(mainItem);
                player.die();
            }
            else if (mainCommand.isNameOrAlias("switch"))
                mainItem.toggle();
            else if (mainCommand.isNameOrAlias("strike"))
                getItemObject(inputArray[3]).Strike(mainItem);
            else 
                Output.revealByLetterln("internal error #1 - sorry bout that");
            
            System.out.println("\n");
            Output.wait(3.0);
        } 
        
        //--------------------------GAME OVER--------------------------
        if (!"room".equals(player.getCurrentRoom().getName()))
            Output.revealByLine("wow.", "you won.", "did you cheat?", 
                    "this game was supposed to be unbeatable", "what a hack");
        else if (!player.isAlive())
            if (gameCount > 4)
                Output.revealByLine("you died in a small, concrete-reinforced room.",
                        "for the " + Game.ordinal(gameCount) + " time",
                        "game over.");
            else
                Output.revealByLine("you died in a small, concrete-reinforced room.",
                        "game over.");        
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
     * @param command the parsed input as given in the input loop as inputArray
     * @return true if the command is a valid command:
     *      the main command is valid
     *      the main item is valid (exists in room), if required according to the 
     *              main command
     *      the secondary command is valid, if required according to the 
     *              main command
     *      the secondary item is valid (exists in room), if secondary command 
     *              is required
     */
    private boolean isValidCommand(String[] inputArray)
    {        
        Command mainCommand = getCommandObject(inputArray[0]);
        Item mainItem = getItemObject(inputArray[1]);
        
        if (
                mainCommand == null ||
                mainCommand.requiresItem() && mainItem == null ||
                mainCommand.requiresSecondCommand() && 
                    (inputArray[2] == null || inputArray[3] == null)
                )
            return false;
        else
            return true;
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

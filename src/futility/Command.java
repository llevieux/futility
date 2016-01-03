/*
 * Copyright (C) 2015 Lucas LeVieux & Kane McGrath
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
 * A single command that can be entered into Game.  Collected under commands[]
 * in Game.java.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Command 
{
    /**
     * The name of the command. eg "go" or "run"
     */
    private String name;
    
    /**
     * True if the command requires an item to perform the action to.
     */
    private boolean requiresItem = false;
    
    /**
     * If a second command is required, it will be here.  For example:
     * 
     *       for the input = "strike box with rufus the hammer"
     * 
     * "with" is the required second command.
     */
    private String secondCommand = null;
    
    /**
     * A short description of how the command should be used. 
     * 
     * eg. "go <room>"
     */
    private String description = "";
    
    /**
     * Other command names that do the exact same thing
     */
    private String[] alias;
    
    /**
     * Constructor that assumes the command needs no Item.
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param alias other command names that do the exact same thing
     */
    public Command(String name, String... alias)
    {
        this.name = name;
        this.alias = alias;
    }
    
    /**
     * Constructor that assumes no secondary command necessary
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param requiresItem If the command requires an Item (in 
     * game.java's main loop).
     * @param description A short description of how the command should be used. 
     * eg. "go <room>"
     * @param alias other command names that do the exact same thing
     */
    public Command(String name, boolean requiresItem, String description,
            String... alias)
    {
        this(name,  alias);
        this.description = description;
        this.requiresItem = requiresItem;
    }
    
    /**
     * Constructor for making a second command required.
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param secondCommand The name of the second command. eg "with"
     * @param requiresItem If the command requires an Item (in 
     * game.java's main loop).
     * @param description A short description of how the command should be used. 
     * eg. "go <room>"
     * @param alias other command names that do the exact same thing
     */
    public Command(String name, String secondCommand, boolean requiresItem, 
            String description, String... alias)
    {
        this(name,  alias);
        this.description = description;
        this.requiresItem = requiresItem;
    }
    
    /**
     * @return The name of the command. eg "go" or "run"
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return True if the command requires an item to perform the action to.
     */
    public boolean requiresItem()
    {
        return requiresItem;
    }
    
    /**
     * @return true if a second command is needed beyond the main command.  for
     * example "with".
     */
    public boolean requiresSecondCommand()
    {
        return secondCommand != null;
    }

    /**
     * @return If a second command is required, this will return it.  Otherwise
     * it will return null.
     */
    public String getSecondCommand() {
        return secondCommand;
    }
    
    /**
     * @return A short description of how the command should be used. 
     * eg. "go &lt;room&gt;"
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return true if the input is equal to either the name or the alias of
     * this command.
     */
    public boolean isNameOrAlias(String input)
    {
        if (input.equals(name) || isAlias(input))
            return true;
        else 
            return false;
    }
    
    /**
     * @return true if the input is equal to any of the alias' of this command.
     */
    public boolean isAlias (String input)
    {
        for (int i=0; i<alias.length; i++) //look through the alias for input
            if (input.equals(alias[i]))
                return true;
        //else
        return false;
    }
    
    @Override
    public String toString()
    {
        String output = name + ". aliases: ";
        for (int i=0; i<alias.length; i++) //look through the alias for input
            output += alias[i] + ", ";
        if (requiresItem)
            output += "requires verb.  description: " + description;
        
        return output;
    }
}

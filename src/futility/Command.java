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
     * If the command requires a second command (in game.java's main loop).
     */
    private boolean requiresVerb = false;
    
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
     * Constructor that assumes the command needs no second command.
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param description A short description of how the command should be used. 
     * eg. "go <room>"
     * @param alias other command names that do the exact same thing
     */
    public Command(String name, String description, String... alias)
    {
        this.name = name;
        this.description = description;
        this.alias = alias;
    }
    
    /**
     * Constructor that assumes the command needs no second command.
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param requiresVerb If the command requires a second command (in 
     * game.java's main loop).
     * @param description A short description of how the command should be used. 
     * eg. "go <room>"
     * @param alias other command names that do the exact same thing
     */
    public Command(String name, boolean requiresVerb, String description,
            String... alias)
    {
        this(name, description, alias);
        this.requiresVerb = requiresVerb;
    }
    
    /**
     * @return The name of the command. eg "go" or "run"
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return If the command requires a second command (in 
     * game.java's main loop).
     */
    public boolean requiresVerb()
    {
        return requiresVerb;
    }
    
    /**
     * @return A short description of how the command should be used. 
     * eg. "go <room>"
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
        if (input == name)
            return true;
        else 
        {
            for (int i=0; i<alias.length; i++) //look through the alias for input
                if (input == alias[i])
                    return true;
            
            //else
            return false;
        }
    }
}

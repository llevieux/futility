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
     * Constructor that assumes the command needs no second command.
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param description A short description of how the command should be used. 
     * eg. "go <room>"
     */
    public Command(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    /**
     * Constructor that assumes the command needs no second command.
     * 
     * @param name The name of the command. eg "go" or "run"
     * @param requiresVerb If the command requires a second command (in 
     * game.java's main loop).
     * @param description A short description of how the command should be used. 
     * eg. "go <room>"
     */
    public Command(String name, boolean requiresVerb, String description)
    {
        this(name, description);
        this.requiresVerb = requiresVerb;
    }
    
    
}

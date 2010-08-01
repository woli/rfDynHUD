/**
 * Copyright (C) 2009-2010 Cars and Tracks Development Project (CTDP).
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
package net.ctdp.rfdynhud.editor.properties;

import java.io.PrintStream;
import java.util.Stack;

import net.ctdp.rfdynhud.editor.hiergrid.FlaggedList;
import net.ctdp.rfdynhud.properties.Property;
import net.ctdp.rfdynhud.properties.WidgetPropertiesContainer;

public class DefaultWidgetPropertiesContainer extends WidgetPropertiesContainer
{
    private final Stack<FlaggedList> groupStack = new Stack<FlaggedList>();
    private FlaggedList currList = null;
    
    @Override
    protected void clearImpl()
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void addGroupImpl( String groupName, boolean initiallyExpanded, boolean pushed )
    {
        if ( !pushed && ( groupStack.size() > 1 ) )
        {
            groupStack.pop();
        }
        
        FlaggedList group = new FlaggedList( groupName, initiallyExpanded );
        
        FlaggedList parentGroup = groupStack.peek();
        parentGroup.add( group );
        groupStack.push( group );
        currList = group;
    }
    
    @Override
    protected void popGroupImpl()
    {
        groupStack.pop();
        currList = groupStack.peek();
    }
    
    @Override
    protected void addPropertyImpl( Property property )
    {
        currList.add( property );
    }
    
    private void dump( FlaggedList group, PrintStream ps, int level )
    {
        for ( int i = 0; i < group.size(); i++ )
        {
            for ( int j = 0; j < level; j++ )
                ps.print( "  " );
            
            Object o = group.get( i );
            
            if ( o instanceof FlaggedList )
            {
                ps.println( ( (FlaggedList)o ).getName() );
                
                dump( (FlaggedList)o, ps, level + 1 );
            }
            else
            {
                ps.println( (Property)o );
            }
        }
    }
    
    @Override
    public void dump( PrintStream ps )
    {
        dump( groupStack.get( 0 ), ps, 0 );
    }
    
    public DefaultWidgetPropertiesContainer( FlaggedList root )
    {
        groupStack.push( root );
        
        this.currList = root;
    }
}

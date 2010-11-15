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
package net.ctdp.rfdynhud.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import net.ctdp.rfdynhud.gamedata.GameFileSystem;
import net.ctdp.rfdynhud.gamedata.SessionType;

public class ConfigurationCandidatesIterator implements Iterator<File>
{
    private final ArrayList<File> candidates = new ArrayList<File>();
    private int pos = 0;
    
    public void reset()
    {
        candidates.clear();
        pos = 0;
    }
    
    @Override
    public boolean hasNext()
    {
        return ( pos < candidates.size() );
    }
    
    @Override
    public File next()
    {
        if ( !hasNext() )
            return ( null );
        
        return ( candidates.get( pos++ ) );
    }
    
    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
    
    protected void addCandidate( File configFolder, String modName, String filename )
    {
        candidates.add( new File( new File( configFolder, modName ), filename ) );
    }
    
    protected void addCandidate( File configFolder, String filename )
    {
        candidates.add( new File( configFolder, filename ) );
    }
    
    protected void addSmallMonitorCandidates( File configFolder, String modName, String vehicleName, SessionType sessionType )
    {
        boolean isPractice = sessionType.isPractice();
        
        addCandidate( configFolder, modName, "overlay_monitor_small_" + vehicleName + "_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_monitor_small_" + vehicleName + "_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_small_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_monitor_small_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, "overlay_monitor_small_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, "overlay_monitor_small_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_small_" + vehicleName + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_small.ini" );
        addCandidate( configFolder, "overlay_monitor_small.ini" );
    }
    
    protected void addBigMonitorCandidates( File configFolder, String modName, String vehicleName, SessionType sessionType )
    {
        boolean isPractice = sessionType.isPractice();
        
        addCandidate( configFolder, modName, "overlay_monitor_big_" + vehicleName + "_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_monitor_big_" + vehicleName + "_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_big_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_monitor_big_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, "overlay_monitor_big_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, "overlay_monitor_big_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_big_" + vehicleName + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_big.ini" );
        addCandidate( configFolder, "overlay_monitor_big.ini" );
    }
    
    protected void addMonitorCandidates( File configFolder, String modName, String vehicleName, SessionType sessionType )
    {
        boolean isPractice = sessionType.isPractice();
        
        addCandidate( configFolder, modName, "overlay_monitor_" + vehicleName + "_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_monitor_" + vehicleName + "_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_monitor_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, "overlay_monitor_big_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, "overlay_monitor_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor_" + vehicleName + ".ini" );
        addCandidate( configFolder, modName, "overlay_monitor.ini" );
        addCandidate( configFolder, "overlay_monitor.ini" );
    }
    
    protected void addGarageCandidates( File configFolder, String modName, String vehicleName, SessionType sessionType )
    {
        boolean isPractice = sessionType.isPractice();
        
        addCandidate( configFolder, modName, "overlay_garage_" + vehicleName + "_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_garage_" + vehicleName + "_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_garage_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_garage_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, "overlay_garage_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, "overlay_garage_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_garage_" + vehicleName + ".ini" );
        addCandidate( configFolder, modName, "overlay_garage.ini" );
        addCandidate( configFolder, "overlay_garage.ini" );
    }
    
    protected void addRegularCandidates( File configFolder, String modName, String vehicleName, SessionType sessionType )
    {
        boolean isPractice = sessionType.isPractice();
        
        addCandidate( configFolder, modName, "overlay_" + vehicleName + "_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_" + vehicleName + "_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, modName, "overlay_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, "overlay_" + sessionType.name() + ".ini" );
        if ( isPractice )
            addCandidate( configFolder, "overlay_" + SessionType.PRACTICE_WILDCARD + ".ini" );
        addCandidate( configFolder, modName, "overlay_" + vehicleName + ".ini" );
        addCandidate( configFolder, modName, "overlay.ini" );
        addCandidate( configFolder, "overlay.ini" );
    }
    
    public void collectCandidates( boolean smallMonitor, boolean bigMonitor, boolean isInGarage, String modName, String vehicleName, SessionType sessionType )
    {
        final File configFolder = GameFileSystem.INSTANCE.getConfigFolder();
        
        if ( smallMonitor )
        {
            addSmallMonitorCandidates( configFolder, modName, vehicleName, sessionType );
            addMonitorCandidates( configFolder, modName, vehicleName, sessionType );
        }
        else if ( bigMonitor )
        {
            addBigMonitorCandidates( configFolder, modName, vehicleName, sessionType );
            addMonitorCandidates( configFolder, modName, vehicleName, sessionType );
        }
        else
        {
            if ( isInGarage )
            {
                addGarageCandidates( configFolder, modName, vehicleName, sessionType );
            }
            
            addRegularCandidates( configFolder, modName, vehicleName, sessionType );
        }
    }
    
    public ConfigurationCandidatesIterator()
    {
    }
}

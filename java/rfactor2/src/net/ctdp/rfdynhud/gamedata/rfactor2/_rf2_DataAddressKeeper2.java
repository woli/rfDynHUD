/**
 * Copyright (C) 2009-2014 Cars and Tracks Development Project (CTDP).
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
package net.ctdp.rfdynhud.gamedata.rfactor2;

/**
 * Objects of this class keep information about a data buffer to be copied in a callback.
 * 
 * @author Marvin Froehlich (CTDP)
 */
public class _rf2_DataAddressKeeper2 extends _rf2_DataAddressKeeper
{
    private long bufferAddress2 = 0L;
    private int bufferSize2 = 0;
    
    public void setBufferInfo2( long address, int size )
    {
        this.bufferAddress2 = address;
        this.bufferSize2 = size;
    }
    
    public final long getBufferAddress2()
    {
        return ( bufferAddress2 );
    }
    
    public final int getBufferSize2()
    {
        return ( bufferSize2 );
    }
}

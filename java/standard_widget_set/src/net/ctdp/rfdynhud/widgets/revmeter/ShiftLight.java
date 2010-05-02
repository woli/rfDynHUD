package net.ctdp.rfdynhud.widgets.revmeter;

import java.io.IOException;

import net.ctdp.rfdynhud.gamedata.LiveGameData;
import net.ctdp.rfdynhud.properties.ImageProperty;
import net.ctdp.rfdynhud.properties.IntProperty;
import net.ctdp.rfdynhud.properties.WidgetPropertiesContainer;
import net.ctdp.rfdynhud.render.ImageTemplate;
import net.ctdp.rfdynhud.render.TransformableTexture;
import net.ctdp.rfdynhud.util.Logger;
import net.ctdp.rfdynhud.util.WidgetsConfigurationWriter;

public class ShiftLight
{
    private static final String[] default_shift_light_on_images =
    {
        "shiftlight_on_red.png",
        "shiftlight_on_orange.png",
        "shiftlight_on_yellow.png",
        "shiftlight_on_lightgreen.png",
        "shiftlight_on_green.png",
    };
    
    public static final ShiftLight DEFAULT_SHIFT_LIGHT1 = new ShiftLight( null, 1 );
    public static final ShiftLight DEFAULT_SHIFT_LIGHT2 = new ShiftLight( null, 2 );
    public static final ShiftLight DEFAULT_SHIFT_LIGHT3 = new ShiftLight( null, 3 );
    public static final ShiftLight DEFAULT_SHIFT_LIGHT4 = new ShiftLight( null, 4 );
    public static final ShiftLight DEFAULT_SHIFT_LIGHT5 = new ShiftLight( null, 5 );
    
    private final RevMeterWidget widget;
    private final int indexOneBased;
    
    private final ImageProperty imageNameOff;
    private final ImageProperty imageNameOn;
    private final IntProperty posX;
    private final IntProperty posY;
    final IntProperty activationRPM;
    
    private TransformableTexture textureOff = null;
    private TransformableTexture textureOn = null;
    
    public void resetTextures()
    {
        this.textureOff = null;
        this.textureOn = null;
    }
    
    private final boolean isOffStatePartOfBackground()
    {
        return ( imageNameOff.getValue().equals( "" ) );
    }
    
    public int loadTextures( boolean isEditorMode, ImageProperty backgroundImageName )
    {
        int n = 0;
        
        if ( !isOffStatePartOfBackground() && ( ( textureOff == null ) || isEditorMode ) )
        {
            try
            {
                ImageTemplate it0 = backgroundImageName.getImage();
                float scale = ( it0 == null ) ? 1.0f : widget.getSize().getEffectiveWidth() / (float)it0.getBaseWidth();
                
                ImageTemplate it = imageNameOff.getImage();
                
                if ( it == null )
                {
                    textureOff = null;
                    return ( n );
                }
                
                int w = Math.round( it.getBaseWidth() * scale );
                int h = Math.round( it.getBaseHeight() * scale );
                if ( ( textureOff == null ) || ( textureOff.getWidth() != w ) || ( textureOff.getHeight() != h ) )
                {
                    textureOff = new TransformableTexture( w, h, 0, 0, 0, 0, 0f, 1f, 1f );
                    it.drawScaled( 0, 0, w, h, textureOff.getTexture(), true );
                }
                
                n++;
            }
            catch ( Throwable t )
            {
                Logger.log( t );
                
                return ( n );
            }
        }
        else if ( isOffStatePartOfBackground() )
        {
            textureOff = null;
        }
        
        if ( ( textureOn == null ) || isEditorMode )
        {
            try
            {
                ImageTemplate it0 = backgroundImageName.getImage();
                float scale = ( it0 == null ) ? 1.0f : widget.getSize().getEffectiveWidth() / (float)it0.getBaseWidth();
                
                ImageTemplate it = imageNameOn.getImage();
                
                if ( it == null )
                {
                    textureOn = null;
                    return ( n );
                }
                
                int w = Math.round( it.getBaseWidth() * scale );
                int h = Math.round( it.getBaseHeight() * scale );
                if ( isOffStatePartOfBackground() )
                {
                    if ( ( textureOn == null ) || ( textureOn.getWidth() != w ) || ( textureOn.getHeight() != h * 2 ) )
                    {
                        textureOn = new TransformableTexture( w, h * 2, 0, 0, 0, 0, 0f, 1f, 1f );
                        textureOn.getTexture().clear( false, null );
                        it0.drawScaled( posX.getIntValue(), posY.getIntValue(), it.getBaseWidth(), it.getBaseHeight(), 0, 0, w, h, textureOn.getTexture(), false );
                        it0.drawScaled( posX.getIntValue(), posY.getIntValue(), it.getBaseWidth(), it.getBaseHeight(), 0, h, w, h, textureOn.getTexture(), false );
                        it.drawScaled( 0, 0, w, h, textureOn.getTexture(), false );
                    }
                }
                else
                {
                    if ( ( textureOn == null ) || ( textureOn.getWidth() != w ) || ( textureOn.getHeight() != h ) )
                    {
                        textureOn = new TransformableTexture( w, h, 0, 0, 0, 0, 0f, 1f, 1f );
                        it.drawScaled( 0, 0, w, h, textureOn.getTexture(), true );
                    }
                }
                
                n++;
            }
            catch ( Throwable t )
            {
                Logger.log( t );
                
                return ( n );
            }
        }
        
        return ( n );
    }
    
    public int writeTexturesToArray( TransformableTexture[] array, int offset )
    {
        if ( textureOff != null )
            array[offset++] = textureOff;
        
        if ( textureOn != null )
            array[offset++] = textureOn;
        
        return ( offset );
    }
    
    public void updateTextures( LiveGameData gameData, float rpm, float baseMaxRPM, int boost, float backgroundScaleX, float backgroundScaleY )
    {
        float maxRPM = gameData.getPhysics().getEngine().getMaxRPM( baseMaxRPM, boost );
        boolean isOn = ( rpm >= maxRPM + activationRPM.getIntValue() );
        
        if ( isOffStatePartOfBackground() )
        {
            if ( textureOn != null )
            {
                if ( isOn )
                {
                    textureOn.setClipRect( 0, 0, textureOn.getWidth(), textureOn.getHeight() / 2, true );
                    textureOn.setTranslation( Math.round( posX.getIntValue() * backgroundScaleX ), Math.round( posY.getIntValue() * backgroundScaleY ) );
                }
                else
                {
                    textureOn.setClipRect( 0, textureOn.getHeight() / 2, textureOn.getWidth(), textureOn.getHeight() / 2, true );
                    textureOn.setTranslation( Math.round( posX.getIntValue() * backgroundScaleX ), Math.round( posY.getIntValue() * backgroundScaleY ) - textureOn.getHeight() / 2 );
                }
            }
        }
        else
        {
            if ( isOn )
            {
                if ( textureOn != null )
                {
                    textureOn.setClipRect( 0, 0, textureOn.getWidth(), textureOn.getHeight(), true );
                    textureOn.setTranslation( Math.round( posX.getIntValue() * backgroundScaleX ), Math.round( posY.getIntValue() * backgroundScaleY ) );
                    textureOn.setVisible( true );
                }
                if ( textureOff != null )
                    textureOff.setVisible( false );
            }
            else
            {
                if ( textureOff != null )
                {
                    textureOff.setClipRect( 0, 0, textureOff.getWidth(), textureOff.getHeight(), true );
                    textureOff.setTranslation( Math.round( posX.getIntValue() * backgroundScaleX ), Math.round( posY.getIntValue() * backgroundScaleY ) );
                    textureOff.setVisible( true );
                }
                if ( textureOn != null )
                    textureOn.setVisible( false );
            }
        }
    }
    
    public void saveProperties( WidgetsConfigurationWriter writer ) throws IOException
    {
        writer.writeProperty( imageNameOff, "The name of the shift light image for \"off\" state." );
        writer.writeProperty( imageNameOn, "The name of the shift light image for \"on\" state." );
        writer.writeProperty( posX, "The x-offset in pixels to the gear label." );
        writer.writeProperty( posY, "The y-offset in pixels to the gear label." );
        writer.writeProperty( activationRPM, "The RPM (rounds per minute) to subtract from the maximum for the level to display shoft light on" );
    }
    
    public boolean loadProperty( String key, String value )
    {
        if ( imageNameOff.loadProperty( key, value ) )
            return ( true );
        if ( imageNameOn.loadProperty( key, value ) )
            return ( true );
        if ( posX.loadProperty( key, value ) )
            return ( true );
        if ( posY.loadProperty( key, value ) )
            return ( true );
        if ( activationRPM.loadProperty( key, value ) )
            return ( true );
        
        return ( false );
    }
    
    public void getProperties( WidgetPropertiesContainer propsCont, boolean forceAll )
    {
        propsCont.addGroup( "Shift Light " + indexOneBased );
        
        propsCont.addProperty( imageNameOff );
        propsCont.addProperty( imageNameOn );
        propsCont.addProperty( posX );
        propsCont.addProperty( posY );
        propsCont.addProperty( activationRPM );
    }
    
    public ShiftLight( RevMeterWidget widget, int indexOneBased )
    {
        this.widget = widget;
        this.indexOneBased = indexOneBased;
        
        this.imageNameOff = new ImageProperty( widget, "shiftLightImageNameOff" + indexOneBased, "imageNameOff", "shiftlight_off.png", false, true )
        {
            @Override
            protected void onValueChanged( String oldValue, String newValue )
            {
                textureOff = null;
            }
        };
        this.imageNameOn = new ImageProperty( widget, "shiftLightImageNameOn" + indexOneBased, "imageNameOn", default_shift_light_on_images[indexOneBased - 1] )
        {
            @Override
            protected void onValueChanged( String oldValue, String newValue )
            {
                textureOn = null;
            }
        };
        this.posX = new IntProperty( widget, "shiftLightPosX" + indexOneBased, "posX", 625 - 32 * ( indexOneBased - 1 ) )
        {
            @Override
            protected void onValueChanged( int oldValue, int newValue )
            {
                resetTextures();
            }
        };
        this.posY = new IntProperty( widget, "shiftLightPosY" + indexOneBased, "posY", 42 )
        {
            @Override
            protected void onValueChanged( int oldValue, int newValue )
            {
                resetTextures();
            }
        };
        this.activationRPM = new IntProperty( widget, "shiftLightRPM" + indexOneBased, "activationRPM", 100 - 250 * indexOneBased, -5000, 0, false );
    }
}

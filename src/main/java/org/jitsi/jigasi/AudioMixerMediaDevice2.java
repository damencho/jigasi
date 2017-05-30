package org.jitsi.jigasi;

import org.jitsi.impl.neomedia.conference.*;
import org.jitsi.impl.neomedia.device.*;

import java.lang.reflect.*;

/**
 * Created by boris on 30/05/2017.
 */
public class AudioMixerMediaDevice2
    extends AudioMixerMediaDevice
{
    public AudioMixerMediaDevice2()
    {
        super(new AudioSilenceMediaDevice());
        System.err.println("xxx new AudioMixerMediaDevice2");
    }

    @Override
    public AudioMixingPushBufferDataSource2 createOutputDataSource()
    {
        AudioMixer audioMixer = null;

        try
        {
            Method method = AudioMixerMediaDevice.class
                .getDeclaredMethod("getAudioMixer");
            method.setAccessible(true);
            audioMixer = (AudioMixer) method.invoke(this);
        }
        catch (NoSuchMethodException nsme)
        {
            System.err.println("No such method, "+nsme);
        }
        catch (Exception e)
        {
            System.err.println("Exception, "+e);
        }
        if (audioMixer == null)
        {
            System.err.println("xxx audio mixer null");
        }

        return new AudioMixingPushBufferDataSource2(audioMixer);
    }
}

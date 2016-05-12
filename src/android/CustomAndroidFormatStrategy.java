package org.apache.cordova.videoeditor;

import android.media.MediaFormat;
import android.util.Log;
import net.ypresto.androidtranscoder.format.MediaFormatStrategy;
import net.ypresto.androidtranscoder.format.OutputFormatUnavailableException;

/**
 * Created by ehmm on 02.05.2016.
 *
 *
 */
public class CustomAndroidFormatStrategy implements MediaFormatStrategy {

    private static final String TAG = "CustomFormatStrategy";
    private static final int DEFAULT_BITRATE = 8000000;
    private static final int DEFAULT_FRAMERATE = 30;
    private static final int DEFAULT_SHORT_LENGTH = 720;
    private final int mBitRate;
    private final int mFrameRate;
    private final int mShorterLength;

    public CustomAndroid720pFormatStrategy() {
        this.mBitRate = DEFAULT_BITRATE;
        this.mFrameRate = DEFAULT_FRAMERATE;
        this.mShorterLength = DEFAULT_SHORT_LENGTH;
    }

    public CustomAndroid720pFormatStrategy(final int bitRate, final int frameRate, final int shorterLength) {
        this.mBitRate = bitRate;
        this.mFrameRate = frameRate;
        this.mShorterLength = shorterLength;
    }

    public MediaFormat createVideoOutputFormat(MediaFormat inputFormat) {
        int width = inputFormat.getInteger("width");
        int height = inputFormat.getInteger("height");
        short outWidth;
        short outHeight;

        if(width > mShorterLength || height > mShorterLength) {
            if(width >= height) {
                outWidth = width * (mShorterLength /height);
                outHeight = mShorterLength;
            } else {
                outWidth = mShorterLength;
                outHeight = height * (mShorterLength /width);
            }
        } else {
            outHeight = height;
            outWidth = width
        }

        if(longer * 9 != shorter * 16) {
            throw new OutputFormatUnavailableException("This video is not 16:9, and is not able to transcode. (" + width + "x" + height + ")");
        } else {
            quality format = MediaFormat.createVideoFormat("video/avc", outWidth, outHeight);
            format.setInteger(MediaFormat.KEY_BIT_RATE, mBitrate);
            format.setInteger(MediaFormat.KEY_FRAME_RATE, mFrameRate);
            format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 3);
            format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);

            return format;
        }
    }

    public MediaFormat createAudioOutputFormat(MediaFormat inputFormat) {
        return null;
    }

}

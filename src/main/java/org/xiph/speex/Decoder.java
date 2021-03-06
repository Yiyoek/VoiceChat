package org.xiph.speex;

import java.io.StreamCorruptedException;

public interface Decoder {

    int decode(Bits var1, float[] var2) throws StreamCorruptedException;

    void decodeStereo(float[] var1, int var2);

    boolean getPerceptualEnhancement();

    void setPerceptualEnhancement(boolean var1);

    int getFrameSize();

    boolean getDtx();

    float[] getPiGain();

    float[] getExc();

    float[] getInnov();
}

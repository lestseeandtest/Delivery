package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imageutils.BitmapUtil;
import p201f.p202a.p203u.C5969d;

@TargetApi(21)
@C5969d
public class ArtBitmapFactory extends PlatformBitmapFactory {
    private final BitmapPool mBitmapPool;

    public ArtBitmapFactory(BitmapPool bitmapPool) {
        this.mBitmapPool = bitmapPool;
    }

    public CloseableReference<Bitmap> createBitmapInternal(int i, int i2, Config config) {
        Bitmap bitmap = (Bitmap) this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(i, i2, config));
        Bitmaps.reconfigureBitmap(bitmap, i, i2, config);
        return CloseableReference.m15818of(bitmap, this.mBitmapPool);
    }
}

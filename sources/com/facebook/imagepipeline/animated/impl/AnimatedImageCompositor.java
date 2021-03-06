package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.BlendOperation;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;

public class AnimatedImageCompositor {
    private final AnimatedDrawableBackend mAnimatedDrawableBackend;
    private final Callback mCallback;
    private final Paint mTransparentFillPaint = new Paint();

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$1 */
    static /* synthetic */ class C34831 {

        /* renamed from: $SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult */
        static final /* synthetic */ int[] f11787x60fd27af = new int[FrameNeededResult.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$FrameNeededResult[] r0 = com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.FrameNeededResult.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f11787x60fd27af = r0
                int[] r0 = f11787x60fd27af     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$FrameNeededResult r1 = com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.FrameNeededResult.REQUIRED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f11787x60fd27af     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$FrameNeededResult r1 = com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.FrameNeededResult.NOT_REQUIRED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f11787x60fd27af     // Catch:{ NoSuchFieldError -> 0x002a }
                com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$FrameNeededResult r1 = com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.FrameNeededResult.ABORT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f11787x60fd27af     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$FrameNeededResult r1 = com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.FrameNeededResult.SKIP     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.C34831.<clinit>():void");
        }
    }

    public interface Callback {
        CloseableReference<Bitmap> getCachedBitmap(int i);

        void onIntermediateResult(int i, Bitmap bitmap);
    }

    private enum FrameNeededResult {
        REQUIRED,
        NOT_REQUIRED,
        SKIP,
        ABORT
    }

    public AnimatedImageCompositor(AnimatedDrawableBackend animatedDrawableBackend, Callback callback) {
        this.mAnimatedDrawableBackend = animatedDrawableBackend;
        this.mCallback = callback;
        this.mTransparentFillPaint.setColor(0);
        this.mTransparentFillPaint.setStyle(Style.FILL);
        this.mTransparentFillPaint.setXfermode(new PorterDuffXfermode(Mode.SRC));
    }

    private void disposeToBackground(Canvas canvas, AnimatedDrawableFrameInfo animatedDrawableFrameInfo) {
        int i = animatedDrawableFrameInfo.xOffset;
        float f = (float) i;
        int i2 = animatedDrawableFrameInfo.yOffset;
        canvas.drawRect(f, (float) i2, (float) (i + animatedDrawableFrameInfo.width), (float) (i2 + animatedDrawableFrameInfo.height), this.mTransparentFillPaint);
    }

    private FrameNeededResult isFrameNeededForRendering(int i) {
        AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(i);
        DisposalMethod disposalMethod = frameInfo.disposalMethod;
        if (disposalMethod == DisposalMethod.DISPOSE_DO_NOT) {
            return FrameNeededResult.REQUIRED;
        }
        if (disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
            if (isFullFrame(frameInfo)) {
                return FrameNeededResult.NOT_REQUIRED;
            }
            return FrameNeededResult.REQUIRED;
        } else if (disposalMethod == DisposalMethod.DISPOSE_TO_PREVIOUS) {
            return FrameNeededResult.SKIP;
        } else {
            return FrameNeededResult.ABORT;
        }
    }

    private boolean isFullFrame(AnimatedDrawableFrameInfo animatedDrawableFrameInfo) {
        return animatedDrawableFrameInfo.xOffset == 0 && animatedDrawableFrameInfo.yOffset == 0 && animatedDrawableFrameInfo.width == this.mAnimatedDrawableBackend.getRenderedWidth() && animatedDrawableFrameInfo.height == this.mAnimatedDrawableBackend.getRenderedHeight();
    }

    private boolean isKeyFrame(int i) {
        boolean z = true;
        if (i == 0) {
            return true;
        }
        AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(i);
        AnimatedDrawableFrameInfo frameInfo2 = this.mAnimatedDrawableBackend.getFrameInfo(i - 1);
        if (frameInfo.blendOperation == BlendOperation.NO_BLEND && isFullFrame(frameInfo)) {
            return true;
        }
        if (frameInfo2.disposalMethod != DisposalMethod.DISPOSE_TO_BACKGROUND || !isFullFrame(frameInfo2)) {
            z = false;
        }
        return z;
    }

    private int prepareCanvasWithClosestCachedFrame(int i, Canvas canvas) {
        while (i >= 0) {
            int i2 = C34831.f11787x60fd27af[isFrameNeededForRendering(i).ordinal()];
            if (i2 == 1) {
                AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(i);
                CloseableReference cachedBitmap = this.mCallback.getCachedBitmap(i);
                if (cachedBitmap != null) {
                    try {
                        canvas.drawBitmap((Bitmap) cachedBitmap.get(), 0.0f, 0.0f, null);
                        if (frameInfo.disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
                            disposeToBackground(canvas, frameInfo);
                        }
                        return i + 1;
                    } finally {
                        cachedBitmap.close();
                    }
                } else if (isKeyFrame(i)) {
                    return i;
                }
            } else if (i2 == 2) {
                return i + 1;
            } else {
                if (i2 == 3) {
                    return i;
                }
            }
            i--;
        }
        return 0;
    }

    public void renderFrame(int i, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0, Mode.SRC);
        for (int prepareCanvasWithClosestCachedFrame = !isKeyFrame(i) ? prepareCanvasWithClosestCachedFrame(i - 1, canvas) : i; prepareCanvasWithClosestCachedFrame < i; prepareCanvasWithClosestCachedFrame++) {
            AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(prepareCanvasWithClosestCachedFrame);
            DisposalMethod disposalMethod = frameInfo.disposalMethod;
            if (disposalMethod != DisposalMethod.DISPOSE_TO_PREVIOUS) {
                if (frameInfo.blendOperation == BlendOperation.NO_BLEND) {
                    disposeToBackground(canvas, frameInfo);
                }
                this.mAnimatedDrawableBackend.renderFrame(prepareCanvasWithClosestCachedFrame, canvas);
                this.mCallback.onIntermediateResult(prepareCanvasWithClosestCachedFrame, bitmap);
                if (disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
                    disposeToBackground(canvas, frameInfo);
                }
            }
        }
        AnimatedDrawableFrameInfo frameInfo2 = this.mAnimatedDrawableBackend.getFrameInfo(i);
        if (frameInfo2.blendOperation == BlendOperation.NO_BLEND) {
            disposeToBackground(canvas, frameInfo2);
        }
        this.mAnimatedDrawableBackend.renderFrame(i, canvas);
    }
}

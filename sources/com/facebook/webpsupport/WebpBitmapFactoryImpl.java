package com.facebook.webpsupport;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.TypedValue;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpBitmapFactory.WebpErrorLogger;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.StaticWebpNativeLoader;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import p201f.p202a.C5952h;
import p205i.p471b.p472a.p473a.p474c.p475i.C13959t;

@DoNotStrip
public class WebpBitmapFactoryImpl implements WebpBitmapFactory {
    private static final int HEADER_SIZE = 20;
    public static final boolean IN_BITMAP_SUPPORTED = (VERSION.SDK_INT >= 11);
    private static final int IN_TEMP_BUFFER_SIZE = 8192;
    private static BitmapCreator mBitmapCreator;
    private static WebpErrorLogger mWebpErrorLogger;

    @DoNotStrip
    private static Bitmap createBitmap(int i, int i2, Options options) {
        if (IN_BITMAP_SUPPORTED && options != null) {
            Bitmap bitmap = options.inBitmap;
            if (bitmap != null && bitmap.isMutable()) {
                return options.inBitmap;
            }
        }
        return mBitmapCreator.createNakedBitmap(i, i2, Config.ARGB_8888);
    }

    @DoNotStrip
    private static byte[] getInTempStorageFromOptions(@C5952h Options options) {
        if (options != null) {
            byte[] bArr = options.inTempStorage;
            if (bArr != null) {
                return bArr;
            }
        }
        return new byte[8192];
    }

    @DoNotStrip
    private static float getScaleFromOptions(Options options) {
        float f = 1.0f;
        if (options == null) {
            return 1.0f;
        }
        int i = options.inSampleSize;
        if (i > 1) {
            f = 1.0f / ((float) i);
        }
        if (!options.inScaled) {
            return f;
        }
        int i2 = options.inDensity;
        int i3 = options.inTargetDensity;
        return (i2 == 0 || i3 == 0 || i2 == options.inScreenDensity) ? f : ((float) i3) / ((float) i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (r3.length >= 20) goto L_0x0011;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] getWebpHeader(java.io.InputStream r2, android.graphics.BitmapFactory.Options r3) {
        /*
            r0 = 20
            r2.mark(r0)
            if (r3 == 0) goto L_0x000f
            byte[] r3 = r3.inTempStorage
            if (r3 == 0) goto L_0x000f
            int r1 = r3.length
            if (r1 < r0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            byte[] r3 = new byte[r0]
        L_0x0011:
            r1 = 0
            r2.read(r3, r1, r0)     // Catch:{ IOException -> 0x0019 }
            r2.reset()     // Catch:{ IOException -> 0x0019 }
            return r3
        L_0x0019:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.webpsupport.WebpBitmapFactoryImpl.getWebpHeader(java.io.InputStream, android.graphics.BitmapFactory$Options):byte[]");
    }

    @DoNotStrip
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i, int i2, Options options) {
        Bitmap bitmap;
        StaticWebpNativeLoader.ensure();
        if (!WebpSupportStatus.sIsWebpSupportRequired || !WebpSupportStatus.isWebpHeader(bArr, i, i2)) {
            bitmap = originalDecodeByteArray(bArr, i, i2, options);
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_array_failed_on_no_webp");
            }
        } else {
            bitmap = nativeDecodeByteArray(bArr, i, i2, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_array");
            }
            setWebpBitmapOptions(bitmap, options);
        }
        return bitmap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0014 */
    @com.facebook.common.internal.DoNotStrip
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap hookDecodeFile(java.lang.String r2, android.graphics.BitmapFactory.Options r3) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0015 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0015 }
            android.graphics.Bitmap r0 = hookDecodeStream(r1, r0, r3)     // Catch:{ all -> 0x000e }
            r1.close()     // Catch:{ Exception -> 0x0015 }
            goto L_0x0015
        L_0x000e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0014 }
        L_0x0014:
            throw r2     // Catch:{ Exception -> 0x0015 }
        L_0x0015:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.webpsupport.WebpBitmapFactoryImpl.hookDecodeFile(java.lang.String, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    @DoNotStrip
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, Options options) {
        Bitmap bitmap;
        StaticWebpNativeLoader.ensure();
        long nativeSeek = nativeSeek(fileDescriptor, 0, false);
        if (nativeSeek != -1) {
            InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(new FileInputStream(fileDescriptor));
            try {
                byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, options);
                if (!WebpSupportStatus.sIsWebpSupportRequired || !WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
                    nativeSeek(fileDescriptor, nativeSeek, true);
                    bitmap = originalDecodeFileDescriptor(fileDescriptor, rect, options);
                    if (bitmap == null) {
                        sendWebpErrorLog("webp_direct_decode_fd_failed_on_no_webp");
                    }
                } else {
                    bitmap = nativeDecodeStream(wrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
                    if (bitmap == null) {
                        sendWebpErrorLog("webp_direct_decode_fd");
                    }
                    setPaddingDefaultValues(rect);
                    setWebpBitmapOptions(bitmap, options);
                }
                try {
                    wrapToMarkSupportedStream.close();
                    return bitmap;
                } catch (Throwable unused) {
                    return bitmap;
                }
            } catch (Throwable unused2) {
            }
        } else {
            Bitmap hookDecodeStream = hookDecodeStream(new FileInputStream(fileDescriptor), rect, options);
            setPaddingDefaultValues(rect);
            return hookDecodeStream;
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r3 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x001c */
    @com.facebook.common.internal.DoNotStrip
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap hookDecodeResource(android.content.res.Resources r2, int r3, android.graphics.BitmapFactory.Options r4) {
        /*
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r1 = 0
            java.io.InputStream r3 = r2.openRawResource(r3, r0)     // Catch:{ Exception -> 0x001d }
            android.graphics.Bitmap r1 = hookDecodeResourceStream(r2, r0, r3, r1, r4)     // Catch:{ all -> 0x0014 }
            if (r3 == 0) goto L_0x001e
            r3.close()     // Catch:{ Exception -> 0x001d }
            goto L_0x001e
        L_0x0014:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r2 = move-exception
            if (r3 == 0) goto L_0x001c
            r3.close()     // Catch:{ all -> 0x001c }
        L_0x001c:
            throw r2     // Catch:{ Exception -> 0x001d }
        L_0x001d:
        L_0x001e:
            boolean r2 = IN_BITMAP_SUPPORTED
            if (r2 == 0) goto L_0x0033
            if (r1 != 0) goto L_0x0033
            if (r4 == 0) goto L_0x0033
            android.graphics.Bitmap r2 = r4.inBitmap
            if (r2 != 0) goto L_0x002b
            goto L_0x0033
        L_0x002b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Problem decoding into existing bitmap"
            r2.<init>(r3)
            throw r2
        L_0x0033:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.webpsupport.WebpBitmapFactoryImpl.hookDecodeResource(android.content.res.Resources, int, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    @DoNotStrip
    public static Bitmap hookDecodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, Options options) {
        if (options == null) {
            options = new Options();
        }
        if (options.inDensity == 0 && typedValue != null) {
            int i = typedValue.density;
            if (i == 0) {
                options.inDensity = C13959t.f40969w2;
            } else if (i != 65535) {
                options.inDensity = i;
            }
        }
        if (options.inTargetDensity == 0 && resources != null) {
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        return hookDecodeStream(inputStream, rect, options);
    }

    @DoNotStrip
    public static Bitmap hookDecodeStream(InputStream inputStream, Rect rect, Options options) {
        Bitmap bitmap;
        StaticWebpNativeLoader.ensure();
        InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(inputStream);
        byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, options);
        if (!WebpSupportStatus.sIsWebpSupportRequired || !WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
            bitmap = originalDecodeStream(wrapToMarkSupportedStream, rect, options);
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_stream_failed_on_no_webp");
            }
        } else {
            bitmap = nativeDecodeStream(wrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (bitmap == null) {
                sendWebpErrorLog("webp_direct_decode_stream");
            }
            setWebpBitmapOptions(bitmap, options);
            setPaddingDefaultValues(rect);
        }
        return bitmap;
    }

    @DoNotStrip
    private static native Bitmap nativeDecodeByteArray(byte[] bArr, int i, int i2, Options options, float f, byte[] bArr2);

    @DoNotStrip
    private static native Bitmap nativeDecodeStream(InputStream inputStream, Options options, float f, byte[] bArr);

    @DoNotStrip
    private static native long nativeSeek(FileDescriptor fileDescriptor, long j, boolean z);

    @DoNotStrip
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i, int i2, Options options) {
        return BitmapFactory.decodeByteArray(bArr, i, i2, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFile(String str, Options options) {
        return BitmapFactory.decodeFile(str, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, Options options) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeResource(Resources resources, int i, Options options) {
        return BitmapFactory.decodeResource(resources, i, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, Options options) {
        return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeStream(InputStream inputStream, Rect rect, Options options) {
        return BitmapFactory.decodeStream(inputStream, rect, options);
    }

    private static void sendWebpErrorLog(String str) {
        WebpErrorLogger webpErrorLogger = mWebpErrorLogger;
        if (webpErrorLogger != null) {
            webpErrorLogger.onWebpErrorLog(str, "decoding_failure");
        }
    }

    @DoNotStrip
    private static void setBitmapSize(@C5952h Options options, int i, int i2) {
        if (options != null) {
            options.outWidth = i;
            options.outHeight = i2;
        }
    }

    private static void setDensityFromOptions(Bitmap bitmap, Options options) {
        if (!(bitmap == null || options == null)) {
            int i = options.inDensity;
            if (i != 0) {
                bitmap.setDensity(i);
                int i2 = options.inTargetDensity;
                if (!(i2 == 0 || i == i2 || i == options.inScreenDensity || !options.inScaled)) {
                    bitmap.setDensity(i2);
                }
            } else if (IN_BITMAP_SUPPORTED && options.inBitmap != null) {
                bitmap.setDensity(C13959t.f40969w2);
            }
        }
    }

    @DoNotStrip
    private static boolean setOutDimensions(Options options, int i, int i2) {
        if (options == null || !options.inJustDecodeBounds) {
            return false;
        }
        options.outWidth = i;
        options.outHeight = i2;
        return true;
    }

    @DoNotStrip
    private static void setPaddingDefaultValues(@C5952h Rect rect) {
        if (rect != null) {
            rect.top = -1;
            rect.left = -1;
            rect.bottom = -1;
            rect.right = -1;
        }
    }

    private static void setWebpBitmapOptions(Bitmap bitmap, Options options) {
        setDensityFromOptions(bitmap, options);
        if (options != null) {
            options.outMimeType = "image/webp";
        }
    }

    @SuppressLint({"NewApi"})
    @DoNotStrip
    private static boolean shouldPremultiply(Options options) {
        if (VERSION.SDK_INT < 19 || options == null) {
            return true;
        }
        return options.inPremultiplied;
    }

    private static InputStream wrapToMarkSupportedStream(InputStream inputStream) {
        return !inputStream.markSupported() ? new BufferedInputStream(inputStream, 20) : inputStream;
    }

    public Bitmap decodeByteArray(byte[] bArr, int i, int i2, Options options) {
        return hookDecodeByteArray(bArr, i, i2, options);
    }

    public Bitmap decodeFile(String str, Options options) {
        return hookDecodeFile(str, options);
    }

    public Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, Options options) {
        return hookDecodeFileDescriptor(fileDescriptor, rect, options);
    }

    public Bitmap decodeStream(InputStream inputStream, Rect rect, Options options) {
        return hookDecodeStream(inputStream, rect, options);
    }

    public void setBitmapCreator(BitmapCreator bitmapCreator) {
        mBitmapCreator = bitmapCreator;
    }

    public void setWebpErrorLogger(WebpErrorLogger webpErrorLogger) {
        mWebpErrorLogger = webpErrorLogger;
    }

    @DoNotStrip
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i, int i2) {
        return BitmapFactory.decodeByteArray(bArr, i, i2);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFile(String str) {
        return BitmapFactory.decodeFile(str);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    @DoNotStrip
    private static Bitmap originalDecodeResource(Resources resources, int i) {
        return BitmapFactory.decodeResource(resources, i);
    }

    @DoNotStrip
    private static Bitmap originalDecodeStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @DoNotStrip
    public static Bitmap hookDecodeFile(String str) {
        return hookDecodeFile(str, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeResource(Resources resources, int i) {
        return hookDecodeResource(resources, i, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i, int i2) {
        return hookDecodeByteArray(bArr, i, i2, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeStream(InputStream inputStream) {
        return hookDecodeStream(inputStream, null, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return hookDecodeFileDescriptor(fileDescriptor, null, null);
    }
}
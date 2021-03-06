package com.facebook.imageformat;

import com.facebook.common.internal.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public final class DefaultImageFormats {
    public static final ImageFormat BMP = new ImageFormat("BMP", "bmp");
    public static final ImageFormat GIF = new ImageFormat("GIF", "gif");
    public static final ImageFormat HEIF = new ImageFormat("HEIF", "heif");
    public static final ImageFormat JPEG = new ImageFormat("JPEG", "jpeg");
    public static final ImageFormat PNG = new ImageFormat("PNG", "png");
    public static final ImageFormat WEBP_ANIMATED;
    public static final ImageFormat WEBP_EXTENDED;
    public static final ImageFormat WEBP_EXTENDED_WITH_ALPHA;
    public static final ImageFormat WEBP_LOSSLESS;
    public static final ImageFormat WEBP_SIMPLE;
    private static ImmutableList<ImageFormat> sAllDefaultFormats;

    static {
        String str = "webp";
        WEBP_SIMPLE = new ImageFormat("WEBP_SIMPLE", str);
        WEBP_LOSSLESS = new ImageFormat("WEBP_LOSSLESS", str);
        WEBP_EXTENDED = new ImageFormat("WEBP_EXTENDED", str);
        WEBP_EXTENDED_WITH_ALPHA = new ImageFormat("WEBP_EXTENDED_WITH_ALPHA", str);
        WEBP_ANIMATED = new ImageFormat("WEBP_ANIMATED", str);
    }

    private DefaultImageFormats() {
    }

    public static List<ImageFormat> getDefaultFormats() {
        if (sAllDefaultFormats == null) {
            ArrayList arrayList = new ArrayList(9);
            arrayList.add(JPEG);
            arrayList.add(PNG);
            arrayList.add(GIF);
            arrayList.add(BMP);
            arrayList.add(WEBP_SIMPLE);
            arrayList.add(WEBP_LOSSLESS);
            arrayList.add(WEBP_EXTENDED);
            arrayList.add(WEBP_EXTENDED_WITH_ALPHA);
            arrayList.add(WEBP_ANIMATED);
            arrayList.add(HEIF);
            sAllDefaultFormats = ImmutableList.copyOf(arrayList);
        }
        return sAllDefaultFormats;
    }

    public static boolean isStaticWebpFormat(ImageFormat imageFormat) {
        return imageFormat == WEBP_SIMPLE || imageFormat == WEBP_LOSSLESS || imageFormat == WEBP_EXTENDED || imageFormat == WEBP_EXTENDED_WITH_ALPHA;
    }

    public static boolean isWebpFormat(ImageFormat imageFormat) {
        return isStaticWebpFormat(imageFormat) || imageFormat == WEBP_ANIMATED;
    }
}

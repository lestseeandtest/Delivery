package com.airbnb.lottie.network;

import android.content.Context;
import androidx.annotation.C0195i0;
import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class NetworkCache {
    private final Context appContext;
    private final String url;

    NetworkCache(Context context, String str) {
        this.appContext = context.getApplicationContext();
        this.url = str;
    }

    private static String filenameForUrl(String str, FileExtension fileExtension, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("lottie_cache_");
        sb.append(str.replaceAll("\\W+", ""));
        sb.append(z ? fileExtension.tempExtension() : fileExtension.extension);
        return sb.toString();
    }

    @C0195i0
    private File getCachedFile(String str) throws FileNotFoundException {
        File file = new File(this.appContext.getCacheDir(), filenameForUrl(str, FileExtension.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(this.appContext.getCacheDir(), filenameForUrl(str, FileExtension.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @androidx.annotation.C0226w0
    @androidx.annotation.C0195i0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.core.p033k.C0938f<com.airbnb.lottie.network.FileExtension, java.io.InputStream> fetch() {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = r5.url     // Catch:{ FileNotFoundException -> 0x0048 }
            java.io.File r1 = r5.getCachedFile(r1)     // Catch:{ FileNotFoundException -> 0x0048 }
            if (r1 != 0) goto L_0x000a
            return r0
        L_0x000a:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{  }
            r2.<init>(r1)     // Catch:{  }
            java.lang.String r0 = r1.getAbsolutePath()
            java.lang.String r3 = ".zip"
            boolean r0 = r0.endsWith(r3)
            if (r0 == 0) goto L_0x001e
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.ZIP
            goto L_0x0020
        L_0x001e:
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.JSON
        L_0x0020:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Cache hit for "
            r3.append(r4)
            java.lang.String r4 = r5.url
            r3.append(r4)
            java.lang.String r4 = " at "
            r3.append(r4)
            java.lang.String r1 = r1.getAbsolutePath()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.airbnb.lottie.utils.Logger.debug(r1)
            androidx.core.k.f r1 = new androidx.core.k.f
            r1.<init>(r0, r2)
            return r1
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkCache.fetch():androidx.core.k.f");
    }

    /* access modifiers changed from: 0000 */
    public void renameTempFile(FileExtension fileExtension) {
        File file = new File(this.appContext.getCacheDir(), filenameForUrl(this.url, fileExtension, true));
        File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean renameTo = file.renameTo(file2);
        StringBuilder sb = new StringBuilder();
        sb.append("Copying temp file to real file (");
        sb.append(file2);
        sb.append(")");
        Logger.debug(sb.toString());
        if (!renameTo) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to rename cache file ");
            sb2.append(file.getAbsolutePath());
            sb2.append(" to ");
            sb2.append(file2.getAbsolutePath());
            sb2.append(".");
            Logger.warning(sb2.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public File writeTempCacheFile(InputStream inputStream, FileExtension fileExtension) throws IOException {
        FileOutputStream fileOutputStream;
        File file = new File(this.appContext.getCacheDir(), filenameForUrl(this.url, fileExtension, true));
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    return file;
                }
            }
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }
}

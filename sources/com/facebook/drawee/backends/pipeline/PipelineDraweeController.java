package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.backends.pipeline.info.ForwardingImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;
import com.facebook.drawee.debug.listener.ImageLoadingTimeControllerListener;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import p201f.p202a.C5952h;
import p201f.p202a.p203u.C5966a;

public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo> {
    private static final Class<?> TAG = PipelineDraweeController.class;
    private CacheKey mCacheKey;
    @C5952h
    private ImmutableList<DrawableFactory> mCustomDrawableFactories;
    private Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
    private final DrawableFactory mDefaultDrawableFactory;
    private boolean mDrawDebugOverlay;
    @C5952h
    private final ImmutableList<DrawableFactory> mGlobalDrawableFactories;
    @C5952h
    @C5966a("this")
    private ImageOriginListener mImageOriginListener;
    @C5952h
    private ImagePerfMonitor mImagePerfMonitor;
    @C5952h
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    @C5952h
    @C5966a("this")
    private Set<RequestListener> mRequestListeners;
    private final Resources mResources;

    public PipelineDraweeController(Resources resources, DeferredReleaser deferredReleaser, DrawableFactory drawableFactory, Executor executor, @C5952h MemoryCache<CacheKey, CloseableImage> memoryCache, @C5952h ImmutableList<DrawableFactory> immutableList) {
        super(deferredReleaser, executor, null, null);
        this.mResources = resources;
        this.mDefaultDrawableFactory = new DefaultDrawableFactory(resources, drawableFactory);
        this.mGlobalDrawableFactories = immutableList;
        this.mMemoryCache = memoryCache;
    }

    private void init(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier) {
        this.mDataSourceSupplier = supplier;
        maybeUpdateDebugOverlay(null);
    }

    private Drawable maybeCreateDrawableFromFactories(@C5952h ImmutableList<DrawableFactory> immutableList, CloseableImage closeableImage) {
        if (immutableList == null) {
            return null;
        }
        Iterator it = immutableList.iterator();
        while (it.hasNext()) {
            DrawableFactory drawableFactory = (DrawableFactory) it.next();
            if (drawableFactory.supportsImageType(closeableImage)) {
                Drawable createDrawable = drawableFactory.createDrawable(closeableImage);
                if (createDrawable != null) {
                    return createDrawable;
                }
            }
        }
        return null;
    }

    private void maybeUpdateDebugOverlay(@C5952h CloseableImage closeableImage) {
        if (this.mDrawDebugOverlay) {
            if (getControllerOverlay() == null) {
                DebugControllerOverlayDrawable debugControllerOverlayDrawable = new DebugControllerOverlayDrawable();
                addControllerListener(new ImageLoadingTimeControllerListener(debugControllerOverlayDrawable));
                setControllerOverlay(debugControllerOverlayDrawable);
            }
            if (getControllerOverlay() instanceof DebugControllerOverlayDrawable) {
                DebugControllerOverlayDrawable debugControllerOverlayDrawable2 = (DebugControllerOverlayDrawable) getControllerOverlay();
                debugControllerOverlayDrawable2.setControllerId(getId());
                DraweeHierarchy hierarchy = getHierarchy();
                ScaleType scaleType = null;
                if (hierarchy != null) {
                    ScaleTypeDrawable activeScaleTypeDrawable = ScalingUtils.getActiveScaleTypeDrawable(hierarchy.getTopLevelDrawable());
                    if (activeScaleTypeDrawable != null) {
                        scaleType = activeScaleTypeDrawable.getScaleType();
                    }
                }
                debugControllerOverlayDrawable2.setScaleType(scaleType);
                if (closeableImage != null) {
                    debugControllerOverlayDrawable2.setDimensions(closeableImage.getWidth(), closeableImage.getHeight());
                    debugControllerOverlayDrawable2.setImageSize(closeableImage.getSizeInBytes());
                } else {
                    debugControllerOverlayDrawable2.reset();
                }
            }
        }
    }

    public synchronized void addImageOriginListener(ImageOriginListener imageOriginListener) {
        if (this.mImageOriginListener instanceof ForwardingImageOriginListener) {
            ((ForwardingImageOriginListener) this.mImageOriginListener).addImageOriginListener(imageOriginListener);
        } else if (this.mImageOriginListener != null) {
            this.mImageOriginListener = new ForwardingImageOriginListener(this.mImageOriginListener, imageOriginListener);
        } else {
            this.mImageOriginListener = imageOriginListener;
        }
    }

    public synchronized void addRequestListener(RequestListener requestListener) {
        if (this.mRequestListeners == null) {
            this.mRequestListeners = new HashSet();
        }
        this.mRequestListeners.add(requestListener);
    }

    /* access modifiers changed from: protected */
    public void clearImageOriginListeners() {
        synchronized (this) {
            this.mImageOriginListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public CacheKey getCacheKey() {
        return this.mCacheKey;
    }

    /* access modifiers changed from: protected */
    public DataSource<CloseableReference<CloseableImage>> getDataSource() {
        if (FLog.isLoggable(2)) {
            FLog.m15774v(TAG, "controller %x: getDataSource", (Object) Integer.valueOf(System.identityHashCode(this)));
        }
        return (DataSource) this.mDataSourceSupplier.get();
    }

    /* access modifiers changed from: protected */
    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier() {
        return this.mDataSourceSupplier;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        return r1;
     */
    @p201f.p202a.C5952h
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.listener.RequestListener getRequestListener() {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            com.facebook.drawee.backends.pipeline.info.ImageOriginListener r1 = r3.mImageOriginListener     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0011
            com.facebook.drawee.backends.pipeline.info.ImageOriginRequestListener r0 = new com.facebook.drawee.backends.pipeline.info.ImageOriginRequestListener     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = r3.getId()     // Catch:{ all -> 0x0025 }
            com.facebook.drawee.backends.pipeline.info.ImageOriginListener r2 = r3.mImageOriginListener     // Catch:{ all -> 0x0025 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0025 }
        L_0x0011:
            java.util.Set<com.facebook.imagepipeline.listener.RequestListener> r1 = r3.mRequestListeners     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0023
            com.facebook.imagepipeline.listener.ForwardingRequestListener r1 = new com.facebook.imagepipeline.listener.ForwardingRequestListener     // Catch:{ all -> 0x0025 }
            java.util.Set<com.facebook.imagepipeline.listener.RequestListener> r2 = r3.mRequestListeners     // Catch:{ all -> 0x0025 }
            r1.<init>(r2)     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0021
            r1.addRequestListener(r0)     // Catch:{ all -> 0x0025 }
        L_0x0021:
            monitor-exit(r3)
            return r1
        L_0x0023:
            monitor-exit(r3)
            return r0
        L_0x0025:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.PipelineDraweeController.getRequestListener():com.facebook.imagepipeline.listener.RequestListener");
    }

    /* access modifiers changed from: protected */
    public Resources getResources() {
        return this.mResources;
    }

    public void initialize(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, String str, CacheKey cacheKey, Object obj, @C5952h ImmutableList<DrawableFactory> immutableList, @C5952h ImageOriginListener imageOriginListener) {
        super.initialize(str, obj);
        init(supplier);
        this.mCacheKey = cacheKey;
        setCustomDrawableFactories(immutableList);
        clearImageOriginListeners();
        addImageOriginListener(imageOriginListener);
    }

    /* access modifiers changed from: protected */
    public synchronized void initializePerformanceMonitoring(@C5952h ImagePerfDataListener imagePerfDataListener) {
        if (this.mImagePerfMonitor != null) {
            this.mImagePerfMonitor.reset();
        }
        if (imagePerfDataListener != null) {
            if (this.mImagePerfMonitor == null) {
                this.mImagePerfMonitor = new ImagePerfMonitor(RealtimeSinceBootClock.get(), this);
            }
            this.mImagePerfMonitor.addImagePerfDataListener(imagePerfDataListener);
            this.mImagePerfMonitor.setEnabled(true);
        }
    }

    public boolean isSameImageRequest(@C5952h DraweeController draweeController) {
        CacheKey cacheKey = this.mCacheKey;
        if (cacheKey == null || !(draweeController instanceof PipelineDraweeController)) {
            return false;
        }
        return Objects.equal(cacheKey, ((PipelineDraweeController) draweeController).getCacheKey());
    }

    /* access modifiers changed from: protected */
    public void releaseDrawable(@C5952h Drawable drawable) {
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches) drawable).dropCaches();
        }
    }

    public synchronized void removeImageOriginListener(ImageOriginListener imageOriginListener) {
        if (this.mImageOriginListener instanceof ForwardingImageOriginListener) {
            ((ForwardingImageOriginListener) this.mImageOriginListener).removeImageOriginListener(imageOriginListener);
        } else if (this.mImageOriginListener != null) {
            this.mImageOriginListener = new ForwardingImageOriginListener(this.mImageOriginListener, imageOriginListener);
        } else {
            this.mImageOriginListener = imageOriginListener;
        }
    }

    public synchronized void removeRequestListener(RequestListener requestListener) {
        if (this.mRequestListeners != null) {
            this.mRequestListeners.remove(requestListener);
        }
    }

    public void setCustomDrawableFactories(@C5952h ImmutableList<DrawableFactory> immutableList) {
        this.mCustomDrawableFactories = immutableList;
    }

    public void setDrawDebugOverlay(boolean z) {
        this.mDrawDebugOverlay = z;
    }

    public void setHierarchy(@C5952h DraweeHierarchy draweeHierarchy) {
        super.setHierarchy(draweeHierarchy);
        maybeUpdateDebugOverlay(null);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("super", (Object) super.toString()).add("dataSourceSupplier", (Object) this.mDataSourceSupplier).toString();
    }

    /* access modifiers changed from: protected */
    public Drawable createDrawable(CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        CloseableImage closeableImage = (CloseableImage) closeableReference.get();
        maybeUpdateDebugOverlay(closeableImage);
        Drawable maybeCreateDrawableFromFactories = maybeCreateDrawableFromFactories(this.mCustomDrawableFactories, closeableImage);
        if (maybeCreateDrawableFromFactories != null) {
            return maybeCreateDrawableFromFactories;
        }
        Drawable maybeCreateDrawableFromFactories2 = maybeCreateDrawableFromFactories(this.mGlobalDrawableFactories, closeableImage);
        if (maybeCreateDrawableFromFactories2 != null) {
            return maybeCreateDrawableFromFactories2;
        }
        Drawable createDrawable = this.mDefaultDrawableFactory.createDrawable(closeableImage);
        if (createDrawable != null) {
            return createDrawable;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized image class: ");
        sb.append(closeableImage);
        throw new UnsupportedOperationException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public CloseableReference<CloseableImage> getCachedImage() {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mMemoryCache;
        if (memoryCache != null) {
            CacheKey cacheKey = this.mCacheKey;
            if (cacheKey != null) {
                CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
                if (closeableReference == null || ((CloseableImage) closeableReference.get()).getQualityInfo().isOfFullQuality()) {
                    return closeableReference;
                }
                closeableReference.close();
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getImageHash(@C5952h CloseableReference<CloseableImage> closeableReference) {
        if (closeableReference != null) {
            return closeableReference.getValueHash();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public ImageInfo getImageInfo(CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        return (ImageInfo) closeableReference.get();
    }

    /* access modifiers changed from: protected */
    public void onImageLoadedFromCacheImmediately(String str, CloseableReference<CloseableImage> closeableReference) {
        super.onImageLoadedFromCacheImmediately(str, closeableReference);
        synchronized (this) {
            if (this.mImageOriginListener != null) {
                this.mImageOriginListener.onImageLoaded(str, 3, true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void releaseImage(@C5952h CloseableReference<CloseableImage> closeableReference) {
        CloseableReference.closeSafely(closeableReference);
    }
}

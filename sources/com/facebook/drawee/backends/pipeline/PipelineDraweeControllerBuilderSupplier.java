package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.content.res.Resources;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import java.util.Set;
import p201f.p202a.C5952h;

public class PipelineDraweeControllerBuilderSupplier implements Supplier<PipelineDraweeControllerBuilder> {
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Context mContext;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilderSupplier(Context context) {
        this(context, null);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, @C5952h DraweeConfig draweeConfig) {
        this(context, ImagePipelineFactory.getInstance(), draweeConfig);
    }

    public PipelineDraweeControllerBuilder get() {
        return new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, @C5952h DraweeConfig draweeConfig) {
        this(context, imagePipelineFactory, null, draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, Set<ControllerListener> set, @C5952h DraweeConfig draweeConfig) {
        this.mContext = context;
        this.mImagePipeline = imagePipelineFactory.getImagePipeline();
        if (draweeConfig == null || draweeConfig.getPipelineDraweeControllerFactory() == null) {
            this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
        } else {
            this.mPipelineDraweeControllerFactory = draweeConfig.getPipelineDraweeControllerFactory();
        }
        PipelineDraweeControllerFactory pipelineDraweeControllerFactory = this.mPipelineDraweeControllerFactory;
        Resources resources = context.getResources();
        DeferredReleaser instance = DeferredReleaser.getInstance();
        DrawableFactory animatedDrawableFactory = imagePipelineFactory.getAnimatedDrawableFactory(context);
        UiThreadImmediateExecutorService instance2 = UiThreadImmediateExecutorService.getInstance();
        MemoryCache bitmapMemoryCache = this.mImagePipeline.getBitmapMemoryCache();
        Supplier supplier = null;
        ImmutableList customDrawableFactories = draweeConfig != null ? draweeConfig.getCustomDrawableFactories() : null;
        if (draweeConfig != null) {
            supplier = draweeConfig.getDebugOverlayEnabledSupplier();
        }
        pipelineDraweeControllerFactory.init(resources, instance, animatedDrawableFactory, instance2, bitmapMemoryCache, customDrawableFactories, supplier);
        this.mBoundControllerListeners = set;
    }
}
package com.facebook.imagepipeline.memory;

public class BitmapCounterConfig {
    public static final int DEFAULT_MAX_BITMAP_COUNT = 384;
    private int mMaxBitmapCount = DEFAULT_MAX_BITMAP_COUNT;

    public static class Builder {
        private int mMaxBitmapCount;

        public BitmapCounterConfig build() {
            return new BitmapCounterConfig(this);
        }

        public int getMaxBitmapCount() {
            return this.mMaxBitmapCount;
        }

        public Builder setMaxBitmapCount(int i) {
            this.mMaxBitmapCount = i;
            return this;
        }

        private Builder() {
            this.mMaxBitmapCount = BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT;
        }
    }

    public BitmapCounterConfig(Builder builder) {
        this.mMaxBitmapCount = builder.getMaxBitmapCount();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getMaxBitmapCount() {
        return this.mMaxBitmapCount;
    }

    public void setMaxBitmapCount(int i) {
        this.mMaxBitmapCount = i;
    }
}

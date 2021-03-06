package org.jcodec.containers.mp4.boxes;

import java.nio.ByteBuffer;

public class TrackFragmentHeaderBox extends FullBox {
    public static final int FLAG_BASE_DATA_OFFSET = 1;
    public static final int FLAG_DEFAILT_SAMPLE_DURATION = 8;
    public static final int FLAG_DEFAILT_SAMPLE_FLAGS = 32;
    public static final int FLAG_DEFAULT_SAMPLE_SIZE = 16;
    public static final int FLAG_SAMPLE_DESCRIPTION_INDEX = 2;
    /* access modifiers changed from: private */
    public long baseDataOffset;
    /* access modifiers changed from: private */
    public int defaultSampleDuration;
    /* access modifiers changed from: private */
    public int defaultSampleFlags;
    /* access modifiers changed from: private */
    public int defaultSampleSize;
    /* access modifiers changed from: private */
    public int sampleDescriptionIndex;
    /* access modifiers changed from: private */
    public int trackId;

    public static class Factory {
        private TrackFragmentHeaderBox box;

        protected Factory(int i) {
            this.box = new TrackFragmentHeaderBox(i);
        }

        public Factory baseDataOffset(long j) {
            TrackFragmentHeaderBox trackFragmentHeaderBox = this.box;
            trackFragmentHeaderBox.flags |= 1;
            trackFragmentHeaderBox.baseDataOffset = (long) ((int) j);
            return this;
        }

        public Box create() {
            try {
                return this.box;
            } finally {
                this.box = null;
            }
        }

        public Factory defaultSampleDuration(long j) {
            TrackFragmentHeaderBox trackFragmentHeaderBox = this.box;
            trackFragmentHeaderBox.flags |= 8;
            trackFragmentHeaderBox.defaultSampleDuration = (int) j;
            return this;
        }

        public Factory defaultSampleFlags(long j) {
            TrackFragmentHeaderBox trackFragmentHeaderBox = this.box;
            trackFragmentHeaderBox.flags |= 32;
            trackFragmentHeaderBox.defaultSampleFlags = (int) j;
            return this;
        }

        public Factory defaultSampleSize(long j) {
            TrackFragmentHeaderBox trackFragmentHeaderBox = this.box;
            trackFragmentHeaderBox.flags |= 16;
            trackFragmentHeaderBox.defaultSampleSize = (int) j;
            return this;
        }

        public Factory sampleDescriptionIndex(long j) {
            TrackFragmentHeaderBox trackFragmentHeaderBox = this.box;
            trackFragmentHeaderBox.flags |= 2;
            trackFragmentHeaderBox.sampleDescriptionIndex = (int) j;
            return this;
        }

        public Factory(TrackFragmentHeaderBox trackFragmentHeaderBox) {
            TrackFragmentHeaderBox trackFragmentHeaderBox2 = new TrackFragmentHeaderBox(trackFragmentHeaderBox.trackId, trackFragmentHeaderBox.baseDataOffset, trackFragmentHeaderBox.sampleDescriptionIndex, trackFragmentHeaderBox.defaultSampleDuration, trackFragmentHeaderBox.defaultSampleSize, trackFragmentHeaderBox.defaultSampleFlags);
            this.box = trackFragmentHeaderBox2;
            this.box.setFlags(trackFragmentHeaderBox.getFlags());
            this.box.setVersion(trackFragmentHeaderBox.getVersion());
        }
    }

    public TrackFragmentHeaderBox() {
        super(new Header(fourcc()));
    }

    public static Factory copy(TrackFragmentHeaderBox trackFragmentHeaderBox) {
        return new Factory(trackFragmentHeaderBox);
    }

    public static Factory create(int i) {
        return new Factory(i);
    }

    public static String fourcc() {
        return "tfhd";
    }

    /* access modifiers changed from: protected */
    public void doWrite(ByteBuffer byteBuffer) {
        super.doWrite(byteBuffer);
        byteBuffer.putInt(this.trackId);
        if (isBaseDataOffsetAvailable()) {
            byteBuffer.putLong(this.baseDataOffset);
        }
        if (isSampleDescriptionIndexAvailable()) {
            byteBuffer.putInt(this.sampleDescriptionIndex);
        }
        if (isDefaultSampleDurationAvailable()) {
            byteBuffer.putInt(this.defaultSampleDuration);
        }
        if (isDefaultSampleSizeAvailable()) {
            byteBuffer.putInt(this.defaultSampleSize);
        }
        if (isDefaultSampleFlagsAvailable()) {
            byteBuffer.putInt(this.defaultSampleFlags);
        }
    }

    public long getBaseDataOffset() {
        return this.baseDataOffset;
    }

    public int getDefaultSampleDuration() {
        return this.defaultSampleDuration;
    }

    public int getDefaultSampleFlags() {
        return this.defaultSampleFlags;
    }

    public int getDefaultSampleSize() {
        return this.defaultSampleSize;
    }

    public int getSampleDescriptionIndex() {
        return this.sampleDescriptionIndex;
    }

    public int getTrackId() {
        return this.trackId;
    }

    public boolean isBaseDataOffsetAvailable() {
        return (this.flags & 1) != 0;
    }

    public boolean isDefaultSampleDurationAvailable() {
        return (this.flags & 8) != 0;
    }

    public boolean isDefaultSampleFlagsAvailable() {
        return (this.flags & 32) != 0;
    }

    public boolean isDefaultSampleSizeAvailable() {
        return (this.flags & 16) != 0;
    }

    public boolean isSampleDescriptionIndexAvailable() {
        return (this.flags & 2) != 0;
    }

    public void parse(ByteBuffer byteBuffer) {
        super.parse(byteBuffer);
        this.trackId = byteBuffer.getInt();
        if (isBaseDataOffsetAvailable()) {
            this.baseDataOffset = byteBuffer.getLong();
        }
        if (isSampleDescriptionIndexAvailable()) {
            this.sampleDescriptionIndex = byteBuffer.getInt();
        }
        if (isDefaultSampleDurationAvailable()) {
            this.defaultSampleDuration = byteBuffer.getInt();
        }
        if (isDefaultSampleSizeAvailable()) {
            this.defaultSampleSize = byteBuffer.getInt();
        }
        if (isDefaultSampleFlagsAvailable()) {
            this.defaultSampleFlags = byteBuffer.getInt();
        }
    }

    public void setDefaultSampleFlags(int i) {
        this.defaultSampleFlags = i;
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }

    public TrackFragmentHeaderBox(int i) {
        this();
        this.trackId = i;
    }

    protected TrackFragmentHeaderBox(int i, long j, int i2, int i3, int i4, int i5) {
        super(new Header(fourcc()));
        this.trackId = i;
        this.baseDataOffset = j;
        this.sampleDescriptionIndex = i2;
        this.defaultSampleDuration = i3;
        this.defaultSampleSize = i4;
        this.defaultSampleFlags = i5;
    }
}

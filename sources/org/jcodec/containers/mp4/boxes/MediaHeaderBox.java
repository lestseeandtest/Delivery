package org.jcodec.containers.mp4.boxes;

import java.nio.ByteBuffer;
import org.jcodec.containers.mp4.TimeUtil;

public class MediaHeaderBox extends FullBox {
    private long created;
    private long duration;
    private int language;
    private long modified;
    private int quality;
    private int timescale;

    public MediaHeaderBox(int i, long j, int i2, long j2, long j3, int i3) {
        super(new Header(fourcc()));
        this.timescale = i;
        this.duration = j;
        this.language = i2;
        this.created = j2;
        this.modified = j3;
        this.quality = i3;
    }

    public static String fourcc() {
        return "mdhd";
    }

    public void doWrite(ByteBuffer byteBuffer) {
        super.doWrite(byteBuffer);
        byteBuffer.putInt(TimeUtil.toMovTime(this.created));
        byteBuffer.putInt(TimeUtil.toMovTime(this.modified));
        byteBuffer.putInt(this.timescale);
        byteBuffer.putInt((int) this.duration);
        byteBuffer.putShort((short) this.language);
        byteBuffer.putShort((short) this.quality);
    }

    public long getCreated() {
        return this.created;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getLanguage() {
        return this.language;
    }

    public long getModified() {
        return this.modified;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getTimescale() {
        return this.timescale;
    }

    public void parse(ByteBuffer byteBuffer) {
        super.parse(byteBuffer);
        byte b = this.version;
        if (b == 0) {
            this.created = TimeUtil.fromMovTime(byteBuffer.getInt());
            this.modified = TimeUtil.fromMovTime(byteBuffer.getInt());
            this.timescale = byteBuffer.getInt();
            this.duration = (long) byteBuffer.getInt();
        } else if (b == 1) {
            this.created = TimeUtil.fromMovTime((int) byteBuffer.getLong());
            this.modified = TimeUtil.fromMovTime((int) byteBuffer.getLong());
            this.timescale = byteBuffer.getInt();
            this.duration = byteBuffer.getLong();
        } else {
            throw new RuntimeException("Unsupported version");
        }
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setTimescale(int i) {
        this.timescale = i;
    }

    public MediaHeaderBox() {
        super(new Header(fourcc()));
    }
}

package org.jcodec.containers.mp4.boxes;

import java.nio.ByteBuffer;
import org.jcodec.common.NIOUtils;

public class LeafBox extends Box {
    private ByteBuffer data;

    public LeafBox(Header header) {
        super(header);
    }

    /* access modifiers changed from: protected */
    public void doWrite(ByteBuffer byteBuffer) {
        NIOUtils.write(byteBuffer, this.data);
    }

    public ByteBuffer getData() {
        return this.data.duplicate();
    }

    public void parse(ByteBuffer byteBuffer) {
        this.data = NIOUtils.read(byteBuffer, (int) this.header.getBodySize());
    }

    public LeafBox(Header header, ByteBuffer byteBuffer) {
        super(header);
        this.data = byteBuffer;
    }
}

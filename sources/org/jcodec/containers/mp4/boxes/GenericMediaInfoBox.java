package org.jcodec.containers.mp4.boxes;

import java.nio.ByteBuffer;

public class GenericMediaInfoBox extends FullBox {
    private short bOpColor;
    private short balance;
    private short gOpColor;
    private short graphicsMode;
    private short rOpColor;

    public GenericMediaInfoBox(short s, short s2, short s3, short s4, short s5) {
        this();
        this.graphicsMode = s;
        this.rOpColor = s2;
        this.gOpColor = s3;
        this.bOpColor = s4;
        this.balance = s5;
    }

    public static String fourcc() {
        return "gmin";
    }

    /* access modifiers changed from: protected */
    public void doWrite(ByteBuffer byteBuffer) {
        super.doWrite(byteBuffer);
        byteBuffer.putShort(this.graphicsMode);
        byteBuffer.putShort(this.rOpColor);
        byteBuffer.putShort(this.gOpColor);
        byteBuffer.putShort(this.bOpColor);
        byteBuffer.putShort(this.balance);
        byteBuffer.putShort(0);
    }

    public void parse(ByteBuffer byteBuffer) {
        super.parse(byteBuffer);
        this.graphicsMode = byteBuffer.getShort();
        this.rOpColor = byteBuffer.getShort();
        this.gOpColor = byteBuffer.getShort();
        this.bOpColor = byteBuffer.getShort();
        this.balance = byteBuffer.getShort();
        byteBuffer.getShort();
    }

    public GenericMediaInfoBox(Header header) {
        super(header);
    }

    public GenericMediaInfoBox() {
        this(new Header(fourcc()));
    }
}

package org.jcodec.api.specific;

import org.jcodec.api.FrameGrab.MediaInfo;
import org.jcodec.codecs.h264.H264Decoder;
import org.jcodec.codecs.h264.H264Utils;
import org.jcodec.codecs.h264.mp4.AvcCBox;
import org.jcodec.codecs.h264.p552io.model.Frame;
import org.jcodec.codecs.h264.p552io.model.SeqParameterSet;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Packet;
import org.jcodec.common.model.Picture;
import org.jcodec.common.model.Size;
import org.jcodec.containers.mp4.MP4Packet;
import org.jcodec.containers.mp4.boxes.Box;
import org.jcodec.containers.mp4.boxes.PixelAspectExt;
import org.jcodec.containers.mp4.boxes.SampleEntry;
import org.jcodec.containers.mp4.boxes.VideoSampleEntry;
import org.jcodec.containers.mp4.demuxer.AbstractMP4DemuxerTrack;

public class AVCMP4Adaptor implements ContainerAdaptor {
    private AvcCBox avcCBox;
    private int curENo;
    private H264Decoder decoder;
    private SampleEntry[] ses;
    private Size size;

    public AVCMP4Adaptor(SampleEntry[] sampleEntryArr) {
        this.ses = sampleEntryArr;
        this.curENo = -1;
        calcBufferSize();
    }

    private void calcBufferSize() {
        SampleEntry[] sampleEntryArr;
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (SampleEntry sampleEntry : this.ses) {
            if ("avc1".equals(sampleEntry.getFourcc())) {
                for (SeqParameterSet seqParameterSet : H264Utils.readSPS(H264Utils.parseAVCC((VideoSampleEntry) sampleEntry).getSpsList())) {
                    int i3 = seqParameterSet.pic_width_in_mbs_minus1 + 1;
                    if (i3 > i) {
                        i = i3;
                    }
                    int picHeightInMbs = H264Utils.getPicHeightInMbs(seqParameterSet);
                    if (picHeightInMbs > i2) {
                        i2 = picHeightInMbs;
                    }
                }
            }
        }
        this.size = new Size(i << 4, i2 << 4);
    }

    private void updateState(Packet packet) {
        int entryNo = ((MP4Packet) packet).getEntryNo();
        if (entryNo != this.curENo) {
            this.curENo = entryNo;
            this.avcCBox = H264Utils.parseAVCC((VideoSampleEntry) this.ses[this.curENo]);
            this.decoder = new H264Decoder();
            this.decoder.addSps(this.avcCBox.getSpsList());
            this.decoder.addPps(this.avcCBox.getPpsList());
        }
    }

    public int[][] allocatePicture() {
        return Picture.create(this.size.getWidth(), this.size.getHeight(), ColorSpace.YUV444).getData();
    }

    public boolean canSeek(Packet packet) {
        updateState(packet);
        return H264Utils.idrSlice(H264Utils.splitMOVPacket(packet.getData(), this.avcCBox));
    }

    public Picture decodeFrame(Packet packet, int[][] iArr) {
        updateState(packet);
        Frame decodeFrame = this.decoder.decodeFrame(H264Utils.splitMOVPacket(packet.getData(), this.avcCBox), iArr);
        PixelAspectExt pixelAspectExt = (PixelAspectExt) Box.findFirst(this.ses[this.curENo], PixelAspectExt.class, "pasp");
        return decodeFrame;
    }

    public MediaInfo getMediaInfo() {
        return new MediaInfo(this.size);
    }

    public AVCMP4Adaptor(AbstractMP4DemuxerTrack abstractMP4DemuxerTrack) {
        this(abstractMP4DemuxerTrack.getSampleEntries());
    }
}

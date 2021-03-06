package org.jcodec.codecs.mjpeg;

import androidx.recyclerview.widget.C1744m.C1750f;
import org.apache.http.C15470v;
import org.jcodec.common.p554io.VLC;
import org.jcodec.common.p554io.VLCBuilder;
import p205i.p471b.p472a.p473a.p474c.p475i.C13959t;

public class JpegConst {
    public static final int APP0 = 224;
    public static final int APP1 = 225;
    public static final int APP2 = 226;
    public static final int APP3 = 227;
    public static final int APP4 = 228;
    public static final int APP5 = 229;
    public static final int APP6 = 230;
    public static final int APP7 = 231;
    public static final int APP8 = 232;
    public static final int APP9 = 233;
    public static final int APPA = 234;
    public static final int APPB = 235;
    public static final int APPC = 236;
    public static final int APPD = 237;
    public static final int APPE = 238;
    public static final int APPF = 239;
    public static final VLC CAC_DEFAULT;
    public static final VLC CDC_DEFAULT;
    public static final int COM = 254;
    public static final int DHT = 196;
    public static final int DQT = 219;
    public static final int DRI = 221;
    public static final int EOI = 217;
    public static final int RST0 = 208;
    public static final int RST1 = 209;
    public static final int RST2 = 210;
    public static final int RST3 = 211;
    public static final int RST4 = 212;
    public static final int RST5 = 213;
    public static final int RST6 = 214;
    public static final int RST7 = 215;
    public static final int SOF0 = 192;
    public static final int SOF1 = 193;
    public static final int SOF2 = 194;
    public static final int SOF3 = 195;
    public static final int SOI = 216;
    public static final int SOS = 218;
    public static final VLC YAC_DEFAULT;
    public static final VLC YDC_DEFAULT;
    private static final String[] names = new String[256];
    public static final int[] naturalOrder = {0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63};

    static {
        VLCBuilder vLCBuilder = new VLCBuilder();
        String str = "00";
        int i = 0;
        vLCBuilder.set(0, str);
        vLCBuilder.set(1, "010");
        vLCBuilder.set(2, "011");
        String str2 = "100";
        vLCBuilder.set(3, str2);
        vLCBuilder.set(4, "101");
        vLCBuilder.set(5, "110");
        vLCBuilder.set(6, "1110");
        vLCBuilder.set(7, "11110");
        vLCBuilder.set(8, "111110");
        vLCBuilder.set(9, "1111110");
        vLCBuilder.set(10, "11111110");
        vLCBuilder.set(11, "111111110");
        YDC_DEFAULT = vLCBuilder.getVLC();
        VLCBuilder vLCBuilder2 = new VLCBuilder();
        vLCBuilder2.set(0, str);
        String str3 = "01";
        vLCBuilder2.set(1, str3);
        vLCBuilder2.set(2, "10");
        vLCBuilder2.set(3, "110");
        vLCBuilder2.set(4, "1110");
        vLCBuilder2.set(5, "11110");
        vLCBuilder2.set(6, "111110");
        vLCBuilder2.set(7, "1111110");
        vLCBuilder2.set(8, "11111110");
        vLCBuilder2.set(9, "111111110");
        vLCBuilder2.set(10, "1111111110");
        vLCBuilder2.set(11, "11111111110");
        CDC_DEFAULT = vLCBuilder2.getVLC();
        VLCBuilder vLCBuilder3 = new VLCBuilder();
        vLCBuilder3.set(0, "1010");
        vLCBuilder3.set(1, str);
        vLCBuilder3.set(2, str3);
        vLCBuilder3.set(3, str2);
        vLCBuilder3.set(4, "1011");
        vLCBuilder3.set(5, "11010");
        vLCBuilder3.set(6, "1111000");
        vLCBuilder3.set(7, "11111000");
        vLCBuilder3.set(8, "1111110110");
        vLCBuilder3.set(9, "1111111110000010");
        vLCBuilder3.set(10, "1111111110000011");
        vLCBuilder3.set(17, "1100");
        vLCBuilder3.set(18, "11011");
        vLCBuilder3.set(19, "1111001");
        vLCBuilder3.set(20, "111110110");
        vLCBuilder3.set(21, "11111110110");
        vLCBuilder3.set(22, "1111111110000100");
        vLCBuilder3.set(23, "1111111110000101");
        vLCBuilder3.set(24, "1111111110000110");
        vLCBuilder3.set(25, "1111111110000111");
        vLCBuilder3.set(26, "1111111110001000");
        vLCBuilder3.set(33, "11100");
        vLCBuilder3.set(34, "11111001");
        vLCBuilder3.set(35, "1111110111");
        vLCBuilder3.set(36, "111111110100");
        vLCBuilder3.set(37, "1111111110001001");
        vLCBuilder3.set(38, "1111111110001010");
        vLCBuilder3.set(39, "1111111110001011");
        vLCBuilder3.set(40, "1111111110001100");
        vLCBuilder3.set(41, "1111111110001101");
        vLCBuilder3.set(42, "1111111110001110");
        vLCBuilder3.set(49, "111010");
        vLCBuilder3.set(50, "111110111");
        vLCBuilder3.set(51, "111111110101");
        vLCBuilder3.set(52, "1111111110001111");
        vLCBuilder3.set(53, "1111111110010000");
        vLCBuilder3.set(54, "1111111110010001");
        vLCBuilder3.set(55, "1111111110010010");
        vLCBuilder3.set(56, "1111111110010011");
        vLCBuilder3.set(57, "1111111110010100");
        vLCBuilder3.set(58, "1111111110010101");
        vLCBuilder3.set(65, "111011");
        vLCBuilder3.set(66, "1111111000");
        vLCBuilder3.set(67, "1111111110010110");
        vLCBuilder3.set(68, "1111111110010111");
        vLCBuilder3.set(69, "1111111110011000");
        vLCBuilder3.set(70, "1111111110011001");
        vLCBuilder3.set(71, "1111111110011010");
        vLCBuilder3.set(72, "1111111110011011");
        vLCBuilder3.set(73, "1111111110011100");
        vLCBuilder3.set(74, "1111111110011101");
        vLCBuilder3.set(81, "1111010");
        vLCBuilder3.set(82, "11111110111");
        vLCBuilder3.set(83, "1111111110011110");
        vLCBuilder3.set(84, "1111111110011111");
        vLCBuilder3.set(85, "1111111110100000");
        vLCBuilder3.set(86, "1111111110100001");
        vLCBuilder3.set(87, "1111111110100010");
        vLCBuilder3.set(88, "1111111110100011");
        vLCBuilder3.set(89, "1111111110100100");
        vLCBuilder3.set(90, "1111111110100101");
        vLCBuilder3.set(97, "1111011");
        vLCBuilder3.set(98, "111111110110");
        vLCBuilder3.set(99, "1111111110100110");
        vLCBuilder3.set(100, "1111111110100111");
        vLCBuilder3.set(101, "1111111110101000");
        vLCBuilder3.set(102, "1111111110101001");
        vLCBuilder3.set(103, "1111111110101010");
        vLCBuilder3.set(104, "1111111110101011");
        vLCBuilder3.set(105, "1111111110101100");
        vLCBuilder3.set(106, "1111111110101101");
        vLCBuilder3.set(113, "11111010");
        vLCBuilder3.set(114, "111111110111");
        vLCBuilder3.set(115, "1111111110101110");
        vLCBuilder3.set(116, "1111111110101111");
        vLCBuilder3.set(117, "1111111110110000");
        vLCBuilder3.set(118, "1111111110110001");
        vLCBuilder3.set(119, "1111111110110010");
        vLCBuilder3.set(120, "1111111110110011");
        vLCBuilder3.set(121, "1111111110110100");
        vLCBuilder3.set(122, "1111111110110101");
        vLCBuilder3.set(C13959t.f40835R1, "111111000");
        vLCBuilder3.set(C13959t.f40839S1, "111111111000000");
        vLCBuilder3.set(C13959t.f40843T1, "1111111110110110");
        vLCBuilder3.set(C13959t.f40847U1, "1111111110110111");
        vLCBuilder3.set(C13959t.f40851V1, "1111111110111000");
        vLCBuilder3.set(C13959t.f40855W1, "1111111110111001");
        vLCBuilder3.set(C13959t.f40859X1, "1111111110111010");
        vLCBuilder3.set(C13959t.f40863Y1, "1111111110111011");
        vLCBuilder3.set(C13959t.f40867Z1, "1111111110111100");
        vLCBuilder3.set(C13959t.f40872a2, "1111111110111101");
        vLCBuilder3.set(C13959t.f40907h2, "111111001");
        vLCBuilder3.set(C13959t.f40912i2, "1111111110111110");
        vLCBuilder3.set(C13959t.f40917j2, "1111111110111111");
        vLCBuilder3.set(C13959t.f40921k2, "1111111111000000");
        vLCBuilder3.set(C13959t.f40925l2, "1111111111000001");
        vLCBuilder3.set(C13959t.f40929m2, "1111111111000010");
        vLCBuilder3.set(C13959t.f40933n2, "1111111111000011");
        vLCBuilder3.set(C13959t.f40937o2, "1111111111000100");
        vLCBuilder3.set(C13959t.f40941p2, "1111111111000101");
        vLCBuilder3.set(C13959t.f40945q2, "1111111111000110");
        vLCBuilder3.set(C13959t.f40973x2, "111111010");
        vLCBuilder3.set(C13959t.f40977y2, "1111111111000111");
        vLCBuilder3.set(C13959t.f40981z2, "1111111111001000");
        vLCBuilder3.set(C13959t.f40768A2, "1111111111001001");
        vLCBuilder3.set(C13959t.f40772B2, "1111111111001010");
        vLCBuilder3.set(C13959t.f40776C2, "1111111111001011");
        vLCBuilder3.set(C13959t.f40780D2, "1111111111001100");
        vLCBuilder3.set(C13959t.f40784E2, "1111111111001101");
        vLCBuilder3.set(C13959t.f40788F2, "1111111111001110");
        vLCBuilder3.set(C13959t.f40792G2, "1111111111001111");
        vLCBuilder3.set(C13959t.f40820N2, "1111111001");
        vLCBuilder3.set(178, "1111111111010000");
        vLCBuilder3.set(179, "1111111111010001");
        vLCBuilder3.set(180, "1111111111010010");
        vLCBuilder3.set(181, "1111111111010011");
        vLCBuilder3.set(C13959t.f40840S2, "1111111111010100");
        vLCBuilder3.set(183, "1111111111010101");
        vLCBuilder3.set(184, "1111111111010110");
        vLCBuilder3.set(C13959t.f40852V2, "1111111111010111");
        vLCBuilder3.set(C13959t.f40856W2, "1111111111011000");
        vLCBuilder3.set(193, "1111111010");
        vLCBuilder3.set(194, "1111111111011001");
        vLCBuilder3.set(195, "1111111111011010");
        vLCBuilder3.set(DHT, "1111111111011011");
        vLCBuilder3.set(C13959t.f40903g3, "1111111111011100");
        vLCBuilder3.set(C13959t.f40908h3, "1111111111011101");
        vLCBuilder3.set(C13959t.f40913i3, "1111111111011110");
        vLCBuilder3.set(200, "1111111111011111");
        vLCBuilder3.set(201, "1111111111100000");
        vLCBuilder3.set(C15470v.f44665f, "1111111111100001");
        vLCBuilder3.set(RST1, "11111111000");
        vLCBuilder3.set(RST2, "1111111111100010");
        vLCBuilder3.set(RST3, "1111111111100011");
        vLCBuilder3.set(RST4, "1111111111100100");
        vLCBuilder3.set(RST5, "1111111111100101");
        vLCBuilder3.set(RST6, "1111111111100110");
        vLCBuilder3.set(215, "1111111111100111");
        vLCBuilder3.set(216, "1111111111101000");
        vLCBuilder3.set(217, "1111111111101001");
        vLCBuilder3.set(218, "1111111111101010");
        vLCBuilder3.set(225, "1111111111101011");
        vLCBuilder3.set(APP2, "1111111111101100");
        vLCBuilder3.set(APP3, "1111111111101101");
        vLCBuilder3.set(APP4, "1111111111101110");
        vLCBuilder3.set(APP5, "1111111111101111");
        vLCBuilder3.set(APP6, "1111111111110000");
        vLCBuilder3.set(APP7, "1111111111110001");
        vLCBuilder3.set(APP8, "1111111111110010");
        vLCBuilder3.set(APP9, "1111111111110011");
        vLCBuilder3.set(APPA, "1111111111110100");
        vLCBuilder3.set(240, "11111111001");
        vLCBuilder3.set(241, "1111111111110101");
        vLCBuilder3.set(242, "1111111111110110");
        vLCBuilder3.set(243, "1111111111110111");
        vLCBuilder3.set(244, "1111111111111000");
        vLCBuilder3.set(245, "1111111111111001");
        vLCBuilder3.set(246, "1111111111111010");
        vLCBuilder3.set(247, "1111111111111011");
        vLCBuilder3.set(248, "1111111111111100");
        vLCBuilder3.set(249, "1111111111111101");
        vLCBuilder3.set(C1750f.f6813c, "1111111111111110");
        YAC_DEFAULT = vLCBuilder3.getVLC();
        VLCBuilder vLCBuilder4 = new VLCBuilder();
        vLCBuilder4.set(0, str);
        vLCBuilder4.set(1, str3);
        vLCBuilder4.set(2, str2);
        vLCBuilder4.set(3, "1010");
        vLCBuilder4.set(4, "11000");
        vLCBuilder4.set(5, "11001");
        vLCBuilder4.set(6, "111000");
        vLCBuilder4.set(7, "1111000");
        vLCBuilder4.set(8, "111110100");
        vLCBuilder4.set(9, "1111110110");
        vLCBuilder4.set(10, "111111110100");
        vLCBuilder4.set(17, "1011");
        vLCBuilder4.set(18, "111001");
        vLCBuilder4.set(19, "11110110");
        vLCBuilder4.set(20, "111110101");
        vLCBuilder4.set(21, "11111110110");
        vLCBuilder4.set(22, "111111110101");
        vLCBuilder4.set(23, "1111111110001000");
        vLCBuilder4.set(24, "1111111110001001");
        vLCBuilder4.set(25, "1111111110001010");
        vLCBuilder4.set(26, "1111111110001011");
        vLCBuilder4.set(33, "11010");
        vLCBuilder4.set(34, "11110111");
        vLCBuilder4.set(35, "1111110111");
        vLCBuilder4.set(36, "111111110110");
        vLCBuilder4.set(37, "111111111000010");
        vLCBuilder4.set(38, "1111111110001100");
        vLCBuilder4.set(39, "1111111110001101");
        vLCBuilder4.set(40, "1111111110001110");
        vLCBuilder4.set(41, "1111111110001111");
        vLCBuilder4.set(42, "1111111110010000");
        vLCBuilder4.set(49, "11011");
        vLCBuilder4.set(50, "11111000");
        vLCBuilder4.set(51, "1111111000");
        vLCBuilder4.set(52, "111111110111");
        vLCBuilder4.set(53, "1111111110010001");
        vLCBuilder4.set(54, "1111111110010010");
        vLCBuilder4.set(55, "1111111110010011");
        vLCBuilder4.set(56, "1111111110010100");
        vLCBuilder4.set(57, "1111111110010101");
        vLCBuilder4.set(58, "1111111110010110");
        vLCBuilder4.set(65, "111010");
        vLCBuilder4.set(66, "111110110");
        vLCBuilder4.set(67, "1111111110010111");
        vLCBuilder4.set(68, "1111111110011000");
        vLCBuilder4.set(69, "1111111110011001");
        vLCBuilder4.set(70, "1111111110011010");
        vLCBuilder4.set(71, "1111111110011011");
        vLCBuilder4.set(72, "1111111110011100");
        vLCBuilder4.set(73, "1111111110011101");
        vLCBuilder4.set(74, "1111111110011110");
        vLCBuilder4.set(81, "111011");
        vLCBuilder4.set(82, "1111111001");
        vLCBuilder4.set(83, "1111111110011111");
        vLCBuilder4.set(84, "1111111110100000");
        vLCBuilder4.set(85, "1111111110100001");
        vLCBuilder4.set(86, "1111111110100010");
        vLCBuilder4.set(87, "1111111110100011");
        vLCBuilder4.set(88, "1111111110100100");
        vLCBuilder4.set(89, "1111111110100101");
        vLCBuilder4.set(90, "1111111110100110");
        vLCBuilder4.set(97, "1111001");
        vLCBuilder4.set(98, "11111110111");
        vLCBuilder4.set(99, "1111111110100111");
        vLCBuilder4.set(100, "1111111110101000");
        vLCBuilder4.set(101, "1111111110101001");
        vLCBuilder4.set(102, "1111111110101010");
        vLCBuilder4.set(103, "1111111110101011");
        vLCBuilder4.set(104, "1111111110101100");
        vLCBuilder4.set(105, "1111111110101101");
        vLCBuilder4.set(106, "1111111110101110");
        vLCBuilder4.set(113, "1111010");
        vLCBuilder4.set(114, "11111111000");
        vLCBuilder4.set(115, "1111111110101111");
        vLCBuilder4.set(116, "1111111110110000");
        vLCBuilder4.set(117, "1111111110110001");
        vLCBuilder4.set(118, "1111111110110010");
        vLCBuilder4.set(119, "1111111110110011");
        vLCBuilder4.set(120, "1111111110110100");
        vLCBuilder4.set(121, "1111111110110101");
        vLCBuilder4.set(122, "1111111110110110");
        vLCBuilder4.set(C13959t.f40835R1, "11111001");
        vLCBuilder4.set(C13959t.f40839S1, "1111111110110111");
        vLCBuilder4.set(C13959t.f40843T1, "1111111110111000");
        vLCBuilder4.set(C13959t.f40847U1, "1111111110111001");
        vLCBuilder4.set(C13959t.f40851V1, "1111111110111010");
        vLCBuilder4.set(C13959t.f40855W1, "1111111110111011");
        vLCBuilder4.set(C13959t.f40859X1, "1111111110111100");
        vLCBuilder4.set(C13959t.f40863Y1, "1111111110111101");
        vLCBuilder4.set(C13959t.f40867Z1, "1111111110111110");
        vLCBuilder4.set(C13959t.f40872a2, "1111111110111111");
        vLCBuilder4.set(C13959t.f40907h2, "111110111");
        vLCBuilder4.set(C13959t.f40912i2, "1111111111000000");
        vLCBuilder4.set(C13959t.f40917j2, "1111111111000001");
        vLCBuilder4.set(C13959t.f40921k2, "1111111111000010");
        vLCBuilder4.set(C13959t.f40925l2, "1111111111000011");
        vLCBuilder4.set(C13959t.f40929m2, "1111111111000100");
        vLCBuilder4.set(C13959t.f40933n2, "1111111111000101");
        vLCBuilder4.set(C13959t.f40937o2, "1111111111000110");
        vLCBuilder4.set(C13959t.f40941p2, "1111111111000111");
        vLCBuilder4.set(C13959t.f40945q2, "1111111111001000");
        vLCBuilder4.set(C13959t.f40973x2, "111111000");
        vLCBuilder4.set(C13959t.f40977y2, "1111111111001001");
        vLCBuilder4.set(C13959t.f40981z2, "1111111111001010");
        vLCBuilder4.set(C13959t.f40768A2, "1111111111001011");
        vLCBuilder4.set(C13959t.f40772B2, "1111111111001100");
        vLCBuilder4.set(C13959t.f40776C2, "1111111111001101");
        vLCBuilder4.set(C13959t.f40780D2, "1111111111001110");
        vLCBuilder4.set(C13959t.f40784E2, "1111111111001111");
        vLCBuilder4.set(C13959t.f40788F2, "1111111111010000");
        vLCBuilder4.set(C13959t.f40792G2, "1111111111010001");
        vLCBuilder4.set(C13959t.f40820N2, "111111001");
        vLCBuilder4.set(178, "1111111111010010");
        vLCBuilder4.set(179, "1111111111010011");
        vLCBuilder4.set(180, "1111111111010100");
        vLCBuilder4.set(181, "1111111111010101");
        vLCBuilder4.set(C13959t.f40840S2, "1111111111010110");
        vLCBuilder4.set(183, "1111111111010111");
        vLCBuilder4.set(184, "1111111111011000");
        vLCBuilder4.set(C13959t.f40852V2, "1111111111011001");
        vLCBuilder4.set(C13959t.f40856W2, "1111111111011010");
        vLCBuilder4.set(193, "111111010");
        vLCBuilder4.set(194, "1111111111011011");
        vLCBuilder4.set(195, "1111111111011100");
        vLCBuilder4.set(DHT, "1111111111011101");
        vLCBuilder4.set(C13959t.f40903g3, "1111111111011110");
        vLCBuilder4.set(C13959t.f40908h3, "1111111111011111");
        vLCBuilder4.set(C13959t.f40913i3, "1111111111100000");
        vLCBuilder4.set(200, "1111111111100001");
        vLCBuilder4.set(201, "1111111111100010");
        vLCBuilder4.set(C15470v.f44665f, "1111111111100011");
        vLCBuilder4.set(RST1, "11111111001");
        vLCBuilder4.set(RST2, "1111111111100100");
        vLCBuilder4.set(RST3, "1111111111100101");
        vLCBuilder4.set(RST4, "1111111111100110");
        vLCBuilder4.set(RST5, "1111111111100111");
        vLCBuilder4.set(RST6, "1111111111101000");
        vLCBuilder4.set(215, "1111111111101001");
        vLCBuilder4.set(216, "1111111111101010");
        vLCBuilder4.set(217, "1111111111101011");
        vLCBuilder4.set(218, "1111111111101100");
        vLCBuilder4.set(225, "11111111100000");
        vLCBuilder4.set(APP2, "1111111111101101");
        vLCBuilder4.set(APP3, "1111111111101110");
        vLCBuilder4.set(APP4, "1111111111101111");
        vLCBuilder4.set(APP5, "1111111111110000");
        vLCBuilder4.set(APP6, "1111111111110001");
        vLCBuilder4.set(APP7, "1111111111110010");
        vLCBuilder4.set(APP8, "1111111111110011");
        vLCBuilder4.set(APP9, "1111111111110100");
        vLCBuilder4.set(APPA, "1111111111110101");
        vLCBuilder4.set(240, "1111111010");
        vLCBuilder4.set(241, "111111111000011");
        vLCBuilder4.set(242, "1111111111110110");
        vLCBuilder4.set(243, "1111111111110111");
        vLCBuilder4.set(244, "1111111111111000");
        vLCBuilder4.set(245, "1111111111111001");
        vLCBuilder4.set(246, "1111111111111010");
        vLCBuilder4.set(247, "1111111111111011");
        vLCBuilder4.set(248, "1111111111111100");
        vLCBuilder4.set(249, "1111111111111101");
        vLCBuilder4.set(C1750f.f6813c, "1111111111111110");
        CAC_DEFAULT = vLCBuilder4.getVLC();
        while (true) {
            String[] strArr = names;
            if (i < strArr.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("(0x");
                sb.append(Integer.toHexString(i));
                sb.append(")");
                strArr[i] = sb.toString();
                i++;
            } else {
                strArr[192] = "SOF0";
                strArr[193] = "SOF1";
                strArr[194] = "SOF2";
                strArr[195] = "SOF3";
                strArr[196] = "DHT";
                strArr[219] = "DQT";
                strArr[218] = "SOS";
                strArr[217] = "EOI";
                strArr[216] = "SOI";
                strArr[224] = "APP0";
                strArr[225] = "APP1";
                strArr[226] = "APP2";
                strArr[227] = "APP3";
                strArr[228] = "APP4";
                strArr[229] = "APP5";
                strArr[230] = "APP6";
                strArr[231] = "APP7";
                strArr[232] = "APP8";
                strArr[233] = "APP9";
                strArr[234] = "APPA";
                strArr[235] = "APPB";
                strArr[236] = "APPC";
                strArr[237] = "APPD";
                strArr[238] = "APPE";
                strArr[239] = "APPF";
                strArr[208] = "RST0";
                strArr[209] = "RST1";
                strArr[210] = "RST2";
                strArr[211] = "RST3";
                strArr[212] = "RST4";
                strArr[213] = "RST5";
                strArr[214] = "RST6";
                strArr[215] = "RST7";
                strArr[221] = "DRI";
                return;
            }
        }
    }

    public static String toString(int i) {
        return names[i];
    }
}

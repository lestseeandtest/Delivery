package com.airbnb.lottie.model;

import androidx.annotation.C0207n0;
import androidx.annotation.C0207n0.C0208a;
import com.airbnb.lottie.model.content.ShapeGroup;
import java.util.List;

@C0207n0({C0208a.LIBRARY})
public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final List<ShapeGroup> shapes;
    private final double size;
    private final String style;
    private final double width;

    public FontCharacter(List<ShapeGroup> list, char c, double d, double d2, String str, String str2) {
        this.shapes = list;
        this.character = c;
        this.size = d;
        this.width = d2;
        this.style = str;
        this.fontFamily = str2;
    }

    public static int hashFor(char c, String str, String str2) {
        return ((((0 + c) * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List<ShapeGroup> getShapes() {
        return this.shapes;
    }

    /* access modifiers changed from: 0000 */
    public double getSize() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public String getStyle() {
        return this.style;
    }

    public double getWidth() {
        return this.width;
    }

    public int hashCode() {
        return hashFor(this.character, this.fontFamily, this.style);
    }
}

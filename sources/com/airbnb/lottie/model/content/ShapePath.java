package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ShapeContent;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class ShapePath implements ContentModel {
    private final boolean hidden;
    private final int index;
    private final String name;
    private final AnimatableShapeValue shapePath;

    public ShapePath(String str, int i, AnimatableShapeValue animatableShapeValue, boolean z) {
        this.name = str;
        this.index = i;
        this.shapePath = animatableShapeValue;
        this.hidden = z;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableShapeValue getShapePath() {
        return this.shapePath;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public Content toContent(LottieDrawable lottieDrawable, BaseLayer baseLayer) {
        return new ShapeContent(lottieDrawable, baseLayer, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShapePath{name=");
        sb.append(this.name);
        sb.append(", index=");
        sb.append(this.index);
        sb.append('}');
        return sb.toString();
    }
}

package com.crashlytics.android.answers;

public class InviteEvent extends PredefinedEvent<InviteEvent> {
    static final String METHOD_ATTRIBUTE = "method";
    static final String TYPE = "invite";

    /* access modifiers changed from: 0000 */
    public String getPredefinedType() {
        return "invite";
    }

    public InviteEvent putMethod(String str) {
        this.predefinedAttributes.put("method", str);
        return this;
    }
}
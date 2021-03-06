package org.jivesoftware.smackx.offline.packet;

import java.io.IOException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OfflineMessageInfo implements ExtensionElement {
    private String node = null;

    public static class Provider extends ExtensionElementProvider<OfflineMessageInfo> {
        public OfflineMessageInfo parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
            OfflineMessageInfo offlineMessageInfo = new OfflineMessageInfo();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        offlineMessageInfo.setNode(xmlPullParser.getAttributeValue("", "node"));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals("offline")) {
                    z = true;
                }
            }
            return offlineMessageInfo;
        }
    }

    public String getElementName() {
        return "offline";
    }

    public String getNamespace() {
        return OfflineMessageRequest.NAMESPACE;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (getNode() != null) {
            sb.append("<item node=\"");
            sb.append(getNode());
            sb.append("\"/>");
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}

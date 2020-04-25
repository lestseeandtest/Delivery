package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.amp.packet.AMPExtension;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class AMPManager {
    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(XMPPConnection xMPPConnection) {
                AMPManager.setServiceEnabled(xMPPConnection, true);
            }
        });
    }

    public static boolean isActionSupported(XMPPConnection xMPPConnection, Action action) throws NoResponseException, XMPPErrorException, NotConnectedException {
        StringBuilder sb = new StringBuilder();
        sb.append("http://jabber.org/protocol/amp?action=");
        sb.append(action.toString());
        return isFeatureSupportedByServer(xMPPConnection, sb.toString(), AMPExtension.NAMESPACE);
    }

    public static boolean isConditionSupported(XMPPConnection xMPPConnection, String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        StringBuilder sb = new StringBuilder();
        sb.append("http://jabber.org/protocol/amp?condition=");
        sb.append(str);
        return isFeatureSupportedByServer(xMPPConnection, sb.toString(), AMPExtension.NAMESPACE);
    }

    private static boolean isFeatureSupportedByServer(XMPPConnection xMPPConnection, String str, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).supportsFeature(str2, str);
    }

    public static boolean isServiceEnabled(XMPPConnection xMPPConnection) {
        xMPPConnection.getServiceName();
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).includesFeature(AMPExtension.NAMESPACE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void setServiceEnabled(org.jivesoftware.smack.XMPPConnection r2, boolean r3) {
        /*
            java.lang.Class<org.jivesoftware.smackx.amp.AMPManager> r0 = org.jivesoftware.smackx.amp.AMPManager.class
            monitor-enter(r0)
            boolean r1 = isServiceEnabled(r2)     // Catch:{ all -> 0x0022 }
            if (r1 != r3) goto L_0x000b
            monitor-exit(r0)
            return
        L_0x000b:
            if (r3 == 0) goto L_0x0017
            org.jivesoftware.smackx.disco.ServiceDiscoveryManager r2 = org.jivesoftware.smackx.disco.ServiceDiscoveryManager.getInstanceFor(r2)     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "http://jabber.org/protocol/amp"
            r2.addFeature(r3)     // Catch:{ all -> 0x0022 }
            goto L_0x0020
        L_0x0017:
            org.jivesoftware.smackx.disco.ServiceDiscoveryManager r2 = org.jivesoftware.smackx.disco.ServiceDiscoveryManager.getInstanceFor(r2)     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "http://jabber.org/protocol/amp"
            r2.removeFeature(r3)     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r0)
            return
        L_0x0022:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.amp.AMPManager.setServiceEnabled(org.jivesoftware.smack.XMPPConnection, boolean):void");
    }
}
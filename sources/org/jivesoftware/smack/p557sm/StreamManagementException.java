package org.jivesoftware.smack.p557sm;

import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Stanza;

/* renamed from: org.jivesoftware.smack.sm.StreamManagementException */
public abstract class StreamManagementException extends SmackException {
    private static final long serialVersionUID = 3767590115788821101L;

    /* renamed from: org.jivesoftware.smack.sm.StreamManagementException$StreamIdDoesNotMatchException */
    public static class StreamIdDoesNotMatchException extends StreamManagementException {
        private static final long serialVersionUID = 1191073341336559621L;

        public StreamIdDoesNotMatchException(String str, String str2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Stream IDs do not match. Expected '");
            sb.append(str);
            sb.append("', but got '");
            sb.append(str2);
            sb.append("'");
            super(sb.toString());
        }
    }

    /* renamed from: org.jivesoftware.smack.sm.StreamManagementException$StreamManagementCounterError */
    public static class StreamManagementCounterError extends StreamManagementException {
        private static final long serialVersionUID = 1;
        private final long ackedStanzaCount;
        private final List<Stanza> ackedStanzas;
        private final long handledCount;
        private final int outstandingStanzasCount;
        private final long previousServerHandledCount;

        public StreamManagementCounterError(long j, long j2, long j3, List<Stanza> list) {
            StringBuilder sb = new StringBuilder();
            sb.append("There was an error regarding the Stream Mangement counters. Server reported ");
            sb.append(j);
            sb.append(" handled stanzas, which means that the ");
            sb.append(j3);
            sb.append(" recently send stanzas by client are now acked by the server. But Smack had only ");
            sb.append(list.size());
            sb.append(" to acknowledge. The stanza id of the last acked outstanding stanza is ");
            sb.append(list.isEmpty() ? "<no acked stanzas>" : ((Stanza) list.get(list.size() - 1)).getStanzaId());
            super(sb.toString());
            this.handledCount = j;
            this.previousServerHandledCount = j2;
            this.ackedStanzaCount = j3;
            this.outstandingStanzasCount = list.size();
            this.ackedStanzas = Collections.unmodifiableList(list);
        }

        public long getAckedStanzaCount() {
            return this.ackedStanzaCount;
        }

        public List<Stanza> getAckedStanzas() {
            return this.ackedStanzas;
        }

        public long getHandledCount() {
            return this.handledCount;
        }

        public int getOutstandingStanzasCount() {
            return this.outstandingStanzasCount;
        }

        public long getPreviousServerHandledCount() {
            return this.previousServerHandledCount;
        }
    }

    /* renamed from: org.jivesoftware.smack.sm.StreamManagementException$StreamManagementNotEnabledException */
    public static class StreamManagementNotEnabledException extends StreamManagementException {
        private static final long serialVersionUID = 2624821584352571307L;
    }

    public StreamManagementException() {
    }

    public StreamManagementException(String str) {
        super(str);
    }
}

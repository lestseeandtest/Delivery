package org.jivesoftware.smackx.time.packet;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.C15617IQ;
import org.jivesoftware.smack.packet.C15617IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.C15617IQ.Type;
import p205i.p486d.p487a.C14051a;

public class Time extends C15617IQ {
    public static final String ELEMENT = "time";
    private static final Logger LOGGER = Logger.getLogger(Time.class.getName());
    public static final String NAMESPACE = "urn:xmpp:time";
    private String tzo;
    private String utc;

    public Time() {
        super(ELEMENT, NAMESPACE);
        setType(Type.get);
    }

    public static Time createResponse(C15617IQ iq) {
        Time time = new Time(Calendar.getInstance());
        time.setType(Type.result);
        time.setTo(iq.getFrom());
        return time;
    }

    /* access modifiers changed from: protected */
    public IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        if (this.utc != null) {
            iQChildElementXmlStringBuilder.rightAngleBracket();
            iQChildElementXmlStringBuilder.append((CharSequence) "<utc>").append((CharSequence) this.utc).append((CharSequence) "</utc>");
            iQChildElementXmlStringBuilder.append((CharSequence) "<tzo>").append((CharSequence) this.tzo).append((CharSequence) "</tzo>");
        } else {
            iQChildElementXmlStringBuilder.setEmptyElement();
        }
        return iQChildElementXmlStringBuilder;
    }

    public Date getTime() {
        String str = this.utc;
        Date date = null;
        if (str == null) {
            return null;
        }
        try {
            date = C14051a.m60631e(str);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting local time", e);
        }
        return date;
    }

    public String getTzo() {
        return this.tzo;
    }

    public String getUtc() {
        return this.utc;
    }

    public void setTime(Date date) {
    }

    public void setTzo(String str) {
        this.tzo = str;
    }

    public void setUtc(String str) {
        this.utc = str;
    }

    public Time(Calendar calendar) {
        super(ELEMENT, NAMESPACE);
        this.tzo = C14051a.m60623a(calendar.getTimeZone());
        this.utc = C14051a.m60622a(calendar.getTime());
    }
}

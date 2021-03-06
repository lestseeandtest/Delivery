package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;

public class AndFilter extends AbstractListFilter implements StanzaFilter {
    public AndFilter() {
    }

    public boolean accept(Stanza stanza) {
        for (StanzaFilter accept : this.filters) {
            if (!accept.accept(stanza)) {
                return false;
            }
        }
        return true;
    }

    public AndFilter(StanzaFilter... stanzaFilterArr) {
        super(stanzaFilterArr);
    }
}

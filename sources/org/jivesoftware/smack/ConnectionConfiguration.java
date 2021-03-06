package org.jivesoftware.smack;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.proxy.ProxyInfo;

public abstract class ConnectionConfiguration {
    protected final boolean allowNullOrEmptyUsername;
    private final CallbackHandler callbackHandler;
    private final SSLContext customSSLContext;
    private final boolean debuggerEnabled;
    private final String[] enabledSSLCiphers;
    private final String[] enabledSSLProtocols;
    protected final String host;
    private final HostnameVerifier hostnameVerifier;
    private final String keystorePath;
    private final String keystoreType;
    private final boolean legacySessionDisabled;
    private final String password;
    private final String pkcs11Library;
    protected final int port;
    protected final ProxyInfo proxy;
    private final String resource;
    private final SecurityMode securityMode;
    private final boolean sendPresence;
    protected final String serviceName;
    private final SocketFactory socketFactory;
    private final CharSequence username;

    public static abstract class Builder<B extends Builder<B, C>, C extends ConnectionConfiguration> {
        /* access modifiers changed from: private */
        public boolean allowEmptyOrNullUsername = false;
        /* access modifiers changed from: private */
        public CallbackHandler callbackHandler;
        /* access modifiers changed from: private */
        public SSLContext customSSLContext;
        /* access modifiers changed from: private */
        public boolean debuggerEnabled = SmackConfiguration.DEBUG;
        /* access modifiers changed from: private */
        public String[] enabledSSLCiphers;
        /* access modifiers changed from: private */
        public String[] enabledSSLProtocols;
        /* access modifiers changed from: private */
        public String host;
        /* access modifiers changed from: private */
        public HostnameVerifier hostnameVerifier;
        /* access modifiers changed from: private */
        public String keystorePath = System.getProperty("javax.net.ssl.keyStore");
        /* access modifiers changed from: private */
        public String keystoreType = "jks";
        /* access modifiers changed from: private */
        public boolean legacySessionDisabled = false;
        /* access modifiers changed from: private */
        public String password;
        /* access modifiers changed from: private */
        public String pkcs11Library = "pkcs11.config";
        /* access modifiers changed from: private */
        public int port = 5222;
        /* access modifiers changed from: private */
        public ProxyInfo proxy;
        /* access modifiers changed from: private */
        public String resource = "Smack";
        /* access modifiers changed from: private */
        public SecurityMode securityMode = SecurityMode.ifpossible;
        /* access modifiers changed from: private */
        public boolean sendPresence = true;
        /* access modifiers changed from: private */
        public String serviceName;
        /* access modifiers changed from: private */
        public SocketFactory socketFactory;
        /* access modifiers changed from: private */
        public CharSequence username;

        protected Builder() {
        }

        public B allowEmptyOrNullUsernames() {
            this.allowEmptyOrNullUsername = true;
            return getThis();
        }

        public abstract C build();

        /* access modifiers changed from: protected */
        public abstract B getThis();

        public B setCallbackHandler(CallbackHandler callbackHandler2) {
            this.callbackHandler = callbackHandler2;
            return getThis();
        }

        public B setCustomSSLContext(SSLContext sSLContext) {
            this.customSSLContext = sSLContext;
            return getThis();
        }

        public B setDebuggerEnabled(boolean z) {
            this.debuggerEnabled = z;
            return getThis();
        }

        public B setEnabledSSLCiphers(String[] strArr) {
            this.enabledSSLCiphers = strArr;
            return getThis();
        }

        public B setEnabledSSLProtocols(String[] strArr) {
            this.enabledSSLProtocols = strArr;
            return getThis();
        }

        public B setHost(String str) {
            this.host = str;
            return getThis();
        }

        public B setHostnameVerifier(HostnameVerifier hostnameVerifier2) {
            this.hostnameVerifier = hostnameVerifier2;
            return getThis();
        }

        public B setKeystorePath(String str) {
            this.keystorePath = str;
            return getThis();
        }

        public B setKeystoreType(String str) {
            this.keystoreType = str;
            return getThis();
        }

        @Deprecated
        public B setLegacySessionDisabled(boolean z) {
            this.legacySessionDisabled = z;
            return getThis();
        }

        public B setPKCS11Library(String str) {
            this.pkcs11Library = str;
            return getThis();
        }

        public B setPort(int i) {
            this.port = i;
            return getThis();
        }

        public B setProxyInfo(ProxyInfo proxyInfo) {
            this.proxy = proxyInfo;
            return getThis();
        }

        public B setResource(String str) {
            this.resource = str;
            return getThis();
        }

        public B setSecurityMode(SecurityMode securityMode2) {
            this.securityMode = securityMode2;
            return getThis();
        }

        public B setSendPresence(boolean z) {
            this.sendPresence = z;
            return getThis();
        }

        public B setServiceName(String str) {
            this.serviceName = str;
            return getThis();
        }

        public B setSocketFactory(SocketFactory socketFactory2) {
            this.socketFactory = socketFactory2;
            return getThis();
        }

        public B setUsernameAndPassword(CharSequence charSequence, String str) {
            this.username = charSequence;
            this.password = str;
            return getThis();
        }
    }

    public enum SecurityMode {
        required,
        ifpossible,
        disabled
    }

    static {
        SmackConfiguration.getVersion();
    }

    protected ConnectionConfiguration(Builder<?, ?> builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.callbackHandler = builder.callbackHandler;
        this.resource = builder.resource;
        this.serviceName = builder.serviceName;
        if (this.serviceName != null) {
            this.host = builder.host;
            this.port = builder.port;
            this.proxy = builder.proxy;
            if (this.proxy == null) {
                this.socketFactory = builder.socketFactory;
            } else if (builder.socketFactory == null) {
                this.socketFactory = this.proxy.getSocketFactory();
            } else {
                throw new IllegalArgumentException("Can not use proxy together with custom socket factory");
            }
            this.securityMode = builder.securityMode;
            this.keystoreType = builder.keystoreType;
            this.keystorePath = builder.keystorePath;
            this.pkcs11Library = builder.pkcs11Library;
            this.customSSLContext = builder.customSSLContext;
            this.enabledSSLProtocols = builder.enabledSSLProtocols;
            this.enabledSSLCiphers = builder.enabledSSLCiphers;
            this.hostnameVerifier = builder.hostnameVerifier;
            this.sendPresence = builder.sendPresence;
            this.legacySessionDisabled = builder.legacySessionDisabled;
            this.debuggerEnabled = builder.debuggerEnabled;
            this.allowNullOrEmptyUsername = builder.allowEmptyOrNullUsername;
            return;
        }
        throw new IllegalArgumentException("Must provide XMPP service name");
    }

    public CallbackHandler getCallbackHandler() {
        return this.callbackHandler;
    }

    public SSLContext getCustomSSLContext() {
        return this.customSSLContext;
    }

    public String[] getEnabledSSLCiphers() {
        return this.enabledSSLCiphers;
    }

    public String[] getEnabledSSLProtocols() {
        return this.enabledSSLProtocols;
    }

    public HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier2 = this.hostnameVerifier;
        if (hostnameVerifier2 != null) {
            return hostnameVerifier2;
        }
        return SmackConfiguration.getDefaultHostnameVerifier();
    }

    public String getKeystorePath() {
        return this.keystorePath;
    }

    public String getKeystoreType() {
        return this.keystoreType;
    }

    public String getPKCS11Library() {
        return this.pkcs11Library;
    }

    public String getPassword() {
        return this.password;
    }

    public String getResource() {
        return this.resource;
    }

    public SecurityMode getSecurityMode() {
        return this.securityMode;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public CharSequence getUsername() {
        return this.username;
    }

    public boolean isCompressionEnabled() {
        return false;
    }

    public boolean isDebuggerEnabled() {
        return this.debuggerEnabled;
    }

    @Deprecated
    public boolean isLegacySessionDisabled() {
        return this.legacySessionDisabled;
    }

    public boolean isSendPresence() {
        return this.sendPresence;
    }
}

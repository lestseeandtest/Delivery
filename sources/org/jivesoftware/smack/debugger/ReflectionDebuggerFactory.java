package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;

public class ReflectionDebuggerFactory implements SmackDebuggerFactory {
    private static final String DEBUGGER_CLASS_PROPERTY_NAME = "smack.debuggerClass";
    private static final String[] DEFAULT_DEBUGGERS = {"org.jivesoftware.smackx.debugger.EnhancedDebugger", "org.jivesoftware.smackx.debugger.android.AndroidDebugger", "org.jivesoftware.smack.debugger.ConsoleDebugger", "org.jivesoftware.smack.debugger.LiteDebugger", "org.jivesoftware.smack.debugger.JulDebugger"};
    private static final Logger LOGGER = Logger.getLogger(ReflectionDebuggerFactory.class.getName());

    private static String getCustomDebuggerClassName() {
        try {
            return System.getProperty(DEBUGGER_CLASS_PROPERTY_NAME);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Class<SmackDebugger> getDebuggerClass() {
        String customDebuggerClassName = getCustomDebuggerClassName();
        if (customDebuggerClassName == null) {
            return getOneOfDefaultDebuggerClasses();
        }
        try {
            return Class.forName(customDebuggerClassName);
        } catch (Exception e) {
            Logger logger = LOGGER;
            Level level = Level.WARNING;
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to instantiate debugger class ");
            sb.append(customDebuggerClassName);
            logger.log(level, sb.toString(), e);
            return null;
        }
    }

    private static Class<SmackDebugger> getOneOfDefaultDebuggerClasses() {
        String[] strArr;
        for (String str : DEFAULT_DEBUGGERS) {
            if (!SmackConfiguration.isDisabledSmackClass(str)) {
                try {
                    return Class.forName(str);
                } catch (ClassNotFoundException unused) {
                    Logger logger = LOGGER;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Did not find debugger class '");
                    sb.append(str);
                    sb.append("'");
                    logger.fine(sb.toString());
                } catch (ClassCastException unused2) {
                    LOGGER.warning("Found debugger class that does not appears to implement SmackDebugger interface");
                } catch (Exception unused3) {
                    LOGGER.warning("Unable to instantiate either Smack debugger class");
                }
            }
        }
        return null;
    }

    public static void setDebuggerClass(Class<? extends SmackDebugger> cls) {
        String str = DEBUGGER_CLASS_PROPERTY_NAME;
        if (cls == null) {
            System.clearProperty(str);
        } else {
            System.setProperty(str, cls.getCanonicalName());
        }
    }

    public SmackDebugger create(XMPPConnection xMPPConnection, Writer writer, Reader reader) throws IllegalArgumentException {
        Class debuggerClass = getDebuggerClass();
        if (debuggerClass == null) {
            return null;
        }
        try {
            return (SmackDebugger) debuggerClass.getConstructor(new Class[]{XMPPConnection.class, Writer.class, Reader.class}).newInstance(new Object[]{xMPPConnection, writer, reader});
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't initialize the configured debugger!", e);
        }
    }
}

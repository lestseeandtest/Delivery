package kotlin.p214b1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.p218u0.C6122a;
import kotlin.p218u0.C6123b;
import kotlin.p218u0.C6124c;
import kotlin.p218u0.C6126e;
import kotlin.p218u0.C6127f;

@C6127f(allowedTargets = {C6123b.FUNCTION, C6123b.PROPERTY_GETTER, C6123b.PROPERTY_SETTER, C6123b.FILE})
@Documented
@Target({ElementType.METHOD})
@C6124c
@C6126e(C6122a.BINARY)
@Retention(RetentionPolicy.CLASS)
/* renamed from: kotlin.b1.e */
/* compiled from: JvmPlatformAnnotations.kt */
public @interface C6055e {
    String name();
}

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.location.b0 */
public final class C5118b0 implements Creator<LocationSettingsStates> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int b = SafeParcelReader.m18926b(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        while (parcel.dataPosition() < b) {
            int a = SafeParcelReader.m18920a(parcel);
            switch (SafeParcelReader.m18919a(a)) {
                case 1:
                    z = SafeParcelReader.m18903K(parcel, a);
                    break;
                case 2:
                    z2 = SafeParcelReader.m18903K(parcel, a);
                    break;
                case 3:
                    z3 = SafeParcelReader.m18903K(parcel, a);
                    break;
                case 4:
                    z4 = SafeParcelReader.m18903K(parcel, a);
                    break;
                case 5:
                    z5 = SafeParcelReader.m18903K(parcel, a);
                    break;
                case 6:
                    z6 = SafeParcelReader.m18903K(parcel, a);
                    break;
                default:
                    SafeParcelReader.m18918Z(parcel, a);
                    break;
            }
        }
        SafeParcelReader.m18902J(parcel, b);
        LocationSettingsStates locationSettingsStates = new LocationSettingsStates(z, z2, z3, z4, z5, z6);
        return locationSettingsStates;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationSettingsStates[i];
    }
}
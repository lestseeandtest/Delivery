package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzo implements Creator<StreetViewPanoramaLocation> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int b = SafeParcelReader.m18926b(parcel);
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        LatLng latLng = null;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = SafeParcelReader.m18920a(parcel);
            int a2 = SafeParcelReader.m18919a(a);
            if (a2 == 2) {
                streetViewPanoramaLinkArr = (StreetViewPanoramaLink[]) SafeParcelReader.m18927b(parcel, a, StreetViewPanoramaLink.CREATOR);
            } else if (a2 == 3) {
                latLng = (LatLng) SafeParcelReader.m18921a(parcel, a, LatLng.CREATOR);
            } else if (a2 != 4) {
                SafeParcelReader.m18918Z(parcel, a);
            } else {
                str = SafeParcelReader.m18898F(parcel, a);
            }
        }
        SafeParcelReader.m18902J(parcel, b);
        return new StreetViewPanoramaLocation(streetViewPanoramaLinkArr, latLng, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new StreetViewPanoramaLocation[i];
    }
}

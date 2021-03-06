package com.oppwa.mobile.connect.exception;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mrsool.chat.C10526n;

public enum ErrorCode implements Parcelable {
    ERROR_CODE_PAYMENT_PARAMS_INVALID(1000),
    ERROR_CODE_PAYMENT_PARAMS_CHECKOUT_ID_INVALID(1010),
    ERROR_CODE_PAYMENT_PARAMS_PAYMENT_BRAND_INVALID(1011),
    ERROR_CODE_PAYMENT_PARAMS_TOKEN_INVALID(1090),
    ERROR_CODE_PAYMENT_PARAMS_TOKENIZATION_UNSUPPORTED(1091),
    ERROR_CODE_PAYMENT_PARAMS_CARD_HOLDER_INVALID(1110),
    ERROR_CODE_PAYMENT_PARAMS_CARD_NUMBER_INVALID(1111),
    ERROR_CODE_PAYMENT_PARAMS_CARD_BRAND_INVALID(1112),
    ERROR_CODE_PAYMENT_PARAMS_CARD_MONTH_INVALID(1113),
    ERROR_CODE_PAYMENT_PARAMS_CARD_YEAR_INVALID(1114),
    ERROR_CODE_PAYMENT_PARAMS_CARD_EXPIRED(1115),
    ERROR_CODE_PAYMENT_PARAMS_CARD_CVV_INVALID(1116),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_HOLDER_INVALID(1130),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_IBAN_INVALID(1131),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_COUNTRY_INVALID(1132),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_BANK_NAME_INVALID(1133),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_NUMBER_INVALID(1134),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_BIC_INVALID(1135),
    ERROR_CODE_PAYMENT_PARAMS_BANK_ACCOUNT_BANK_CODE_INVALID(1136),
    ERROR_CODE_PAYMENT_PARAMS_MOBILE_PHONE_INVALID(1160),
    ERROR_CODE_PAYMENT_PARAMS_COUNTRY_CODE_INVALID(1161),
    ERROR_CODE_PAYMENT_PARAMS_EMAIL_INVALID(1162),
    ERROR_CODE_PAYMENT_PARAMS_NATIONAL_IDENTIFIER_INVALID(1163),
    ERROR_CODE_PAYMENT_PROVIDER_NOT_INITIALIZED(2000),
    ERROR_CODE_PAYMENT_PROVIDER_INTERNAL_ERROR(2001),
    ERROR_CODE_PAYMENT_PROVIDER_SECURITY_INVALID_CHECKSUM(2100),
    ERROR_CODE_PAYMENT_PROVIDER_SECURITY_SSL_VALIDATION_FAILED(2101),
    ERROR_CODE_PAYMENT_PROVIDER_CONNECTION_FAILURE(3000),
    ERROR_CODE_PAYMENT_PROVIDER_CONNECTION_MALFORMED_RESPONSE(3100),
    ERROR_CODE_NO_AVAILABLE_PAYMENT_METHODS(4000),
    ERROR_CODE_PAYMENT_METHOD_NOT_AVAILABLE(4001),
    ERROR_CODE_CHECKOUT_SETTINGS_MISSED(4002),
    ERROR_CODE_TRANSACTION_ABORTED(4003),
    ERROR_CODE_PAYMENT_PARAMS_CARD_MOBILE_PHONE_NUMBER_INVALID(1117),
    ERROR_CODE_PAY_WITH_GOOGLE(C10526n.f28536a),
    ERROR_CODE_GOOGLEPAY(C10526n.f28536a);
    
    public static final Creator<ErrorCode> CREATOR = null;

    /* renamed from: a */
    private int f34450a;

    /* renamed from: com.oppwa.mobile.connect.exception.ErrorCode$a */
    static class C11940a implements Creator<ErrorCode> {
        C11940a() {
        }

        /* renamed from: a */
        public ErrorCode createFromParcel(Parcel parcel) {
            return ErrorCode.values()[parcel.readInt()];
        }

        /* renamed from: a */
        public ErrorCode[] newArray(int i) {
            return new ErrorCode[i];
        }
    }

    static {
        CREATOR = new C11940a();
    }

    private ErrorCode(int i) {
        this.f34450a = i;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: j */
    public int mo40754j() {
        return this.f34450a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}

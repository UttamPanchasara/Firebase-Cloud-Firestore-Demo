package com.uttampanchasara.baseprojectkotlin.data.network;

public final class ApiEndPoint {

    public static final String FIREBASE_SIGN_UP = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/signupNewUser";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

    public interface API_KEYS {
        String KEY = "key";
    }
}
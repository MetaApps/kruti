package com.a6studios.kruti;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

/**
 * Created by user on 3/24/2018.
 */

public class FirestoreDataBase {
    private static FirestoreDataBase mFirestoreDatabase;
    private static FirebaseFirestore db;
    private static FirebaseFirestoreSettings settings;
    private static FirebaseUser firebaseUser;
    private static String user_uidai;
    private static final String auth_user = "auth_user";
    private static final String TAG = "MY FIREBASE ERROR";

    private FirestoreDataBase() {

        if (mFirestoreDatabase != null)
            throw new RuntimeException("Use getFireStoreDataBase() method");
        else {
            db = FirebaseFirestore.getInstance();
            settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            user_uidai = firebaseUser.getUid();
        }
    }

    public static synchronized FirestoreDataBase getFirestoreDatabase()
    {
        if(mFirestoreDatabase==null)
            mFirestoreDatabase = new FirestoreDataBase();
        return mFirestoreDatabase;
    }

    public static FirestoreDataBase getmFirestoreDatabase() {
        return mFirestoreDatabase;
    }

    public static FirebaseFirestore getDb() {
        return db;
    }

    public static FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public static String getUserUIDAI() {
        return user_uidai;
    }

    public static void cleanUp()
    {
        user_uidai = null;
        firebaseUser = null;
        db = null;
        settings=null;
        mFirestoreDatabase = null;
    }
}

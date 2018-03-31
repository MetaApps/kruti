package com.a6studios.kruti;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.a6studios.kruti.package_ProfileSetup.ProfileSetupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

    public void addImage(Uri filePath, final ProfileSetupActivity profileSetupActivity) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference phtoRef = storageRef.child("images/" + filePath.getLastPathSegment());
        final ProgressDialog progressDialog = new ProgressDialog(profileSetupActivity);
        progressDialog.setTitle("Uploading");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        UploadTask uploadTask = storageRef.child("images/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).putFile(filePath);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(profileSetupActivity,"Failed Upload", Toast.LENGTH_SHORT);
            }
        });
    }
}

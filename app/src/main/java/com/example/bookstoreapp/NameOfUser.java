package com.example.bookstoreapp;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NameOfUser {
    private String FirstName;
    private String LastName;
    private String ID;

    public NameOfUser(String FN, String LN, String id)
    {
        FirstName = FN;
        LastName = LN;
        ID = id;
    }
    public NameOfUser(String FN, String LN)
    {
        FirstName = FN;
        LastName = LN;
    }

    public int checkIfUserExists(final String FullName)
    {
        final Map<String, String> user = new HashMap<>();

        final Random random = new Random();
        final boolean[] notfound = {true};
        final int[] randid2 = {random.nextInt((10000 - 1) + 1) + 1};
        FirebaseFirestore.getInstance().collection("User's Names").document("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        Map<String, Object> tmp = document.getData();
                        System.out.println(tmp.toString());
                        for (int i = 0; i < 1; i++)
                        {
                            notfound[0] = true;
                            for (Map.Entry<String, Object> entry : tmp.entrySet()) {
                                if (Integer.parseInt(entry.getKey()) == randid2[0])
                                {
                                    randid2[0] = random.nextInt((10000-1) + 1) + 1;
                                    i=-1;
                                    notfound[0] = false;
                                    break;
                                }
                                else if (entry.getValue().equals(FullName))
                                {
                                    notfound[0] = false;
                                    break;

                                }
                            }
                        }

                    }

                });
        if (notfound[0])
        {
            return randid2[0];
        }
        return 0;
    }
}

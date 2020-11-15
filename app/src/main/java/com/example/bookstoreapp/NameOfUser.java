package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Users_Names")
public class NameOfUser implements Serializable {
    @NonNull
    @PrimaryKey
    private String ID;
    @ColumnInfo(name = "Full Name")
    private String FullName;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int checkIfUserExists(final String FullName)
    {
        /*final Map<String, String> user = new HashMap<>();

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
        }*/
        return 0;
    }
}

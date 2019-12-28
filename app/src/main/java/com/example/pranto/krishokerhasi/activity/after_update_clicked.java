package com.example.pranto.krishokerhasi.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pranto.krishokerhasi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class after_update_clicked extends AppCompatActivity implements View.OnClickListener {

    Button button1,button2,button3;
    Button btn;
    TextView textView;
    EditText editText;
    private LinearLayout ll;
    private DatabaseReference mDatabase;
    private  String[] list;
    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_update_clicked);

        /*btn = new Button[10];
        textView = (TextView)findViewById(R.id.tv);
        editText = (EditText)findViewById(R.id.edittext1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);*/
        ll = (LinearLayout ) findViewById(R.id.ll_parent);

        list = new String[20];
        idx =0;
        int[] btnFrequency = load_frequency();
        int[] FrequencyRatio = RatioOfFrequency(btnFrequency);
        int[] BackupFrequencyRatio= new int[4];

        for (int i = 0; i < 4; i++)
            BackupFrequencyRatio[i] = FrequencyRatio[i];

        int [] sortedindex = SortIndexByFrequencyRatio(BackupFrequencyRatio);
        final String y = DivideRatio(FrequencyRatio,sortedindex);

        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetOnlineData(0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateIntent(y);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int query = Integer.parseInt(editText.getText().toString());
                GetOfflineData(query);

            }
        });*/
    }

    //net asche ki nai dekhe data niya ase
    void GetInformation(int i)
    {
        if(IsNetAvailable())
            GetOnlineData(i);
        else
            GetOfflineData(i);
    }

    //index er data firebase thk niya ase
    void GetOnlineData(int i)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("data/"+String.valueOf(i));
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("option"+"0"+"/"+"0");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString() ;
                CreateIntent(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void GetOnlineData(String i)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference().child(i);
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("option"+"0"+"/"+"0");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString() ;
                CreateIntent(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void GetOfflineData(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        boolean empty=false;

        Cursor res = dbh.getAllData(i);
        if(res.getCount()==0) {
            empty = true;
            //showMessage("Error","Nothing");
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {buffer.append(res.getString(1)+"\n\n");}

        String getrec=buffer.toString();

        if(empty)
            getrec = "Empty";

        CreateIntent(getrec);
    }

    void ClearDataBase()
    {
        final DatabaseHelper dbh = new DatabaseHelper(this);
        int[] TempArray = new int[4];
        TempArray = load_frequency();
        dbh.db.execSQL("DROP TABLE IF EXISTS "+dbh.table);
        dbh.db.execSQL("create table "+dbh.table+" (ID INTEGER,INFO TEXT)");
        save_frequency(TempArray);
    }


    void CreateIntent(String data)
    {
        Intent intent = new Intent(after_update_clicked.this, InfoPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("stuff", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    boolean IsNetAvailable()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            return true;
        return false;
    }

    void ToastMethod(String str)
    {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();}




    int Getfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(i+1000);
        if(res.getCount()==0) {
            return 0;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            //buffer.append("ID :" + res.getString(0)+"\n");
            buffer.append(res.getString(1));
        }
        //showMessage("Data",buffer.toString());

        return Integer.parseInt(buffer.toString());
    }

    void save_frequency(int[] a)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        for (int i = 0; i < 4; i++) {
            boolean isInseted = dbh.insertData(i+1000, String.valueOf(a[i]));
        }
    }

    int[] load_frequency()
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = Getfrequency(i);
        }
        return a;
    }

    private int[] RatioOfFrequency(int[] a){
        int sum = a[0] + a[1] + a[2] + a[3];
        System.out.println(sum);
        int[] ans = new int[4];

        for (int i = 0; i < 4; i++) {
            ans[i] = (a[i]*10)/sum;
            if((a[i]*10)%sum>(sum/2))
                ans[i]++;
            //System.out.println(ans[i]);
        }

        return ans;
    }

    private int[] SortIndexByFrequencyRatio(int[] ratio) {
        int[] indices = new int[4];
        for (int i = 0; i < 4; i++) {
            int max = 0;
            for (int j = 0; j < 4; j++) {
                if(ratio[j]>ratio[max])
                    max=j;
            }
            indices[i] = max ;
            ratio[max] = -2;
        }
        return indices;
    }

    private String DivideRatio(int[] ratio,int[] sortedindex) {
        String x = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < ratio[sortedindex[i]]; j++) {
                list[idx] = "update"+sortedindex[i]+"/"+j+"";
                x+= list[idx];
                //GetOnlineData(list[idx]);
                idx++;
            }
            x+="\n";
        }
        //String z = ("INDEX"+String.valueOf(idx)+"\n");


        for (int i = 0; i < idx; i++) {
            btn = new Button(after_update_clicked.this);
            btn.setId(i);
            int offlinequery = GetID(list[i]);
            String UpdateText = GetUpadateTextFromOffline(offlinequery);
            btn.setText(UpdateText.substring( 0, UpdateText.indexOf("#")));
            //SetButtonName(list[i],btn);
            btn.setTag(i);
            ll.addView(btn);
            btn.setOnClickListener(after_update_clicked.this);}

        //info.append(z);
        return x;
    }

    @Override
    public void onClick(View view) {
        {
            String str = view.getTag().toString();
            for (int i = 0; i < idx; i++) {
                String istr = String.valueOf(i);
                if (str.equals(istr)) {
                    int offlinequery = GetID(list[i]);
                    String site = GetUpadateTextFromOffline(offlinequery);
                    site = site.substring(site.indexOf("#")+1);
                    //ToastMethod(UpdateText);
                    WebSiteAccess(site);
                }
            }
        }
    }

    private void WebSiteAccess(String site) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(site));
        startActivity(intent);
    }

    private int GetID(String s) {
        return Integer.parseInt((s.substring(6,7)+s.substring(8)));}

    String GetUpadateTextFromOffline(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(i+400);

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            //buffer.append("ID :" + res.getString(0)+"\n");
            buffer.append(res.getString(1));
        }
        //showMessage("Data",buffer.toString());
        return (buffer.toString());
    }


}

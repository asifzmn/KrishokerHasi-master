package com.example.pranto.krishokerhasi.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pranto.krishokerhasi.R;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.MainActivity_socialnetwork;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.PostActivity;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.RegisterActivity;
import com.example.pranto.krishokerhasi.socialnetwork.ui.fragments.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final long GET_DATA_INTERVAL = 3000;

    //int images[] = {R.drawable.homepage1, R.drawable.homepage2,R.drawable.homepage3, R.drawable.homepage4,R.drawable.homepage5};
    int images[] = {R.drawable.homepage1, R.drawable.homepage3, R.drawable.homepage4, R.drawable.homepage6, R.drawable.homepage7};
    int images1[] = {R.drawable.homepage4, R.drawable.homepage1, R.drawable.homepage3, R.drawable.homepage7, R.drawable.homepage6};
    int images2[] = {R.drawable.homepage6, R.drawable.homepage7, R.drawable.homepage1, R.drawable.homepage4, R.drawable.homepage3};
    int images3[] = {R.drawable.homepage7, R.drawable.homepage4, R.drawable.homepage6, R.drawable.homepage3, R.drawable.homepage1};

    int index = 0;
    int index1 = 0;
    int index2 = 0;
    int index3 = 0;

    Handler hand = new Handler();
    Handler hand1 = new Handler();
    Handler hand2 = new Handler();
    Handler hand3 = new Handler();

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainpage);

        imageView = (ImageView)findViewById(R.id.backgroundImage1);
        hand.postDelayed(run, GET_DATA_INTERVAL);

        imageView1 = (ImageView)findViewById(R.id.backgroundImage2);
        hand1.postDelayed(run1, GET_DATA_INTERVAL);

        imageView2 = (ImageView)findViewById(R.id.backgroundImage3);
        hand2.postDelayed(run2, GET_DATA_INTERVAL);

        imageView3 = (ImageView)findViewById(R.id.backgroundImage4);
        hand3.postDelayed(run3, GET_DATA_INTERVAL);

        //first time app install dile frequency reset kore dea hbe
        FirstTimeRun();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /*-----------------------------------this function is used to make animation on the homepage---------------------------------*/


    Runnable run = new Runnable() {
        @Override
        public void run() {
            if(index<images.length) {
                imageView.setBackgroundDrawable(getResources().getDrawable(images[index++]));
            }
            else {
                index = 0;
            }
            hand.postDelayed(run, GET_DATA_INTERVAL);
        }
    };

    Runnable run1 = new Runnable() {
        @Override
        public void run() {
            if(index1<images1.length) {
                imageView1.setBackgroundDrawable(getResources().getDrawable(images1[index1++]));
            }
            else {
                index1 = 0;
            }
            hand1.postDelayed(run1, GET_DATA_INTERVAL);
        }
    };

    Runnable run2 = new Runnable() {
        @Override
        public void run() {
            if(index2<images2.length) {
                imageView2.setBackgroundDrawable(getResources().getDrawable(images2[index2++]));
            }
            else {
                index2 = 0;
            }
            hand2.postDelayed(run2, GET_DATA_INTERVAL);
        }
    };

    Runnable run3 = new Runnable() {
        @Override
        public void run() {
            if(index3<images3.length) {
                imageView3.setBackgroundDrawable(getResources().getDrawable(images3[index3++]));
            }
            else {
                index3 = 0;
            }
            hand3.postDelayed(run3, GET_DATA_INTERVAL);
        }
    };

    private void FirstTimeRun() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            refreshfrequency();

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*--------------------app theke exit korar jonno "exitFunction" lekha hoise------------------------*/

    public void exitFunction()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("আপনি কি অ্যাপ থেকে বের হতে চান ???");
        builder.setCancelable(true);
        builder.setNegativeButton("না", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("হ্যাঁ!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /*-------------------------- End part of "exitFunction"----------------------------------------------------*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*--------------------catagory button e click korle catagory gula show korbe-------------------*/

        if (id == R.id.catagory)
        {
            Intent intent = new Intent(MainActivity.this, after_catagory_clicked.class);
            startActivity(intent);
        }

        /*---------------------after clicked exit button exit function will execute-----------------------------------------*/

        else if (id == R.id.exit)
        {
            exitFunction();
        }

        /*----------------------post button will work for socialnetwork to post, comment-------------------------------------*/

        else if (id == R.id.post)
        {
            int from_where = 2;
            Intent intent = new Intent(MainActivity.this, MainActivity_socialnetwork.class);
            intent.putExtra("from_where", from_where);
            startActivity(intent);
        }

        /*--------------------update button will work as a sync button to load data from firebase to sqlite database----------*/
        else if (id == R.id.update)
        {
            if(IsNetAvailable())
            {
                ClearDataBase();
                SaveUpdatesPageData();
                SaveCatagory();
            }
        }

        else if (id == R.id.about_us) {
            Intent intent = new Intent(MainActivity.this, after_update_clicked.class);
            startActivity(intent);
        }

        else if (id==R.id.search)
        {
            Intent intent = new Intent(MainActivity.this, text_search.class);
            startActivity(intent);
        }

        else if (id==R.id.hotline)
        {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:16123"));
            startActivity(callIntent);
        }

        /*else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //catagory button er frequency reset kora
    void refreshfrequency() {
        final DatabaseHelper dbh = new DatabaseHelper(this);
        dbh.db.execSQL("DROP TABLE IF EXISTS " + dbh.table);
        dbh.db.execSQL("create table " + dbh.table + " (ID INTEGER,INFO TEXT)");
        //int[] arr = {0, 0, 0, 0};
        int[] arr = {1,1,1,1};

        for (int i = 0; i < 4; i++) {
            boolean isInseted = dbh.insertData(i + 1000, String.valueOf(arr[i]));
        }
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

    void ClearDataBase()
    {
        final DatabaseHelper dbh = new DatabaseHelper(this);
        int[] TempArray = new int[4];
        TempArray = load_frequency();
        dbh.db.execSQL("DROP TABLE IF EXISTS "+dbh.table);
        dbh.db.execSQL("create table "+dbh.table+" (ID INTEGER,INFO TEXT)");
        save_frequency(TempArray);
    }

    int Getfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(i+1000);
        if(res.getCount()==0)
            return 0;

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext())
            buffer.append(res.getString(1));

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
        for (int i = 0; i < 4; i++)
            a[i] = Getfrequency(i);
        return a;
    }

    void SaveUpdatesPageData()
    {
        final DatabaseHelper dbh = new DatabaseHelper(this);

        for (int i = 0; i < 40; i++) {
            String indexa = String.valueOf(i/10);
            String indexb = String.valueOf(i%10);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("update"+indexa+"/"+indexb);

            final int finalI = i;
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue().toString() ;
                    boolean isInseted = dbh.insertData(finalI+400,value);}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
        }

    }

    void SaveCatagory()
    {
        final DatabaseHelper dbh = new DatabaseHelper(this);

        for (int i = 0; i < 4; i++) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("catagory/"+String.valueOf(i));

            final int finalI = i;
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String catagory = dataSnapshot.getValue().toString() ;
                    String[] separatedcatagory = catagory.split(",");
                    boolean isInseted = dbh.insertData(finalI+500, String.valueOf(separatedcatagory.length));
                    boolean isInseted1 = dbh.insertData(finalI+600, catagory);
                    SaveOfflineData(finalI,separatedcatagory);}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
        }
    }

    void SaveOfflineData(int idx,String[] separatedcatagory)
    {
        final DatabaseHelper dbh = new DatabaseHelper(this);
        for (int j = 0; j < separatedcatagory.length; j++)
        {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("data/"+String.valueOf(idx*100+j));

            final int finalI = idx*100+j;
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue().toString() ;
                    //edit.append(value);
                    boolean isInseted = dbh.insertData(finalI,value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
        }
    }
}

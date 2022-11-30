package vn.edu.poly.andromeda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import vn.edu.poly.andromeda.fragment.DownloadFragment;
import vn.edu.poly.andromeda.fragment.ForumFragment;
import vn.edu.poly.andromeda.fragment.HomeFragment;
import vn.edu.poly.andromeda.fragment.ProfileFragment;
import vn.edu.poly.andromeda.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Checknetwork checknetwork = new Checknetwork();
    HomeFragment homeFragment = new HomeFragment();
    SettingFragment settingsFragment = new SettingFragment();
    ForumFragment forumFragment = new ForumFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    DownloadFragment downloadFragment = new DownloadFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        String toan = "updated";

        bottomNavigationView  = findViewById(R.id.bottom_navigation);


        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        getSupportActionBar().setTitle("Andromeda");
                        return true;
                    case R.id.forum:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,forumFragment).commit();
                        getSupportActionBar().setTitle("Bình luận");
                        return true;
                    case R.id.download:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,downloadFragment).commit();
                        getSupportActionBar().setTitle("Tải xuống");
                        return true;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                        getSupportActionBar().setTitle("Setting");
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        getSupportActionBar().setTitle("Profile");
                        return true;
                }

                return false;
            }
        });
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checknetwork, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(checknetwork);
        super.onStop();
    }
}
package com.example.uas_pwdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    BookFragment bookFragment = new BookFragment();
    ActivityFragment activityFragment = new ActivityFragment();
    MemberFragment memberFragment = new MemberFragment();

    private static final int BOOK_NAV_ID = R.id.bookNav;
    private static final int BORROW_NAV_ID = R.id.borrowNav;
    private static final int MEMBER_NAV_ID = R.id.memberNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.footer);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,bookFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId(); // Get the ID of the selected item

                // Use if-else statements instead of switch
                if (itemId == BOOK_NAV_ID) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, bookFragment).commit();
                    return true;
                } else if (itemId == BORROW_NAV_ID) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, activityFragment).commit();
                    return true;
                } else if (itemId == MEMBER_NAV_ID) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, memberFragment).commit();
                    return true;
                }

                return false;
            }
        });
    }
}
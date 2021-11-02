package com.t3g.manvi.activity;


import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t3g.manvi.R;
import com.t3g.manvi.menu.DrawerAdapter;
import com.t3g.manvi.menu.DrawerItem;
import com.t3g.manvi.menu.SimpleItem;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class NavActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    //    private static final int HOME = 0;
    private static final int ABOUT = 0;
    private static final int GALLARY = 1;
    private static final int SHARE = 2;
    //    private static final int CREDITS = 3;
    private static final int PRIVACY_POLICY = 3;
    private static final int TERMS_AND_CONDITIONS = 4;
    private static final int FEEDBACK = 5;
    private static final int RATE_REVIEW = 6;
    private static final int MORE = 7;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slideView;
    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
//                createItemFor(HOME),
                createItemFor(ABOUT),
                createItemFor(GALLARY),
                createItemFor(SHARE),
//                createItemFor(CREDITS),
                createItemFor(PRIVACY_POLICY),
                createItemFor(TERMS_AND_CONDITIONS),
                createItemFor(FEEDBACK),
                createItemFor(RATE_REVIEW),
                createItemFor(MORE)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(ABOUT);
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorPrimary))
                .withTextTint(color(R.color.colorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.drawerTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.drawerIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @Override
    public void onItemSelected(int position) {
        if (position == ABOUT) {
//            callAboutActivity();
        }
        else if (position == GALLARY) {
//            callGalleryActivity();
        }
        else if (position == SHARE) {
//            share();
        }
//        else if (position == CREDITS) {
//            Intent h = new Intent(MainActivity.this, CreditsActivity.class);
//            startActivity(h);
//        }
        else if (position == PRIVACY_POLICY) {
//            privacyPolicy();
        }
        else if (position == TERMS_AND_CONDITIONS) {
//            termsAndConditions();
        }
        else if (position == FEEDBACK) {
//            openSupport();
        }
        else if (position == RATE_REVIEW) {
//            rateReview();
        }
        else if (position == MORE) {
//            moreFromt3g();
        }
        slidingRootNav.closeMenu();
    }
}
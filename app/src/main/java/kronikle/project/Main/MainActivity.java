package kronikle.project.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kronikle.project.Adapters.ViewPagerAdapter;
import kronikle.project.ContactUs.ContactUsActivity;
import kronikle.project.Activity.ActivityActivity;
import kronikle.project.Info.InfoActivity;
import kronikle.project.Landing.LandingActivity;
import kronikle.project.Profile.ProfileActivity;
import kronikle.project.R;
import kronikle.project.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private LikeButton iconHeart;
    private SlidingRootNav slidingRootNav;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView TasksTab;
    private TextView HabitsTab;
    private TextView TimersTab;
    private boolean backButtonPressedTwice = false;

    private LinearLayout layoutHome;
    private LinearLayout layoutActivity;
    private LinearLayout layoutProfile;
    private LinearLayout layoutContactUs;
    private LinearLayout layoutInfo;
    private LinearLayout layoutSettings;
    private LinearLayout layoutSignOut;

    private ImageView iconHome;
    private TextView textViewHome;

    private ImageView iconSignOut;
    private TextView textViewSignOut;

    private MultiChoicesCircleButton circleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializer();
        toolbarInitializer();
        heartListener();
        circleButtonInitializer();
        circleButtonListener();
        drawerMenuInitializer();
        drawerMenuListener();
        tabLayoutInitializer();
        tabLayoutSelected();
    }

    private void initializer() {
        linearLayout = findViewById(R.id.linear_layout_MA);
        toolbar = findViewById(R.id.toolbar_MA);
        iconHeart = findViewById(R.id.icon_heart_MA);
        circleButton = findViewById(R.id.circle_button_MA);
        tabLayout = findViewById(R.id.tab_layout_MA);
        viewPager = findViewById(R.id.view_pager_MA);
    }

    private void toolbarInitializer() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    // Needs Code
    private void heartListener() {
        iconHeart.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        iconHeart.setLiked(false);
                    }
                }, 500);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                iconHeart.setLiked(true);
            }
        });
    }

    private void circleButtonInitializer () {
        MultiChoicesCircleButton.Item addTask = new MultiChoicesCircleButton.Item( getString(R.string.add_task), getDrawable(R.drawable.icon_add_task), 30);
        MultiChoicesCircleButton.Item addHabit = new MultiChoicesCircleButton.Item(getString(R.string.add_habit),getDrawable(R.drawable.icon_add_habit), 90);
        MultiChoicesCircleButton.Item addTimer = new MultiChoicesCircleButton.Item(getString(R.string.add_timer), getDrawable(R.drawable.icon_add_timer), 150);

        List<MultiChoicesCircleButton.Item> buttonItems = new ArrayList<>();
        buttonItems.add(addTask);
        buttonItems.add(addHabit);
        buttonItems.add(addTimer);

        circleButton.setButtonItems(buttonItems);
    }

    // Needs Code
    private void circleButtonListener() {
        circleButton.setOnSelectedItemListener(new MultiChoicesCircleButton.OnSelectedItemListener() {
            @Override
            public void onSelected(MultiChoicesCircleButton.Item item, int index) {
                if (index == 0) {
                    circleButton.hide(true);
                    Toast.makeText(MainActivity.this, item.getText(), Toast.LENGTH_SHORT).show();
                }

                else if (index == 1) {
                    Toast.makeText(MainActivity.this, item.getText(), Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(MainActivity.this, item.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void drawerMenuInitializer() {
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        layoutHome = findViewById(R.id.layout_home_DM);
        layoutActivity = findViewById(R.id.layout_activity_DM);
        layoutProfile = findViewById(R.id.layout_profile_DM);
        layoutContactUs = findViewById(R.id.layout_contact_us_DM);
        layoutInfo = findViewById(R.id.layout_info_DM);
        layoutSettings = findViewById(R.id.layout_settings_DM);
        layoutSignOut = findViewById(R.id.layout_sign_out_DM);

        iconHome = findViewById(R.id.icon_home_DM);
        textViewHome = findViewById(R.id.text_view_home_DM);

        iconHome.setImageResource(R.drawable.icon_home_focused);
        textViewHome.setTextColor(getResources().getColor(R.color.colorTextLight));

        iconSignOut = findViewById(R.id.icon_sign_out_DM);
        textViewSignOut = findViewById(R.id.text_view_sign_out_DM);
    }

    private void drawerMenuListener() {
        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingRootNav.closeMenu();
            }
        });

        layoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityIntent = new Intent(getBaseContext(), ActivityActivity.class);
                startActivity(ActivityIntent);
            }
        });

        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ProfileIntent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(ProfileIntent);
            }
        });

        layoutContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ContactUsIntent = new Intent(getBaseContext(), ContactUsActivity.class);
                startActivity(ContactUsIntent);
            }
        });

        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent InfoIntent = new Intent(getBaseContext(), InfoActivity.class);
                startActivity(InfoIntent);
            }
        });

        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsIntent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(SettingsIntent);
            }
        });

        layoutSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconHome.setImageResource(R.drawable.icon_home);
                textViewHome.setTextColor(getResources().getColor(R.color.colorBaseLight));

                iconSignOut.setImageResource(R.drawable.icon_sign_out_focused);
                textViewSignOut.setTextColor(getResources().getColor(R.color.colorTextLight));

                slidingRootNav.closeMenu();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent LandingActivityIntent = new Intent(getBaseContext(), LandingActivity.class);
                        startActivity(LandingActivityIntent);
                        overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
                        finish();
                    }
                }, 300);
            }
        });
    }

    @SuppressLint("InflateParams")
    private void tabLayoutInitializer() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TasksFragment(), getString(R.string.tasks));
        adapter.AddFragment(new HabitsFragment(), getString(R.string.habits));
        adapter.AddFragment(new TimersFragment(), getString(R.string.timers));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        TasksTab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout_custom_format, null);
        TasksTab.setText(getString(R.string.tasks));
        TasksTab.setTextColor(getResources().getColor(R.color.colorTextLight));
        TasksTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_tasks_focused, 0, 0, 0);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setCustomView(TasksTab);

        HabitsTab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout_custom_format, null);
        HabitsTab.setText(getString(R.string.habits));
        HabitsTab.setTextColor(getResources().getColor(R.color.colorBaseLight));
        HabitsTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_habits_not_focused, 0, 0, 0);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setCustomView(HabitsTab);

        TimersTab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout_custom_format, null);
        TimersTab.setText(getString(R.string.timers));
        TimersTab.setTextColor(getResources().getColor(R.color.colorBaseLight));
        TimersTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_timers_not_focused, 0, 0, 0);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setCustomView(TimersTab);
    }

    private void tabLayoutSelected() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    TasksTab.setTextColor(getResources().getColor(R.color.colorTextLight));
                    TasksTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_tasks_focused, 0, 0, 0);
                }

                else if (tab.getPosition() == 1) {
                    HabitsTab.setTextColor(getResources().getColor(R.color.colorTextLight));
                    HabitsTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_habits_focused, 0, 0, 0);
                }

                else if (tab.getPosition() == 2) {
                    TimersTab.setTextColor(getResources().getColor(R.color.colorTextLight));
                    TimersTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_timers_focused, 0, 0, 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    TasksTab.setTextColor(getResources().getColor(R.color.colorBaseLight));
                    TasksTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_tasks_not_focused, 0, 0, 0);
                }

                else if (tab.getPosition() == 1) {
                    HabitsTab.setTextColor(getResources().getColor(R.color.colorBaseLight));
                    HabitsTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_habits_not_focused, 0, 0, 0);
                }

                else if (tab.getPosition() == 2) {
                    TimersTab.setTextColor(getResources().getColor(R.color.colorBaseLight));
                    TimersTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_timers_not_focused, 0, 0, 0);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                slidingRootNav.closeMenu();
            }
        }, 100);
    }

    @Override
    public void onBackPressed() {

        if (slidingRootNav.isMenuOpened()) {
            slidingRootNav.closeMenu();
        }

        else {

            if (backButtonPressedTwice) {
                super.onBackPressed();
            }

            else {
                Toast.makeText(this, getString(R.string.exit_app), Toast.LENGTH_LONG).show();

                backButtonPressedTwice = true;
                new CountDownTimer(3000, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        backButtonPressedTwice = false;
                    }
                }.start();
            }
        }
    }
}

package kronikle.project.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Objects;

import kronikle.project.Adapters.ViewPagerAdapter;
import kronikle.project.Dashboard.DashboardActivity;
import kronikle.project.Info.InfoActivity;
import kronikle.project.MyAccount.MyAccountActivity;
import kronikle.project.R;
import kronikle.project.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private SlidingRootNav slidingRootNav;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView TasksTab;
    private TextView HabitsTab;
    private TextView TimersTab;

    private LinearLayout layoutHome;
    private LinearLayout layoutDashboard;
    private LinearLayout layoutMyAccount;
    private LinearLayout layoutContact;
    private LinearLayout layoutInfo;
    private LinearLayout layoutSettings;
    private LinearLayout layoutSignOut;

    private ImageView iconHome;
    private TextView textViewHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializer();
        toolbarInitializer();
        drawerMenuInitializer();
        drawerMenuListener();
        tabLayoutInitializer();
        tabLayoutSelected();
    }

    private void initializer() {
        linearLayout = findViewById(R.id.linear_layout_MA);
        toolbar = findViewById(R.id.toolbar_MA);
        tabLayout = findViewById(R.id.tab_layout_MA);
        viewPager = findViewById(R.id.view_pager_MA);
    }

    private void toolbarInitializer() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void drawerMenuInitializer() {
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        layoutHome = findViewById(R.id.layout_home_DM);
        layoutDashboard = findViewById(R.id.layout_dashboard_DM);
        layoutMyAccount = findViewById(R.id.layout_my_account_DM);
        layoutContact = findViewById(R.id.layout_contact_DM);
        layoutInfo = findViewById(R.id.layout_info_DM);
        layoutSettings = findViewById(R.id.layout_settings_DM);
        layoutSignOut = findViewById(R.id.layout_sign_out_DM);

        iconHome = findViewById(R.id.icon_home_DM);
        textViewHome = findViewById(R.id.text_view_home_DM);

        iconHome.setImageResource(R.drawable.icon_home_focused);
        textViewHome.setTextColor(getResources().getColor(R.color.colorTextLight));
    }

    private void drawerMenuListener() {
        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingRootNav.closeMenu();
            }
        });

        layoutDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DashboardIntent = new Intent(getBaseContext(), DashboardActivity.class);
                startActivity(DashboardIntent);
            }
        });

        layoutMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MyAccountIntent = new Intent(getBaseContext(), MyAccountActivity.class);
                startActivity(MyAccountIntent);
            }
        });

        layoutContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}

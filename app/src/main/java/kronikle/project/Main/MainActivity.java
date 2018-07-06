package kronikle.project.Main;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Objects;

import kronikle.project.Adapters.ViewPagerAdapter;
import kronikle.project.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private SlidingRootNav slidingRootNav;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView TasksTab;
    private TextView HabitsTab;
    private TextView TimersTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializer();
        toolbarInitializer();
        slideMenuInitializer();
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

    private void slideMenuInitializer() {
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();
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

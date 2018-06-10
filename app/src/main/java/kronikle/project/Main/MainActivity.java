package kronikle.project.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import java.util.Objects;

import kronikle.project.Adapters.ViewPagerAdapter;
import kronikle.project.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabLayoutIcons = { R.drawable.icon_tasks_not_focused, R.drawable.icon_habits_not_focused, R.drawable.icon_timers_not_focused,
                                     R.drawable.icon_tasks_focused, R.drawable.icon_habits_focused, R.drawable.icon_timers_focused};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializer();
        toolbarInitializer();
        tabLayoutInitializer();
        tabLayoutSelected();
    }

    private void  initializer() {
        linearLayout = findViewById(R.id.linear_layout_MA);
        toolbar = findViewById(R.id.toolbar_MA);
        tabLayout = findViewById(R.id.tab_layout_MA);
        viewPager = findViewById(R.id.view_pager_MA);
    }

    private void toolbarInitializer() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void tabLayoutInitializer() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TasksFragment(), getString(R.string.tasks));
        adapter.AddFragment(new HabitsFragment(), getString(R.string.habits));
        adapter.AddFragment(new TimersFragment(), getString(R.string.timers));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void tabLayoutSelected() {
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabLayoutIcons[3]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabLayoutIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabLayoutIcons[2]);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(tabLayoutIcons[3]);
                }

                else if (tab.getPosition() == 1) {
                    tab.setIcon(tabLayoutIcons[4]);
                }

                else if (tab.getPosition() == 2) {
                    tab.setIcon(tabLayoutIcons[5]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(tabLayoutIcons[0]);
                }

                else if (tab.getPosition() == 1) {
                    tab.setIcon(tabLayoutIcons[1]);
                }

                else if (tab.getPosition() == 2) {
                    tab.setIcon(tabLayoutIcons[2]);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

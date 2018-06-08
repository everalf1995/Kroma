package kronikle.project.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.Objects;

import kronikle.project.Adapters.ViewPagerAdapter;
import kronikle.project.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabLayoutIcons = { R.drawable.icon_tasks, R.drawable.icon_habits, R.drawable.icon_timers};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializer();
        toolbarInitializer();
        tabLayoutInitializer();
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

    public void tabLayoutInitializer() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TasksFragment(), getString(R.string.tasks));
        adapter.AddFragment(new HabitsFragment(), getString(R.string.habits));
        adapter.AddFragment(new TimersFragment(), getString(R.string.timers));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabLayoutIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabLayoutIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabLayoutIcons[2]);
    }

}

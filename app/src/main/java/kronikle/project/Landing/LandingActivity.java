package kronikle.project.Landing;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kronikle.project.R;
import kronikle.project.ViewPagerAdapter;

public class LandingActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        tabLayoutInitializer();
    }

    public void tabLayoutInitializer() {

        tabLayout = findViewById(R.id.tab_layout_LA);
        viewPager = findViewById(R.id.view_pager_LA);
        textView = findViewById(R.id.kronikle_LA);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new SignInFragment(), getString(R.string.sign_in));
        adapter.AddFragment(new SignUpFragment(), getString(R.string.sign_up));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

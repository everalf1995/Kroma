package kronikle.project.Landing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import kronikle.project.R;
import kronikle.project.Adapters.ViewPagerAdapter;

public class LandingActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        initializer();
        tabLayoutInitializer();
        layoutFocus();
    }

    private void  initializer() {
        linearLayout = findViewById(R.id.linear_layout_LA);
        tabLayout = findViewById(R.id.tab_layout_LA);
        viewPager = findViewById(R.id.view_pager_LA);
        textView = findViewById(R.id.kronikle_LA);
    }

    public void tabLayoutInitializer() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new SignInFragment(), getString(R.string.sign_in));
        adapter.AddFragment(new SignUpFragment(), getString(R.string.sign_up));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void layoutFocus() {
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                return true;
            }
        });
    }
}

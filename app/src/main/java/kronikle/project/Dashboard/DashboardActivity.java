package kronikle.project.Dashboard;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Objects;

import kronikle.project.Contact.ContactActivity;
import kronikle.project.Info.InfoActivity;
import kronikle.project.MyAccount.MyAccountActivity;
import kronikle.project.R;
import kronikle.project.Settings.SettingsActivity;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private SlidingRootNav slidingRootNav;
    private ViewPager viewPager;

    private LinearLayout layoutHome;
    private LinearLayout layoutDashboard;
    private LinearLayout layoutMyAccount;
    private LinearLayout layoutContact;
    private LinearLayout layoutInfo;
    private LinearLayout layoutSettings;
    private LinearLayout layoutSignOut;

    private ImageView iconDashboard;
    private TextView textViewDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        initializer();
        toolbarInitializer();
        drawerMenuInitializer();
        drawerMenuListener();
    }

    private void initializer() {
        linearLayout = findViewById(R.id.linear_layout_DA);
        toolbar = findViewById(R.id.toolbar_DA);
        viewPager = findViewById(R.id.view_pager_DA);
    }

    private void toolbarInitializer() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void drawerMenuInitializer() {
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(true)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                slidingRootNav.closeMenu();
            }
        }, 100);

        layoutHome = findViewById(R.id.layout_home_DM);
        layoutDashboard = findViewById(R.id.layout_dashboard_DM);
        layoutMyAccount = findViewById(R.id.layout_my_account_DM);
        layoutContact = findViewById(R.id.layout_contact_DM);
        layoutInfo = findViewById(R.id.layout_info_DM);
        layoutSettings = findViewById(R.id.layout_settings_DM);
        layoutSignOut = findViewById(R.id.layout_sign_out_DM);

        iconDashboard = findViewById(R.id.icon_dashboard_DM);
        textViewDashboard = findViewById(R.id.text_view_dashboard_DM);

        iconDashboard.setImageResource(R.drawable.icon_dashboard_focused);
        textViewDashboard.setTextColor(getResources().getColor(R.color.colorTextLight));
    }

    private void drawerMenuListener() {
        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingRootNav.closeMenu();
            }
        });

        layoutMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MyAccountIntent = new Intent(getBaseContext(), MyAccountActivity.class);
                startActivity(MyAccountIntent);
                finish();
            }
        });

        layoutContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ContactIntent = new Intent(getBaseContext(), ContactActivity.class);
                startActivity(ContactIntent);
                finish();
            }
        });

        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent InfoIntent = new Intent(getBaseContext(), InfoActivity.class);
                startActivity(InfoIntent);
                finish();
            }
        });

        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsIntent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(SettingsIntent);
                finish();
            }
        });

        layoutSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        slidingRootNav.openMenu();
    }
}


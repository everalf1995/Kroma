package kronikle.project.Main.Tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scwang.wave.MultiWaveHeader;

import java.util.Objects;

import kronikle.project.R;

public class NewTaskActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout relativeLayout;
    private MultiWaveHeader wave;
    private LinearLayout linearLayout;



    private String taskType;
    private String taskRelevance;
    private String taskDate;
    private String taskTime;
    private String taskReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        toolbarInitializer();
        initializer();
        layoutFocus();
        waveListener();

    }

    private void toolbarInitializer() {
        toolbar = findViewById(R.id.toolbar_NTA);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.icon_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorAccent, typedValue, true);
            @ColorInt int color = typedValue.data;

            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    private void initializer() {
        relativeLayout = findViewById(R.id.relative_layout_NTA);
        wave = findViewById(R.id.wave_NTA);
        linearLayout = findViewById(R.id.linear_layout_NTA);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void layoutFocus() {
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                return true;
            }
        });
    }

    private void waveListener() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
    }
}
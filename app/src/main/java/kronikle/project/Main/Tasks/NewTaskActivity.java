package kronikle.project.Main.Tasks;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scwang.wave.MultiWaveHeader;

import java.util.Objects;

import kronikle.project.R;

public class NewTaskActivity extends AppCompatActivity {

    private RelativeLayout linearLayout;
    private Toolbar toolbar;
    private MultiWaveHeader wave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        initializer();
        toolbarInitializer();
        waveListener();
    }

    private void initializer() {
        linearLayout = findViewById(R.id.linear_layout_NTA);
        toolbar = findViewById(R.id.toolbar_NTA);
        wave = findViewById(R.id.wave_NTA);
    }

    private void toolbarInitializer() {
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
    }

    private void waveListener() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
    }
}
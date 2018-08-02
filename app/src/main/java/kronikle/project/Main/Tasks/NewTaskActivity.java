package kronikle.project.Main.Tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.scwang.wave.MultiWaveHeader;

import java.util.Objects;

import kronikle.project.R;

public class NewTaskActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Toolbar toolbar;
    private MultiWaveHeader wave;

    private LinearLayout card1;
    private LinearLayout linearLayoutCard1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        initializer();
        cardsInitializer();
        toolbarInitializer();
        waveListener();
        spinnerListener();
        layoutFocus();

    }

    private void initializer() {
        relativeLayout = findViewById(R.id.relative_layout_NTA);
        toolbar = findViewById(R.id.toolbar_NTA);
        wave = findViewById(R.id.wave_NTA);
    }

    private void cardsInitializer() {
        card1 = findViewById(R.id.card_holder_1_NTA);
        getLayoutInflater().inflate(R.layout.new_task_card_1, card1);
        linearLayoutCard1 = findViewById(R.id.linear_layout_NTC1);


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

    private void spinnerListener() {
        String[] items = new String[] {"School", "Work", "Leisure"};
        Spinner spinner = findViewById(R.id.spinner_task_type_NTC1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void waveListener() {
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

        linearLayoutCard1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
    }
}
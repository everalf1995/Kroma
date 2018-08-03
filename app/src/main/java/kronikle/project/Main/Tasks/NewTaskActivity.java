package kronikle.project.Main.Tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import android.widget.TextView;

import com.scwang.wave.MultiWaveHeader;

import java.util.Objects;

import kronikle.project.R;

public class NewTaskActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Toolbar toolbar;
    private MultiWaveHeader wave;

    private LinearLayout card1;
    private LinearLayout linearLayoutCard1;
    private TextInputLayout textInputLayoutTaskTitle;
    private TextInputEditText editTextTaskTitle;
    private TextInputLayout textInputLayoutTaskDescription;
    private TextInputEditText editTextTaskDescription;
    private TextView textViewTaskType;
    private Spinner spinnerTaskType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        initializer();
        cardsInitializer();
        toolbarInitializer();
        waveListener();
        spinnerTaskTypeListener();
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
        textInputLayoutTaskTitle = findViewById(R.id.text_input_layout_task_title_NTC1);
        editTextTaskTitle = findViewById(R.id.edit_text_task_title_NTC1);
        textInputLayoutTaskTitle = findViewById(R.id.text_input_layout_task_description_NTC1);
        editTextTaskTitle = findViewById(R.id.edit_text_task_description_NTC1);
        textViewTaskType = findViewById(R.id.text_view_task_type_NTC1);
        spinnerTaskType = findViewById(R.id.spinner_task_type_NTC1);

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

    private void spinnerTaskTypeListener() {
        String[] spinnerTaskTypeItems = new String[] {getString(R.string.add_new_entry)};
        ArrayAdapter<String> spinnerTaskTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerTaskTypeItems);
        spinnerTaskTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaskType.setAdapter(spinnerTaskTypeAdapter);
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
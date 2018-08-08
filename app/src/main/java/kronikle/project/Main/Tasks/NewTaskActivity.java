package kronikle.project.Main.Tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import kronikle.project.R;

public class NewTaskActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout relativeLayout;
    private MultiWaveHeader wave;
    private Button buttonCreateTask;

    private LinearLayout card1;
    private LinearLayout linearLayoutCard1;
    private TextInputLayout textInputLayoutTaskTitle;
    private TextInputEditText editTextTaskTitle;
    private TextInputLayout textInputLayoutTaskDescription;
    private TextInputEditText editTextTaskDescription;
    private TextView textViewTaskType;
    private Spinner spinnerTaskType;

    private String taskTitle;
    private String taskDescription;
    private String taskType;

    private RelativeLayout card2;
    private RelativeLayout relativeLayoutCard2;
    private LinearLayout linearLayoutCard2;
    private TextView textViewImportance;
    private RadioGroup radioGroupImportance;
    private RadioButton radioButtonNotImportant;
    private RadioButton radioButtonSomewhatImportant;
    private RadioButton radioButtonImportant;
    private RadioButton radioButtonVeryImportant;
    private RadioButton radioButtonReallyImportant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        toolbarInitializer();
        initializer();
        cardInitializerNo1();
        cardInitializerNo2();
        buttonCreateTaskListener();
        spinnerTaskTypeInitializer();
        radioGroupImportanceInitializer();
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
    }

    private void initializer() {
        relativeLayout = findViewById(R.id.relative_layout_NTA);
        wave = findViewById(R.id.wave_NTA);
        buttonCreateTask = findViewById(R.id.button_create_task_NTA);
    }



    private void cardInitializerNo1() {
        card1 = findViewById(R.id.card_holder_1_NTA);
        getLayoutInflater().inflate(R.layout.new_task_card_1, card1);

        linearLayoutCard1 = findViewById(R.id.linear_layout_NTC1);
        textInputLayoutTaskTitle = findViewById(R.id.text_input_layout_task_title_NTC1);
        editTextTaskTitle = findViewById(R.id.edit_text_task_title_NTC1);
        textInputLayoutTaskDescription = findViewById(R.id.text_input_layout_task_description_NTC1);
        editTextTaskDescription = findViewById(R.id.edit_text_task_description_NTC1);
        textViewTaskType = findViewById(R.id.text_view_task_type_NTC1);
        spinnerTaskType = findViewById(R.id.spinner_task_type_NTC1);
    }

    private void cardInitializerNo2() {
        card2 = findViewById(R.id.card_holder_2_NTA);
        getLayoutInflater().inflate(R.layout.new_task_card_2, card2);

        relativeLayoutCard2 = findViewById(R.id.relative_layout_NTC2);
        linearLayoutCard2 = findViewById(R.id.linear_layout_NTC2);
        textViewImportance = findViewById(R.id.text_view_importance_NTC2);
        radioGroupImportance = findViewById(R.id.radio_group_importance_NTC2);
        radioButtonNotImportant = findViewById(R.id.radio_button_not_important);
        radioButtonSomewhatImportant = findViewById(R.id.radio_button_somewhat_important);
        radioButtonImportant = findViewById(R.id.radio_button_important);
        radioButtonVeryImportant = findViewById(R.id.radio_button_very_important);
        radioButtonReallyImportant = findViewById(R.id.radio_button_really_important);
    }

    private void buttonCreateTaskListener() {
        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateTaskTitle()) {
                    focusView(editTextTaskTitle);
                }

                else if (!validateTaskDescription()) {
                    focusView(editTextTaskDescription);
                }

                else if (!validateTaskType()) {
                    spinnerTaskType.requestFocus();

                }

                else {

                    new StyleableToast
                            .Builder(Objects.requireNonNull(getApplication()))
                            .text(getString(R.string.task_created))
                            .textColor(getResources().getColor(R.color.colorTextLight))
                            .backgroundColor(getResources().getColor(R.color.colorBackground))
                            .iconStart(R.drawable.icon_save)
                            .cornerRadius(2)
                            .length(6000)
                            .show();

                    finish();
                    overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
                }
            }
        });
    }

    private boolean validateTaskTitle() {
        taskTitle = editTextTaskTitle.getText().toString().trim();

        if (taskTitle.isEmpty()) {
            return false;
        }

        else {
            return true;
        }
    }

    private boolean validateTaskDescription() {
        taskDescription = editTextTaskDescription.getText().toString().trim();

        if (taskDescription.isEmpty()) {
            return false;
        }

        else {
            return true;
        }
    }

    //Needs work
    private void spinnerTaskTypeInitializer() {
        String[] spinnerTaskTypeItems = new String[] {getString(R.string.add_new_entry)};
        final List<String> spinnerTaskTypeList = new ArrayList<>(Arrays.asList(spinnerTaskTypeItems));
        ArrayAdapter<String> spinnerTaskTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerTaskTypeList);
        spinnerTaskTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaskType.setAdapter(spinnerTaskTypeAdapter);
        spinnerTaskType.setFocusable(true);
        spinnerTaskType.setFocusableInTouchMode(true);

        // TESTING ADDING NEW STRINGS TO SPINNER
        spinnerTaskTypeList.add("test");
        spinnerTaskTypeAdapter.notifyDataSetChanged();
    }

    //Needs work
    private boolean validateTaskType() {
        taskType = spinnerTaskType.toString();

        if (taskType.isEmpty()) {
            return false;
        }

        else {
            return true;
        }
    }

    private void radioGroupImportanceInitializer() {

        String notImportant1 = "<font color='#FD3026'>!</font>";
        String notImportant2 = "<font color='#FFFFFF'>!!!!</font>";
        radioButtonNotImportant.setText(Html.fromHtml(notImportant1 + notImportant2));

        String somewhatImportant1 = "<font color='#FE8332'>!!</font>";
        String somewhatImportant2 = "<font color='#FFFFFF'>!!!</font>";
        radioButtonSomewhatImportant.setText(Html.fromHtml(somewhatImportant1 + somewhatImportant2));

        String important1 = "<font color='#F8D742'>!!!</font>";
        String important2 = "<font color='#FFFFFF'>!!</font>";
        radioButtonImportant.setText(Html.fromHtml(important1 + important2));

        String veryImportant1 = "<font color='#AEE76C'>!!!!</font>";
        String veryImportant2 = "<font color='#FFFFFF'>!</font>";
        radioButtonVeryImportant.setText(Html.fromHtml(veryImportant1 + veryImportant2));

        String reallyImportant = "<font color='#58EA9B'>!!!!!</font>";
        radioButtonReallyImportant.setText(Html.fromHtml(reallyImportant));
    }

    private void focusView(View view) {
        view.requestFocus();
        InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getApplicationContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethod != null;
        Objects.requireNonNull(inputMethod).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
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

    private void waveListener() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
    }
}
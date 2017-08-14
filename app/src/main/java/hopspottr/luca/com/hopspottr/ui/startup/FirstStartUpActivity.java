package hopspottr.luca.com.hopspottr.ui.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hopspottr.luca.com.hopspottr.R;

public class FirstStartUpActivity extends AppCompatActivity {

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start_up);

        btnNext = (Button) findViewById(R.id.btn_next);

        initUI();
    }

    private void initUI() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstStartUpActivity.this, SecondStartUpActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
    }
}

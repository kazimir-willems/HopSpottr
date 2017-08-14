package hopspottr.luca.com.hopspottr.ui.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import hopspottr.luca.com.hopspottr.R;
import hopspottr.luca.com.hopspottr.ui.HomeActivity;

public class ThirdStartUpActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_start_up);

        btnFinish = (Button) findViewById(R.id.btn_finish);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdStartUpActivity.this, HomeActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

                finish();
            }
        });
    }
}

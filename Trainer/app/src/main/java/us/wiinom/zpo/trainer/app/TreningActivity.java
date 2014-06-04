package us.wiinom.zpo.trainer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lubo on 02.06.14.
 */
public class TreningActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.trening);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(String.valueOf(getIntent().getSerializableExtra("SelectedTrainingFile")));

        Button btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

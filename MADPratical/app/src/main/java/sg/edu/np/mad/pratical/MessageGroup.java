package sg.edu.np.mad.pratical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button grp1 = findViewById(R.id.grp1);
        Button grp2 = findViewById(R.id.grp2);
        TextView group1text = findViewById(R.id.group1);
        CardView homeicon = findViewById(R.id.croptocircle);

        grp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeicon.setVisibility(View.GONE);
                group1text.setVisibility(View.VISIBLE);
            }
        });

        grp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group1text.setVisibility(View.GONE);
                homeicon.setVisibility(View.VISIBLE);
            }
        });
    }
}
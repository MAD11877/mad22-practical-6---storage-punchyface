package sg.edu.np.mad.pratical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    private UserDatabaseHandler db;
    public Button dfollow;
    public Button dmessage;
    public TextView dname;
    public TextView ddescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new UserDatabaseHandler(this);
        dfollow = findViewById(R.id.followbtn);
        dmessage = findViewById(R.id.messagbtn);
        dname = findViewById(R.id.helloWorld);
        ddescription = findViewById(R.id.Description);


        Integer myUserid = helloUser();
        dfollow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User myUser = db.getUser(myUserid);
                if (myUser.isFollowed() == false){
                    myUser.setFollowed(true);
                    db.updateUser(myUser);
                    dfollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                else {
                    myUser.setFollowed(false);
                    db.updateUser(myUser);
                    dfollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendtomsg = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(sendtomsg);
            }
        });


    }
    public Integer helloUser(){
        Intent recievetomain = getIntent();
        Integer myUserid = recievetomain.getIntExtra("userid",0);
        User myUser = db.getUser(myUserid);
        if (myUser.isFollowed() == false){
            dfollow.setText("Follow");
        }
        else {
            dfollow.setText("Unfollow");
        }
        ddescription.setText(myUser.getDescription());
        dname.setText(myUser.getName());
        return myUser.getId();
    }
}
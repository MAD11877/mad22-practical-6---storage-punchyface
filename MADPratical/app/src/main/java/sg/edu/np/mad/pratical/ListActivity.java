package sg.edu.np.mad.pratical;
import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;


public class ListActivity extends AppCompatActivity {
    private UserDatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = new UserDatabaseHandler(this);

        //TODO uncomment create user function
        //createuser(20);

        //RecyclerView
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerView);
        Adapter adapter = new Adapter(this, db.allUser(), R.mipmap.ic_launcher_round);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }
    public void createuser(Integer totaluser){
        for (Integer i = 1; i <= totaluser; i++){
            Random rand = new Random();
            Integer randomnum1 = rand.nextInt(999999999);
            String name = "Name " + randomnum1;
            Integer randomnum2 = rand.nextInt(999999999);
            String description = "Description " + randomnum2;
            User myUser = new User(name, description, i,false);
            db.addUser(myUser);
        }
    }
}
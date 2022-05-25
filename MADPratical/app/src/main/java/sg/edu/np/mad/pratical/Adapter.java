package sg.edu.np.mad.pratical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context ct;
    ArrayList<User> userList;
    int images;

    public Adapter(Context ct, ArrayList<User> userList, int images) {
        this.ct = ct;
        this.userList = userList;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User userobj = userList.get(position);
        String desc = userobj.getDescription();
        holder.ddescription.setText(desc);
        holder.dname.setText(userobj.getName());
        holder.dimages.setImageResource(images);

        if (desc.endsWith("7")){
            holder.hugeicon.setVisibility(View.VISIBLE);
        }

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                builder.setTitle("Profile");
                builder.setMessage(userobj.getName());
                builder.setCancelable(false);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent sendtomain = new Intent(ct, MainActivity.class);
                        sendtomain.putExtra("userid", userList.get(position).getId());
                        ct.startActivity(sendtomain);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dname;
        TextView ddescription;
        ImageView dimages;
        CardView profile;
        CardView hugeicon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dname = itemView.findViewById(R.id.Name);
            ddescription = itemView.findViewById(R.id.Description);
            dimages = itemView.findViewById(R.id.homeicon);
            profile = itemView.findViewById(R.id.profile);
            hugeicon = itemView.findViewById(R.id.hugeicon);
        }
    }
}

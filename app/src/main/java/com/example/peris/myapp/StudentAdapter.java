package com.example.peris.myapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prashabt on 3/9/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

private Context context;
private List<Student> stds;
public StudentAdapter(Context context, List<Student> stds){
this.context=context;
this.stds=stds;

}
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.home_recycler_display,parent,false);
        StudentViewHolder viewHolder= new StudentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
    Student student=stds.get(position);
    holder.nam.setText(student.getName());
    holder.email.setText(student.getEmail());
    holder.cont.setText(student.getContact());



    }

    @Override
    public int getItemCount() {
        return stds.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView nam,email,cont;
        private Button logout;

        public StudentViewHolder(View itemView) {
            super(itemView);
            nam=itemView.findViewById(R.id.home_name);
            email=itemView.findViewById(R.id.home_email);
            cont=itemView.findViewById(R.id.home_contact);
            logout=itemView.findViewById(R.id.logout);


        }
    }

    public interface RecyclerViewClickListerner{

    void onViewClick();
    }
}

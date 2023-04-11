package com.example.realm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.viewHolder> {

    List<DataModal> dataModalList;
    Context context;

    public RecycleViewAdapter(List<DataModal> dataModalList, Context context) {
        this.dataModalList = dataModalList;
        this.context = context;
    }




    @NonNull
    @Override
    public RecycleViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_course_item,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.viewHolder holder, int position) {

        DataModal modal = dataModalList.get(position);
        holder.vh_CourseName.setText(modal.getCourseName());
        holder.vh_CourseTrack.setText(modal.getCourseTracks());
        holder.vh_CourseDuration.setText(modal.getCourseTracks());
        holder.vh_CourseDesc.setText(modal.getCourseDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("courseName",modal.getCourseName());
                i.putExtra("courseDescription",modal.getCourseDescription());
                i.putExtra("courseDuration",modal.getCourseDuration());
                i.putExtra("courseTracks",modal.getCourseTracks());
                i.putExtra("id",modal.getId());
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataModalList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView vh_CourseName;
        private  TextView vh_CourseDuration;
        private TextView vh_CourseTrack;
        private TextView vh_CourseDesc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            vh_CourseName = itemView.findViewById(R.id.CourseName);

            vh_CourseDuration = itemView.findViewById(R.id.courseduration);

            vh_CourseTrack = itemView.findViewById(R.id.courseduration);

            vh_CourseDesc = itemView.findViewById(R.id.coursedescription);

        }
    }
}

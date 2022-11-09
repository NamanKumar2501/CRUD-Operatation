package com.example.crudoperation.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudoperation.Model.CourseRVModal;
import com.example.crudoperation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHoled> {

    int lastPos = -1;
    private ArrayList<CourseRVModal>courseRVModalArrayList;
    private Context context;
    private CourseClickInterface courseClickInterface;


    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVModalArrayList = courseRVModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHoled onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_rv_item,parent, false);
        return new ViewHoled(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHoled holder, @SuppressLint("RecyclerView") int position) {
        CourseRVModal courseRVModal = courseRVModalArrayList.get(position);
        holder.courseNameTV.setText(courseRVModal.getCourseName());
        holder.coursePriseTV.setText("RS. "+courseRVModal.getCoursePrice());
        Picasso.get().load(courseRVModal.getCourseImg()).into(holder.courseIV);
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position){
        if (position > lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return courseRVModalArrayList.size();
    }


    public interface CourseClickInterface{
        void onCourseClick(int position);
    }

    public class ViewHoled extends RecyclerView.ViewHolder{

        private TextView courseNameTV, coursePriseTV;
        private ImageView courseIV;

        public ViewHoled(@NonNull View itemView) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            coursePriseTV = itemView.findViewById(R.id.idTVPrice);
            courseIV = itemView.findViewById(R.id.idIVCourse);

        }
    }

}

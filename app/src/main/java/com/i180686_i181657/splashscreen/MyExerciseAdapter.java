package com.i180686_i181657.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyExerciseAdapter extends RecyclerView.Adapter<MyExerciseAdapter.ViewHolder>
{
    ExercisesData[] exercisesData;
    ExercisesData[] exercisesDataAll;
    Context context;
    public MyExerciseAdapter(ExercisesData[] exercisesData , HomeScreen activity)
    {
        this.exercisesData = exercisesData;
        this.exercisesDataAll = exercisesData;
        this.context = activity ;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.exercises_list , parent , false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final ExercisesData exercisesDatalist = exercisesData[position];
        holder.exerciseName.setText(exercisesDatalist.getEx_Name());
        holder.exerciseDesc.setText(exercisesDatalist.getEx_Desc());
        holder.exerciseImage.setImageResource(exercisesDatalist.getEx_pic());


        holder.exerciseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , level_activity.class) ;
                intent.putExtra("imagename" , exercisesDatalist.getEx_pic()) ;
                intent.putExtra("ExerciesName" , exercisesDatalist.getEx_Name()) ;
                intent.putExtra("Description" , exercisesDatalist.getEx_Desc()) ;

                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }



        });



    }

    @Override
    public int getItemCount()
    {

        return 8;
    }
//
//    @Override
//    public void OnNoteClick(int position)
//    {
//
//    }


    public class ViewHolder  extends RecyclerView.ViewHolder
    {
        ImageView exerciseImage ;
        TextView exerciseName ;
        TextView exerciseDesc ;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            exerciseImage = itemView.findViewById(R.id.recyclerview1imageview);
            exerciseName = itemView.findViewById(R.id.exercisename);
            exerciseDesc = itemView.findViewById(R.id.exercisedetails) ;

        }

    }
    public interface OnNoteListener
    {
        void OnNoteClick(int position);
    }
}

package com.example.hamid_pc.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class StudentListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private FirebaseRecyclerAdapter<Student, StudentViewHolder> mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    //TODO: Make Signout work in Student Panel
    public static  StudentListFragment NewInstance(){
        StudentListFragment studentListFragment = new StudentListFragment();
        return studentListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("students");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.student_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();

        return view;
    }

    public void UpdateUI(){

      mAdapter = new FirebaseRecyclerAdapter<Student,StudentViewHolder>(
              Student.class,
              R.layout.list_student,
              StudentViewHolder.class,
              mDatabaseReference
      ){
          @Override
          protected void populateViewHolder(StudentViewHolder viewHolder, Student model, int position) {

              viewHolder.StudentText.setText(model.getmName());
              viewHolder.ProgramEnrolled.setText(model.getmEnrolledProgram());
              Student student = getItem(position);
              viewHolder.bindView(student);

          }

      };

        mRecyclerView.setAdapter(mAdapter);

    }


    public static class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView StudentText;
        TextView ProgramEnrolled;
        Student mStudent;
        String mUUID;


        public StudentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            StudentText = (TextView) itemView.findViewById(R.id.list_item_student_name);
            ProgramEnrolled = (TextView) itemView.findViewById(R.id.program_enrolled);

        }

        @Override
        public void onClick(View v) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if(appCompatActivity instanceof StudentListActivity){
               StudentListActivity studentListActivity = (StudentListActivity) appCompatActivity;
                mUUID = this.mStudent.getmUuid();
                Intent intent = StudentProfileActivity.newIntent(studentListActivity, mUUID);
                studentListActivity.startActivity(intent);

            }

        }

        public void bindView (Student student){
            this.mStudent = student;
        }
    }


}

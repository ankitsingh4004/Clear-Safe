package com.workorder.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.workorder.app.R;
import com.workorder.app.Util;
import com.workorder.app.pojo.survey.SurveyQuestionPojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Survey extends RecyclerView.Adapter<Survey.QuestionListAdapter> {
    Context context;
    List<SurveyQuestionPojo.SurveyAnswer> attachementPOJOList;
    private RatingSelectionInterface ratingSelectionInterface;
    int pos;
    int selectedpos=-1;
    boolean buttonOn;
    private LinkedHashMap<Integer, String> map=new LinkedHashMap<>();
    public List<SurveyQuestionPojo> lcs;

    public Survey(Context context, List<SurveyQuestionPojo.SurveyAnswer> attachementPOJOList, int pos, RatingSelectionInterface ratingSelectionInterface, LinkedHashMap<Integer, String> map, List<SurveyQuestionPojo> lcs) {
        this.context = context;
        this.attachementPOJOList=attachementPOJOList;
        this.pos=pos;
        this.ratingSelectionInterface=ratingSelectionInterface;
        this.map=map;
        this.lcs=lcs;
    }

    @NonNull
    @Override
    public QuestionListAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.survey_quesadapter, parent, false);

        return new QuestionListAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionListAdapter holder, final int position) {

        try {

            holder.option.setText(attachementPOJOList.get(position).getSURVEYANSWERTITLE());
            if(attachementPOJOList.get(position).getSURVEYACOMMENT()!=null){
            holder.comment.setText("Desc - "+attachementPOJOList.get(position).getSURVEYACOMMENT());
            }else {
                holder.comment.setVisibility(View.GONE);

            }

            if(lcs.get(pos).getQUESTIONTYPEID()==3){
                    holder.freetext.setVisibility(View.VISIBLE);
                    holder.optionchoice.setVisibility(View.GONE);

            }else {
                    holder.freetext.setVisibility(View.GONE);
            }

            holder.text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    ratingSelectionInterface.itemselect(lcs.get(pos).getSurveyAnswers().get(position).getSURVEYQUESTIONID(),attachementPOJOList.get(position).getSURVEYANSWERID(),  attachementPOJOList.get(position).getGOTOQUESTIONID(), lcs.get(pos).getSURVEYID(), holder.text.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    ratingSelectionInterface.itemselect(lcs.get(pos).getSurveyAnswers().get(position).getSURVEYQUESTIONID(),attachementPOJOList.get(position).getSURVEYANSWERID(),  attachementPOJOList.get(position).getGOTOQUESTIONID(), lcs.get(pos).getSURVEYID(), holder.text.getText().toString());

                }
            });
            if(selectedpos==position) {
                holder.imag.setImageResource(R.drawable.ic_check_box_black_24dp);
            } else {
                holder.imag.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedpos=position;
                   /* if(attachementPOJOList.get(position).getGOTOQUESTIONID()==null){
                        ratingSelectionInterface.itemselect(lcs.get(pos).getSurveyAnswers().get(position).getSURVEYQUESTIONID(), attachementPOJOList.get(position).getSURVEYANSWERID(), "", lcs.get(pos).getSURVEYID(), attachementPOJOList.get(position).getSURVEYANSWERTITLE());

                    }else {*/
                  //  }
                    notifyDataSetChanged();
                }
            });


            if(map.size()!=0) {
                for (Map.Entry mEntry : map.entrySet()) {
                    if (mEntry.getKey().equals(lcs.get(pos).getSURVEYQQUESTIONID())) {
                        try {
                            String srValue = mEntry.getValue().toString();
                            String aa = srValue.substring(srValue.indexOf(",") + 1);
                            String surveyAnswerID = Util.before(aa, ",");
                            String aaa = aa.substring(aa.indexOf(",") + 1);
                            String surveyID = Util.before(aaa, ",");
                            String selecion = Util.after(aaa, ",");
                            for (int i = 0; i < attachementPOJOList.size(); i++) {
                                Log.v("value", selecion + "    " + attachementPOJOList.get(i).getSURVEYANSWERTITLE());
                                if (attachementPOJOList.get(i).getSURVEYANSWERTITLE().equalsIgnoreCase(selecion)) {
                                     if(i==position)
                                      holder.imag.setImageResource(R.drawable.ic_check_box_black_24dp);
                                }
                            }

                            if(lcs.get(pos).getSurveyAnswers().get(position).getSURVEYQUESTIONID() == mEntry.getKey()){
                                holder.text.setText(selecion);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else {

            }

        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return attachementPOJOList.size();
    }




    public class QuestionListAdapter extends RecyclerView.ViewHolder {
        TextView option,comment;
        ImageView imag;
        EditText text;
        LinearLayout optionchoice,freetext,check;
        public QuestionListAdapter(@NonNull View itemView) {
            super(itemView);
            option=itemView.findViewById(R.id.option);
            comment=itemView.findViewById(R.id.comment);
            imag=itemView.findViewById(R.id.img);
            optionchoice=itemView.findViewById(R.id.optionchoice);
            freetext=itemView.findViewById(R.id.freetext);
            text=itemView.findViewById(R.id.text);


        }


    }

    public interface RatingSelectionInterface {
        void itemselect(Integer questionID, Integer answerID, Object gotoid, Integer surveyID, String aswer);
        void itemUnSelect(int questionID);
    }


}

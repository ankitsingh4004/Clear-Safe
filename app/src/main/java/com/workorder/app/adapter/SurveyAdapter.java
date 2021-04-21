package com.workorder.app.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.workorder.app.R;
import com.workorder.app.Util;
import com.workorder.app.activity.SubmitPojo;

import com.workorder.app.pojo.survey.SurveyQuestionPojo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.QuestionListAdapter> {
    Context context;
    List<SurveyQuestionPojo.SurveyAnswer> attachementPOJOList;
    private RatingSelectionInterface ratingSelectionInterface;
    int pos;
    public static String selected="";
    int selectedpos=-1;
    boolean buttonOn;
    private LinkedHashMap<Integer, String> map=new LinkedHashMap<>();
    public static LinkedHashMap<Integer, SubmitPojo> map3=new LinkedHashMap<>();
    public List<SurveyQuestionPojo> lcs;
    ArrayList<Integer> answer=new ArrayList<>();

    public SurveyAdapter(Context context, List<SurveyQuestionPojo.SurveyAnswer> attachementPOJOList, int pos, RatingSelectionInterface ratingSelectionInterface, LinkedHashMap<Integer, String> map, List<SurveyQuestionPojo> lcs) {
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
                .inflate(R.layout.survey_adapter, parent, false);

        return new QuestionListAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionListAdapter holder, final int position) {

        try {
            int p=pos+1;
            holder.ques.setText(p+"."+lcs.get(pos).getQUESTIONTITLE());

            if(lcs.get(pos).getQUESTIONTYPEID()==3){
                holder.radioGroup.setVisibility(View.GONE);
                holder.check.setVisibility(View.GONE);
                holder.blinear.setVisibility(View.GONE);
                holder.freetext.setVisibility(View.VISIBLE);

            }else if(lcs.get(pos).getQUESTIONTYPEID()==4) {
                holder.check.setVisibility(View.VISIBLE);
                holder.freetext.setVisibility(View.GONE);
                holder.radioGroup.setVisibility(View.GONE);
                holder.blinear.setVisibility(View.VISIBLE);
            }else{
                holder.check.setVisibility(View.GONE);
                holder.freetext.setVisibility(View.GONE);
                holder.radioGroup.setVisibility(View.VISIBLE);
                holder.blinear.setVisibility(View.VISIBLE);
            }

            RadioButton[] rb = new RadioButton[lcs.get(pos).getSurveyAnswers().size()];

            for (int i = 0; i < lcs.get(pos).getSurveyAnswers().size(); i++) {
                rb[i] = new RadioButton(context);
                rb[i].setTextSize(16f);
                rb[i].setPadding(10,5,5,10);
                ColorStateList darkStateList = ContextCompat.getColorStateList(context, R.color.checkbox_tinit_dark_theme);
                CompoundButtonCompat.setButtonTintList(rb[i], darkStateList);
              //  rb[i].setButtonDrawable(R.drawable.checkbox);
                rb[i].setText(lcs.get(pos).getSurveyAnswers().get(i).getSURVEYANSWERTITLE());

                if(lcs.get(pos).getSurveyAnswers().get(i).getSURVEYACOMMENT()==null){
                    holder.radioGroup.addView(rb[i]);
                }else {
                    TextView textView = new TextView(context);
                    textView.setTextSize(16f);
                    textView.setText("Desc - " + lcs.get(pos).getSurveyAnswers().get(i).getSURVEYACOMMENT());

                    holder.radioGroup.addView(rb[i]);
                    holder.radioGroup.addView(textView);
                }


            }

            if(lcs.get(pos).getQUESTIONTYPEID()==4) {
            for (int i = 0; i < lcs.get(pos).getSurveyAnswers().size(); i++) {
                final CheckBox checkBox = new CheckBox(context);
                checkBox.setTextSize(16f);
                checkBox.setId(i);
                checkBox.setPadding(10,5,5,10);
                checkBox.setText(lcs.get(pos).getSurveyAnswers().get(i).getSURVEYANSWERTITLE());
                checkBox.setButtonDrawable(R.drawable.checkbox);
                if(lcs.get(pos).getSurveyAnswers().get(i).getSURVEYACOMMENT()==null){
                    holder.check.addView(checkBox);
                }else {
                    TextView textView = new TextView(context);
                    textView.setTextSize(16f);
                    textView.setText("Desc - " + lcs.get(pos).getSurveyAnswers().get(i).getSURVEYACOMMENT());

                    holder.check.addView(checkBox);
                    holder.check.addView(textView);
                }
               /* for (Map.Entry mEntry: map.entrySet())
                {
                    if (mEntry.getKey().equals(lcs.get(pos).getSURVEYQQUESTIONID()))
                    {
                        try {
                            String srValue=mEntry.getValue().toString();
                            String aa = srValue.substring(srValue.indexOf(",") + 1);
                            Log.v("value22", aa);
                            String surveyAnswerID = Util.before(aa, ",");
                            String aaa = aa.substring(aa.indexOf(",") + 1);
                            String surveyID = Util.before(aaa, ",");
                            String selecion = Util.after(aaa, ",");

                        List<String> cList = Arrays.asList(srValue.split(","));
                        for (int q = 0; q < lcs.get(pos).getSurveyAnswers().size(); q++) {
                            Log.v("value23", srValue);
                            for(int j=0;j<cList.size();j++){
                                Log.v("value24", cList.get(j));
                                if (lcs.get(pos).getSurveyAnswers().get(q).getSURVEYANSWERID()== Integer.valueOf(cList.get(j)))
                                    checkBox.setChecked(true);
                            }
                        }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }*/


                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int id = checkBox.getId();
                            if (checkBox.isChecked()) {
                                String selection = (String) checkBox.getText();
                                Log.e("Selected", "" + selection);
                                Log.v("score", ratingSelectionInterface.toString());
                                answer.add(lcs.get(pos).getSurveyAnswers().get(id).getSURVEYANSWERID());

                                ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), 0, holder.comment.getText().toString(), lcs.get(pos).getSURVEYID(), attachementPOJOList.get(id).getSURVEYANSWERTITLE());
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, holder.comment.getText().toString());
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                Log.v("SELECt4",map3.toString());
                                Log.v("SELECt4", String.valueOf(submitPojo.getYes()));
                                Log.v("SELEC4", String.valueOf(submitPojo.getAnswer()));
                            } else {
                                for (int a = 0; a < answer.size(); a++) {
                                    if (lcs.get(pos).getSurveyAnswers().get(id).getSURVEYANSWERID() == answer.get(a)) {
                                        answer.remove(a);
                                    }
                                }
                                map3.remove(lcs.get(pos).getSURVEYQQUESTIONID());
                                ratingSelectionInterface.itemUnSelect(lcs.get(pos).getSURVEYQQUESTIONID());
                                Log.v("SELECt4",map3.toString());

                            }
                        }
                    });

                 /*   for (Map.Entry mEntry: map1.entrySet())
                    {
                        if (mEntry.getKey().equals(lcs.get(pos).getSurveyAnswers().get(position).getSURVEYQUESTIONID()))
                        {
                            try {

                                String srValue =mEntry.getValue().toString();
                                String aa = srValue.substring(srValue.indexOf(",") + 1);
                                String surveyAnswerID = Util.before(aa, ",");
                                String aaa = aa.substring(aa.indexOf(",") + 1);
                                String surveyID = Util.before(aaa, ",");
                                String aaaa = aaa.substring(aaa.indexOf(",") + 1);
                                String yes = Util.before(aaaa, ",");
                                String comment = Util.after(aaaa, ",");

                                JSONArray jsonArray=new JSONArray(surveyID);
                              for (int b = 0; b < lcs.get(pos).getSurveyAnswers().size(); b++) {
                                    for (int a = 0; a < jsonArray.length(); a++) {
                                        if (lcs.get(pos).getSurveyAnswers().get(b).getSURVEYANSWERID() == jsonArray.get(a)) {
                                           checkBox.setChecked(true);
                                           Log.v("aq",jsonArray.get(a).toString());
                                        }else {
                                            checkBox.setChecked(false);
                                        }
                                    }
                              }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }*/


               }

            }


            Log.v("SELECt5",map3.toString());

            holder.edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(holder.edittext.length() == 0){
                        map3.remove(lcs.get(pos).getSURVEYQQUESTIONID());
                    }else{
                        SubmitPojo submitPojo = new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), holder.edittext.getText().toString(), answer, false, "");
                        map3.put(lcs.get(pos).getSURVEYQQUESTIONID(), submitPojo);
                        ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), 0, "0", lcs.get(pos).getSURVEYID(), charSequence.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(holder.edittext.length() == 0){
                        map3.remove(lcs.get(pos).getSURVEYQQUESTIONID());
                    }else{
                        SubmitPojo submitPojo = new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), holder.edittext.getText().toString(), answer, false, holder.comment.getText().toString());
                        map3.put(lcs.get(pos).getSURVEYQQUESTIONID(), submitPojo);
                        ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), 0, "0", lcs.get(pos).getSURVEYID(), editable.toString());
                    }
                }
            });

            for (Map.Entry mEntry: map.entrySet())
            {
                if (mEntry.getKey().equals(lcs.get(pos).getSURVEYQQUESTIONID()))
                {
                    try {
                        String srValue=mEntry.getValue().toString();
                        String aa = srValue.substring(srValue.indexOf(",") + 1);
                        String surveyAnswerID = Util.before(aa, ",");
                        String aaa = aa.substring(aa.indexOf(",") + 1);
                        String surveyID = Util.before(aaa, ",");
                        String selecion = Util.after(aaa, ",");
                        for (int i = 0; i < lcs.get(pos).getSurveyAnswers().size(); i++) {
                            Log.v("value",selecion+"    "+lcs.get(pos).getSurveyAnswers().get(i).getSURVEYANSWERTITLE());
                            if(lcs.get(pos).getSurveyAnswers().get(i).getSURVEYANSWERTITLE().equalsIgnoreCase(selecion)){
                                rb[i].setChecked(true);
                            }
                        }

               /*     if(lcs.get(pos).getQUESTIONTYPEID()==4) {
                        List<String> cList = Arrays.asList(srValue.split(","));
                        for (int i = 0; i < lcs.get(pos).getSurveyAnswers().size(); i++) {
                            Log.v("value", srValue);
                            for(int j=0;j<cList.size();j++){
                                Log.v("value", cList.get(j));
                                if (lcs.get(pos).getSurveyAnswers().get(i).getSURVEYANSWERID()== Integer.valueOf(cList.get(j)))
                                    checkBox.setChecked(true);
                            }

                        }
                    }*/
                        holder.comment.setText(surveyAnswerID);
                        if(lcs.get(pos).getQUESTIONTYPEID()==3) {
                            if (lcs.get(pos).getSURVEYQQUESTIONID() == mEntry.getKey()) {
                                holder.edittext.setText(selecion);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class QuestionListAdapter extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
        TextView ques,textview1;
        RadioGroup radioGroup;
        LinearLayout check;
        LinearLayout freetext,blinear;
        EditText edittext,comment;
        CheckBox checkbox1;


        public QuestionListAdapter(@NonNull View itemView) {
            super(itemView);
            ques = itemView.findViewById(R.id.ques);
            check = itemView.findViewById(R.id.check);
            freetext = itemView.findViewById(R.id.freetext);
            radioGroup = itemView.findViewById(R.id.radiogroup);
            edittext = itemView.findViewById(R.id.edittext);
            blinear = itemView.findViewById(R.id.blinear);
            comment = itemView.findViewById(R.id.comment);
           comment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), 0, charSequence.toString(), lcs.get(pos).getSURVEYID(), selected);

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), 0, editable.toString(), lcs.get(pos).getSURVEYID(), selected);

                    //    coment=comment.getText().toString();
                    if(lcs.get(pos).getQUESTIONTYPEID()==1){
                        // 375779\
                        if(answer.size()==0){
                            //   Toast.makeText(context,"Select one option first",Toast.LENGTH_LONG).show();
                        }else {
                            if(selected.equalsIgnoreCase("yes")){
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,true,editable.toString());
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                            }else if(selected.equalsIgnoreCase("no")) {
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,false,editable.toString());
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                            }
                        }

                    }else {
                        if(answer.size()==0){
                         //   Toast.makeText(context,"Select one option first",Toast.LENGTH_LONG).show();
                        }else {
                            SubmitPojo submitPojo = new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, editable.toString());
                            map3.put(lcs.get(pos).getSURVEYQQUESTIONID(), submitPojo);
                        }
                    }
                }
            });

            radioGroup.setOnCheckedChangeListener(this);
            edittext.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent event) {

                    if (view.getId() == R.id.edittext) {
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                view.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int i) {
            int index = getLayoutPosition();
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(context, "Please select answer", Toast.LENGTH_LONG).show();
            } else {
                try {
                    int radioButtonId = group.getCheckedRadioButtonId();
                    View radio = group.findViewById(radioButtonId);
                    int position = group.indexOfChild(radio);
                    RadioButton butt = (RadioButton) radioGroup.getChildAt(position);

                        String selection = (String) butt.getText();

                        answer=new ArrayList<>();
                        Log.e("Selected", "" + selection);
                        Log.v("score", ratingSelectionInterface.toString());
                        selected = selection;
                        answer.add(attachementPOJOList.get(position).getSURVEYANSWERID());
                        ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), 0, comment.getText().toString(), lcs.get(pos).getSURVEYID(), attachementPOJOList.get(position).getSURVEYANSWERTITLE());
                        if (lcs.get(pos).getQUESTIONTYPEID() == 1) {
                            if (selection.equalsIgnoreCase("Yes")) {
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, true, comment.getText().toString());
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                Log.v("SELECt",map3.toString());
                                Log.v("SELECt", String.valueOf(submitPojo.getYes()));
                            } else {
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, comment.getText().toString());
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                Log.v("SELECt1",map3.toString());
                                Log.v("SELECt1", String.valueOf(submitPojo.getYes()));
                            }
                        } else {

                            SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, comment.getText().toString());
                            map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);

                            Log.v("SELECt2",map3.toString());
                            Log.v("SELECt2", String.valueOf(submitPojo.getYes()));
                            Log.v("SELECt2", String.valueOf(submitPojo.getAnswer()));
                        }

                } catch (Exception e) {

                }
            }
        }
    }

    public interface RatingSelectionInterface {
        void itemselect(Integer questionID, Integer answerID, String comment, Integer surveyID, String aswer);
        void itemUnSelect(int questionID);
    }

}

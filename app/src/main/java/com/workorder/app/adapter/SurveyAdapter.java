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

import java.util.ArrayList;
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
    private LinkedHashMap<Integer, String> map1=new LinkedHashMap<>();
    public static LinkedHashMap<Integer, ArrayList<Integer>> map2=new LinkedHashMap<>();
    public static LinkedHashMap<Integer, SubmitPojo> map3=new LinkedHashMap<>();
    public List<SurveyQuestionPojo> lcs;
    public static String coment;
    ArrayList<Integer> answer=new ArrayList<>();

    public SurveyAdapter(Context context, List<SurveyQuestionPojo.SurveyAnswer> attachementPOJOList, int pos, RatingSelectionInterface ratingSelectionInterface, LinkedHashMap<Integer, String> map, List<SurveyQuestionPojo> lcs, LinkedHashMap<Integer, String> map1) {
        this.context = context;
        this.attachementPOJOList=attachementPOJOList;
        this.pos=pos;
        this.ratingSelectionInterface=ratingSelectionInterface;
        this.map=map;
        this.map1=map1;
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


//            for( int i = 0; i <=attachementPOJOList.get(pos).getSurveyquestPOJOS().size(); i++) {
//                final CheckBox cb = new CheckBox(context);
//                cb.setText(attachementPOJOList.get(pos).getSurveyquestPOJOS().get(i).getTitle());
//                cb.setPadding(0,0,0,5);
//                ColorStateList darkStateList = ContextCompat.getColorStateList(context, R.color.checkbox_tinit_dark_theme);
//                CompoundButtonCompat.setButtonTintList(cb, darkStateList);
//                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//                {                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//
//                        String string = buttonView.getText().toString();
//
//                        if(!isChecked){
//
//                        }
//                        else
//                        {
//                            SubmitSurveyAns informationBean = new SubmitSurveyAns(attachementPOJOList.get(pos).getQuestionID(), attachementPOJOList.get(pos).getSurveyquestPOJOS().get(position).getAnswerID(), attachementPOJOList.get(pos).getSurveyAID(), attachementPOJOList.get(pos).getSurveyID(), attachementPOJOList.get(pos).getSurveyquestPOJOS().get(position).getSurveyQID());
//                            map1.put(attachementPOJOList.get(pos).getQuestionID(), informationBean);
//                          //  notifyDataSetChanged();
//                            Log.v("shalu1",map1.toString());
//                            try{
//
//                                ratingSelectionInterface.itemselect(attachementPOJOList.get(pos).getQuestionID(), string, attachementPOJOList.get(pos).getSurveyAID(), attachementPOJOList.get(pos).getSurveyID(), attachementPOJOList.get(pos).getSurveyquestPOJOS().get(pos).getSurveyQID());
//                            }catch (Exception ee){}
//
//                        }
//                    }
//                });
//                holder.check.addView(cb);
//
//            }
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

              if(lcs.get(pos).getQUESTIONTYPEID()==4) {
                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = checkBox.getId();
                            if (checkBox.isChecked()) {
                                String selection = (String) checkBox.getText();
                                Log.e("Selected", "" + selection);
                                Log.v("score", ratingSelectionInterface.toString());
                                selected = selection;
                                ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), attachementPOJOList.get(id).getSURVEYANSWERID(), attachementPOJOList.get(id).getGOTOQUESTIONID(), lcs.get(pos).getSURVEYID(), attachementPOJOList.get(id).getSURVEYANSWERTITLE());

                                answer.add(lcs.get(pos).getSurveyAnswers().get(id).getSURVEYANSWERID());
                                map2.put(lcs.get(pos).getSURVEYQQUESTIONID(),answer);
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, "");
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, "false", "");
                            } else {
                                for (int a = 0; a < answer.size(); a++) {
                                    if (lcs.get(pos).getSurveyAnswers().get(id).getSURVEYANSWERID() == answer.get(a)) {
                                        answer.remove(a);
                                    }
                                }
                                map2.put(lcs.get(pos).getSURVEYQQUESTIONID(),answer);
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, "");
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, "false", "");

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

            holder.comment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    coment=holder.comment.getText().toString();

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    coment=holder.comment.getText().toString();
                    if(lcs.get(pos).getQUESTIONTYPEID()==1){
                        if(selected.equalsIgnoreCase("yes")){
                            SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,true,coment);
                            map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                            ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,"true",coment);
                        }else {
                            SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,false,coment);
                            map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                            ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,"false",coment);
                        }
                    }else {
                        SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,false,coment);
                        map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                        ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),"",answer,"",coment);
                    }
                }
            });

            holder.edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),holder.edittext.getText().toString(),answer,false,"");
                    map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                    ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(),0, 0, lcs.get(pos).getSURVEYID(), editable.toString());
                    ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(),lcs.get(pos).getPARENTQID(),holder.edittext.getText().toString(),answer,"","");

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


            Log.v("map",map.toString());


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
                        ratingSelectionInterface.itemselect(lcs.get(pos).getSURVEYQQUESTIONID(), attachementPOJOList.get(position).getSURVEYANSWERID(), attachementPOJOList.get(position).getGOTOQUESTIONID(), lcs.get(pos).getSURVEYID(), attachementPOJOList.get(position).getSURVEYANSWERTITLE());
                        if (lcs.get(pos).getQUESTIONTYPEID() == 1) {
                            if (selection.equalsIgnoreCase("yes")) {
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, true, "");
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, "true", "");
                            } else {
                                SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, "");
                                map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                                ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, "false", "");
                            }
                        } else {
                            answer.add(attachementPOJOList.get(position).getSURVEYANSWERID());
                            SubmitPojo submitPojo=new SubmitPojo(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, false, "");
                            map3.put(lcs.get(pos).getSURVEYQQUESTIONID(),submitPojo);
                            ratingSelectionInterface.itemselect1(lcs.get(pos).getSURVEYQQUESTIONID(), lcs.get(pos).getPARENTQID(), "", answer, "", "");
                        }

                } catch (Exception e) {

                }
            }
        }
    }

    public interface RatingSelectionInterface {
        void itemselect(Integer questionID, Integer answerID, Object gotoid, Integer surveyID, String aswer);
        void itemselect1(Integer questionID, Object ParentQuestionId, String FreeText,ArrayList<Integer> answer,String yes,String comment);
        void itemUnSelect(int questionID);
    }

}

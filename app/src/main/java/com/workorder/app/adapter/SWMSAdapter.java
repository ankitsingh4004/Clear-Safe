package com.workorder.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.workorder.app.R;
import com.workorder.app.activity.MapsActivity;
import com.workorder.app.activity.ShowDocumentActivity;
import com.workorder.app.activity.ShowPdf;
import com.workorder.app.pojo.docPOJO.AttachementPOJO;
import com.workorder.app.pojo.docPOJO.GetSwmsTemplate;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UtilityFunction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SWMSAdapter extends RecyclerView.Adapter<SWMSAdapter.DocListViewHolder> {

    Context context;
    List<GetSwmsTemplate> attachementPOJOList;
    int assessmentid;
   // DocClickListner mclickListner;
   // List<Attachement> attachementList;

    public SWMSAdapter(Context context, List<GetSwmsTemplate> attachementPOJOList, int assessmentid) {
        this.context = context;
        this.attachementPOJOList=attachementPOJOList;
        this.assessmentid=assessmentid;
      //  this.mclickListner = clickListner;
      //  this.attachementList = attachement;
    }

    @Override
    public DocListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_doc_list, parent, false);

        return new DocListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DocListViewHolder holder, final int position) {
        final GetSwmsTemplate attachementPOJO=attachementPOJOList.get(position);


            holder.tv_DocName.setText("" + attachementPOJO.getTitle());


            holder.tv_version.setText("V.no : " + attachementPOJO.getVersion());
            holder.tv_doc_temp.setText("" + attachementPOJO.getTemplateNumber());
            holder.tv_doc_date.setText(UtilityFunction.getSplitedDate(attachementPOJO.getAssignedDate()));
        if(attachementPOJO.getSWMSStatus().equalsIgnoreCase("Signed" +
                "")){
            holder.workOrderStatus.setText(attachementPOJO.getSWMSStatus());
        }else {
            holder.workOrderStatus.setText("Active");
        }

        holder.warningtext.setText(attachementPOJO.getSWMSWarningLevel());
        if(attachementPOJO.getSWMSWarningLevel().equalsIgnoreCase("Ok")){
            holder.warning.setImageResource(R.drawable.ic_warning_black2_24dp);
        }else if(attachementPOJO.getSWMSWarningLevel().equalsIgnoreCase("Failure")){
            holder.warning.setImageResource(R.drawable.ic_warning_black3_24dp);
        }else {
            holder.warning.setImageResource(R.drawable.ic_warning_black1_24dp);
        }
        PdfTemplate gridAdapter = new PdfTemplate(context,attachementPOJOList.get(position).getAttachements() ,assessmentid,attachementPOJO);
        holder.recycler_view.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return attachementPOJOList.size();
    }

    public class DocListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_DocName,tv_doc_temp,tv_signed_status;
        TextView tv_doc_date;
        TextView tv_version;
        CardView taskCard;
        RecyclerView recycler_view;
     //   DocClickListner myclickListner;
        Integer position;
        TextView warningtext;
        TextView workOrderStatus;

        ImageView warning;
        LinearLayout ds;
        Button file;

        public DocListViewHolder(View itemView) {
            super(itemView);
          //  position = viewType;
          //  this.myclickListner = clickListner;
            tv_DocName = itemView.findViewById(R.id.tv_doc_name);

            tv_doc_temp = itemView.findViewById(R.id.tv_doc_temp);
            recycler_view = itemView.findViewById(R.id.recycler_view);
            tv_doc_date = itemView.findViewById(R.id.tv_doc_date);
            tv_version=itemView.findViewById(R.id.tv_version);

            taskCard = itemView.findViewById(R.id.firstcard);

            warning = itemView.findViewById(R.id.warning1);
            workOrderStatus = itemView.findViewById(R.id.workOrderStatus);
            warningtext = itemView.findViewById(R.id.warningtext);
            recycler_view.setNestedScrollingEnabled(false);
            RecyclerView.LayoutManager mRecyclerGrid=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            recycler_view.setLayoutManager(mRecyclerGrid);


            // downloadImage.setOnClickListener(this);
            //openButton.setOnClickListener(this);
        }
/*
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_doc_download:
                    myclickListner.docClick(getAdapterPosition());
                    break;

                case R.id.btn_doc_open:
                    myclickListner.openClick(getAdapterPosition());
                    break;
            }
        }*/
    }


   /* interface DocClickListner {

        void docClick(Integer position);


        void openClick(Integer position);
    }*/
}
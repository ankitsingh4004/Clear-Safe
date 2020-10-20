package com.workorder.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.workorder.app.R;
import com.workorder.app.activity.ShowDocumentActivity;
import com.workorder.app.pojo.docPOJO.AttachementPOJO;
import com.workorder.app.pojo.docPOJO.GetSwmsTemplate;
import com.workorder.app.util.UtilityFunction;

import java.util.List;

public class PdfTemplate extends RecyclerView.Adapter<PdfTemplate.MyViewHolder> {

    Context context;
    List<GetSwmsTemplate.Attachement> attachements;
    int assessmentid;

    public PdfTemplate(Context context, List<GetSwmsTemplate.Attachement> attachements,int assessmentid) {
        this.context=context;
        this.attachements=attachements;
        this.assessmentid=assessmentid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_pdf_list, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final GetSwmsTemplate.Attachement attachementPOJO=attachements.get(position);

        int i=position+1;
        holder.tv_DocName.setText(""+attachementPOJO.getDocumentName());
        holder.tv_doc.setText(""+attachementPOJO.getDocumentName());
        holder.img_attachment.setText(""+i+".");
        holder.tv_doc_date.setText(UtilityFunction.getSplitedDate(attachementPOJO.getAssignedDate()));

            holder.tv_signed_status.setText(attachementPOJO.getStatus());



        holder.openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowDocumentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("pdf",""+attachements.get(position).getDocumentName());
                intent.putExtra("status",attachements.get(position).getStatus());
                intent.putExtra("assesmenttemplateid",attachements.get(position).getAssesmentTemplateId());
                intent.putExtra("assesmentid",assessmentid);
                intent.putExtra("assesmentempid",attachements.get(position).getAssignedEmployeeId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return attachements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_DocName;
        TextView tv_doc;
        TextView img_attachment;
        TextView tv_doc_date;
        TextView tv_signed_status;
        CardView taskCard;
        Integer position;
        Button openButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_DocName = itemView.findViewById(R.id.tv_doc_name);
            tv_doc = itemView.findViewById(R.id.tv_doc);
            img_attachment = itemView.findViewById(R.id.img_attachment);
            tv_doc_date = itemView.findViewById(R.id.tv_doc_date);
            tv_signed_status=itemView.findViewById(R.id.tv_signed_status);
            openButton = itemView.findViewById(R.id.btn_doc_open);

            taskCard = itemView.findViewById(R.id.firstcard);
        }
    }
}

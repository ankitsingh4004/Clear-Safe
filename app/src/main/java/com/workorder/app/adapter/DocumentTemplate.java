package com.workorder.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.workorder.app.R;
import com.workorder.app.activity.ShowDocumentActivity;
import com.workorder.app.activity.ShowPdf;
import com.workorder.app.pojo.docPOJO.AssessmentPOJO;
import com.workorder.app.util.UtilityFunction;

import java.util.List;

public class DocumentTemplate extends RecyclerView.Adapter<DocumentTemplate.MyViewHolder> {
    Context context;
    List<AssessmentPOJO.Documents> attachements;
    AssessmentPOJO attachementPOJO1;
    int assessmentid;

    public DocumentTemplate(Context context, List<AssessmentPOJO.Documents> attachements, int assessmentid, AssessmentPOJO attachementPOJO) {
        this.context=context;
        this.attachements=attachements;
        this.assessmentid=assessmentid;
        this.attachementPOJO1=attachementPOJO;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.documentshow, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final AssessmentPOJO.Documents attachementPOJO=attachements.get(position);
        int i=position+1;
        holder.tv_DocName.setText(""+attachementPOJO.getFILENAME());
        holder.documentfile.setText(""+attachementPOJO.getDOCUMENTNAME());
        holder.tv_version.setText("V.no : "+attachementPOJO.getVERSION_NUMBER());
        holder.img_attachment.setText(""+i+".");
        holder.tv_doc_date.setText(UtilityFunction.getSplitedDate(attachementPOJO1.getAssignedDate()));
        if(attachementPOJO1.isSignedStatus()){
            holder.tv_signed_status.setText("Signed");
        }else {
            holder.tv_signed_status.setText("Active");
        }

    holder.btn_doc_open.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ShowPdf.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("documentname",attachementPOJO.getFILENAME());
            intent.putExtra("documenturl",attachementPOJO.getDOCUMENT_URL());
            intent.putExtra("status",String.valueOf(attachementPOJO1.isSignedStatus()));
            intent.putExtra("versionno",attachementPOJO.getVERSION_NUMBER());
            //   intent.putExtra("assesmenttemplateid",attachements.get(position).getAssesmentTemplateId());
            intent.putExtra("assesmentid",attachementPOJO1.getAssesmentId());
            //   intent.putExtra("assesmentempid",attachements.get(position).getAssignedEmployeeId());
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
        TextView tv_version;
        TextView documentfile;
RelativeLayout relateive;
        Integer position;
        Button btn_doc_open;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_DocName = itemView.findViewById(R.id.tv_doc_name);
            img_attachment = itemView.findViewById(R.id.img_attachment);
            tv_doc_date = itemView.findViewById(R.id.tv_doc_date);
            tv_signed_status=itemView.findViewById(R.id.tv_signed_status);
            tv_version=itemView.findViewById(R.id.tv_version);
            documentfile=itemView.findViewById(R.id.documentfile);
            relateive=itemView.findViewById(R.id.relateive);
            btn_doc_open=itemView.findViewById(R.id.btn_doc_open);

        }
    }
}

package com.workorder.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.workorder.app.R;
import com.workorder.app.activity.HomeActivity;
import com.workorder.app.activity.MapsActivity;
import com.workorder.app.pojo.GetWorkorderPOJO;
import com.workorder.app.pojo.WorkOrderPOJO;
import com.workorder.app.util.Constants;
import com.workorder.app.util.UtilityFunction;

import java.util.List;

public class SyncronizedHomeAdapter extends RecyclerView.Adapter<SyncronizedHomeAdapter.SynchronizedHomeHolder> {

    Context context;
    List<GetWorkorderPOJO> homePOJOList;
    String workorderno;

    public SyncronizedHomeAdapter(Context context, List<GetWorkorderPOJO> homePOJOList, String workorderno) {
        this.context = context;
        this.homePOJOList = homePOJOList;
        this.workorderno=workorderno;
    }


    @Override
    public SynchronizedHomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_synchronized_home, parent, false);
        return new SynchronizedHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(SynchronizedHomeHolder holder, final int position) {

        try {
          /*  if(HomeActivity.tv_go_on_site.getText().toString().equalsIgnoreCase("On-Site")) {
                if (workorderno.equalsIgnoreCase(homePOJOList.get(position).getWorkOrderNumber())) {
                    holder.tv_place_of_work.setText("On-Site");
                } else {
                    holder.tv_place_of_work.setText("");
                }
            }else {

            }*/
            holder.tv_assesment.setText(homePOJOList.get(position).getWorkOrderNo());
            holder.priority.setText(homePOJOList.get(position).getPriority());
            holder.ordertype.setText(homePOJOList.get(position).getOrderType());
            holder.clientname.setText(homePOJOList.get(position).getClientName());
          //  holder.tv_place_of_work.setText(homePOJOList.get(position).getAssesment().getScopeOfWork());
            holder.tv_date.setText(UtilityFunction.changeDateTime(homePOJOList.get(position).getDueDate()));

          //  Log.d("AssesmentId",homePOJOList.get(position));


            // holder.tv_time.setText(UtilityFunction.getSplitedTime(homePOJOList.get(position).getAssignedDate()));
      /*      if (homePOJOList.get(position).getStatus().equals("1")) {
                holder.tv_time.setText("OPEN");
            } else if (homePOJOList.get(position).getStatus().equals("2")) {
                holder.tv_time.setTextColor(Color.GREEN);
                holder.tv_time.setText("ASSIGNED");
            } else if (homePOJOList.get(position).getStatus().equals("3")) {
                holder.tv_time.setTextColor(Color.RED);
                holder.tv_time.setText("CANCEL");
            } else if (homePOJOList.get(position).getStatus().equals("4")) {
                holder.tv_time.setText("SIGNED");
            } else if (homePOJOList.get(position).getStatus().equals("5")) {
                holder.tv_time.setTextColor(Color.GREEN);
                holder.tv_time.setText("COMPLETED");
            }*/
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("WorkOrderPOJO", homePOJOList.get(position));
                    intent.putExtra("WorkOrderid", homePOJOList.get(position).getWorkOrderId());
                    Constants.workOrderPOJO=homePOJOList.get(position);
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {
            Log.d("SynchronizedAdapter", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return homePOJOList.size();
    }

    public class SynchronizedHomeHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tv_assesment;
        TextView tv_place_of_work;
        TextView priority;
        TextView clientname;
        TextView ordertype;
        TextView tv_date;
        TextView tv_time;
        ImageView iv_location;

        public SynchronizedHomeHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.firstcard);
            tv_assesment = itemView.findViewById(R.id.tv_inflate_work_order_number);
            priority = itemView.findViewById(R.id.priority);
            clientname = itemView.findViewById(R.id.clientname);
            ordertype = itemView.findViewById(R.id.ordertype);
            tv_place_of_work= itemView.findViewById(R.id.tv_inflate_synchronized_place_work);
            tv_date = itemView.findViewById(R.id.tv_inflate_synchronized_date);
            iv_location=itemView.findViewById(R.id.iv_iflate_synchronized_direction);
           // tv_time = itemView.findViewById(R.id.tv_inflate_synchronized_time);

        }
    }
}

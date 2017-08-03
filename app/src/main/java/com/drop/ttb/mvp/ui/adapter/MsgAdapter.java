package com.drop.ttb.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algebra.sdk.API;
import com.algebra.sdk.entity.HistoryRecord;
import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.activity.LoginActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by 夏夜晚凤 on 2017/2/28.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    private List<HistoryRecord> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout leftLayout;

        RelativeLayout rightLayout;

        TextView leftMsg;

        TextView rightMsg;

        CircleImageView leftHead;

        CircleImageView rightHead;

        ImageView imageView;

        TextView rightName;

        TextView leftName;

        RelativeLayout rll;

        RelativeLayout rlr;

        public ViewHolder(View View) {
            super(View);
            leftLayout = (RelativeLayout) View.findViewById(R.id.left_layout);
            rightLayout = (RelativeLayout) View.findViewById(R.id.right_layout);
            leftMsg = (TextView) View.findViewById(R.id.left_time);
            rightMsg = (TextView) View.findViewById(R.id.right_time);
            leftHead = (CircleImageView) View.findViewById(R.id.left_head);
            rightHead = (CircleImageView) View.findViewById(R.id.right_head );
            imageView = (ImageView) View.findViewById(R.id.imageView0);
            rightName = (TextView) View.findViewById(R.id.textview11);
            leftName = (TextView) View.findViewById(R.id.textView10);
            rll = (RelativeLayout) View.findViewById(R.id.rll);
            rlr = (RelativeLayout) View.findViewById(R.id.rlr);
        }
    }

    public MsgAdapter(List<HistoryRecord> msgList){
        mMsgList = msgList;
    }




    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
        HistoryRecord historyRecord = mMsgList.get(position);
        if(historyRecord.owner != LoginActivity.getLoginActivity().uid){
            holder.rll.setVisibility(View.VISIBLE);
            holder.rlr.setVisibility(View.GONE);
//            holder.rightName.setVisibility(View.GONE);
//            holder.leftName.setVisibility(View.VISIBLE);
            holder.leftName.setText(API.uid2nick(historyRecord.owner));
//            holder.leftLayout.setVisibility(View.VISIBLE);
//            holder.rightLayout.setVisibility(View.GONE);
            holder.leftHead.setVisibility(View.GONE);
            holder.rightHead.setVisibility(View.VISIBLE);
//            holder.leftMsg.setVisibility(View.VISIBLE);
//            holder.rightMsg.setVisibility(View.GONE);
//            holder.imageView.setVisibility(View.GONE);
            holder.leftMsg.setText(dur2str(historyRecord.duration)+"s");
            holder.leftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.getLoginActivity().sessionApi.playLastSpeaking(LoginActivity.getLoginActivity().uid,
                            historyRecord.owner, historyRecord.mediaData);
                }
            });
        } else if(historyRecord.owner == LoginActivity.getLoginActivity().uid){

            holder.rlr.setVisibility(View.VISIBLE);
            holder.rll.setVisibility(View.GONE);
//            holder.leftLayout.setVisibility(View.GONE);
//            holder.rightLayout.setVisibility(View.VISIBLE);
//            holder.leftName.setVisibility(View.GONE);
//            holder.rightName.setVisibility(View.VISIBLE);
            holder.rightName.setText(API.uid2nick(historyRecord.owner));
            holder.leftHead.setVisibility(View.VISIBLE);
            holder.rightHead.setVisibility(View.GONE);
//            holder.leftMsg.setVisibility(View.GONE);
//            holder.imageView.setVisibility(View.VISIBLE);
//            holder.rightMsg.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(dur2str(historyRecord.duration)+"s");
            holder.rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.getLoginActivity().sessionApi.playLastSpeaking(LoginActivity.getLoginActivity().uid,
                            historyRecord.owner, historyRecord.mediaData);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    private String dur2str(int d10ms) {
        double sec = (double) d10ms * 0.01;
        java.text.DecimalFormat df = new java.text.DecimalFormat("###.#");
        return df.format(sec);
    }
}

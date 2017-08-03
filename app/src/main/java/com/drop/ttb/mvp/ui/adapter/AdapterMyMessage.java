package com.drop.ttb.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.GetMyMessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.drop.ttb.R.id.notice;
import static com.drop.ttb.R.id.owner;

/**
 * Created by Drop on 2017/8/1.
 */

public class AdapterMyMessage extends ArrayAdapter<GetMyMessageBean.ResultDataBean> {
    private Context mContext;

    private int resourceId;

    public AdapterMyMessage(@NonNull Context context, @LayoutRes int resource, @NonNull List<GetMyMessageBean.ResultDataBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GetMyMessageBean.ResultDataBean data = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.title.setText(data.getCreatid() + "");
        viewHolder.time.setText(data.getCreatdate());
        viewHolder.notice.setText(data.getTextdata());
        viewHolder.owner.setText(data.getChannelname());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.owner)
        TextView owner;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.notice)
        TextView notice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

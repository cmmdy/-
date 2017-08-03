package com.drop.ttb.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.drop.ttb.R;
import com.jess.arms.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by Drop on 2017/7/28.
 */

public class HolderComment extends BaseHolder<String> {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.text)
    TextView text;

    public HolderComment(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(String data, int position) {
        String[] temp = data.split(":");
        name.setText(temp[0]+": ");
        try {
            text.setText(temp[1]);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}

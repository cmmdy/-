package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;
import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.holder.HolderContact;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Drop on 2017/7/21.
 */

public class AdapterContact  extends DefaultAdapter<Contact> {

    public AdapterContact(List<Contact> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Contact> getHolder(View v, int viewType) {
        return new HolderContact(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycler_people;
    }
}

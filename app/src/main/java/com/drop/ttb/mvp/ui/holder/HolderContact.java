package com.drop.ttb.mvp.ui.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;
import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.activity.PersonActivity;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by Drop on 2017/7/21.
 */

public class HolderContact extends BaseHolder<Contact> {

    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.click)
    RelativeLayout relativeLayout;
    private AppComponent mAppComponent;

    public HolderContact(View itemView) {
        super(itemView);
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
    }

    @Override
    public void setData(Contact data, int position) {
        id.setText(data.name);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mAppComponent.appManager().getCurrentActivity(), PersonActivity.class);
                intent.putExtra("id", data.id);
                intent.putExtra("name", data.name);
                intent.putExtra("phont", data.phone);
                mAppComponent.appManager().startActivity(intent);
            }
        });
    }
}

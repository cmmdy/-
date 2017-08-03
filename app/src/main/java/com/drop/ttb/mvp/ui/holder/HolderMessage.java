package com.drop.ttb.mvp.ui.holder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.IntStr;
import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.activity.FocusChannelActivity;
import com.drop.ttb.mvp.ui.activity.LoginActivity;
import com.drop.ttb.mvp.ui.activity.TalkActivity;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.data;
import static android.R.attr.factor;

/**
 * Created by Drop on 2017/5/15.
 */

public class HolderMessage extends BaseHolder<Channel> {

    @BindView(R.id.iv)
    CircleImageView iv;
    @BindView(R.id.iv_name)
    TextView ivName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.click)
    RelativeLayout click;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;
    private List<Channel> infos;
    public static Channel channel = null;


    public HolderMessage(View itemView) {
        super(itemView);
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(Channel data, int position) {
        if(data.name.length() != 0){
            ivName.setText(data.name.substring(0, 1));
        }

        name.setText(data.name);
        id.setText(data.memberCount + "");


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channel = data;
                if (!FocusChannelActivity.focus) {
                    Intent intent = new Intent(mAppComponent.appManager().getCurrentActivity(), TalkActivity.class);
                    intent.putExtra("name", data.name);
                    intent.putExtra("cidType", data.cid.getType());
                    intent.putExtra("cidId", data.cid.getId());
                    intent.putExtra("owner", data.owner.i);
                    UiUtils.startActivity(intent);

                } else {
                    showDialog(data);
                }
            }
        });
    }


    public List<Channel> getInfos() {
        return infos;
    }

    @Override
    protected void onRelease() {
    }

    private void showDialog(Channel data) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mAppComponent.appManager().getCurrentActivity());
        LayoutInflater inflater = mAppComponent.appManager().getCurrentActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog, null);
        TextView name = (TextView) dialog.findViewById(R.id.dialog_name);
        TextView uid = (TextView) dialog.findViewById(R.id.dialog_uid);
        EditText pw = (EditText) dialog.findViewById(R.id.password);
        name.setText("频道名: " + data.name);
        uid.setText("创建者: " + data.owner.i+"");
        if (!data.needPassword) {
            pw.setVisibility(View.GONE);
        }
        alertDialog
                .setView(dialog)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (data.needPassword) {
                            LoginActivity.getLoginActivity().channelApi.focusPublicChannel(
                                    LoginActivity.getLoginActivity().uid, data.cid.getType(), data.cid.getId(), pw.getText().toString()
                            );
                        } else {
                            LoginActivity.getLoginActivity().channelApi.focusPublicChannel(
                                    LoginActivity.getLoginActivity().uid, data.cid.getType(), data.cid.getId(), null
                            );
                        }
                        mAppComponent.appManager().getCurrentActivity().finish();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
}

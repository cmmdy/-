package com.drop.ttb.mvp.model.api.service;

import com.drop.ttb.mvp.model.entity.AddNewComment;
import com.drop.ttb.mvp.model.entity.AddNewDynamic;
import com.drop.ttb.mvp.model.entity.AddNewMessageBean;
import com.drop.ttb.mvp.model.entity.AddNewNoticeBean;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.DeleteMessageBean;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.model.entity.GetChannelDynamic;
import com.drop.ttb.mvp.model.entity.GetComment;
import com.drop.ttb.mvp.model.entity.GetMyMessageBean;
import com.drop.ttb.mvp.model.entity.PostAddNewComment;
import com.drop.ttb.mvp.model.entity.PostAddNewDynamic;
import com.drop.ttb.mvp.model.entity.PostAddNewMessageBean;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.PostChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.PostDeleteMessageBean;
import com.drop.ttb.mvp.model.entity.PostGetChannelDynamic;
import com.drop.ttb.mvp.model.entity.PostGetComment;
import com.drop.ttb.mvp.model.entity.PostMyMessageBean;
import com.drop.ttb.mvp.model.entity.PostNewChannelNoticeBean;
import com.drop.ttb.mvp.model.entity.PostNoticeChannelIdBean;
import com.drop.ttb.mvp.model.entity.PostUpdataDynamic;
import com.drop.ttb.mvp.model.entity.PostUserDynamicBean;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.drop.ttb.mvp.model.entity.UpdataDynamic;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Drop on 2017/7/25.
 */

public interface TalkService {
    String APP_HOST = "http://139.224.164.183:8010/";

    /**
     * 添加新频道
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Channel_AddNewChannel.aspx")
    Observable<ChannelAddNewChannelBean> addNewChannel(@Body PostChannelAddNewChannelBean postChannelAddNewChannelBean);

    /**
     * 更新频道信息（插播）
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Channel_EditChannel.aspx.aspx")
    Observable<ResultCodeBean> updataChannel(@Body PostChannelAddNewChannelBean postChannelAddNewChannelBean);

    /**
     * 返回频道信息
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Channel_ReturnChannel.aspx.aspx")
    Observable<ChannelAddNewChannelBean> getChannel(@Body PostChannelId postChannelId);

    /**
     * 删除频道
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Channel_ReturnChannel.aspx.aspx")
    Observable<ResultCodeBean> deleteChannel(@Body PostChannelId postChannelId);

    /**
     * 添加新公告
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Notice_AddNewNotice.aspx")
    Observable<AddNewNoticeBean> addNewNotice(@Body PostNewChannelNoticeBean postNewChannelNoticeBean);

    /**
     * 删除公告
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Nodtice_DeleteNotice.aspx")
    Observable<DeleteMessageBean> deleteNotice(@Body PostChannelId postChannelId);

    /**
     * 根据频道ID返回公告列表
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Notice_ReturnNoticeListByChannelid.aspx")
    Observable<GetAllNoticeBean> getChannelNotice(@Body PostNoticeChannelIdBean postNoticeChannelIdBean);

    /**
     * 添加新动态
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Dynamic_AddNewDynamic.aspx")
    Observable<AddNewDynamic> addNewDynamic(@Body PostAddNewDynamic postAddNewDynamic);

    /**
     * 根据频道ID返回动态列表
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Dynamic_ReturnDynamicListByChannelid.aspx")
    Observable<GetChannelDynamic> getChannelDynamic(@Body PostGetChannelDynamic postGetChannelDynamic);

    /**
     * 添加新评论
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Comment_AddNewComment.aspx")
   Observable<AddNewComment> addNewComment(@Body PostAddNewComment postAddNewComment);

    /**
     * 返回评论列表
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Comment_ReturnCommentList.aspx")
    Observable<GetComment> getComment(@Body PostGetComment postGetComment);

    /**
     * 更新动态
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Dynamic_EditDynamic.aspx")
    Observable<UpdataDynamic> updataDynamic(@Body PostUpdataDynamic postUpdataDynamic);

    /**
     * 根据创建者ID返回动态列表
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Dynamic_ReturnDynamicListByCreatid.aspx")
    Observable<GetChannelDynamic> getUserDynamic(@Body PostUserDynamicBean postUserDynamicBean);

    /**
     * 添加新消息
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Communication_AddNewCommunication.aspx")
    Observable<AddNewMessageBean> addNewMessage(@Body PostAddNewMessageBean postAddNewMessageBean);

    /**
     * 通过接收者ID返回未读消息列表
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Communication_ReturnCommunicationListByAccepterid.aspx")
    Observable<GetMyMessageBean> getMyMessage(@Body PostMyMessageBean postMyMessageBean);

    /**
     * 删除消息列表
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept-Language:zh-CN,en-US;q=0.8,en;q=0.6,zh;q=0.4"})
    @POST("http://139.224.164.183:8010/Communication_DeleteCommunication.aspx")
    Observable<DeleteMessageBean> deleteMessage(@Body PostDeleteMessageBean postDeleteMessageBean);
}

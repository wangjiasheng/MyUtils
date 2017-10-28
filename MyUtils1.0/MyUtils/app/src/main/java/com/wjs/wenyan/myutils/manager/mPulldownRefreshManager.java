package com.wjs.wenyan.myutils.manager;

import android.content.Context;
import android.view.View;

import com.wjs.network.json.HttpMethod;
import com.wjs.utils.NetworkUtils;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by WJS on 2017/7/25.
 */

public class mPulldownRefreshManager
{
    HttpMethod method;
    mPulldownRefreshCallback cni;
    Context context;
    View RootView;
    private int pageSize=25;
    private int pageNunber=1;
    PtrClassicFrameLayout mPtrview;
    public mPulldownRefreshManager(HttpMethod method, PtrClassicFrameLayout mPtrview, LoadMoreListViewContainer mLoadview, View RootView, mPulldownRefreshCallback cni, Context context)
    {
        this.cni=cni;
        this.context=context;
        this.RootView=RootView;
        this.method=method;
        this.mPtrview=mPtrview;
        initPullDown(mPtrview);
        initPullUp(mLoadview);
    }
    public void initPullDown(PtrClassicFrameLayout mPtrFrame)
    {
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame)
            {
                pullDowonToRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, RootView, header);
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setEnabledNextPtrAtOnce(true);//下拉刷新不等UIReset
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        pullDowonToRefresh();
    }
    public void initPullUp(LoadMoreListViewContainer mLoadMore)
    {
        if(mLoadMore!=null) {
            mLoadMore.useDefaultHeader();
            mLoadMore.setLoadMoreHandler(new LoadMoreHandler() {
                @Override
                public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                    pullUpToaddData();
                }
            });
        }
    }
    public void pullDowonToRefresh()
    {
        if(NetworkUtils.isNetworkAvailable(context))
        {
            mPulldownRefreshTask ct = new mPulldownRefreshTask(pageNunber = 1);
            ct.execute();
        }
        else
        {
            cni.networkNotUse();
            cni.onFaile(new Exception("网络不可用"));
            mPtrview.refreshComplete();
        }
    }
    public void pullUpToaddData()
    {
        if(NetworkUtils.isNetworkAvailable(context))
        {

        }
        else
        {
            cni.networkNotUse();
        }
    }
}

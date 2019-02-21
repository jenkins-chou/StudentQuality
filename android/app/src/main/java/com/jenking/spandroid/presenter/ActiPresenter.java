package com.jenking.spandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.jenking.spandroid.api.ApiService;
import com.jenking.spandroid.api.ApiUtil;
import com.jenking.spandroid.contracts.BaseCallBack;
import com.jenking.spandroid.models.base.ResultModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * presenter模板
 */
public class ActiPresenter {

    private Context context;

    private BaseCallBack view;

    private OnCallBack onCallBack;

    public ActiPresenter(Context context){
        this.context = context;
        this.view = view;
    }

    public void setOnCallBack(OnCallBack onCallBack){
        this.onCallBack = onCallBack;
    }

    //带参基本请求方法
    public void addActi(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .addActi(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.addActi(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.addActi(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void deleteActi(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .deleteActi(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.deleteActi(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.deleteActi(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void updateActi(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .updateActi(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.updateActi(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.updateActi(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void getAllActi(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .getAllActi(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.getAllActi(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.getAllActi(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface OnCallBack{
        void addActi(boolean isSuccess, Object object);
        void deleteActi(boolean isSuccess, Object object);
        void updateActi(boolean isSuccess, Object object);
        void getAllActi(boolean isSuccess, Object object);
    }

}

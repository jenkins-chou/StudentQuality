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
public class UserCoursePresenter {

    private Context context;

    private BaseCallBack view;

    private OnCallBack onCallBack;

    public UserCoursePresenter(Context context){
        this.context = context;
        this.view = view;
    }

    public void setOnCallBack(OnCallBack onCallBack){
        this.onCallBack = onCallBack;
    }

    //带参基本请求方法
    public void getCoursesByClassId(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .getCoursesByClassId(params)
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
                            onCallBack.getCoursesByClassId(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.getCoursesByClassId(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void deleteByClassIdAndCourseId(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .deleteByClassIdAndCourseId(params)
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
                            onCallBack.deleteByClassIdAndCourseId(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.deleteByClassIdAndCourseId(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void addCourseTypeClass(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .addCourseTypeClass(params)
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
                            onCallBack.addCourseTypeClass(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.addCourseTypeClass(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface OnCallBack{
        void getCoursesByClassId(boolean isSuccess, Object object);
        void deleteByClassIdAndCourseId(boolean isSuccess, Object object);
        void addCourseTypeClass(boolean isSuccess, Object object);
    }

}

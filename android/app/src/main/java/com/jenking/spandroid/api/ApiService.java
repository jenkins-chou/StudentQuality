package com.jenking.spandroid.api;

import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.SchoolModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhouzhenjian on 2018/3/26.
 */

public interface ApiService {

    //模板接口


    @FormUrlEncoded
    @POST("user/login")
    Observable<ResultModel> template(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("school/addSchool")
    Observable<ResultModel> addSchool(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("school/updateSchool")
    Observable<ResultModel> updateSchool(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("school/deleteSchool")
    Observable<ResultModel> deleteSchool(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("school/getAllSchool")
    Observable<ResultModel<SchoolModel>> getAllSchool(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("college/addCollege")
    Observable<ResultModel> addCollege(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("college/updateCollege")
    Observable<ResultModel> updateCollege(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("college/deleteCollege")
    Observable<ResultModel> deleteCollege(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("college/getAllCollege")
    Observable<ResultModel<CollegeModel>> getAllCollege(@FieldMap Map<String, String> body);

}

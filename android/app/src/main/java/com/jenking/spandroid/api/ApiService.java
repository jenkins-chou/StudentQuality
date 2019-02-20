package com.jenking.spandroid.api;

import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.SchoolModel;
import com.jenking.spandroid.models.base.UserModel;

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
    @POST("user/login")
    Observable<ResultModel<UserModel>> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/addUser")
    Observable<ResultModel<UserModel>> addUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/getTeachers")
    Observable<ResultModel<UserModel>> getTeachers(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/getTeachersByCollege")
    Observable<ResultModel<UserModel>> getTeachersByCollege(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/deleteUser")
    Observable<ResultModel<UserModel>> deleteUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/updateUser")
    Observable<ResultModel<UserModel>> updateUser(@FieldMap Map<String, String> body);

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

    @FormUrlEncoded
    @POST("college/getCollegeBySchool")
    Observable<ResultModel<CollegeModel>> getCollegeBySchool(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("class/addClass")
    Observable<ResultModel> addClass(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("class/updateClass")
    Observable<ResultModel> updateClass(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("class/deleteClass")
    Observable<ResultModel> deleteClass(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("class/getAllClass")
    Observable<ResultModel<ClassModel>> getAllClass(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("class/getClassByCollege")
    Observable<ResultModel<ClassModel>> getClassByCollege(@FieldMap Map<String, String> body);

}

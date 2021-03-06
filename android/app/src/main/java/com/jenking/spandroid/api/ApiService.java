package com.jenking.spandroid.api;

import com.jenking.spandroid.models.base.ActiModel;
import com.jenking.spandroid.models.base.CertificateModel;
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.base.MoralModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.SchoolModel;
import com.jenking.spandroid.models.base.TermModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.models.impl.UserActivityDetail;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserCourseDetail;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.models.impl.UserMoralDetail;

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
    @POST("user/getAllStudents")
    Observable<ResultModel<UserModel>> getAllStudents(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/getStudentByClass")
    Observable<ResultModel<UserModel>> getStudentByClass(@FieldMap Map<String, String> body);

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

    @FormUrlEncoded
    @POST("match/addMatch")
    Observable<ResultModel<MatchModel>> addMatch(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("match/updateMatch")
    Observable<ResultModel<MatchModel>> updateMatch(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("match/deleteMatch")
    Observable<ResultModel<MatchModel>> deleteMatch(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("match/getAllMatchs")
    Observable<ResultModel<MatchModel>> getAllMatchs(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("cert/addCert")
    Observable<ResultModel<CertificateModel>> addCert(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("cert/updateCert")
    Observable<ResultModel<CertificateModel>> updateCert(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("cert/deleteCert")
    Observable<ResultModel<CertificateModel>> deleteCert(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("cert/getAllCerts")
    Observable<ResultModel<CertificateModel>> getAllCerts(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("acti/addActi")
    Observable<ResultModel<ActiModel>> addActi(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("acti/updateActi")
    Observable<ResultModel<ActiModel>> updateActi(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("acti/deleteActi")
    Observable<ResultModel<ActiModel>> deleteActi(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("acti/getAllActi")
    Observable<ResultModel<ActiModel>> getAllActi(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("moral/addMoral")
    Observable<ResultModel<MoralModel>> addMoral(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("moral/updateMoral")
    Observable<ResultModel<MoralModel>> updateMoral(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("moral/deleteMoral")
    Observable<ResultModel<MoralModel>> deleteMoral(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("moral/getAllMorals")
    Observable<ResultModel<MoralModel>> getAllMorals(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("term/addTerm")
    Observable<ResultModel<TermModel>> addTerm(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("term/updateTerm")
    Observable<ResultModel<TermModel>> updateTerm(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("term/deleteTerm")
    Observable<ResultModel<TermModel>> deleteTerm(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("term/getAllTerms")
    Observable<ResultModel<TermModel>> getAllTerms(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("course/addCourse")
    Observable<ResultModel<CourseModel>> addCourse(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("course/updateCourse")
    Observable<ResultModel<CourseModel>> updateCourse(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("course/deleteCourse")
    Observable<ResultModel<CourseModel>> deleteCourse(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("course/getAllCourses")
    Observable<ResultModel<CourseModel>> getAllCourses(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("course/getCourseSelected")
    Observable<ResultModel<CourseModel>> getCourseSelected(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("course/getCoursesByTeacher")
    Observable<ResultModel<CourseModel>> getCoursesByTeacher(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/getCoursesByClassId")
    Observable<ResultModel<CourseModel>> getCoursesByClassId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/deleteByClassIdAndCourseId")
    Observable<ResultModel<CourseModel>> deleteByClassIdAndCourseId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/addCourseTypeClass")
    Observable<ResultModel<CourseModel>> addCourseTypeClass(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/getCoursesByUserId")
    Observable<ResultModel<UserCourseDetail>> getCoursesByUserId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/deleteByUserIdAndCourseId")
    Observable<ResultModel<UserCourseDetail>> deleteByUserIdAndCourseId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/addCourseTypeUser")
    Observable<ResultModel<UserCourseDetail>> addCourseTypeUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/getUserByCourseId")
    Observable<ResultModel<UserCourseDetail>> getUserByCourseId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_course/excute")
    Observable<ResultModel<UserCourseDetail>> excute(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_match/getUserMatchByUserId")
    Observable<ResultModel<UserMatchDetail>> getUserMatchByUserId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_match/addUserMatch")
    Observable<ResultModel<UserMatchDetail>> addUserMatch(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_match/updateUserMatch")
    Observable<ResultModel<UserMatchDetail>> updateUserMatch(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_match/deleteUserMatch")
    Observable<ResultModel<UserMatchDetail>> deleteUserMatch(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_cert/getUserCertByUserId")
    Observable<ResultModel<UserCertDetail>> getUserCertByUserId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_cert/addUserCert")
    Observable<ResultModel<UserCertDetail>> addUserCert(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_cert/updateUserCert")
    Observable<ResultModel<UserCertDetail>> updateUserCert(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_cert/deleteUserCert")
    Observable<ResultModel<UserCertDetail>> deleteUserCert(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_activity/getUserActivityByUserId")
    Observable<ResultModel<UserActivityDetail>> getUserActivityByUserId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_activity/addUserActivity")
    Observable<ResultModel<UserActivityDetail>> addUserActivity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_activity/updateUserActivity")
    Observable<ResultModel<UserActivityDetail>> updateUserActivity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_activity/deleteUserActivity")
    Observable<ResultModel<UserActivityDetail>> deleteUserActivity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_moral/getUserMoralByUserId")
    Observable<ResultModel<UserMoralDetail>> getUserMoralByUserId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_moral/addUserMoral")
    Observable<ResultModel<UserMoralDetail>> addUserMoral(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_moral/updateUserMoral")
    Observable<ResultModel<UserMoralDetail>> updateUserMoral(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user_moral/deleteUserMoral")
    Observable<ResultModel<UserMoralDetail>> deleteUserMoral(@FieldMap Map<String, String> body);

}


var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "course";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getAllCourses', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据id获取信息
router.post('/getCourse',function (req, res) {
    var sql = "select * from "+tableName+" where "+tableKey+" = "+req.body.id +" and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//条件查询
router.post('/getCourseSelected',function (req, res) {
    var school_id = req.body.school_id;
    var college_id = req.body.college_id;
    var term_id = req.body.term_id;
    var course_type = req.body.course_type;
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    if (school_id!=null&&college_id!=null) {
        sql = sql + " and school_id = '"+school_id+"' and college_id = '"+college_id+"'";
    }
    if (term_id!=null) {
        sql = sql + " and term_id = '"+term_id+"'";
    }
    if (course_type!=null) {
        sql = sql + " and course_type = '"+course_type+"'";
    }
    console.log(sql);
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});



//添加
router.post('/addCourse', function (req, res) {
    var sql = "insert into "+tableName+"(course_name,course_stunum,course_abstract,course_detail,course_type,course_status,course_score,school_id,college_id,term_id,teacher_id,school_name,college_name,term_name,teacher_name,remark,create_time,del) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.course_name,
        req.body.course_stunum,
        req.body.course_abstract,
        req.body.course_detail,
        req.body.course_type,
        req.body.course_status,
        req.body.course_score,
        req.body.school_id,
        req.body.college_id,
        req.body.term_id,
        req.body.teacher_id,
        req.body.school_name,
        req.body.college_name,
        req.body.term_name,
        req.body.teacher_name,
        req.body.remark,
        req.body.create_time,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where course_name = '" + req.body.course_name+"' and school_id = '"+req.body.school_id+"' and college_id = '"+req.body.college_id+"' and del != 'delete'";//用于查询是否存在同名的
    connectDB.query(sqlQuery,function(result){
        console.log(result);
        if(result.data[0]!=null){
            console.log("已经存在");
            var resultexist = {
                    "status": "201",
                    "message": "已经存在",
                    "data":[]
                }
            return res.jsonp(resultexist);
        }else{
            console.log("可注册");
            connectDB.add(sql,sqlparams,function(result){
                console.log(result);
                if (result.status=="200") {
                    var sqlQueryAgain = "select * from "+tableName+" where course_name = '" + req.body.course_name+"' and school_id = '"+req.body.school_id+"' and college_id = '"+req.body.college_id+"' and del != 'delete'";
                    connectDB.query(sqlQueryAgain,function(resultAgain){
                        return res.jsonp(resultAgain);
                    })
                }else{
                    return res.jsonp(result);
                }
            })
        }
    })

});
//更新信息
router.post('/updateCourse', function (request, response) {
    var req = request;
    var res = response;
    var id = req.body.id;

    if (id==null) {
        return res.jsonp("id is null! please check!");
    }
    //console.log("hahahhah");
    connectDB.query("select * from "+tableName+" where "+tableKey+" = "+id + " and del != 'delete'",function(result){
        if (result.status=="200") {
            if (result.data[0]!=null) {
                    var course_name = checkUpdateData(req.body.course_name,result.data[0].course_name);
                    var course_stunum = checkUpdateData(req.body.course_stunum,result.data[0].course_stunum);
                    var course_abstract = checkUpdateData(req.body.course_abstract,result.data[0].course_abstract);
                    var course_detail = checkUpdateData(req.body.course_detail,result.data[0].course_detail);
                    var course_type = checkUpdateData(req.body.course_type,result.data[0].course_type);
                    var course_status = checkUpdateData(req.body.course_status,result.data[0].course_status);
                    var course_score = checkUpdateData(req.body.course_score,result.data[0].course_score);
                    var school_id = checkUpdateData(req.body.school_id,result.data[0].school_id);
                    var college_id = checkUpdateData(req.body.college_id,result.data[0].college_id);
                    var term_id = checkUpdateData(req.body.term_id,result.data[0].term_id);
                    var teacher_id = checkUpdateData(req.body.teacher_id,result.data[0].teacher_id);
                    var school_name = checkUpdateData(req.body.school_name,result.data[0].school_name);
                    var college_name = checkUpdateData(req.body.college_name,result.data[0].college_name);
                    var term_name = checkUpdateData(req.body.term_name,result.data[0].term_name);
                    var teacher_name = checkUpdateData(req.body.teacher_name,result.data[0].teacher_name);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);
                    var sql  =  "update "+tableName
                    +" set course_name = '"+course_name
                    +"' , course_stunum = '"+course_stunum
                    +"' , course_abstract = '"+course_abstract
                    +"' , course_detail = '"+course_detail
                    +"' , course_type = '"+course_type
                    +"' , course_status = '"+course_status
                    +"' , course_score = '"+course_score
                    +"' , school_id = '"+school_id
                    +"' , college_id = '"+college_id
                    +"' , term_id = '"+term_id
                    +"' , teacher_id = '"+teacher_id
                    +"' , school_name = '"+school_name
                    +"' , college_name = '"+college_name
                    +"' , term_name = '"+term_name
                    +"' , teacher_name = '"+teacher_name
                    +"' , remark = '"+remark
                    +"' where "+tableKey+" = "+id;
                connectDB.update(sql,function(result){
                    console.log(result);
                    return res.jsonp(result);
                })
            }else{
                var result = {
                    "status": "201",
                    "message": "failed"
                }
                return res.jsonp(result);
            }
        }else{
            var result = {
                    "status": "201",
                    "message": "failed"
                }
            return res.jsonp(result);
        }
    })
});
router.post('/deleteCourse', function (req, res) {
    var id = req.body.id;
    if (id==null) {
        return res.jsonp("id is null! please check!");
    }else{
        var sql = "update "+tableName+" set "+tableDelete+" = 'delete' where "+tableKey+" = "+id;
        connectDB.delete(sql,function(result){
            console.log(result);
            return res.jsonp(result);
        })
    }
});

//更新时，用于校验是否是否有更新字段值
    function checkUpdateData(target,current){
        if (target == null||target =="") {
            return current;
        }else if(target !=null||target !=""){
            if (target != current) {
                return target;
            }else{
                return current;
            }
        }else{
            return current;
        }
    }
module.exports = router;
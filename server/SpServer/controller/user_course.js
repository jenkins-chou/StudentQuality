
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "user_course";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//根据班级id获取班级课程
router.post('/getCoursesByClassId', function (req, res) {
    var sql = "select * from course where id in (select distinct course_id from user_course where del != 'del' and class_id = '"+req.body.class_id+"') and del != 'delete'"
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据班级id和课程id移除课程
router.post('/deleteByClassIdAndCourseId', function (req, res) {
    var sql = "update user_course set del = 'delete' where class_id = '"+req.body.class_id+"' and course_id = '"+req.body.course_id+"'";
    connectDB.delete(sql,function(result){
        return res.jsonp(result);
    })
});

//已班级为单位添加课程安排
router.post('/addCourseTypeClass', function (req, res) {
    var class_id = req.body.class_id;
    var course_id = req.body.course_id;
    var sql = req.body.sql;
    var sqlQuery = "select * from "+tableName+" where class_id = '"+class_id+"' and course_id = '"+course_id+"' and del != 'delete'";//用于查询是否存在同名的
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
                console.log("可添加");
                connectDB.excute(sql,function(result){
                    console.log(result);
                    return res.jsonp(result);
                })
            }
        })    
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

var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "user_activity";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//根据id获取信息
router.post('/getUserActivityByUserId',function (req, res) {
    var sql = "select a.*,b.* from activity a,user_activity b where a.id = b.activity_id and a.del != 'delete' and b.del != 'delete' and b.user_id ='"+req.body.user_id+"'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//添加
router.post('/addUserActivity', function (req, res) {
    var sql = "insert into "+tableName+"(user_id,activity_id,user_activity_score,user_activity_status,create_time,remark,del) value (?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.user_id,
        req.body.activity_id,
        req.body.user_activity_score,
        req.body.user_activity_status,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where user_id = '" + req.body.user_id+"' and activity_id = '"+req.body.activity_id+"' and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where user_id = '" + req.body.user_id+"' and activity_id = '"+req.body.activity_id+"' and del != 'delete'";
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
router.post('/updateUserActivity', function (request, response) {
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
                    var user_id = checkUpdateData(req.body.user_id,result.data[0].user_id);
                    var activity_id = checkUpdateData(req.body.activity_id,result.data[0].activity_id);
                    var user_activity_score = checkUpdateData(req.body.user_activity_score,result.data[0].user_activity_score);
                    var user_activity_status = checkUpdateData(req.body.user_activity_status,result.data[0].user_activity_status);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);
                    var sql  =  "update "+tableName
                    +" set user_id = '"+user_id
                    +"' , activity_id = '"+activity_id
                    +"' , user_activity_score = '"+user_activity_score
                    +"' , user_activity_status = '"+user_activity_status
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
router.post('/deleteUserActivity', function (req, res) {
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
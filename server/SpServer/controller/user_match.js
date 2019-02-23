
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "user_match";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位


//根据id获取信息
router.post('/getUserMatchByUserId',function (req, res) {
    var sql = "select * from matchs where id in (select match_id from user_match where user_id = '"+req.body.user_id+"' and del != 'delete')"+" and del != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//添加
router.post('/addUserMatch', function (req, res) {
    var sql = "insert into "+tableName+"(user_id,match_id,user_match_status,user_match_score,create_time,remark,del) value (?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.user_id,
        req.body.match_id,
        req.body.user_match_status,
        req.body.user_match_score,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where user_id = '" + req.body.user_id+"' and match_id = '"+req.body.match_id+"'  and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where user_id = '" + req.body.user_id+"' and match_id = '"+req.body.match_id+"' and del != 'delete'";
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
router.post('/updateUserMatch', function (request, response) {
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
                    var match_id = checkUpdateData(req.body.match_id,result.data[0].match_id);
                    var user_match_status = checkUpdateData(req.body.user_match_status,result.data[0].user_match_status);
                    var user_match_score = checkUpdateData(req.body.user_match_score,result.data[0].user_match_score);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);
                    var sql  =  "update "+tableName
                    +" set user_id = '"+user_id
                    +"' , match_id = '"+match_id
                    +"' , user_match_status = '"+user_match_status
                    +"' , user_match_score = '"+user_match_score
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
router.post('/deleteUserMatch', function (req, res) {
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

var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "activity";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getAllActi', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据id获取信息
router.post('/getActiById',function (req, res) {
    var sql = "select * from "+tableName+" where "+tableKey+" = "+req.body.id +" and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});



//添加
router.post('/addActi', function (req, res) {
    var sql = "insert into "+tableName+"(activity_name,activity_time,activity_leader,activity_abstract,activity_detail,activity_stunum,activity_status,activity_score,create_time,remark,del) value (?,?,?,?,?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.activity_name,
        req.body.activity_time,
        req.body.activity_leader,
        req.body.activity_abstract,
        req.body.activity_detail,
        req.body.activity_stunum,
        req.body.activity_status,
        req.body.activity_score,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where activity_name = '" + req.body.activity_name+"'  and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where activity_name = '" + req.body.activity_name+"'  and del != 'delete'";
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
router.post('/updateActi', function (request, response) {
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

                    var activity_name = checkUpdateData(req.body.activity_name,result.data[0].activity_name);
                    var activity_time = checkUpdateData(req.body.activity_time,result.data[0].activity_time);

                    var activity_leader = checkUpdateData(req.body.activity_leader,result.data[0].activity_leader);
                    var activity_abstract = checkUpdateData(req.body.activity_abstract,result.data[0].activity_abstract);

                    var activity_detail = checkUpdateData(req.body.activity_detail,result.data[0].activity_detail);
                    var activity_stunum = checkUpdateData(req.body.activity_stunum,result.data[0].activity_stunum);

                    var activity_status = checkUpdateData(req.body.activity_status,result.data[0].activity_status);
                    var activity_score = checkUpdateData(req.body.activity_score,result.data[0].activity_score);

                    var create_time = checkUpdateData(req.body.create_time,result.data[0].create_time);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);
                    var sql  =  "update "+tableName
                    +" set activity_name = '"+activity_name
                    +"' , activity_time = '"+activity_time
                    +"' , activity_leader = '"+activity_leader
                    +"' , activity_abstract = '"+activity_abstract
                    +"' , activity_detail = '"+activity_detail
                    +"' , activity_stunum = '"+activity_stunum
                    +"' , activity_status = '"+activity_status
                    +"' , activity_score = '"+activity_score
                    +"' , create_time = '"+create_time
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
router.post('/deleteActi', function (req, res) {
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
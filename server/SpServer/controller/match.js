
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "matchs";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getAllMatchs', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据id获取信息
router.post('/getMatchById',function (req, res) {
    var sql = "select * from "+tableName+" where "+tableKey+" = "+req.body.id +" and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});



//添加
router.post('/addMatch', function (req, res) {
    var sql = "insert into "+tableName+"(match_name,match_time,match_abstract,match_detail,match_leader,match_sponsor,match_level,match_status,create_time,remark,del) value (?,?,?,?,?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.match_name,
        req.body.match_time,
        req.body.match_abstract,
        req.body.match_detail,
        req.body.match_leader,
        req.body.match_sponsor,
        req.body.match_level,
        req.body.match_status,
        req.body.create_time,
        req.body.remark,
        'normal' //del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where match_name = '" + req.body.match_name+"'  and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where match_name = '" + req.body.match_name+"' and del != 'delete'";
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
router.post('/updateMatch', function (request, response) {
    var req = request;
    var res = response;
    var id = req.body.id;

    if (id==null) {
        return res.jsonp("id is null! please check!");
    }
    //console.log("hahahhah");
    connectDB.query("select * from "+tableName+" where "+tableKey+" = "+id,function(result){
        if (result.status=="200") {
            if (result.data[0]!=null) {

                    var match_name = checkUpdateData(req.body.match_name,result.data[0].match_name);
                    var match_time = checkUpdateData(req.body.match_time,result.data[0].match_time);

                    var match_abstract = checkUpdateData(req.body.match_abstract,result.data[0].match_abstract);
                    var match_detail = checkUpdateData(req.body.match_detail,result.data[0].match_detail);

                    var match_leader = checkUpdateData(req.body.match_leader,result.data[0].match_leader);
                    var match_sponsor = checkUpdateData(req.body.match_sponsor,result.data[0].match_sponsor);

                    var match_level = checkUpdateData(req.body.match_level,result.data[0].match_level);
                    var match_status = checkUpdateData(req.body.match_status,result.data[0].match_status);

                    var create_time = checkUpdateData(req.body.create_time,result.data[0].create_time);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);

                    var sql  =  "update "+tableName
                    +" set match_name = '"+match_name
                    +"' , match_time = '"+match_time
                    +"' , match_abstract = '"+match_abstract
                    +"' , match_detail = '"+match_detail
                    +"' , match_leader = '"+match_leader
                    +"' , match_sponsor = '"+match_sponsor
                    +"' , match_level = '"+match_level
                    +"' , match_status = '"+match_status
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
router.post('/deleteMatch', function (req, res) {
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
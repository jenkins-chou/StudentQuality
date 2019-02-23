
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "user_certificate";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//根据id获取信息
router.post('/getUserCertByUserId',function (req, res) {
    var sql = "select a.*,b.* from certificate a,user_certificate b where a.id = b.certificate_id and a.del != 'delete' and b.del != 'delete' and b.user_id ='"+req.body.user_id+"'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//添加
router.post('/addUserCert', function (req, res) {
    var sql = "insert into "+tableName+"(user_id,certificate_id,get_certificate_time,user_certificate_status,create_time,remark,del) value (?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.user_id,
        req.body.certificate_id,
        req.body.get_certificate_time,
        req.body.user_certificate_status,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where user_id = '" + req.body.user_id+"' and certificate_id = '"+req.body.certificate_id+"' and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where user_id = '" + req.body.user_id+"' and certificate_id = '"+req.body.certificate_id+"' and del != 'delete'";
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
router.post('/updateUserCert', function (request, response) {
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
                    var certificate_id = checkUpdateData(req.body.certificate_id,result.data[0].certificate_id);
                    var get_certificate_time = checkUpdateData(req.body.get_certificate_time,result.data[0].get_certificate_time);
                    var user_certificate_status = checkUpdateData(req.body.user_certificate_status,result.data[0].user_certificate_status);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);
                    var sql  =  "update "+tableName
                    +" set user_id = '"+user_id
                    +"' , certificate_id = '"+certificate_id
                    +"' , get_certificate_time = '"+get_certificate_time
                    +"' , user_certificate_status = '"+user_certificate_status
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
router.post('/deleteUserCert', function (req, res) {
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
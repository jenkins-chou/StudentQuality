
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "user_moral";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//根据id获取信息
router.post('/getUserMoralByUserId',function (req, res) {
    var sql = "select a.*,b.* from moral a,user_moral b where a.id = b.moral_id and a.del != 'delete' and b.del != 'delete' and b.user_id ='"+req.body.user_id+"'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//添加
router.post('/addUserMoral', function (req, res) {
    var sql = "insert into "+tableName+"(user_id,moral_id,create_time,remark,del) value (?,?,?,?,?)";
    var sqlparams = [
        req.body.user_id,
        req.body.moral_id,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    connectDB.add(sql,sqlparams,function(result){
                console.log(result);
                return res.jsonp(result);
    })

});


//更新信息
router.post('/updateUserMoral', function (request, response) {
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
                    var moral_id = checkUpdateData(req.body.moral_id,result.data[0].moral_id);
                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);
                    var sql  =  "update "+tableName
                    +" set user_id = '"+user_id
                    +"' , moral_id = '"+moral_id
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
router.post('/deleteUserMoral', function (req, res) {
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

var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "moral";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getAllMorals', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据id获取信息
router.post('/getMoralById',function (req, res) {
    var sql = "select * from "+tableName+" where "+tableKey+" = "+req.body.id +" and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});



//添加
router.post('/addMoral', function (req, res) {
    var sql = "insert into "+tableName+"(moral_name,moral_abstract,moral_detail,moral_type,moral_status,moral_manager,moral_score,create_time,remark,del) value (?,?,?,?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.moral_name,
        req.body.moral_abstract,
        req.body.moral_detail,
        req.body.moral_type,
        req.body.moral_status,
        req.body.moral_manager,
        req.body.moral_score,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where moral_name = '" + req.body.moral_name+"'  and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where moral_name = '" + req.body.moral_name+"'  and del != 'delete'";
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
router.post('/updateMoral', function (request, response) {
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
                    var moral_name = checkUpdateData(req.body.moral_name,result.data[0].moral_name);
                    var moral_abstract = checkUpdateData(req.body.moral_abstract,result.data[0].moral_abstract);

                    var moral_detail = checkUpdateData(req.body.moral_detail,result.data[0].moral_detail);
                    var moral_type = checkUpdateData(req.body.moral_type,result.data[0].moral_type);

                    var moral_status = checkUpdateData(req.body.moral_status,result.data[0].moral_status);
                    var moral_manager = checkUpdateData(req.body.moral_manager,result.data[0].moral_manager);

                    var moral_score = checkUpdateData(req.body.moral_score,result.data[0].moral_score);
                    var create_time = checkUpdateData(req.body.create_time,result.data[0].create_time);

                    var remark = checkUpdateData(req.body.remark,result.data[0].remark);

                    var sql  =  "update "+tableName
                    +" set moral_name = '"+moral_name
                    +"' , moral_abstract = '"+moral_abstract
                    +"' , moral_detail = '"+moral_detail
                    +"' , moral_type = '"+moral_type
                    +"' , moral_status = '"+moral_status
                    +"' , moral_manager = '"+moral_manager
                    +"' , moral_score = '"+moral_score
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
router.post('/deleteMoral', function (req, res) {
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
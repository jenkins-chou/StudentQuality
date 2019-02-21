
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "certificate";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getAllCerts', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据id获取信息
router.post('/getCertById',function (req, res) {
    var sql = "select * from "+tableName+" where "+tableKey+" = "+req.body.id +" and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});



//添加
router.post('/addCert', function (req, res) {
    var sql = "insert into "+tableName+"(certificate_name,certificate_sponsor,certificate_abstract,certificate_detail,certificate_score,certificate_manager,del) value (?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.certificate_name,
        req.body.certificate_sponsor,
        req.body.certificate_abstract,
        req.body.certificate_detail,
        req.body.certificate_score,
        req.body.certificate_manager,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where certificate_name = '" + req.body.certificate_name+"'  and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where certificate_name = '" + req.body.certificate_name+"'  and del != 'delete'";
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
router.post('/updateCert', function (request, response) {
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
                    var certificate_name = checkUpdateData(req.body.certificate_name,result.data[0].certificate_name);
                    var certificate_sponsor = checkUpdateData(req.body.certificate_sponsor,result.data[0].certificate_sponsor);
                    var certificate_abstract = checkUpdateData(req.body.certificate_abstract,result.data[0].certificate_abstract);
                    var certificate_detail = checkUpdateData(req.body.certificate_detail,result.data[0].certificate_detail);
                    var certificate_score = checkUpdateData(req.body.certificate_score,result.data[0].certificate_score);
                    var certificate_manager = checkUpdateData(req.body.certificate_manager,result.data[0].certificate_manager);
                    var sql  =  "update "+tableName
                    +" set certificate_name = '"+certificate_name
                    +"' , certificate_sponsor = '"+certificate_sponsor
                    +"' , certificate_abstract = '"+certificate_abstract
                    +"' , certificate_detail = '"+certificate_detail
                    +"' , certificate_score = '"+certificate_score
                    +"' , certificate_manager = '"+certificate_manager
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
router.post('/deleteCert', function (req, res) {
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
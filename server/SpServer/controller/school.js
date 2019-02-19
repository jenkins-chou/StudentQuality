
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "school";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getAllSchool', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//添加
router.post('/addSchool', function (req, res) {
    var sql = "insert into "+tableName+"(school_name,school_abstract,school_detail,school_number,school_address,school_build_time,create_time,remark,del) value (?,?,?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.school_name,
        req.body.school_abstract,
        req.body.school_detail,
        req.body.school_number,
        req.body.school_address,
        req.body.school_build_time,
        req.body.create_time,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where school_name = '" + req.body.school_name+"'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where school_name = '" + req.body.school_name+"'";
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
router.post('/updateSchool', function (request, response) {
    var req = request;
    var res = response;
    var id = req.body.id;

    if (id==null) {
        return res.jsonp("id is null! please check!");
    }
    //console.log("hahahhah");
    connectDB.query("select * from "+tableName+" where "+tableKey+" = "+user_id,function(result){
        if (result.status=="200") {
            if (result.data[0]!=null) {
                console.log(checkUpdateData("dsadsa","adsadsa"));
                    var school_name = connectDB.checkUpdateData(req.body.school_name,result.data[0].school_name);
                    var school_abstract = connectDB.checkUpdateData(req.body.school_abstract,result.data[0].school_abstract);
                    var school_detail = connectDB.checkUpdateData(req.body.school_detail,result.data[0].school_detail);
                    var school_address = connectDB.checkUpdateData(req.body.school_address,result.data[0].school_address);
                    var school_build_time = connectDB.checkUpdateData(req.body.school_build_time,result.data[0].school_build_time);
                    
                    var sql  =  "update "+tableName
                    +" set school_name = '"+school_name
                    +"' , school_abstract = '"+school_abstract
                    +"' , school_detail = '"+school_detail
                    +"' , school_address = '"+school_address
                    +"', school_build_time = '"+school_build_time
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
module.exports = router;
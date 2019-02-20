
var express = require('express');
var router = express.Router();
var url = require('url');
var connectDB = require('../tool/connectDB');
connectDB = new connectDB();

var tableName = "user";//表名
var tableKey = "id";//主键
var tableDelete = "del";//删除标志位

//获取所有数据
router.post('/getusers', function (req, res) {
    var sql = "select * from "+tableName+" where "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        return res.jsonp(result);
    })
});

//根据id获取信息
router.post('/getUserById',function (req, res) {
    var sql = "select * from "+tableName+" where "+tableKey+" = "+req.body.user_id +" and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//根据id获取信息
router.post('/login',function (req, res) {
    var sql = "select * from "+tableName+" where name = '"+req.body.name +"' and pass = '"+req.body.pass+"' and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//根据获取所有老师
router.post('/getTeachers',function (req, res) {
    var sql = "select * from "+tableName+" where type = '2' and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});

//根据学院获取所有老师
router.post('/getTeachersByCollege',function (req, res) {
    var sql = "select * from "+tableName+" where type = '2' and college_id = '"+req.body.college_id +"' and school_id = '"+req.body.school_id+"' and "+tableDelete+" != 'delete'";
    connectDB.query(sql,function(result){
        console.log(result);
        return res.jsonp(result);
    });
});


//添加
router.post('/addUser', function (req, res) {
    var sql = "insert into "+tableName+"(name,pass,realname,avatar,slogan,sex,age,idnum,nation,registered_residence,email,useridentify,phone,address,health,type,create_time,entrance_time,class_id,class_name,college_id,college_name,school_id,school_name,remark,del) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    var sqlparams = [
        req.body.name,
        req.body.pass,
        req.body.realname,
        req.body.avatar,
        req.body.slogan,
        req.body.sex,
        req.body.age,
        req.body.idnum,
        req.body.nation,
        req.body.registered_residence,
        req.body.email,
        req.body.useridentify,
        req.body.phone,
        req.body.address,
        req.body.health,
        req.body.type,
        req.body.create_time,
        req.body.entrance_time,
        req.body.class_id,
        req.body.class_name,
        req.body.college_id,
        req.body.college_name,
        req.body.school_id,
        req.body.school_name,
        req.body.remark,
        'normal' //user_del 状态
    ]
    var sqlQuery = "select * from "+tableName+" where name = '" + req.body.name+"'  and del != 'delete'";//用于查询是否存在同名的
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
                    var sqlQueryAgain = "select * from "+tableName+" where name = '" + req.body.name+"'";
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
router.post('/updateuser', function (request, response) {
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
                console.log(checkUpdateData("dsadsa","adsadsa"));
                    var name = checkUpdateData(req.body.name,result.data[0].name);
                    var pass = checkUpdateData(req.body.pass,result.data[0].pass);
                    var sql  =  "update "+tableName+" set name = '"+name
                    +"' , pass = '"+pass
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
router.post('/deleteuser', function (req, res) {
    var user_id = req.body.user_id;
    if (user_id==null) {
        return res.jsonp("user_id is null! please check!");
    }else{
        var sql = "update "+tableName+" set "+tableDelete+" = 'delete' where "+tableKey+" = "+user_id;
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

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
router.post('/updateUser', function (request, response) {
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

                    var name = checkUpdateData(req.body.name,result.data[0].name);
                    var pass = checkUpdateData(req.body.pass,result.data[0].pass);

                    var realname = checkUpdateData(req.body.realname,result.data[0].realname);
                    var avatar = checkUpdateData(req.body.avatar,result.data[0].avatar);
                    var slogan = checkUpdateData(req.body.slogan,result.data[0].slogan);
                    var sex = checkUpdateData(req.body.sex,result.data[0].sex);
                    var age = checkUpdateData(req.body.age,result.data[0].age);
                    var idnum = checkUpdateData(req.body.idnum,result.data[0].idnum);
                    var nation = checkUpdateData(req.body.nation,result.data[0].nation);
                    var registered_residence = checkUpdateData(req.body.registered_residence,result.data[0].registered_residence);
                    var email = checkUpdateData(req.body.email,result.data[0].email);
                    var useridentify = checkUpdateData(req.body.useridentify,result.data[0].useridentify);
                    var phone = checkUpdateData(req.body.phone,result.data[0].phone);
                    var address = checkUpdateData(req.body.address,result.data[0].address);
                    var health = checkUpdateData(req.body.health,result.data[0].health);
                    var type = checkUpdateData(req.body.type,result.data[0].type);
                    var create_time = checkUpdateData(req.body.create_time,result.data[0].create_time);
                    var entrance_time = checkUpdateData(req.body.entrance_time,result.data[0].entrance_time);

                    var class_id = checkUpdateData(req.body.class_id,result.data[0].class_id);
                    var class_name = checkUpdateData(req.body.class_name,result.data[0].class_name);
                    var college_id = checkUpdateData(req.body.college_id,result.data[0].college_id);
                    var college_name = checkUpdateData(req.body.college_name,result.data[0].college_name);
                    var school_id = checkUpdateData(req.body.school_id,result.data[0].school_id);
                    var school_name = checkUpdateData(req.body.school_name,result.data[0].school_name);

                    var sql  =  "update "+tableName
                    +" set name = '"+name
                    +"' , pass = '"+pass

                    +"' , realname = '"+realname
                    +"' , avatar = '"+avatar
                    +"' , slogan = '"+slogan
                    +"' , sex = '"+sex
                    +"' , age = '"+age
                    +"' , idnum = '"+idnum
                    +"' , nation = '"+nation
                    +"' , registered_residence = '"+registered_residence
                    +"' , email = '"+email
                    +"' , useridentify = '"+useridentify
                    +"' , phone = '"+phone
                    +"' , address = '"+address
                    +"' , health = '"+health
                    +"' , type = '"+type
                    +"' , create_time = '"+create_time
                    +"' , entrance_time = '"+entrance_time
                    +"' , class_id = '"+class_id
                    +"' , class_name = '"+class_name
                    +"' , college_id = '"+college_id
                    +"' , college_name = '"+college_name
                    +"' , school_id = '"+school_id
                    +"' , school_name = '"+school_name
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
router.post('/deleteUser', function (req, res) {
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
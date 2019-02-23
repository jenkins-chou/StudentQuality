
var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var path = require('path');

//路由列表

var uploadController = require("./controller/upload")
var errorController = require("./controller/operate_record")

var schoolController = require("./controller/school")
var collegeController = require("./controller/college")
var classController = require("./controller/class")
var userController = require("./controller/user")

var matchController = require("./controller/match")
var certController = require("./controller/cert")
var actiController = require("./controller/acti")
var moralController = require("./controller/moral")
var termController = require("./controller/term")
var courseController = require("./controller/course")

var userCourseController = require("./controller/user_course")
var userMatchController = require("./controller/user_match")
var userCertController = require("./controller/user_cert")



//var urlencodedParser = bodyParser.urlencoded({extended:false})
app.use(express.static('public'));//静态文件
app.use(express.static(path.join(__dirname, '')))

// app.use(urlencodedParser)//这里使用 urlencodedParser方式
app.use(bodyParser.urlencoded({extended: true}));

// app.use(bodyParser.json({limit: '1mb'}));  //这里指定参数使用 json 格式
app.get('/', function(req,res){
    res.send('hello this is xiaoyunhui app server api');
})

app.post('/post',function(req,res){
    console.log("params:"+req.body.params);
    res.send('hello this is post request!');
})

app.use('/upload',uploadController)
app.use('/error',errorController)

app.use('/school',schoolController)
app.use('/college',collegeController)
app.use('/class',classController)
app.use('/user',userController)

app.use('/match',matchController)
app.use('/cert',certController)
app.use('/acti',actiController)
app.use('/moral',moralController)
app.use('/term',termController)
app.use('/course',courseController)

app.use('/user_course',userCourseController)
app.use('/user_match',userMatchController)
app.use('/user_cert',userCertController)


app.listen(7777)
console.log("数据服务器已打开, 端口: 7777");
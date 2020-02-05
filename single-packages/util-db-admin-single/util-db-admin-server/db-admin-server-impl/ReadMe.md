# 数据库管理工具

## 概述


## 用法说明


## 接口清单

通用请求参数定义如下：

**BaseUrl：** http://127.0.0.1:9918/dbadmin

### 获取操作主界面

**url：** ${BaseUrl}/ui/mainPage

**请求方式：** http + get  

**响应方式：** html

**请求示例：**

无请求参数。
```
http://127.0.0.1:9918/dbadmin/database/layuiTestPage
```

**返回示例：**

返回 html 页面。

### 获取服务器上的全部数据库

**url：** ${BaseUrl}/database/showDatabases

**请求方式：** http + get  

**响应方式：** json

**请求示例：**

无请求参数。
```
http://127.0.0.1:9918/dbadmin/database/showDatabases
```

**返回示例：**

```
["information_schema","1-temp","eladmin","iot_smarthome_platform","iot_smarthome_platform20190115",
"iotservice_20190302","iotservice_mcloud","iotservice_mcloud20190604","iotservice_mcloud20190701",
"iotservice_mcloud20190710","iotservice_mcloud20190813","iotservice_mcloud20190820",
"iotservice_mcloud20190822删除欧瑞博之前备份","iotservice_mcloud20190822新增欧瑞博数据后",
"iotservice_mcloud20190827","iotservice_mcloud20190828","iotservice_mcloud20190920",
"iotservice_mcloud20190925","iotservice_mcloud20191018","iotservice_mcloud_20190321",
"iotservice_mcloud_20190430","iotservice_mcloud_20190506","iotservice_user_20180612","iotservice_user_test",
"iptv-qiao20190625","iptv-test20190625","iptv_msgdispatch","iptv_msgdispatch20190508",
"iptv_msgdispatch20190708","mcloud-debugging开发db","my_db","mysql","ouruibo2w20190821",
"performance_schema","sakila","speaker_1205","sys","world"]
```

### 切换数据库

**url：** ${BaseUrl}/database/useDatabase/{dbName}

**请求方式：** http + get  

**响应方式：** json

**请求示例：**

无请求参数。
```
http://127.0.0.1:9918/dbadmin/database/useDatabase/mysql
```

**返回示例：**

无返回参数。

### 获取数据库的全部表

**url：** ${BaseUrl}/database/showTables

**请求方式：** http + get  

**响应方式：** json

**请求示例：**

无请求参数。
```
http://127.0.0.1:9918/dbadmin/database/showTables
```

**返回示例：**

```

```

### 获取指定数据库中指定表的所有字段

**url：** ${BaseUrl}/database/showFields

**请求方式：** http + get  

**响应方式：** json

**请求示例：**

```
http://127.0.0.1:9918/dbadmin/database/showFields?dbName=iotservice_mcloud&tableName=t_mc_brand
```

**返回示例：**

```
["id","manufacturer","brand","brand_name","trademark_number","mark_expires","markfile_copy","status","gmt_create","gmt_update"]
```

### showFieldsByResult

**url：** ${BaseUrl}/database/showFieldsByResult

**请求方式：** http + get  

**响应方式：** json

**请求示例：**

```
http://127.0.0.1:9918/dbadmin/database/showFieldsByResult?dbName=iotservice_mcloud&tableName=t_mc_brand
```

**返回示例：**

```
{
    "code": "000000",
    "msg": "请求成功",
    "data": [
        "id",
        "manufacturer",
        "brand",
        "brand_name",
        "trademark_number",
        "mark_expires",
        "markfile_copy",
        "status",
        "gmt_create",
        "gmt_update"
    ],
    "success": true
}
```


## NEXT(后续将开发的功能) 

1. 监控数据库访问者

2. 监控指定表的操作记录

3. 动态切换数据源





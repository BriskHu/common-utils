# HTTP 常用工具

## 文件上传

### 获取上传页面

**url:** localhost:9001/file/upload

**请求方式:** http + get

**请求示例:** 
无请求参数，直接在浏览器中输入本地址即可访问。

**返回示例:**
返回为一个html页面，通过在页面完成上传操作。

### 同时上传文件和字段(基于html form的方式)

**url:** localhost:9001/file/uploadFileAndField

**请求方式:** http + post

**Content-Type:** form-data; charset=utf-8

**请求示例:**

```
userAccount : 13688072287
modelId : 1188
provinceCode : 320000
excelFile : 串码上传参考示例.xlsx
```

**返回示例:** 

### 同时上传文件和字段(基于layui的方式)

**url:** localhost:9001/file/getExcelFileFromHtml

**请求方式:** http + post

**Content-Type:** form-data; charset=utf-8

**请求示例:**

```
userAccount : 13688072287
modelId : 1188
provinceCode : 320000
excelFile : 串码上传参考示例.xlsx
```

**返回示例:** 



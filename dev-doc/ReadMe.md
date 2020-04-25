# 开发常用文档

## 模块介绍
本模块用来管理一些常用软件的配置、使用信息。模块目录结构如下所示
```
|-- ReadMe.md
|-- config
|   |-- maven
|       |-- settings.xml
|       |-- settingsAli.xml
|       |-- settingsCompany.xml
|-- pom.xml
|-- script
|   |-- shell

```
其中，ReadMe.md文档是模块的说明文档，config目录放置常用软件配置相关的文档，script目录放置一些常用脚本。


## Markdown文档编写

### 表格处理

在一般应用场景下，使用“--- | ---”就可以了。如果需要处理合并单元格等操作，则可以使用基于html的方式。示例如下。
合并单元格的写法：

<table>
    <tr>
        <td>列一</td> 
        <td>列二</td> 
   </tr>
   <tr>
        <td colspan="2">合并行</td>    
   </tr>
   <tr>
        <td>列一</td> 
        <td>列二</td> 
   </tr>
    <tr>
        <td rowspan="2">合并列</td>    
        <td >行二列二</td>  
    </tr>
    <tr>
        <td >行三列二</td>  
    </tr>
</table>

说明：上述写法在idea中可能显示不出预想的效果，但是在网页上会正常显示单元格合并的效果。




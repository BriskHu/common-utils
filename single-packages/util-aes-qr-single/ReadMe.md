# AES加密和二维码生成图形化工具


## 文档结构

```
util-aes-qr-single
|-- attachment 第三方包
|-- release-package 本项目的发布包，是可直接运行的jar包

```
“attachment”文件夹中存放的是一些可能需要的第三方包。其中的“bcprov-jdk16-1.45.jar”是使用BC作为AES算法提供者时需要的
第三方包。在运行时如果报security provider 异常，则需要将该包放置到电脑jre安装目录下的“lib/ext”目录中。
# AES加密和二维码生成图形化工具


## 文档结构

项目的模块和文档结构如下

```
util-aes-qr-single
|-- attachment 第三方包
|-- common-jgui 使用Java进行GUI开发的通用工具方法包。该包可用于其他使用Java进行GUI开发的项目。
|-- util-aes aes加密、解密及UI界面工具包。该包可单独运行，提供aes加密、解密的操作界面。
|-- util-aes-qr aes加解密，生成二维码、扫描二维码的工具包。该包提供完整的aes加解密、生成/扫描二维码的操作界面。
该包是整个项目的主类入口。该包依赖于其他四个模块。
|-- util-aes-qr-common aes加解密、生成/扫描二维码工具包的公用代码模块。不能独立运行。
|-- util-qr 生成、扫描二维码即UI界面工具包。该包可单独运行，提供生成二维码、扫描二维码的操作界面。 
|-- release-package 本项目的发布包，是可直接运行的jar包

```
“attachment”文件夹中存放的是一些可能需要的第三方包。其中的“bcprov-jdk16-1.45.jar”是使用BC作为AES算法提供者时需要的
第三方包。在运行时如果报security provider 异常，则需要将该包放置到电脑jre安装目录下的“lib/ext”目录中。


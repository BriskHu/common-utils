# 编码与解码

本模块提供了常用的编码、解码工具方法，包括加解密方法。提供的工具方法如下：

<table>
    <tr>
        <td>类型</td>
        <td>核心算法</td>
        <td>接口</td>
        <td>说明</td>
    </tr>
    <tr>
        <td rowspan="3" >加密</td>
        <td >AES</td>
        <td >AesUtil.encrypt(String origin)</td>
        <td >使用默认的秘钥和偏移量</td>
    </tr>
    <tr>
        <td >AES</td>
        <td >AesUtil.decrypt(String encryptedString)</td>
        <td >使用默认的秘钥和偏移量</td>
    </tr>
    <tr>
        <td>AES</td>
        <td>AesUtil.decrypt(String encryptedString)</td>
        <td>使用默认的秘钥和偏移量</td>
    </tr>
</table>
 

注意：默认要求字符串为UTF-8编码的。

# ShortUrlCreator

一个短网址生成器,基于`bootsrap.js`做了简单的前端交互,后台使用`java web`,做了简单的登录授权.

### 部署

默认部署端口号：`8081`



### 接口文档

####  存储URL信息


支持格式

> JSON

HTTP请求方式

> POST

请求参数

>| 参数     | 必选  | 类型   | 说明             |
>| -------- | ----- | ------ | ---------------- |
>| longurl  | ture  | string | 原网址           |
>| shorturl | false | String | 用户自定义短网址 |
>| expire   | false | int    | 用户设置过期时间 |

​	返回字段 

| 返回字段 | 字段类型 | 说明                                                         |
| -------- | -------- | ------------------------------------------------------------ |
| status   | int      | 返回结果状态。0：正常；1：错误。                             |
| content  | string   | 返回状态如果为正常，返回短网址，返回结果如果为错误，返回错误信息 |

接口示例

> 地址:http://xxxx:8081/save?longurl=www.baidu.com&expire=1&shorturl=baidu

> 结果：
>
> {
> "status": "0",
> "content": "\"http://xxxx:8081/ZS/baidu\""
> }



#### 重定向url

URL

> http:/xxxx:8081/ZS/

支持格式

> String

HTTP请求方式

> GET

请求参数

> | 参数 | 必选 | 类型   | 说明           |
> | ---- | ---- | ------ | -------------- |
> | url  | ture | string | 获取短网址信息 |

返回字段 

>| 返回字段 | 字段类型 | 说明                                             |
>| -------- | -------- | ------------------------------------------------ |
>| longurl  | String   | 获取短网址和长网址的映射，将短网址重定向到长网址 |

接口示例

> 地址：http://xxxx:8081/ZS/baidu

> 结果: 获取映射百度页面



#### 登录信息

URL

> http://xxxx:8081/login

支持格式

> JSON

HTTP请求方式

> POST	

请求参数

> | 参数     | 必选 | 类型   | 说明       |
> | -------- | ---- | ------ | ---------- |
> | username | ture | String | 获取用户名 |
> | password | true | String | 获取密码   |

返回字段 

> | 返回字段 | 字段类型    | 说明                                                         |
> | -------- | ----------- | ------------------------------------------------------------ |
> | status   | String      | 返回结果状态。0：正常；1：错误。                             |
> | content  | String/User | 返回状态如果为正常，返回用户信息，返回结果如果为错误，返回错误信息 |

接口示例

> 地址：http://xxxx:8081/login?username=z&password=z

> 结果: {"status":"0","content":"{\"isPrime\":0,\"password\":\"z\",\"userId\":1,\"username\":\"z\"}"} 

> 地址：http://xxxx:8081/login?username=z&password=zzz

>结果：{"status":"-1","content":"密码错误"} 



#### 注册信息

URL

>http://xxxx:8081/register

支持格式

>JSON

HTTP请求方式

>POST

请求参数

> | 参数     | 必选 | 类型   | 说明       |
> | -------- | ---- | ------ | ---------- |
> | username | ture | String | 获取用户名 |
> | password | true | String | 获取密码   |

返回字段 

> | 返回字段 | 字段类型    | 说明                                                         |
> | -------- | ----------- | ------------------------------------------------------------ |
> | status   | String      | 返回结果状态。0：正常；1：错误。                             |
> | content  | String/User | 返回状态如果为正常，返回用户信息，返回结果如果为错误，返回错误信息 |

接口示例


> 结果:{"status":"0","content":"{\"isPrime\":0,\"password\":\"zzz\",\"username\":\"zzz\"}"} 

> 地址：http://xxxx:8081/register?username=z&password=zzz

> 结果：{"status":"-1","content":"用户名已存在！"} 


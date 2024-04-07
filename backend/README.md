# 工程简介

# upd 2024.3.31

- 数据库sql带上了数据，图片存在了./img下
- 管理员账号`admin`，密码`123456`，前端没有提供带管理员权限的注册
- 直接执行`clubWithData.sql`即可创建带数据的数据库，没有图片信息


# 改动

- 重新生成了一次pojo，mapper，使用`sql`目录下的文件创建数据库

- 配置信息写在`application.yml`中，`application.properties`加入`.gitignore`中，在`application.properties`中包含本地信息

```properties
# application.properties文件内容
custom.mysql.username=本地数据库账号
custom.mysql.password=本地数据库密码
custom.mysql.host=服务ip
custom.mysql.port=端口
custom.mysql.datasource=club
```

- `ReturnCode`改为枚举类型，使用`ReturnCodes.SUCCESS.getCode()/getMessage()`方式获取信息

- 增加`CommonUtil`工具类，提供检查类是否含有空字段的方法

# 组件

- knife4j: 访问`localhost:3000/doc.html`查看、在线调试接口
- 全局异常处理器：抛出异常会根据异常类型匹配`GlobalExceptionHandler`类的方法中，由方法返回对应的信息

# 约定

- 对于`GET`请求使用`@RequestParam`传参，对于`POST`请求使用`@RequestBody`传参

- `@RequestParam`只能接收普通的类(如：`String`, `int`, `Long`, `map`(特殊))，不能接收其他实体类

- 传入和返回都使用实体类传参，见`LoginController.getToken`方法
  - 传入实体使用`DTO`，放到`model.dto`下
  - 返回实体使用`VO`或已存在的`pojo`或其他可以共用的实体类，放到`model.*`下
  
# 待解决
- [x] login失败时 `Authentication authenticate = authenticationManager.authenticate(authenticationToken);` 会自动处理，无法返回统一错误格式

- [x] 带token的header错误时会自动处理，无法返回统一错误格式，[使用这的第二个解决方法](https://blog.csdn.net/m0_37731470/article/details/116754395)

- 数据备份功能，不知道怎么执行sql脚本导入数据，接口在AdminService.recovery()，调用了SqlScriptService，里面写了三个版本都不行，第三个最合理（读sql文件转化为JDBC的statement执行，但执行到一半执行失败，可能是编码问题，也有可能JDBC不支持），测试入口在BackendApplicationTests，等价的cmd指令是:
```agsl
mysql -h 127.0.0.1 -P 3306 -u{用户名} -p{密码}
use club
source {sql文件路径}
```
# 工程简介

# 改动

- 重新生成了一次pojo，mapper，使用`sql`目录下的文件创建数据库

- 配置信息写在`application.yml`中，`application.properties`加入`.gitignore`中，在`application.properties`中包含本地信息

```properties
# application.properties文件内容
custom.mysql.username=本地数据库账号
custom.mysql.password=本地数据库密码
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
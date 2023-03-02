# small-spring
记录学习Spring IOC、AOP过程

我们需要在现有的 Spring 框架雏形中添加一个资源解析器，也就是能读取classpath、本地文件和云文件的配置内容。
这些配置内容就是像使用 Spring 时配置的 Spring.xml 一样，里面会包括 Bean 对象的描述和属性信息。 
在读取配置文件信息后，接下来就是对配置文件中的 Bean 描述信息解析后进行注册操作，把 Bean 对象注册到 Spring 容器中。

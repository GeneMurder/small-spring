# small-spring
IOC-定义一个简单的 Spring 容器，用于定义、存放和获取 Bean 对象

Spring Bean 容器的整个实现内容非常简单，也仅仅是包括了一个简单的 BeanFactory 和 BeanDefinition，
这里的类名称是与 Spring 源码中一致，只不过现在的类实现会相对来说更简化一些，在后续的实现过程中再不断的添加内容。
1) BeanDefinition，用于定义 Bean 实例化信息，现在的实现是以一个 Object 存放对象;
2) BeanFactory，代表了 Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取;

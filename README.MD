## 场景：
现有Meeting Room一间， Foosball Table 一张， 因需求时间经常发生冲突， 希望能有一个系统让多人可以预定

## 目标：
为了简化模型，现准备实现 **任意用户** 可以预定 未来 **七天** 的Meeting Room 和 Foosball，预定时间至多为 **两个小时**， 
用户可以 **预定**、 **更改**、 **查询**、 **取消**

## 示例相关技术：
- Java 8
- Spring Boot 1.5
- Spring Framework 4.3
- Spock Framework 1.1
- Hamcrest
- Gradle 3.3
- JPA
- H2
- Lombok
- Flyway
- IDE: IntelliJ

## 备注：
要求中并未指定使用REST API作为使用接口，本例子使用REST API是出于通常使用场景考虑，实际情况中，telnet command interface 也是可以采用的方案。
尽管接口实现方式多样，但内在的模型和逻辑是通用的，本例子也将重点展示如何设计model以及测试model
<h1>DApi接口管理平台🐋<br /></h1>举个栗子，对于开发人员想要调用接口，<br />以往：打开百度，搜索xxxx接口，在大量网络广告和链接中找到自己的所需的信息，再去搜索如何调用该接口，费时又费力还不一定跑*😇<br />现在：打开DApi官网，搜索想要的接口，输入参数一键调用，还可以复制代码加入到自己的程序中😍

- 💨一款在线接口管理，接口共享的工具，让开发者可以更轻便的调用接口
- 💬严密的接口调用检测机制，通过签名+nonce+二次检测机制防止接口抓包、重放等常见攻击。
- 🤺与社区中的小伙伴一起分享接口，拒绝要用接口时疯狂百度。
- 🦍超越postman的接口调用体验。
- 💭提供sdk版本，maven导入后一键使用
- 更多功能正在逐步开发中...

技术选型：

   - 核心框架:SpringBoot 2.3.9
   - Mybatis,MybatisPlus作为持久层和业务层
   - Mysql+Redis
   - SpringCloud Gateway ,SpringCloud Alibaba(Nacos+Feign)
   - Hutool工具类、commons-lang3工具类、Gson工具类

开发流程图:<br />![](https://cdn.nlark.com/yuque/0/2023/jpeg/35193804/1677501935298-e6e5c7ee-bce1-49de-8f68-968cb383aed8.jpeg)

- 结构图

![](https://cdn.nlark.com/yuque/0/2023/jpeg/35193804/1677503480877-ffcdc934-5893-47e7-b560-96ae1bb887cd.jpeg)<br />页面截图:<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/35193804/1677501964896-c809ed35-bb0b-452c-bacf-157d8df4e0af.png#averageHue=%23f2f2f2&clientId=u04eeffee-36a0-4&from=paste&height=762&id=ub40ef2e0&name=image.png&originHeight=952&originWidth=1793&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=117109&status=done&style=none&taskId=u9960b9b7-434b-4366-9222-20e82c36d60&title=&width=1434.4)

- 版本更新方向:
      1. 在接口调用时提供传送图片功能，复制调用接口后的结果。
      2. 前端页面重构
      3. 添加更多接口，增加接口类型
      4. 增加接口提供与接口调用之间的正反馈机制🍬🍬🍬...


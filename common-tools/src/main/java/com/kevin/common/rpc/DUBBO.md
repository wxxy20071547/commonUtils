# dubbo服务端启动流程
1.Spring ServiceBean监听入口中的监听事件 onApplicationEvent
ServiceBean的export()方法内部最终会执行到ServiceConfig的doExportUrls()方法-->
ServiceConfig执行发布：
1）加载所有注册中心URL
2）遍历所有Protocol，进行发布


doExportUrls()方法内部最终执行下面两个重要步骤，即 “本地发布” 和 “远程发布” -->

重点：Exporter<?> exporter = protocol.export(
proxyFactory.getInvoker(ref, (Class) interfaceClass, local));
代码中的proxyFactory为通过ExtensionLoader动态生成的JavassistProxyFactory。
代码中的protocol为通过ExtensionLoader动态生成的InjvmProtocol。



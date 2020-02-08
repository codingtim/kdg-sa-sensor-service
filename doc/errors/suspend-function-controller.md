Error was fixed by adding dependency on

    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-reflect</artifactId>
    </dependency>
    
Stacktrace:

```
09:22:39.168 [reactor-http-epoll-2] ERROR o.s.w.s.a.HttpWebHandlerAdapter - [2382213e] 500 Server Error for HTTP GET "/test"
kotlin.KotlinNullPointerException: null
    at kotlin.coroutines.jvm.internal.ContinuationImpl.getContext(ContinuationImpl.kt:105)
    Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
    |_ checkpoint ⇢ HTTP GET "/test" [ExceptionHandlingWebHandler]
Stack trace:
        at kotlin.coroutines.jvm.internal.ContinuationImpl.getContext(ContinuationImpl.kt:105)
        at kotlin.coroutines.jvm.internal.ContinuationImpl.intercepted(ContinuationImpl.kt:112)
        at kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsJvm.kt:137)
        at kotlinx.coroutines.DelayKt.delay(Delay.kt:82)
        at be.codingtim.velo.sensor.service.web.HelloController.hello(HelloController.kt:14)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.springframework.web.reactive.result.method.InvocableHandlerMethod.lambda$invoke$0(InvocableHandlerMethod.java:147)
        at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:118)
        at reactor.core.publisher.Operators$MonoSubscriber.complete(Operators.java:1637)
        at reactor.core.publisher.MonoZip$ZipCoordinator.signal(MonoZip.java:247)
        at reactor.core.publisher.MonoZip$ZipInner.onNext(MonoZip.java:329)
        at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onNext(MonoPeekTerminal.java:173)
        at reactor.core.publisher.Operators$ScalarSubscription.request(Operators.java:2199)
        at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.request(MonoPeekTerminal.java:132)
        at reactor.core.publisher.MonoZip$ZipInner.onSubscribe(MonoZip.java:318)
        at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onSubscribe(MonoPeekTerminal.java:145)
        at reactor.core.publisher.MonoJust.subscribe(MonoJust.java:54)
        at reactor.core.publisher.Mono.subscribe(Mono.java:4105)
        at reactor.core.publisher.MonoZip.subscribe(MonoZip.java:128)
        at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:55)
        at reactor.core.publisher.MonoDefer.subscribe(MonoDefer.java:52)
        at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.drain(MonoIgnoreThen.java:153)
        at reactor.core.publisher.MonoIgnoreThen.subscribe(MonoIgnoreThen.java:56)
        at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:55)
        at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:150)
        at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onNext(FluxSwitchIfEmpty.java:67)
        at reactor.core.publisher.MonoNext$NextSubscriber.onNext(MonoNext.java:76)
        at reactor.core.publisher.FluxConcatMap$ConcatMapImmediate.innerNext(FluxConcatMap.java:274)
        at reactor.core.publisher.FluxConcatMap$ConcatMapInner.onNext(FluxConcatMap.java:851)
        at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:121)
        at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onNext(MonoPeekTerminal.java:173)
        at reactor.core.publisher.Operators$ScalarSubscription.request(Operators.java:2199)
        at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.request(MonoPeekTerminal.java:132)
        at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.request(FluxMapFuseable.java:162)
        at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.set(Operators.java:2007)
        at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.onSubscribe(Operators.java:1881)
        at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onSubscribe(FluxMapFuseable.java:90)
        at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onSubscribe(MonoPeekTerminal.java:145)
        at reactor.core.publisher.MonoJust.subscribe(MonoJust.java:54)
        at reactor.core.publisher.Mono.subscribe(Mono.java:4105)
        at reactor.core.publisher.FluxConcatMap$ConcatMapImmediate.drain(FluxConcatMap.java:441)
        at reactor.core.publisher.FluxConcatMap$ConcatMapImmediate.onSubscribe(FluxConcatMap.java:211)
        at reactor.core.publisher.FluxIterable.subscribe(FluxIterable.java:139)
        at reactor.core.publisher.FluxIterable.subscribe(FluxIterable.java:63)
        at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:55)
        at reactor.core.publisher.MonoDefer.subscribe(MonoDefer.java:52)
        at reactor.core.publisher.Mono.subscribe(Mono.java:4105)
        at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.drain(MonoIgnoreThen.java:172)
        at reactor.core.publisher.MonoIgnoreThen.subscribe(MonoIgnoreThen.java:56)
        at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:55)
        at reactor.netty.http.server.HttpServerHandle.onStateChange(HttpServerHandle.java:64)
        at reactor.netty.tcp.TcpServerBind$ChildObserver.onStateChange(TcpServerBind.java:228)
        at reactor.netty.http.server.HttpServerOperations.onInboundNext(HttpServerOperations.java:465)
        at reactor.netty.channel.ChannelOperationsHandler.channelRead(ChannelOperationsHandler.java:90)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:377)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:363)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:355)
        at reactor.netty.http.server.HttpTrafficHandler.channelRead(HttpTrafficHandler.java:167)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:377)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:363)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:355)
        at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:436)
        at io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:316)
        at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:290)
        at io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:251)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:377)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:363)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:355)
        at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1410)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:377)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:363)
        at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:919)
        at io.netty.channel.epoll.AbstractEpollStreamChannel$EpollStreamUnsafe.epollInReady(AbstractEpollStreamChannel.java:792)
        at io.netty.channel.epoll.EpollEventLoop.processReady(EpollEventLoop.java:475)
        at io.netty.channel.epoll.EpollEventLoop.run(EpollEventLoop.java:378)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:834)
09:22:39.171 [reactor-http-epoll-2] DEBUG r.n.http.server.HttpServerOperations - [id: 0x2382213e, L:/127.0.0.1:8080 - R:/127.0.0.1:57914] Last HTTP response frame
09:22:39.171 [reactor-http-epoll-2] DEBUG r.n.http.server.HttpServerOperations - [id: 0x2382213e, L:/127.0.0.1:8080 - R:/127.0.0.1:57914] No sendHeaders() called before complete, sending zero-length header
09:22:39.172 [reactor-http-epoll-2] DEBUG r.n.http.server.HttpServerOperations - [id: 0x2382213e, L:/127.0.0.1:8080 - R:/127.0.0.1:57914] Decreasing pending responses, now 0
09:22:39.173 [reactor-http-epoll-2] DEBUG r.n.http.server.HttpServerOperations - [id: 0x2382213e, L:/127.0.0.1:8080 - R:/127.0.0.1:57914] Last HTTP packet was sent, terminating the channel
09:22:39.173 [reactor-http-epoll-2] DEBUG r.n.channel.ChannelOperationsHandler - [id: 0x2382213e, L:/127.0.0.1:8080 - R:/127.0.0.1:57914] No ChannelOperation attached. Dropping: EmptyLastHttpContent
```
### RPC框架对比

1、gRPC

gRPC是一个高性能、通用的开源RPC框架，其由Google主要面向移动应用开发并基于HTTP/2协议标准设计，基于ProtoBuf(Protocol Buffers)序列化协议开发，支持多种开发语言：C, C++, Java, Go, Ruby, Node.js, Python, Ruby, Objective-C, PHP, C#...

主要应用于：Google、Hadoop、ActiveMQ、Netty

2、Thrift

Thrift是一种可伸缩的跨语言开发框架

支持语言：C++, Java, Python, PHP, Ruby, Erlang, Perl, Haskell, C#, Cocoa, JavaScript, Node.js, Smalltalk, OCaml, Delphi...

主要应用于：Hadoop、HBase、Cassandra、Scribe、LastFM、Facebook、 Evernote、Spark、Storm、Flume、Flink、Aurora、Parquet

Thrift支持的服务模型

- TSimpleServer：简单的单线程服务模型，常用于测试；
- TThreadPoolServer：多线程服务模型，使用标准的阻塞式IO；
- TNonblockingServer：多线程服务模型，使用非阻塞式IO（需使用TFramedTransport数据传输方式）

TProtocol（协议层），定义数据传输格式，例如：

- TBinaryProtocol：二进制格式；
- TCompactProtocol：压缩格式；
- TJSONProtocol：JSON格式；
- TSimpleJSONProtocol：提供JSON只写协议, 生成的文件很容易通过脚本语言解析；
- TDebugProtocol：使用易懂的可读的文本格式，以便于debug

TTransport（传输层），定义数据传输方式，可以为TCP/IP传输，内存共享或者文件共享等）被用作运行时库。

- TSocket：阻塞式socker；
- TFramedTransport：以frame为单位进行传输，非阻塞式服务中使用；
- TFileTransport：以文件形式进行传输；
- TMemoryTransport：将内存用于I/O，java实现时内部实际使用了简单的ByteArrayOutputStream；
- TZlibTransport：使用zlib进行压缩， 与其他传输方式联合使用，当前无java实现；

3、ZeroC ICE

*ZeroC*公司的*ICE*(Internet Communications Engine)中间件

支持语言：目前Ice平台支持客户端API的语言有C++、.NET、Java、Python、Object-C、Ruby、PHP、JavaScript等。在服务器可以使用C、.NET、Java、Python等来开发

||gRPC|Thrift|ZeroC ICE|
|---|---|---|---|
|git hub fork|4353|2735|424|
|git hub contributors| 401 |249|20|
|mvn引用|383|729|18|

### 性能对比

使用生产环境机器（虚拟机）进行性能测试
- 内存：24G
- cpu:6核 2.2GHz
- 40线程并发

|rpc|条件|tps|cpu|网络|平均延时(ms)|
|---|---|---|---|---|---|
|thrift|thread-selector 报文长度：5|11.349w|413%|76Mb|0.3524|
|thrift| thread-selector 报文长度：500 | 8.642w  | 370% | 407Mb | 0.4628       |
|thrift|thread-selector 报文长度：1k| 7.324w  |390%|671Mb|0.5462|
|thrift|thread-selector 报文长度：10k|1.109w|147%|895Mb|3.6068|
|grpc|报文长度：5|5.961w|516%| 48Mb  |0.6710|
|grpc|报文长度：500|5.399w|550%|260Mb|0.7409|
|grpc|报文长度：1k|5.228w|500%| 448Mb |0.7651|
|grpc|报文长度：10k|1.109w|230%|890Mb|3.6068|


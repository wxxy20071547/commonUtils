#Redis查看慢查询：

设置慢查询的队列大小 ： slowlog-max-len = 128 （默认值）

说明：redis的慢查询其实是一个先进先出队列，且是固定长度，保存在内存当中。

设置慢查询的记录阀值（微秒）： slowlog-log-slower-than = 10000 （默认值）

说明：

1.例如默认值10000，即代表命令超过10000微秒（10毫秒）就记录到慢查询队列

2.如果想记录所有的命令到慢查询，则将其值设为 0。

3.如果所有命令都不记录，则将其值设为 -1。

配置方法：

1.修改配置文件重启

2.使用命令动态配置 ：

config set slowlog-max-len 1000

config set slowlog-log-slower-than 1000

慢查询命令

slowlog get [n] --- 获取慢查询队列，n可选，代表获取多少条 

slowlog len --- 获取慢查询队列长度

slowlog reset --- 清空慢查询队列


#使用Profiling捕捉MongoDb慢查询

**返回最近的10条记录**

db.system.profile.find().limit(10).sort({ts:-1}).pretty()

**返回所有的操作，除command类型的**

db.system.profile.find({op: {$ne:'command'}}).pretty()

**返回特定集合**

db.system.profile.find({ns:'mydb.test'}).pretty()

**返回大于5毫秒慢的操作**

db.system.profile.find({millis:{$gt:5}}).pretty()

**从一个特定的时间范围内返回信息**

db.system.profile.find(
                  {
                   ts : {
                         $gt : new ISODate("2015-10-18T03:00:00Z"),
                         $lt : new ISODate("2015-10-19T03:40:00Z")
                        }
                  }
                 ).pretty()
                 
**特定时间，限制用户，按照消耗时间排序**

    db.system.profile.find({
                    ts : {
                          $gt : newISODate("2015-10-12T03:00:00Z") ,
                          $lt : newISODate("2015-10-12T03:40:00Z")
                         }
                  },
                  { user : 0 }
                 ).sort( { millis : -1 } )
                 
**查看最新的 Profile  记录：** 

db.system.profile.find().sort({$natural:-1}).limit(1)

** 显示5个最近的事件**

show profile

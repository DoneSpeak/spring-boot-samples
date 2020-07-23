## 需要确定的问题

## cron job 和 simple job不同之处

Cron Trigger
```
CRON_EXPRESSION VARCHAR(120) NOT NULL COMMENT 'cron表达式',
TIME_ZONE_ID    VARCHAR(80) COMMENT '时区ID',
```

Simple Trigger
```
REPEAT_COUNT    BIGINT(7)    NOT NULL COMMENT '重复次数',
REPEAT_INTERVAL BIGINT(12)   NOT NULL COMMENT '重复间隔',
```

```sql
CREATE TABLE QRTZ_TRIGGERS
(
    SCHED_NAME     VARCHAR(120) NOT NULL COMMENT '调度器名，和配置文件org.quartz.scheduler.instanceName保持一致',
    TRIGGER_NAME   VARCHAR(190) NOT NULL COMMENT '触发器的名字',
    TRIGGER_GROUP  VARCHAR(190) NOT NULL COMMENT '触发器所属组的名字',
    JOB_NAME       VARCHAR(190) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
    JOB_GROUP      VARCHAR(190) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
    DESCRIPTION    VARCHAR(250) NULL COMMENT '描述',
    NEXT_FIRE_TIME BIGINT(13)   NULL COMMENT '下一次触发时间',
    PREV_FIRE_TIME BIGINT(13)   NULL COMMENT '上一次触发时间',
    PRIORITY       INTEGER      NULL COMMENT '线程优先级',
    TRIGGER_STATE  VARCHAR(16)  NOT NULL COMMENT '当前trigger状态，设置为ACQUIRED,如果设置为WAITING,则job不会触发',
    TRIGGER_TYPE   VARCHAR(8)   NOT NULL COMMENT '触发器类型',
    START_TIME     BIGINT(13)   NOT NULL COMMENT '开始时间',
    END_TIME       BIGINT(13)   NULL COMMENT '结束时间',
    CALENDAR_NAME  VARCHAR(190) NULL COMMENT '日历名称',
    MISFIRE_INSTR  SMALLINT(2)  NULL COMMENT 'misfire处理规则:
        * 1代表【以当前时间为触发频率立刻触发一次，然后按照Cron频率依次执行】,
        * 2代表【不触发立即执行,等待下次Cron触发频率到达时刻开始按照Cron频率依次执行】,
        * -1代表【以错过的第一个频率时间立刻开始执行,重做错过的所有频率周期后，当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行】',
    JOB_DATA       BLOB         NULL COMMENT 'JOB存储对象'
) ENGINE = InnoDB COMMENT = '存储已配置的 Trigger 的信息';
```

- [ ] 设法把blob使用json或者properties
- [ ] 监控功能怎么实现？
- [ ] 是否可以提交trigger，但不执行？ -》应该可以通过Trigger的状态进行分析

## 参考

- [分布式定时任务调度框架实践](https://mp.weixin.qq.com/s/l4vuYpNRjKxQRkRTDhyg2Q?spm=a2c4e.10696291.0.0.560e19a42ZSDGd)
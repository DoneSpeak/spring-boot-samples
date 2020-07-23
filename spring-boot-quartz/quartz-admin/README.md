Quartz Admin
===

提供Quartz Job管理功能。

- 方便了解Job的执行情况
- 无需重启服务器就能够操作job的执行
- 能够及时发现job的失败和进行重试
- 可以查看有哪些scheduler实例和状态

功能：

- 触发当个job
    - 获取列表
    - 直接触发
    - 触发时设置trigger
    - 添加不执行
- 控制trigger的执行
    - 获取列表
    - 暂停
    - 执行
    - 删除
- 操作scheduler
    - 获取列表
  
- 拓展功能
    - 获取trigger执行结果（trigger的状态无法表示执行结果）
    - 获取trigger执行失败的原因

需要特别处理：

- 多数据源
- 权限控制配置
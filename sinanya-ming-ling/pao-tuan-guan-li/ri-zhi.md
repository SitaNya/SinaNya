---
description: 自动记录文字团日志
---

# 日志

## .log on &lt;日志名称&gt;

开启一个日志

* 如果日志不存在会创建
* 如果日志存在会在原记录上追加

同一时间一个群只能开启一个日志

![](../../.gitbook/assets/f267cd97-28a5-4494-9423-f2c5330c2021.png)

![](../../.gitbook/assets/9bc678d6-1d3f-4b23-918a-1d8a4cced414.png)

## .log off &lt;日志名称&gt;

![](../../.gitbook/assets/bb479256-4546-4c28-9e23-57872f05b207.png)

## .log list

查看本群内日志列表

![](../../.gitbook/assets/7c122b68-acb2-4b87-ba13-05699500b332.png)

## .log get &lt;日志名称&gt;

获取日志，会分为日志文件和染色文件两个文件发送，结果会发送到get人的QQ邮箱

* 日志文件:txt可以打开，包含了规整后的日志内容，会自动给玩家说话加“”并补全括号
* 染色文件:word可以打开，包含了过滤后的日志内容，不会包含（）内文件

![](../../.gitbook/assets/72593443-d4cb-4c3f-b205-2ac75401f5e2.png)

## .log rm &lt;日志名称&gt;

删除日志，会永远删除日志的记录再也无法恢复

![](../../.gitbook/assets/36e9842b-238d-49df-90df-8fe104e34f6d.png)


---
description: Linux服务器搭建说明
---

# 我是一名新手，我使用Linux服务器

## 我是一名新手，我使用Linux服务器

Linux服务器搭建说明 

**你应购买Centos7的服务器，因为只有Centos7的服务器默认支持Docker服务**

## 准备

### JDK

首先，你需要一个JDK，这是运行Java程序的必备物品

你可以从此链接得到JDK

* 官方: [JDK1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* 新版本已使用新官网[https://sinanya.com](https://sinanya.com)
* 请跳转至新页面，放弃本页面内容

下载应该获得一个.rpm结尾的文件，使用命令

`yum localinstall -y jdk-8u211-linux-x64.rpm`

即可完成安装

### 酷Q

你可以按照以下教程获得一个Linux下的酷Q

[酷Q on docker](https://cqp.cc/t/34558)

这里必须提及的注意事项是。本系统的9999端口必须被开启，因此你的docker命令应该类似如下

```text
docker run --name=test29 -d -p 9999:9999 -p 8080:9000 -v /cool-test:/home/user/coolq -e VNC_PASSWD=woyebuzhidao -e COOLQ_ACCOUNT=1984749515 coolq/wine-coolq 
```

### 服务本体

### HttpApi

下载的服务本体解压后，可以看到SinaNya文件夹，进去后可以找到bin目录

bin目录下存在一个文件名为`org.inlinc.inhttp.cpk`的文件，将它放到酷Q的app目录下即可，这个目录如果你按照上方教程操作，应该是`/root/coolq-data/app`

### 启动酷Q

首先你的骰子QQ号必须开启设备锁，具体如何开启请百度

尝试启动酷Q，按照[酷Q on docker](https://cqp.cc/t/34558)教程中的方式打开网页进入，找到悬浮窗，右键按照如下步骤打开应用管理

打开应用管理后寻找HttpApi

![](../../.gitbook/assets/9e712542-b213-4649-b2aa-e4c300f4c82f.png)

右侧寻找菜单，关闭服务 右侧寻找菜单，设置

![](../../.gitbook/assets/5d8090b9-c22e-45f6-bd1b-63fbdf1d377e.png)

### 首先看地址部分

![](../../.gitbook/assets/76ffb0b9-0213-4775-b25d-c0a858c8613b.png)

```text
这里的默认值为: http://localhost/coolq/demo.php
如果你在本机启动服务，就无需修改了，如果不在本机的话，需要将localhost修改为你启动服务器的地址

所有人都需要在localhost后面添加:50070
最终结果为
http://localhost:50070/coolq/demo.php
```

> 这里HttpApi有一个小bug，关闭服务后可能显示一次崩溃，点击酷Q重启即可 ![](media/15608698791397/15608725506449.jpg)

## 修改配置文件

打开`SinaNya/conf`目录，下面有个文件叫`sinanya.properties`，使用记事本或者其它文本工具打开它

可以看到里面有很多配置信息，如下

```text
######################################必填信息######################################
###############系统(务必修改hostIp、javaPort为本机IP和服务执行端口)####################
#主机的外网IP，服务器的话很好找，个人电脑的话请百度如何找
hostIp=localhost
#httpApi的默认端口就是9999，没有特殊必要无需更改
javaPort=50070
serverPort=9999
######################################邮箱信息######################################
##不填会使用缇娜的邮箱发送，填错会导致发不出去日志邮件###
mailUserName=2730902267@qq.com
mailPassword=kktjwuakdafbdcej
#这里填写你主人的邮箱，如果服务因为自身停止会有报警邮件
masterMail=450609203@qq.com
##################################以下均为选填信息###################################
#如果删除会使用系统内默认值，实在觉得改出问题了就删干净##
#####################################数据库信息#####################################
#无需修改
dbPassword=rong
######################################基础信息######################################
#机器人开关
botStart=啊~您需要奈梅斯了吗？我一定会好好努力的
botAlreadyStart=嗯！我在的呢，您尽管吩咐吧
botStop=这样啊……你找到更好的骰子了对吗？奈梅斯明白的，会好好安静下来的……
botAlreadyStop=奈梅斯还不够安静吗？您……您需要我离开吗？
botExit=明白了……我现在就走（鞠躬）
#获取资料
bookCard=COC七版规则空白卡奈梅斯·西莉亚私人订制版By贝尔巨佬.xlsx\n请使用此链接下载https://pan.baidu.com/s/1M3veskXYFJjwXP1eKHaX4g。有更新更好的版本请随时联系窝。
bookRP=角色扮演三百六十五问.zip\n请使用此链接下载https://share1.heiluo.com/share/link/8b93f1f15a974d4a9fda1890863a0af1。有更新更好的版本请随时联系窝。
bookKp=克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/e88daa8d6565440cbff0a8f7c9c8fe29。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/12XSQc9EEBsfEhhQEesc6nw
bookMake=车卡指南.pdf\n请使用此链接下载https://share1.heiluo.com/share/link/0e1083cdb8144b109be07fd4ef09b082。有更新更好的版本请随时联系窝。\n此外提供网盘下载版，上面的链接无需登录即可查看。\n链接：https://pan.baidu.com/s/1HHo1B1F9kMRIFDB8J9Ulww 提取码：ql5h
#随机骰点回复，使用\n换行，使用|分割
CRITICAL_SUCCESS=啊~是大成功呢！\n如果您是玩家的话还请不要放松哦~\n不过如果您是KP的话……这些都是我可爱的玩家们，可否通融一下呢？|大成功呢~\n这一定是各位努力的成果，奈梅斯也为你们感到高兴哦|哇，是大成功！\n不过~时来运转是好事，但千万不要骄傲过头哦？奈梅斯还期待你们有个好结局呢~|啊，大成功吗？\n谢谢我？这其实都是各位努力的结果呀，奈梅斯没做什么的|大成功！太棒……\n……抱歉我失态了，但各位小小的庆祝一下应该是可以的吧？|是大成功呢\n如果想要有更好的结果，请安静下来给KP多一点思考的时间哦
EXTREME_SUCCESS=极难成功！\n如果是战斗轮的话可别忘了贯穿伤害哦？\n是KP投出来的？那还是请您姑且忘记那回事吧~|极难成功吗？\n是个好兆头呢|极难成功！愿好运永伴您左右|是极难成功!\n您下次一定会大成功的！|极难成功！\n请好好利用它，幸运要配上努力才会更加闪耀哦
HARD_SUCCESS=困难成功！\n这已经是很好的结果了，请开心起来吧|困难成功!\n请不要大意的继续前进吧，奈梅斯会尽力保护大家的|困难成功！\n您有好的点子吗？请开始吧，无论什么结果奈梅斯都会为您鼓掌的|困难成功了！\n这是好运的起始哦~|困难成功呢\n哎？就算您夸我我也……好吧我接下来会努力的！
SUCCESS=成功了呢\n不要大意的继续前进吧|成功\n我会再接再厉的|成功了吗？\n不要太过兴奋啊，我们得静下心来才好继续一起往前走呢|成功了\n这是您努力的回报呢|成功了！\n您之前的辛苦没有白费，我会一直尽力帮您的|成功了！\n不要看奈梅斯啦，这是您的功劳哦|成功！\n看来我进入状态了|成功！\n请继续相信我吧！|成功！\n虽然奈梅斯并不强，但稍微依靠我一下也是可以的哦
FAILURE=啊！失败……\n真是非常抱歉!|失败了吗……\n不要放弃，我会努力让下一次成功的！|失败了呀……\n对不起这是我的错，我会想想办法的|失败了？！\n怎么可能……|失败\n但至少不是大失败，松一口气想办法补救吧|失败了……\n也许值得孤注一掷？|失败\n但请不要灰心呢~（摸摸头）|失败了\n但这是我们所有人的游戏，大家一起想办法挽回局面吧！
FUMBLE=大失败……\n哦天……我今天是怎么了|大失败？！\nKP你一定是看错了，请让我重骰一次！|大失败了吗……\n但这可打不倒我们！|大失败？！\nKP大大，这些都是我可爱的玩家们，能否想办法救他们一下？（鞠躬）|大失败……\n我不喜欢这样……|大失败……\n我保证，这是最后一次了！请相信我！|大失败……\n……这都是奈梅斯的错，非常对不起！
######################################报错列表######################################
#多次投掷
manyRollsFormat=抱歉您的格式输入有误哦QwQ，请按照".ral 值 次数"的格式输入;
diceTimesTooBig=太……太多了！我有些忙不过来，可以少一些吗……
#人物卡
setPropFormat=那个……您输入的值似乎有问题哦？奈梅斯很难办的QwQ\n应该类似.st角色名-力量50体质60
setHelp=请使用.help make命令查看.st命令的具体用法哦
NotFoundSkill=抱歉窝找不到您的技能了，您真的有用.st设置人物卡嘛？
#dnd
dndInitIsEmtpy=您现在的先攻列表就是空的哦~
#对抗
needKpGroup=好的我这就帮您转……您的KP群呢？哎哎哎？！
#非私聊命令
can'tInPrivate=这个命令不能在私聊中用哦？和群里的小伙伴一起玩吧（摸头）
#日志
alreadyOpen=已经打开了呢，奈梅斯同一时间只能记录一份日志哦~
alreadyClose=？这个日志已经关上了呀~
notFoundLog=没找到这个日志呢QAQ
readLock=正在有人取日志呢，请您耐心排队等待~一个一个来~
deleteOpenLog=窝刚记到一半没法删掉它哦~您尝试关闭后再删吧~
#骰点格式
sanCheck=您输入的这个格式好奇怪哦……理智检定不应该是1/1d3|1d6/1d3|0/1这样的格式咩？
#小队
teamIsEmpty=您的小队当前没有成员哦~我们一起找点朋友来玩吧！
teamMemberEnIsEmpty=的技能似乎还没有成功过哦~请下次再努力吧XD
######################################回复信息######################################
#人物卡
setPropSuccess=奈梅斯好好地记住您了！也请您好好表现哦！
#Dnd
clrDndInit=帮您清空掉先攻列表啦~
#对抗
antagonizeOver=对抗结束啦~多少松了一口气呢
antagonizeFirstSuccess=先手胜利！
antagonizeSecondSuccess=后手胜利啦！
antagonizeAllFailed=全……全都失败？！
antagonizeDraw=平手？！这是多少概率呀！1……2……3……呜哇！
#疯狂
symptom=\n陷入疯狂了吗？但只要还活着希望就还在
#幕间成长
enSuccess=\n成功啦！您又变强啦！
enFailed=\n非常可惜，但您现在已经够强得了，也不必遗憾哦
#暗骰
hiddenDice=kp在暗骰~请kp大大好心一些！（鞠躬）
#日志
appendLog=似乎在本群中记录过呢，窝已经重新打开它啦~您开始吧，新的记录窝会追加在后面的
createLog=是吧？窝开始动笔记啦!
#理智检定
sanCheckFumble=\n大失败？！在这种时候？！神啊救救他吧QAQ
sanCheckCriticalSuccess=\n还好还好~
sanCheckSuccess=\n不幸中的万幸嘛~
sanCheckFailure=\n愿……愿您好运？
```

其中必填信息，尤其是要修改`hostIp`这一段。既然你使用了`linux`的话，你的服务一定在`docker`内，因此`hostIp`无法使用`hostIp=localhost`其余项目除了`masterMail`都无需修改

```text
######################################必填信息######################################
###############系统(务必修改hostIp、javaPort为本机IP和服务执行端口)####################
#主机的外网IP，服务器的话很好找，个人电脑的话请百度如何找
hostIp=62.234.90.130
#httpApi的默认端口就是9999，没有特殊必要无需更改
javaPort=50070
serverPort=9999
######################################邮箱信息######################################
##不填会使用缇娜的邮箱发送，填错会导致发不出去日志邮件###
mailUserName=2730902267@qq.com
mailPassword=kktjwuakdafbdcej
#这里填写你主人的邮箱，如果服务因为自身停止会有报警邮件
masterMail=450609203@qq.com
```

Linux搭建骰娘的人，大部分都可以用`ifconfig`命令或从云服务商处查到自己的IP地址。

> 注意:这里千万不能用`127.0.0.1`或`localhost`，docker不认！

## 启动

`cd`到`SinaNya/bin`下

`sh ./deamons.sh start`即可启动

**注意必须在bin目录下，程序中有取相对路径的地方！！！**

## 如何重启、更新

同样cd到`SinaNya/bin`下

`sh ./deamons.sh restart`即可重启 `sh ./deamons.sh stop`即可停止

下载新版的`SinaNya.tar.gz`，按照刚才提到的上述方式重新布置即可（通常配置文件可以沿用）

无需重启酷Q

## 如果我遇到问题了怎么办

`SinaNya/logs`目录下的所有文件反馈到交流群`162279609`即可


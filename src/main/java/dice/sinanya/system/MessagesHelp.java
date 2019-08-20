package dice.sinanya.system;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 帮助的静态信息
 */
public interface MessagesHelp {
    String SEQ = "\n-----------------------------------\n";
    StringBuilder NORMAL_HELP = new StringBuilder()
            .append(".r\n")
            .append(".r\t.rd\t.r3d6\t.rd6\t.r3d6+5*3d6-12/6\t.r4#3d6\t.r4d+8d")
            .append("普通骰掷，支持数学计算，不支持判定")
            .append(SEQ)

            .append(".set\n")
            .append(".set 20")
            .append("更改骰掷上限，但只作用于.r和.rd")
            .append(SEQ)

            .append(".ra\n")
            .append(".ra 50\t.ra 50+10\t.ra 力量\t.ra 侦查\t.ra 力量+10\t.ra 力量*2\n")
            .append("房规判定，支持数学计算，支持技能和数值判定")
            .append(SEQ)

            .append(".rc\n")
            .append(".rc 50\t.rc 50+10\t.rc 力量\t.rc 侦查\t.rc 力量+10\t.rc 力量*2\n")
            .append("规则书判定，支持数学计算，支持技能和数值判定")
            .append(SEQ)

            .append(".rb\t")
            .append(".rb 50\t.rb 50+10\t.rb 力量\t.rb 力量+10\t.rb2 力量\n")
            .append("奖励骰判定，紧跟在.rb后面的数字代表奖励骰的个数。空格后的数字或文字将被视为技能进行判定。支持数学计算")
            .append(SEQ)

            .append(".rp\n")
            .append(".rp 50\t.rp 50+10\t.rp 力量\t.rp 力量+10\t.rp2 力量\n")
            .append("惩罚骰判定，紧跟在.rb后面的数字代表奖励骰的个数。空格后的数字或文字将被视为技能进行判定。支持数学计算")
            .append("此外rp命令可以接#次数，譬如手枪连射时可以使用.rp3#2 60，意思是2个惩罚骰，连续骰3次，60成功率判定")
            .append(SEQ)

            .append(".sc\n")
            .append(".sc 1/1d6\t.sc 1/1d6 50\n")
            .append("理智检定，可以直接输入数值进行判定，也可以不输入数值调用当前人物卡进行判定。需要注意的是当调用当前人物卡时，结果会自动更新到人物卡中。")
            .append(SEQ)

            .append(".hiy\n")
            .append("查看使用本骰子以来的所有历史信息")
            .append(SEQ)

            .append(".en\n")
            .append(".en 侦查\n")
            .append("技能成长，必须输入技能名，不可以是技能值。结果会自动更新到当前人物卡。")
            .append(SEQ)

            .append(".rav\n")
            .append(".rav 50\t.rav 50+10\t.rav 力量\t.rav 力量+10\t.rav 力量*3\n")
            .append("对抗骰掷，有2种使用方法:\n")
            .append("1.在群中使用，第一次使用被称为先手，第二次使用会成为后手。骰娘会自动给出对抗结论，采用对抗等级->投出点数->技能等级的顺序进行比较\n")
            .append("2.当您已经在群中使用.kp进行kp身份设定后。可以私聊骰子.rav命令进行骰掷，视为在群中骰掷。骰掷技能和结果不会暴露给PL")
            .append(SEQ)

            .append(".rcv\n")
            .append(".rcv 50\t.rcv 50+10\t.rcv 力量\t.rcv 力量+10\t.rcv 力量*3\n")
            .append("对抗骰掷，与rav相同，不过使用的是规则书判定");

    StringBuilder MAKE_HELP = new StringBuilder()
            .append(".coc\n")
            .append(".coc\t.coc7\t.coc7 5\t.coc 5\n")
            .append("生成7版简易人物卡")
            .append(SEQ)

            .append(".coc6\n")
            .append(".coc6\t.coc6 5\n")
            .append("生成6版简易人物卡")
            .append(SEQ)

            .append(".coc7d\n")
            .append("生成7版详细人物卡")
            .append(SEQ)

            .append(".coc6d\n")
            .append("生成6版详细人物卡")
            .append(SEQ)

            .append(".gas\n")
            .append("生成基于6版coc的煤气灯特质")
            .append(SEQ)

            .append(".tz\n")
            .append("生成非官方的玩家定义特质(不推荐)")
            .append(SEQ)

            .append(".getbook card\n")
            .append("获取最新版人物卡，非常好用！")
            .append(SEQ)

            .append(".getbook make\n")
            .append("获取车卡指南，新手花点时间准能看懂，能帮kp省很多事")
            .append(SEQ)

            .append(".st\n")
            .append(".st缇娜-力量30\t.st安娜-智力80\n")
            .append("录入多人物卡档位，本骰子不支持普通人物卡录入，请务必录入多人物卡，注意是减号分割。此功能与本骰子多项其余功能联动，忘记录入的话可能会影响其它功能的效果。")
            .append(SEQ)

            .append(".st list\n")
            .append("查看当前已录入哪些人物卡")
            .append(SEQ)

            .append(".st人物\n")
            .append(".st缇娜\t.st安娜\n")
            .append("选择人物卡，当不带有减号分割时将会视为选择你的人物卡。每个人同一时间仅能激活一张人物卡")
            .append(SEQ)

            .append(".st rm\n")
            .append(".st rm 缇娜\t.st rm 安娜\n")
            .append("移除某一张人物卡，无法移除已选择的人物卡")
            .append(SEQ)

            .append(".st show\n")
            .append("查看当前选定的人物卡的属性");

    StringBuilder GROUP_HELP = new StringBuilder()
            .append(".kp\n")
            .append("将自己设为本群内的kp，同一时间只能设置一个kp。此命令设置后，下方的对抗、查看队伍中角色技能等功能才可以正常运作")
            .append(SEQ)

            .append(".team set\n")
            .append(".team set @缇娜\t.team set @安娜 @茶茶不想再摸鱼\n")
            .append("将对方加入队伍，加入后可以方便的对团队血量、理智值进行管理并查看状态，需要at对方（可以at多个）。此团队是以群为最小单位的。")
            .append(SEQ)

            .append(".team\n")
            .append("查看团队状态")
            .append(SEQ)

            .append(".team desc\n")
            .append("查看团队所有角色的技能情况，需要使用.kp设定本群kp后才可以使用,结果会私聊发送给kp")
            .append(SEQ)

            .append(".team en\n")
            .append("查看团队所有角色技能成功情况，可能回显中会带一些奇怪的东西，但最终还是方便大家了")
            .append(SEQ)

            .append(".team hp\n")
            .append(".team hp @缇娜 5\t.team hp @安娜 @茶茶不想再摸鱼 +5\n")
            .append("可以对成员的血量进行调整，可以at多个人。默认为降低血量，使用+号为恢复血量，会自动报告对方的重伤和死亡情况。这个操作会更新对方的人物卡数据。")
            .append(SEQ)

            .append(".team san\r")
            .append(".team san @缇娜 5\t.team san @缇娜 @安娜 3D6\t.team san @安娜 @茶茶不想再摸鱼 +3D6\t.team san @安娜 @谢伟 1d3/1d6\n")
            .append("可以对成员的理智值进行调整，可以at多个人。默认为降低理智值，使用+号为恢复理智值，会自动报告对方的疯狂情况," +
                    "这个操作会更新对方的人物卡数据。\n数值可以是类似于3D6的多重投掷，更可以是sanCheck表达式，系统会自动计算对方的人物卡属性，强制对方进行SanCheck\n" +
                    "“所有人，san check”这种话已经过时了kp们，现在一条命令就让他们知道什么叫身不由己！")
            .append(SEQ)

            .append(".team rm\n")
            .append(".team rm @缇娜\n")
            .append("将某人移出队伍，在队伍期间改变的人物卡属性不会还原")
            .append(SEQ)

            .append(".team clr\n")
            .append("清空队伍")
            .append(SEQ)

            .append(".log on\n")
            .append(".log on test\t.log on 卡森德拉\n")
            .append("开始日志记录")
            .append(SEQ)

            .append(".log off\n")
            .append(".log off test\t.log off 卡森德拉\n")
            .append("终止日志记录，日志名必须和开始时一致，但开始和关闭不需要是同一个人")
            .append(SEQ)

            .append(".log list\n")
            .append(".log list\n")
            .append("查看当前群组的日志列表")
            .append(SEQ)

            .append(".log rm\n")
            .append(".log rm test\t.log rm 卡森德拉\n")
            .append("删除某个日志，基本很少会用到，删除后就再也找不回来了")
            .append(SEQ)

            .append(".clue\n")
            .append(".clue 线索1\n")
            .append("录入某条线索")
            .append(SEQ)

            .append(".clue show\n")
            .append("显示所有线索")
            .append(SEQ)

            .append(".clue rm\n")
            .append(".clue rm 2019-06-24 18:21:33\n")
            .append("删除某条线索\n")
            .append(SEQ)

            .append(".clue clr\n")
            .append("清空所有线索")
            .append(SEQ)

            .append(".npc\n")
            .append("生成一个NPC，部分发送到群里给PC，部分私聊KP，注意这可不是车卡用的！")
            .append(SEQ)

            .append(".rav\n")
            .append(".rav 50\t.rav 50+10\t.rav 力量\t.rav 力量+10\t.rav 力量*3\n")
            .append("对抗骰掷，有2种使用方法:\n")
            .append("1.在群中使用，第一次使用被称为先手，第二次使用会成为后手。骰娘会自动给出对抗结论，采用对抗等级->投出点数->技能等级的顺序进行比较\n")
            .append("2.当您已经在群中使用.kp进行kp身份设定后。可以私聊骰子.rav命令进行骰掷，视为在群中骰掷。骰掷技能和结果不会暴露给PL")
            .append(SEQ)

            .append(".rcv\n")
            .append(".rcv 50\t.rcv 50+10\t.rcv 力量\t.rcv 力量+10\t.rcv 力量*3\n")
            .append("对抗骰掷，与rav相同，不过使用的是规则书判定");

    StringBuilder BOOK_HELP = new StringBuilder()
            .append(".getbook card\n")
            .append("获取奈梅斯定制版人物卡，感谢贝尔sama喵巨佬！！卡中第二页有st多人物卡导入字符串，填好后可以直接复制~")
            .append(SEQ)

            .append(".getbook make\n")
            .append("获取车卡指南，新手花点时间准能看懂，能帮kp省很多事")
            .append(SEQ)

            .append(".getbook kp\n")
            .append("读规则书是好文明！")
            .append(SEQ)

            .append(".getbook rp\n")
            .append("角色扮演365问，不过只有问没有答，估计还是要读的人自己思考吧")

            .append(".rule\n")
            .append(".rule 力量\t.rule 领航\n")
            .append("规则查询");


    StringBuilder DND_HELP = new StringBuilder()
            .append(".dnd\n")
            .append(".dnd\t.dnd 10\n")
            .append("生成dnd英雄人物卡")
            .append(SEQ)

            .append(".ri\n")
            .append(".ri\t.ri 5\t.ri+5\t.ri-5\n")
            .append("先攻骰掷，目前只支持加减，有其它需要再联系我")
            .append(SEQ)

            .append(".init\n")
            .append("先攻骰掷列表")
            .append(SEQ)

            .append(".init clr\n")
            .append("清空先攻骰掷列表");
}

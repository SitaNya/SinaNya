package dice.sinanya.system;

public interface MessagesHelp {
    StringBuilder normalHelp = new StringBuilder()
            .append(".r\t")
            .append(".r|.r3d6|.rd6|.r3d6+5*3d6-12/6\n")
            .append("普通骰掷，支持数学计算，不支持判定")
            .append("\n\n")

            .append(".ra\t")
            .append(".ra 50|.ra 50+10|.ra 力量|.ra 侦查|.ra 力量+10|.ra 力量*2\n")
            .append("房规判定，支持数学计算，支持技能和数值判定")
            .append("\n\n")

            .append(".rc\t")
            .append(".rc 50|.rc 50+10|.rc 力量|.rc 侦查|.rc 力量+10|.rc 力量*2\n")
            .append("规则书判定，支持数学计算，支持技能和数值判定")
            .append("\n\n")

            .append(".rb\t")
            .append(".rb 50|.rb 50+10|.rb 力量|.rb 力量+10|.rb2 力量\n")
            .append("奖励骰判定，紧跟在.rb后面的数字代表奖励骰的个数。空格后的数字或文字将被视为技能进行判定。支持数学计算")
            .append("\n\n")

            .append(".rp\t")
            .append(".rp 50|.rp 50+10|.rp 力量|.rp 力量+10|.rp2 力量\n")
            .append("惩罚骰判定，紧跟在.rb后面的数字代表奖励骰的个数。空格后的数字或文字将被视为技能进行判定。支持数学计算")
            .append("\n\n")

            .append(".sc\t")
            .append(".sc 1/1d6|.sc 1/1d6 50\n")
            .append("理智检定，可以直接输入数值进行判定，也可以不输入数值调用当前人物卡进行判定。需要注意的是当调用当前人物卡时，结果会自动更新到人物卡中。")
            .append("\n\n")

            .append(".en\t")
            .append(".en 侦查\n")
            .append("技能成长，必须输入技能名，不可以是技能值。结果会自动更新到当前人物卡。");

    StringBuilder makeHelp = new StringBuilder()
            .append(".coc\r")
            .append(".coc|.coc7|.coc7 5|.coc 5\n")
            .append("生成7版简易人物卡")
            .append("\n\n")

            .append(".coc6\r")
            .append(".coc6|.coc6 5\n")
            .append("生成6版简易人物卡")
            .append("\n\n")

            .append(".coc7d\n")
            .append("生成7版详细人物卡")
            .append("\n\n")

            .append(".coc6d\n")
            .append("生成6版详细人物卡")
            .append("\n\n")

            .append(".getbook card\n")
            .append("获取最新版人物卡，非常好用！")
            .append("\n\n")

            .append(".getbook make\n")
            .append("获取车卡指南，新手花点时间准能看懂，能帮kp省很多事")
            .append("\n\n")

            .append(".st\r")
            .append(".st缇娜-力量30|.st安娜-智力80\n")
            .append("录入多人物卡档位，本骰子不支持普通人物卡录入，请务必录入多人物卡，注意是减号分割。此功能与本骰子多项其余功能联动，忘记录入的话可能会影响其它功能的效果。")
            .append("\n\n")

            .append(".st list\n")
            .append("查看当前已录入哪些人物卡")
            .append("\n\n")

            .append(".st人物\r")
            .append(".st缇娜|.st安娜\n")
            .append("选择人物卡，当不带有减号分割时将会视为选择你的人物卡。每个人同一时间仅能激活一张人物卡")
            .append("\n\n")

            .append(".st move\r")
            .append(".st move 缇娜|.st move 安娜\n")
            .append("移除某一张人物卡，无法移除已选择的人物卡")
            .append("\n\n")

            .append(".st show\n")
            .append("查看当前选定的人物卡的属性");

    StringBuilder groupHelp = new StringBuilder()
            .append(".team set\r")
            .append(".team set @缇娜|.team set @安娜 @茶茶不想再摸鱼\n")
            .append("将对方加入队伍，加入后可以方便的对团队血量、理智值进行管理并查看状态，需要at对方（可以at多个）。此团队是以群为最小单位的。")
            .append("\n\n")

            .append(".team\n")
            .append("查看团队状态")
            .append("\n\n")

            .append(".team hp\r")
            .append(".team hp @缇娜 5|.team hp @安娜 @茶茶不想再摸鱼 +5\n")
            .append("可以对成员的血量进行调整，可以at多个人。默认为降低血量，使用+号为恢复血量，会自动报告对方的重伤和死亡情况。这个操作会更新对方的人物卡数据。")
            .append("\n\n")

            .append(".team san\r")
            .append(".team san @缇娜 5|.team san @缇娜 @安娜 3D6|.team san @安娜 @茶茶不想再摸鱼 +3D6|.team san @安娜 @谢伟 1d3/1d6\n")
            .append("可以对成员的理智值进行调整，可以at多个人。默认为降低理智值，使用+号为恢复理智值，会自动报告对方的疯狂情况," +
                    "这个操作会更新对方的人物卡数据。\n数值可以是类似于3D6的多重投掷，更可以是sanCheck表达式，系统会自动计算对方的人物卡属性，强制对方进行SanCheck\n" +
                    "“所有人，san check”这种话已经过时了kp们，现在一条命令就让他们知道什么叫身不由己！")
            .append("\n\n")

            .append(".team move\r")
            .append(".team move @缇娜\n")
            .append("将某人移出队伍，在队伍期间改变的人物卡属性不会还原")
            .append("\n\n")

            .append(".team clr\n")
            .append("清空队伍")
            .append("\n\n")

            .append(".npc\n")
            .append("生成一个NPC，部分发送到群里给PC，部分私聊KP，注意这可不是车卡用的！");

    StringBuilder bookHelp = new StringBuilder()
            .append(".getbook card\n")
            .append("获取最新版人物卡，非常好用！")
            .append("\n\n")

            .append(".getbook make\n")
            .append("获取车卡指南，新手花点时间准能看懂，能帮kp省很多事")
            .append("\n\n")

            .append(".getbook kp\n")
            .append("读规则书是好文明！")
            .append("\n\n")

            .append(".getbook rp\n")
            .append("角色扮演365问，不过只有问没有答，估计还是要读的人自己思考吧");
}

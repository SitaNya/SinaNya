package dice.sinanya.system;

import java.util.HashMap;

/**
 * @author zhangxiaozhou
 */
public interface MessagesError {
    HashMap<String, String> MESSAGES_ERROR = new HashMap<String, String>(200) {
        {
            put("strNoExtractionTime", "抱歉，您今天的抽取次数已经用完。请明天再试。");
            put("strGetBookHumanMailTitle", "COC七版规则空白卡19.3.1.xlsx");
            put("strGetBookHumanMailContent", "请使用此链接下载https://share1.heiluo.com/share/link/f87376536f8f4d0baba2178cb621aebe。有更新更好的版本请随时联系窝。");
            put("strGetBookHumanMessage", "COC七版规则空白卡19.3.1.xlsx已经发到你的邮箱啦~~\n记得回复一下邮件哟，不然以后窝发的邮件会被爸爸扔进垃圾桶的。");
            put("pathBookOfHuman", "C:/Files/Docment/COC七版规则空白卡19.3.1.xlsx");
            put("strGetBookRPMailTitle", "角色扮演三百六十五问.zip");
            put("strGetBookRPMailContent", "请使用此链接下载https://share1.heiluo.com/share/link/8b93f1f15a974d4a9fda1890863a0af1。有更新更好的版本请随时联系窝。");
            put("strGetBookRPMessage", "角色扮演三百六十五问.zip已经发到你的邮箱啦~~\n记得回复一下邮件哟，不然以后窝发的邮件会被爸爸扔进垃圾桶的。");
            put("pathBookOfRP", "C:/Files/Docment/角色扮演三百六十五问.zip");
            put("strGetBookKPMailTitle", "克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf");
            put("strGetBookKPMailContent", "请使用此链接下载https://share1.heiluo.com/share/link/e88daa8d6565440cbff0a8f7c9c8fe29。有更新更好的版本请随时联系窝。");
            put("strGetBookKPMessage", "克苏鲁的呼唤第七版守秘人规则书 Version1901.pdf已经发到你的邮箱啦~~\n记得回复一下邮件哟，不然以后窝发的邮件会被爸爸扔进垃圾桶的。");
            put("pathBookOfKP", "C:/Files/Docment/规则书.pdf");
            put("strGetBookMakeCardMailTitle", "车卡指南.pdf");
            put("strGetBookMakeCardMailContent", "请使用此链接下载https://share1.heiluo.com/share/link/0e1083cdb8144b109be07fd4ef09b082。有更新更好的版本请随时联系窝。");
            put("strGetBookMakeCardMessage", "车卡指南.pdf已经发到你的邮箱啦~~\n记得回复一下邮件哟，不然以后窝发的邮件会被爸爸扔进垃圾桶的。");
            put("strLogNotSupportPrivateChat", "日志记录指令仅在群里起作用，私聊中无效");

            put("strNameNumTooBig", "太……太多了！我有些忙不过来，可以少一些吗……");
            put("strNameNumCannotBeZero", "生成数量不能为零!请输入1-10之间的数字!");
            put("strSetInvalid", "无效的默认骰!请输入1-99999之间的数字!");
            put("strSetTooBig", "默认骰过大!请输入1-99999之间的数字!");
            put("strSetCannotBeZero", "默认骰不能为零!请输入1-99999之间的数字!");
            put("strCharacterCannotBeZero", "人物作成次数不能为零!请输入1-10之间的数字!");

            put("strCharacterTooBig", "人物作成次数过多!请输入1-10之间的数字!");
            put("strCharacterInvalid", "人物作成次数无效!请输入1-10之间的数字!");
            put("strSCInvalid", "SC表达式输入不正确,格式为成功扣San/失败扣San,如1/1d6!");
            put("strSanInvalid", "San值输入不正确,请输入1-99范围内的整数!");
            put("strEnValInvalid", "技能值或属性输入不正确,请输入1-99范围内的整数!");
            put("strNameDelErr", "没有设置名称,无法删除!");
            put("strValueErr", "那个……您输入的值似乎有问题哦？奈梅斯很难办的QwQ");
            put("strInputErr", "命令或掷骰表达式输入错误!");
            put("strUnknownErr", "发生了未知错误!");
            put("strUnableToGetErrorMsg", "无法获取错误信息!");
            put("strDiceTooBigErr", "奈梅斯来帮……呜哇好多啊啊啊啊啊！");
            put("strRequestRetCodeErr", "访问服务器时出现错误! HTTP状态码: {0}");
            put("strRequestNoResponse", "服务器未返回任何信息");
            put("strTypeTooBigErr", "哇!让窝数数骰子有多少面先~1...2...呜哇不想数啦！");
            put("strZeroTypeErr", "这是...!!时空裂(骰娘被骰子产生的时空裂缝卷走了)");
            put("strAddDiceValErr", "你这样要让窝扔骰子扔到什么时候嘛~(请输入正确的加骰参数:5-10之内的整数)");
            put("strZeroDiceErr", "咦?窝的骰子呢?");
            put("strRollTimeExceeded", "掷骰轮数超过了最大轮数限制!");
            put("strRollTimeErr", "异常的掷骰轮数");
            put("strWelcomeMsgClearedNotice", "已清除本群的入群欢迎词");
            put("strWelcomeMsgIsEmptyErr", "错误:没有设置入群欢迎词，清除失败");
            put("strWelcomeMsgUpdatedNotice", "啊，这样迎接客人吗？奈梅斯会记住的！");
            put("strPermissionDeniedErr", "错误:此操作需要群主或管理员权限");
            put("strNameTooLongErr", "错误:名称过长(最多为50英文字符)");
            put("strUnknownPropErr", "哎？这是什么属性……是……是我忘了吗？对不起！QAQ");
            put("strEmptyWWDiceErr", "格式错误:正确格式为.w(w)XaY!其中X≥1, 5≤Y≤10");
            put("strPropErr", "这个属性是不是输入的有点问题？奈梅斯看不懂来着");
            put("strSetPropSuccess", "奈梅斯好好地记住您了！也请您好好表现哦！");
            put("strPropCleared", "已清除所有属性");
            put("strRuleErr", "规则数据获取失败,具体信息:\n");
            put("strRulesFailedErr", "请求失败,无法连接数据库");
            put("strPropDeleted", "属性删除成功");
            put("strPropNotFound", "错误:属性不存在");
            put("strCharsetted", "啊，切换到人物:{0}吗？好的，我已经办好了！");
            put("strChanotFind", "哎？{0}这张人物卡我找不到了……奇怪……");
            put("strRuleNotFound", "未找到对应的规则信息");
            put("strProp", "{0}的{1}属性值为{2}");
            put("strStErr", "格式错误:请参考帮助文档获取.st命令的使用方法");
            put("strClueErr", "格式错误:请在.clue命令后以空格分割输入线索");
            put("strClueCleared", "已清除本群内所有线索");
            put("strClue", "当前群内记录了一下线索，请每天save或一定时间后一定记得使用.clue clr清理，否则时间长了可能造成骰子崩溃，导致线索记录丢失\n");
            put("strClueNotFound", "错误:当前未记录线索");
            put("strRulesFormatErr", "格式错误:正确格式为.rules[规则名称:]规则条目 如.rules COC7:力量");
            put("strJrrp", "{0}？奈梅斯觉得，以您的好运，至少可以得到{1}个大成功哦~");
            put("strJrrpsuc", "{0}……很遗憾，您今天可能会获得{1}个大失败，但我会站在您这边的，请不必担心");
            put("strJrrpErr", "JRRP获取失败! 错误信息: \n{0}");
            put("strGetBookCatInfo", "【喵苏鲁图书抽取 - 抽中概率】\n《基础规则》         -30%\n《猫之书(英文版)》      - 10 % \n《牧猫人手册(英文版)》 - 2 % \n《世界观(英文版)》   - 0.4%\n《血光将至log》by杏子 - 0.2%\n《猫之书(中文版)》   - 0.05% \n《牧猫人手册(中文版)》 - 0.01 % \n《世界观(中文版)》    - 0.003 % \n");
            put("strDeckNotFound", "没听说过呢……");
            put("strDeckEmpty", "疲劳警告！已经什么也不剩了！");


            put("strInitListClearedNotice", "成功清除先攻记录!");
            put("strInitListIsEmptyErr", "错误: 先攻列表为空!");
            put("strCommandNotAvailableErr", "错误: 此命令仅可在群/讨论组中使用");
            put("strSuccessfullyEnabledNotice", "啊~您需要奈梅斯了吗？我一定会好好努力的");
            put("strAlreadyEnabledErr", "嗯！我在的呢，您尽管吩咐吧");
            put("strSuccessfullyDisabledNotice", "这样啊……你找到更好的骰子了对吗？奈梅斯明白的，会好好安静下来的……");
            put("strAlreadyDisabledErr", "奈梅斯还不够安静吗？您……您需要我离开吗？");
            put("strDiceInsertValTooBig", "哎？这个……太大了！");
            put("strMsgToLong", "那个……只需要录入有改动的数据就可以哦？这样长的话奈梅斯处理起来会很费劲的");
            put("strObCommandSuccessfullyEnabledNotice", "成功在本群/讨论组中启用旁观模式!");
            put("strObCommandAlreadyEnabledErr", "错误: 在本群/讨论组旁观模式已经被启用!");
            put("strObCommandSuccessfullyDisabledNotice", "成功在本群/讨论组中禁用旁观模式!");
            put("strObCommandAlreadyDisabledErr", "错误: 在本群/讨论组旁观模式已经被禁用!");
            put("strObCommandDisabledErr", "管理员已在本群/讨论组中禁用旁观模式!");
            put("strObListClearedNotice", "成功删除所有旁观者!");
            put("strJrrpCommandSuccessfullyEnabledNotice", "成功在本群/讨论组中启用.jrrp命令!");
            put("strJrrpCommandAlreadyEnabledErr", "错误: 在本群/讨论组中.jrrp命令已经被启用!");
            put("strJrrpCommandSuccessfullyDisabledNotice", "成功在本群/讨论组中禁用.jrrp命令!");
            put("strJrrpCommandAlreadyDisabledErr", "错误: 在本群/讨论组中.jrrp命令已经被禁用!");
            put("strJrrpCommandDisabledErr", "管理员已在此群/讨论组中禁用.jrrp命令!");
            put("strHelpCommandSuccessfullyEnabledNotice", "成功在本群/讨论组中启用.help命令!");
            put("strHelpCommandAlreadyEnabledErr", "错误: 在本群/讨论组中.help命令已经被启用!");
            put("strHelpCommandSuccessfullyDisabledNotice", "成功在本群/讨论组中禁用.help命令!");
            put("strHelpCommandAlreadyDisabledErr", "错误: 在本群/讨论组中.help命令已经被禁用!");
            put("strHelpCommandDisabledErr", "管理员已在此群/讨论组中禁用.help命令!");

            put("strMESpaceErr", "你的自定义名称为空或自定义名称首位为空格,不能复读");
            put("strCPropNotFound", "{0}这个人物卡好像不存在的样子……我也很为难啊");
            put("strCnameErr", "你账号下的人物卡已经到达数量上限啦！再多我就记不住了（抱头）");
            put("strClistCleared", "你的人物卡列表已经清空了，新的旅程开始也会很愉快吧？");
            put("strCharErased", "恩，我清除掉名为{0}的人物卡了");
            put("strKPsetted", "好的，我会将这里记为您的KP群，您所有私聊我的骰点结果我都会转播到这里的，请您放心");
            put("strcharErr", "读取信息错误，发生什么了？你问我我问谁嘛");
        }
    };
}

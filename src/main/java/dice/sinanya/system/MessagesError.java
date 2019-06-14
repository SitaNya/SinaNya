package dice.sinanya.system;

/**
 * @author SitaNya
 */
public interface MessagesError {


    String strValueErr = "那个……您输入的值似乎有问题哦？奈梅斯很难办的QwQ\n应该类似.st角色名-力量50体质60";
    String strDiceTimesTooBig = "太……太多了！我有些忙不过来，可以少一些吗……";
    String strHiddenDice = "kp在暗骰~请kp大大好心一些！（鞠躬））";
    String strSetPropSuccess = "奈梅斯好好地记住您了！也请您好好表现哦！";
    String strPropErr = "这个属性是不是输入的有点问题？奈梅斯看不懂来着";
    String strNameNumCannotBeZero = "生成数量不能为零!请输入1-10之间的数字!";
    String strSetInvalid = "无效的默认骰!请输入1-99999之间的数字!";
    String strSetTooBig = "默认骰过大!请输入1-99999之间的数字!";
    String strSetCannotBeZero = "默认骰不能为零!请输入1-99999之间的数字!";
    String strCharacterCannotBeZero = "人物作成次数不能为零!请输入1-10之间的数字!";

    String strCharacterTooBig = "人物作成次数过多!请输入1-10之间的数字!";
    String strCharacterInvalid = "人物作成次数无效!请输入1-10之间的数字!";
    String strSCInvalid = "SC表达式输入不正确,格式为成功扣San/失败扣San,如1/1d6!";
    String strSanInvalid = "San值输入不正确,请输入1-99范围内的整数!";
    String strEnValInvalid = "技能值或属性输入不正确,请输入1-99范围内的整数!";
    String strNameDelErr = "没有设置名称,无法删除!";

    String strInputErr = "命令或掷骰表达式输入错误!";
    String strUnknownErr = "发生了未知错误!";
    String strUnableToGetErrorMsg = "无法获取错误信息!";
    String strDiceTooBigErr = "奈梅斯来帮……呜哇好多啊啊啊啊啊！";
    String strRequestRetCodeErr = "访问服务器时出现错误! HTTP状态码: {0}";
    String strRequestNoResponse = "服务器未返回任何信息";
    String strTypeTooBigErr = "哇!让窝数数骰子有多少面先~1...2...呜哇不想数啦！";
    String strZeroTypeErr = "这是...!!时空裂(骰娘被骰子产生的时空裂缝卷走了)";
    String strAddDiceValErr = "你这样要让窝扔骰子扔到什么时候嘛~(请输入正确的加骰参数:5-10之内的整数)";
    String strZeroDiceErr = "咦?窝的骰子呢?";
    String strRollTimeExceeded = "掷骰轮数超过了最大轮数限制!";
    String strRollTimeErr = "异常的掷骰轮数";
    String strWelcomeMsgClearedNotice = "已清除本群的入群欢迎词";
    String strWelcomeMsgIsEmptyErr = "错误:没有设置入群欢迎词，清除失败";
    String strWelcomeMsgUpdatedNotice = "啊，这样迎接客人吗？奈梅斯会记住的！";
    String strPermissionDeniedErr = "错误:此操作需要群主或管理员权限";
    String strNameTooLongErr = "错误:名称过长(最多为50英文字符)";
    String strUnknownPropErr = "哎？这是什么属性……是……是我忘了吗？对不起！QAQ";
    String strEmptyWWDiceErr = "格式错误:正确格式为.w(w)XaY!其中X≥1, 5≤Y≤10";


    String strPropCleared = "已清除所有属性";
    String strRuleErr = "规则数据获取失败,具体信息:\n";
    String strRulesFailedErr = "请求失败,无法连接数据库";
    String strPropDeleted = "属性删除成功";
    String strPropNotFound = "错误:属性不存在";
    String strCharsetted = "啊，切换到人物:{0}吗？好的，我已经办好了！";
    String strChanotFind = "哎？{0}这张人物卡我找不到了……奇怪……";
    String strRuleNotFound = "未找到对应的规则信息";
    String strProp = "{0}的{1}属性值为{2}";
    String strStErr = "格式错误:请参考帮助文档获取.st命令的使用方法";
    String strClueErr = "格式错误:请在.clue命令后以空格分割输入线索";
    String strClueCleared = "已清除本群内所有线索";
    String strClue = "当前群内记录了一下线索，请每天save或一定时间后一定记得使用.clue clr清理，否则时间长了可能造成骰子崩溃，导致线索记录丢失\n";
    String strClueNotFound = "错误:当前未记录线索";
    String strRulesFormatErr = "格式错误:正确格式为.rules[规则名称:]规则条目 如.rules COC7:力量";
    String strJrrp = "{0}？奈梅斯觉得，以您的好运，至少可以得到{1}个大成功哦~";
    String strJrrpsuc = "{0}……很遗憾，您今天可能会获得{1}个大失败，但我会站在您这边的，请不必担心";
    String strJrrpErr = "JRRP获取失败! 错误信息: \n{0}";
    String strGetBookCatInfo = "【喵苏鲁图书抽取 - 抽中概率】\n《基础规则》         -30%\n《猫之书(英文版)》      - 10 % \n《牧猫人手册(英文版)》 - 2 % \n《世界观(英文版)》   - 0.4%\n《血光将至log》by杏子 - 0.2%\n《猫之书(中文版)》   - 0.05% \n《牧猫人手册(中文版)》 - 0.01 % \n《世界观(中文版)》    - 0.003 % \n";
    String strDeckNotFound = "没听说过呢……";
    String strDeckEmpty = "疲劳警告！已经什么也不剩了！";


    String strInitListClearedNotice = "成功清除先攻记录!";
    String strInitListIsEmptyErr = "错误: 先攻列表为空!";
    String strCommandNotAvailableErr = "错误: 此命令仅可在群/讨论组中使用";

    String strDiceInsertValTooBig = "哎？这个……太大了！";
    String strMsgToLong = "那个……只需要录入有改动的数据就可以哦？这样长的话奈梅斯处理起来会很费劲的";
    String strObCommandSuccessfullyEnabledNotice = "成功在本群/讨论组中启用旁观模式!";
    String strObCommandAlreadyEnabledErr = "错误: 在本群/讨论组旁观模式已经被启用!";
    String strObCommandSuccessfullyDisabledNotice = "成功在本群/讨论组中禁用旁观模式!";
    String strObCommandAlreadyDisabledErr = "错误: 在本群/讨论组旁观模式已经被禁用!";
    String strObCommandDisabledErr = "管理员已在本群/讨论组中禁用旁观模式!";
    String strObListClearedNotice = "成功删除所有旁观者!";
    String strJrrpCommandSuccessfullyEnabledNotice = "成功在本群/讨论组中启用.jrrp命令!";
    String strJrrpCommandAlreadyEnabledErr = "错误: 在本群/讨论组中.jrrp命令已经被启用!";
    String strJrrpCommandSuccessfullyDisabledNotice = "成功在本群/讨论组中禁用.jrrp命令!";
    String strJrrpCommandAlreadyDisabledErr = "错误: 在本群/讨论组中.jrrp命令已经被禁用!";
    String strJrrpCommandDisabledErr = "管理员已在此群/讨论组中禁用.jrrp命令!";
    String strHelpCommandSuccessfullyEnabledNotice = "成功在本群/讨论组中启用.help命令!";
    String strHelpCommandAlreadyEnabledErr = "错误: 在本群/讨论组中.help命令已经被启用!";
    String strHelpCommandSuccessfullyDisabledNotice = "成功在本群/讨论组中禁用.help命令!";
    String strHelpCommandAlreadyDisabledErr = "错误: 在本群/讨论组中.help命令已经被禁用!";
    String strHelpCommandDisabledErr = "管理员已在此群/讨论组中禁用.help命令!";

    String strMESpaceErr = "你的自定义名称为空或自定义名称首位为空格,不能复读";
    String strCPropNotFound = "{0}这个人物卡好像不存在的样子……我也很为难啊";
    String strCnameErr = "你账号下的人物卡已经到达数量上限啦！再多我就记不住了（抱头）";
    String strClistCleared = "你的人物卡列表已经清空了，新的旅程开始也会很愉快吧？";
    String strCharErased = "恩，我清除掉名为{0}的人物卡了";
    String strKPsetted = "好的，我会将这里记为您的KP群，您所有私聊我的骰点结果我都会转播到这里的，请您放心";
    String strcharErr = "读取信息错误，发生什么了？你问我我问谁嘛";
}

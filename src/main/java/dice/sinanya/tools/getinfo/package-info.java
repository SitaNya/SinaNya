/**
 * @author SitaNya
 * 日期: 2019-06-18
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 获取信息方法包
 * <p>
 * 这里大多是与骰点逻辑没太大关系的信息获取包
 * 而且有大部分是用来包装数据库交互工具的
 * <p>
 * Clue 获取线索方法
 * DefaultMaxRolls 默认骰数据库交互类
 * GetMessagesProperties 设定默认回复语，并读取配置文件中的回复语
 * GetMessagesToValue 将传入的msg人物属性信息整理包装为HashMap格式
 * GetName 获取人名，不是指玩家，而是NPC名称
 * GetNickName 获取昵称
 * GetSkillValue 获取技能值
 * GetTime 当前时间的相关方法
 * History 骰点历史信息的数据库交互类
 * Kp KP主群数据库交互类
 * LogTag 日志标签交互类
 * LogText 日志信息存储数据库交互类
 * MakeSkillName 技能名对照表
 * ReplaceSkillName 将技能名规整化为系统可以识别的技能名（英文）
 * RoleChoose 当前选择角色数据库交互类
 * RoleInfo 角色信息数据库交互类
 * MakeRolesInfo 角色信息的具体包装生成处理类
 * SwitchBot 机器人开关数据库交互类
 * Team 小队信息数据库交互类
 */
package dice.sinanya.tools.getinfo;
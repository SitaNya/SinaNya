/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 所有对象的集合包，这里包装成对象后传递，可以有效降低Map套Map的现象
 * <p>
 * EntityAntagonize 对抗对象
 * EntityClue 线索集对象
 * EntityCoc6CardInfo 6版细化对象
 * EntityCoc7CardInfo coc7版数据细化对象
 * EntityHistory 历史骰点信息对象
 * EntityLevelResult 骰点成功等级对象，包含成功等级与骰点结果
 * EntityLogTag 日志标签对象，包含群号和日志名
 * EntityNickAndRandomAndSkill 包装骰点信息对象，包含骰点人昵称、骰点结果、技能值
 * EntityQqAndGroup QQ和群号的包装对象
 * EntityRewardAndPunishment 惩罚投、奖励投对象
 * EntityRoleTag 角色标签对象，这个不同于角色信息，是用来标注此qq当前激活角色的
 * EntityStrManyRolls 骰点过程包装类，包含最终的结果和骰点表达式
 * EntityTeamInfo 小队信息对象，包装了ArrayList列表形式的QQ列表与群号
 * EntityTypeMessages API信息包装对象，非常重要。各种程序里都会带着它传来传去
 * MailBean 邮箱发件对象
 */
package dice.sinanya.entity;
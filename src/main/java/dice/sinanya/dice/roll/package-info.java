/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 各种骰点判定信息，也是骰子的最核心部分
 * <p>
 * @param En 技能成功标记接口
 * @param RewardAndPunishment 奖励骰、惩罚投。这两者不再支持计算公式
 * @param RiAndInit DND先攻骰掷及列表
 * @param Roll 默认骰掷，最基础也是最繁复的一个方法，里面包含很多种逻辑
 * @param RollAndCheck ra、rc判定，ral、rcl多次骰点，rav、rcv对抗
 * @param SanCheck 理智检定
 * @param SkillUp 幕间成长
 * @param TiAndLi 临时疯狂与总结疯狂
 */
package dice.sinanya.dice.roll;
/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 所有报错的方法
 *
 * 通过throw这些方法，可以在返回回复语的同时也打印出很详细的日志
 * 回复语大多在GetMessagesSystem中定义了，也可以读取配置文件
 *
 * ManyRollsFormatException 多重骰点格式错误
 * ManyRollsTimesTooMoreException 多重骰点次数过多报错
 * NotFoundSkillException 未找到技能报错
 * NotSetKpGroupException 未设定KP管理群报错
 * PlayerSetException 玩家属性录入格式错误报错
 * SanCheckSetException SanCheck表达式格式错误报错
 * TeamIsEmptyException 小队为空报错
 */
package dice.sinanya.exceptions;
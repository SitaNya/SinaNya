/**
 * @author SitaNya
 * 日期: 2019-06-18
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 数据计算包
 * <p>
 * 这里进行了随机数生成、骰点计算、人物卡实际数据生成、SanCheck实际计算逻辑等等，很重要。
 * <p>
 * ArithHelper 对数学公式进行计算
 * Calculator 算数表达式求值
 * GetFutureToString 将多线程的Future列表转化为String字符串结果
 * GetMaxNumsResult 获取指定个数的最大骰点结果（XDXK3中的3）
 * GetNickAndRandomAndSkill 获取昵称、随机值、技能值
 * GetRollResultAndStr 根据一个类似于3D6K3的字符串计算，返回计算表达式和结果
 * MakeDndCard DND多线程车卡
 * MakeDndCardInfo DND多线程车卡中的信息生成类
 * MakeManyRollsInThread ral、rcl使用的多次骰点多线程类
 * MakeMessages 消息格式化类
 * MakeRal ral的多线程判断成功等级类
 * MakeRcl rcl的多线程判断成功等级类
 * MakeSanCheck 进行SanCheck检定
 * ManyRolls 多重骰掷
 * RandomInt 最重要的随机数生成类，骰娘的所有随机数都取决于这里
 * Sender 很重要的消息发送包装类
 * ThreadCoc6 返回一张简易coc6人物卡字符串，可以被多线程调用
 * ThreadCoc7 返回一张简易coc7人物卡字符串，可以被多线程调用
 */
package dice.sinanya.tools.makedata;
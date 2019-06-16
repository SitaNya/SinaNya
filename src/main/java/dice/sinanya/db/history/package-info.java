/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 骰点信息数据库交互包，以后可能改造为根据不同机器人分别记录，目前是所有机器人公用一份记录
 *
 * InsertHistory 骰点历史录入类，包含插入、清空、更新、删除。只会被dice.sinanya.listener.InputHistoryToDatabase类中被定时启用，而不是每次骰点都会入库
 * SelectHistory 查询骰点历史类，这里不会返回对象而是直接刷写到静态变量中，只有在静态变量中获取不到时才会使用
 */
package dice.sinanya.db.history;
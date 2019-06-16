/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 人物卡数据库交互包
 * <p>
 * InsertRoles 录入角色信息，包含当前激活角色和角色内容（分两张表存储），log内容的信息会在另一个类中初始化好，未设定的技能将置为初始值
 * SelectRoles 查询角色信息，其中当前激活角色信息和角色技能信息的结果不会反回，而是自动刷写到静态变量中
 */
package dice.sinanya.db.roles;
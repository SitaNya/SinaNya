/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 总入口方法
 *
 * 除了main函数之外，第二个你来的地方就应该是这里，这里对监听了所有机器人收到的信息，并进行了过滤分流最终调用实际的处理逻辑
 *
 * Listener 总监听入口类，这里是实际上接收到消息的第一个类
 * Flow 入口分流类，从Listener过来后到达这里，结合MessagesTag中配置的正则表达式，将被机器人捕捉的小心分流给各个逻辑模块
 * InputHistoryToDataBase 定时任务类，会根据设定的时间每隔多久自动执行一次，与消息分流关系不大但仍然很重要
 */
package dice.sinanya.listener;
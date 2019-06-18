/**
 * @author SitaNya
 * 日期: 2019-06-18
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 包说明: 整体入口包
 * <p>
 * 首先，欢迎你使用SinaNya，这是由SitaNya制作的基于Java语言的骰子
 * <p>
 * 很显然，打开程序的第一步你会来到这里（如果你看了Wiki的话），而同层的RunApplication是Main函数
 * <p>
 * 我个人建议你的浏览顺序是:
 * 1.dice.sinanya.RunApplication
 * 2.dice.sinanya.listener.Listener
 * 3.dice.sinanya.listener.Flow
 * 4.dice.sinanya.dice.*
 * 5.dice.sinanya.tools.*
 * 6.dice.sinanya.entity.*
 * 7.dice.sinanya.system.*
 * 8.dice.sinanya.exceptions.*
 * 9.dice.sinanya.db.*
 * 10.pom.xml
 * 11.assembly.xml
 * 12.log4j2.xml
 * <p>
 * 按照这个顺序，你可以更舒适的了解整个工程的运行逻辑。
 * 哪怕你是个新手也无需担心，到每个包中先看package-info.java，然后每个类中多看注释
 * <p>
 * 以下是包功能列表:
 * <p>
 * 1.db 数据库相关工程包
 * 2.dice 骰点入口在这里
 * 3.entity 所有对象的集合包，这里包装成对象后传递，可以有效降低Map套Map的现象
 * 4.exceptions 所有报错的报出方法
 * 5.listener 总入口方法
 * 6.system 静态信息与对象包
 * 7.tools 工具包类
 */
package dice.sinanya;
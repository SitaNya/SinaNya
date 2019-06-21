---
description: 基本的骰点检定方式
---

# 常用命令

.r

最基本的骰点功能，允许使用各种计算表达式，但不允许使用判定

![](../../.gitbook/assets/b7f02e7b-618d-4238-a6b3-d54703d4626f.png)

![](../../.gitbook/assets/48877a5d-d910-4f50-8b23-e7cc294ef427.png)

![](../../.gitbook/assets/7094b48c-ff6e-493b-b764-3f8e55b9d532.png)

## .ra

房规判定，允许技能值和技能名，允许简单的运算

![](../../.gitbook/assets/8811678e-703e-4413-9dd1-a081310bcb81.png)

## .rc

规则书判定，允许技能值和技能名，允许简单的运算

![](../../.gitbook/assets/3a262f59-161b-4757-8cd9-70fd30af47f3.png)

## .rb

奖励骰，紧跟的参数是奖励骰个数，空格后是技能值。当然，也可以不带技能

奖励骰计算方式为:骰1D100，再骰奖励骰个数个1D10-1。比较1D100的十位数，和1D10-1的值哪个小就作为1D100的十位数

![](../../.gitbook/assets/2da4887e-8dd7-4e3c-b531-6432aabc39fe.png)

## .rp

惩罚骰，紧跟的参数是惩罚骰个数，空格后是技能值。当然，也可以不带技能

惩罚骰计算方式为:骰1D100，再骰奖励骰个数个1D10-1。比较1D100的十位数，和1D10-1的值哪个大就作为1D100的十位数

![](../../.gitbook/assets/23cb8d1b-9819-4e01-ae6f-0d50fbcfc540.png)

## .sc

理智值检定，允许常规的理智值表达式，允许不带技能名调用人物卡信息

![](../../.gitbook/assets/eafa12c8-79d0-4ab4-b56d-aaedead52534.png)

## .ti/li

疯狂症状获取

![](../../.gitbook/assets/c2f199c9-2178-4416-9f94-999984cf0e57.png)

## .en

技能成长，由于本骰子强制使用多人物卡，因此在不设定人物卡的情况下会无法使用en命令

![](../../.gitbook/assets/cfd21e61-a61b-49f6-8308-b9ffcdece7c6.png)

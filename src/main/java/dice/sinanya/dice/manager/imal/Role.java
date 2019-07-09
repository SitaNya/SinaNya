package dice.sinanya.dice.manager.imal;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static dice.sinanya.tools.getinfo.ReplaceSkillName.replaceSkillName;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.checkRoleInfoFromChooseExistByQQ;
import static dice.sinanya.tools.getinfo.RoleInfo.getRoleInfoFromChooseByFromQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 角色接口，实现此接口则可以通过其下的方法调用或修改人物卡中的信息
 */
public interface Role extends MakeNickToSender {

    ArrayList<String> PROP_MAIN = new ArrayList<String>() {{
        add("hp");
        add("san");
        add("str");
        add("pow");
        add("con");
        add("app");
        add("edu");
        add("siz");
        add("intValue");
        add("luck");
        add("mp");
    }};

    ArrayList<String> PROP_INVESTIGATION_OF_CRIMES = new ArrayList<String>() {{
        add("libraryUse");
        add("investigationOfCrimes");
        add("listen");
        add("unlock");
        add("aWonderfulHand");
    }};

    ArrayList<String> PROP_TALK = new ArrayList<String>() {{
        add("persuade");
        add("intimidate");
        add("enchantment");
        add("talkingSkill");
        add("psychology");
    }};

    ArrayList<String> PROP_FIGHT = new ArrayList<String>() {{
        add("aFistFight");
        add("dodge");
        add("pistol");
        add("firstAid");
        add("medicalScience");
    }};

    /**
     * 格式化属性值为xxx:50
     *
     * @param entityTypeMessages 获取当前选择角色用的标准数据类
     * @param propMain           主属性列表值
     * @return 获取后的列表值
     */
    default ArrayList<String> formatProp(EntityTypeMessages entityTypeMessages, ArrayList<String> propMain) {

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> mapEntry : Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).entrySet()) {
            if (propMain.contains(mapEntry.getKey()) && mapEntry.getValue() != 0) {
                result.add(replaceSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
            }

        }
        return result;
    }

    /**
     * 格式化属性值为xxx:50，这里会过滤掉所有传入的项目
     *
     * @param entityTypeMessages 获取当前选择角色用的标准数据类
     * @param prop               需要过滤的列表值
     * @param up                 过滤后的值，true则返回50以上的项目，false则返回50以下的项目
     * @return 获取后的列表值
     */
    default ArrayList<String> formatProp(EntityTypeMessages entityTypeMessages, ArrayList<String> prop, boolean up) {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> mapEntry : Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).entrySet()) {
            if (!prop.contains(mapEntry.getKey()) && mapEntry.getValue() != 0) {
                if (up && mapEntry.getValue() >= 50) {
                    result.add(replaceSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
                } else if (!up && mapEntry.getValue() < 50 && mapEntry.getValue() > 5) {
                    result.add(replaceSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
                }

            }

        }
        return result;
    }

    /**
     * 格式化列表值，变成4列的形式方便查看
     *
     * @param stringBuilder 格式化的文字容器
     * @param propList      所有属性的列表值
     */
    default void formatResult(StringBuilder stringBuilder, ArrayList<String> propList) {
        int rowNum = 0;
        int propertiesWeight = 13;
        for (String prop : propList) {
            if (propList.indexOf(prop) == 0) {
                stringBuilder.append(prop);
                int textLen = (prop).length();
                for (int i = 0; i < propertiesWeight - textLen; i++) {
                    stringBuilder.append(" ");
                }
                continue;
            }
            if (rowNum < 3) {
                stringBuilder.append(prop);
                int textLen = (prop).length();
                for (int i = 0; i < propertiesWeight - textLen; i++) {
                    stringBuilder.append(" ");
                }
                rowNum++;
            } else {
                stringBuilder.append("\n");
                stringBuilder.append(prop);
                int textLen = (prop).length();
                for (int i = 0; i < propertiesWeight - textLen; i++) {
                    stringBuilder.append(" ");
                }
                rowNum = 0;
            }
        }
    }

    /**
     * 将某个人当前角色的属性格式化好返回
     *
     * @param entityTypeMessages 获取当前选择角色用的标准数据类
     * @param qq                 要查询哪个QQ的属性值
     * @return 格式化好的字符串
     */
    default StringBuilder showProp(EntityTypeMessages entityTypeMessages, String qq) {

        ArrayList<String> allSelect = new ArrayList<>();

        allSelect.addAll(PROP_MAIN);
        allSelect.addAll(PROP_INVESTIGATION_OF_CRIMES);
        allSelect.addAll(PROP_TALK);
        allSelect.addAll(PROP_FIGHT);

        StringBuilder stringBuilder = new StringBuilder();


        if (checkRoleInfoFromChooseExistByQQ(qq)) {
            stringBuilder
                    .append("\n")
                    .append("======================================================")
                    .append("\n")
                    .append(makeNickToSender(getRoleChooseByQQ(qq)))
                    .append("的角色: ")
                    .append("包含有以下数据\n");

            stringBuilder.append("主属性为:\n");
            ArrayList<String> propMainResult = formatProp(entityTypeMessages, PROP_MAIN);
            Collections.sort(propMainResult);
            formatResult(stringBuilder, propMainResult);

            stringBuilder.append("\n常规侦查技能:\n");
            ArrayList<String> propInvestigationOfCrimesResult = formatProp(entityTypeMessages, PROP_INVESTIGATION_OF_CRIMES);
            Collections.sort(propInvestigationOfCrimesResult);
            formatResult(stringBuilder, propInvestigationOfCrimesResult);

            stringBuilder.append("\n常规社交技能:\n");
            ArrayList<String> propTalkResult = formatProp(entityTypeMessages, PROP_TALK);
            Collections.sort(propTalkResult);
            formatResult(stringBuilder, propTalkResult);

            stringBuilder.append("\n常规战斗技能:\n");
            ArrayList<String> propFightResult = formatProp(entityTypeMessages, PROP_FIGHT);
            Collections.sort(propFightResult);
            formatResult(stringBuilder, propFightResult);

            stringBuilder.append("\n剩余技能值在50以上的技能:\n");
            ArrayList<String> propUpResult = formatProp(entityTypeMessages, allSelect, true);
            Collections.sort(propUpResult);
            formatResult(stringBuilder, propUpResult);

            stringBuilder.append("\n不足50但也不止5的技能:\n");
            ArrayList<String> propDownResult = formatProp(entityTypeMessages, allSelect, false);
            Collections.sort(propDownResult);
            formatResult(stringBuilder, propDownResult);
        } else {
            stringBuilder.append(getRoleChooseByQQ(qq)).append("似乎未设定角色");
        }
        return stringBuilder;
    }
}

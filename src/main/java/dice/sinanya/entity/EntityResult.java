package dice.sinanya.entity;

import net.sf.json.JSONObject;

/**
 * 回显传递实例，本系统所有的接口信息传递都是用此实例。包含一个返回值和一条信息{0,"成功"}，初始化时都为成功
 *
 * @author zhangxiaozhou
 */
public class EntityResult {
    /**
     * 初始化result对象用于传递消息，这是个JSON格式，即{KEY1:VALUE1,KEY2:VALUE2}它和map的不同在于，它的key可以重复，value可以为不同类型
     * status为状态码，初始化0
     * info为object，你可以理解为它可以存储一切数据类型，并被转化为一切类型且不会遇到类型转换错误。不过它在被转换前是无法直接被使用的。
     */
    private JSONObject result;
    private int status = 0;
    private Object info = "成功";


    /**
     * 我可以直接用空初始化ResultEntity变量，这时它会是个空Json
     */
    public EntityResult() {
        this.result = new JSONObject();
    }


    /**
     * @param json 传入的json类型参数，里面已经包含了status和inf
     *             我可以传入一个已经包含info和status内容的json来初始化ResultEntity变量
     */
    public EntityResult(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        this.status = jsonObject.getInt("status");
        this.info = jsonObject.getString("info");
    }


    /**
     * 我不应该用等于号为ResultEntity这个自构建变量赋值，而应该使用set和get，这被称为"反射"
     *
     * @param info   传入info
     * @param status 传入status
     */
    public void set(Object info, int status) {
        this.status = status;
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    /**
     * 我可以用一个builder方法来将ResultEntity的值转化为json。毕竟json是最常用的基本数据类型，而ResultEntity是我自己定义的类型。我使用它仅仅是为了简化代码逻辑
     *
     * @return JSON
     */
    public JSONObject builder() {
        result.put("status", status);
        result.put("info", info);
        return result;
    }

    /**
     * 在java中，当一个变量被打印时，会默认调用了它的toString方法。这里使用@Override字头重写toString方法，就可以使ResultEntity在被打印时自动格式化好并转变成字符串形式。
     *
     * @return 输出值
     */
    @Override
    public String toString() {
        result.put("status", status);
        result.put("info", info);
        return result.toString();
    }
}

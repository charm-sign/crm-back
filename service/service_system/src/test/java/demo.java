import com.alibaba.excel.EasyExcel;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: demo
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/11/7 14:22
 */
public class demo {
    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = "员工信息" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

    }

    private List<DemoData> data() {
       /* List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }*/
        return null;
    }


}

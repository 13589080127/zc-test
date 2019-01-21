package com.zcs.test.result;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对错误码进行处理
 */
@Slf4j
@Data
@Component
public class ResultUtil{
    private volatile List<Map<Integer, String>> resultCodeList = new ArrayList<>(2);
    private volatile int curIndex = 0;
    private volatile List<Map<String, Integer>> otherCodeList = new ArrayList<>(2);

    /**
     * 返回本系统错误码 如果不存在 返回系统异常错误码
     *
     * @param retCode
     * @return
     */
    public ResponseInfo returnResult(int retCode) {
        ResultInfo builder = new ResultInfo();
        String message = resultCodeList.get(curIndex).get(retCode);
        return ResultInfo.result(message == null ? "-1": String.valueOf(retCode),message == null ? "错误" : message,null);
    }

    /**
     * 根据其他系统错误码 返回本系统错误码
     *
     * @param chanId
     * @param errCode
     * @return
     */
    public ResponseInfo returnByOthCode(String chanId, String errCode) {
        return returnResult(convertCode(chanId, errCode));
    }

    /**
     * 清除缓存然后重新放入,不影响其他业务
     */
    public void cleanAndPut() {
        insertToMap();
    }

    /**
     * 转换成本系统得错误码 如果不存在转换成系统异常错误码
     *
     * @param chanId
     * @param errCode
     * @return
     */
    public int convertCode(String chanId, String errCode) {
        Integer code = otherCodeList.get(curIndex).get(chanId + "-" + errCode);
        return code == null ? -1 : code;
    }

    /**
     * 将错误码插入到缓存中
     */
    private void insertToMap() {
        int s = 0;
        for (int i = 0; i < resultCodeList.size(); i++) {
            if (i != curIndex) {
                s = i;
                break;
            }
        }
        resultCodeList.get(s).clear();
        List<Map> CscCodeMaps = new ArrayList<>();
        for (Map cscCodeMap : CscCodeMaps) {
            resultCodeList.get(s).put(1,"value");
        }
        otherCodeList.get(s).clear();
        List<Map> cscSwitchCodes = new ArrayList<>();
        for (Map cscSwitchCode : cscSwitchCodes) {
            /*otherCodeList.get(s).put(cscSwitchCode.getChanId() + "-" + cscSwitchCode.getOthCode(),
                    Integer.parseInt(cscSwitchCode.getErrCode()));*/
        }

        int tempCurIndex = curIndex;
        curIndex = s;
        resultCodeList.get(tempCurIndex).clear();
        resultCodeList.get(tempCurIndex).putAll(resultCodeList.get(s));
        otherCodeList.get(tempCurIndex).clear();
        otherCodeList.get(tempCurIndex).putAll(otherCodeList.get(s));
    }

    @PostConstruct
    public void startUp() {
        for (int i = 0; i < 2; i++) {
            resultCodeList.add(new HashMap<>());
            otherCodeList.add(new HashMap<>());
        }
        insertToMap();
        log.info("init errCode end...");
    }
}

package com.zcs.test;

import com.zcsmart.ccks.SE;
import com.zcsmart.ccks.codec.Base64;
import com.zcsmart.ccks.enums.CkeysTypeEnum;
import com.zcsmart.ccks.enums.EncTypeEnum;
import com.zcsmart.ccks.exceptions.SecurityLibExecption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 公共加密，解密，验签，签名，计算hash
 *
 * @author fjy
 * @data 2017年7月18日
 */
public class CommonDealUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonDealUtil.class);

    /**
     * 对数据进行加密
     *
     * @param body        需要加密的数据
     * @param se          需要使用的加密se
     * @param subDomainId 子域标示
     * @return 加密后的byte数据
     * @throws SecurityLibExecption
     * @throws NumberFormatException
     */
    public static byte[] encryptData(String body, SE se, String subDomainId, String ccksId) throws NumberFormatException, SecurityLibExecption {
        logger.debug("需加密的数据为：" + body + ",使用的se为：" + se.getCurrentDomainName() + ",子域为：" + subDomainId + ",ccksId：" + ccksId);
        if (body == null || se == null || subDomainId == null || ccksId == null) {
            return null;
        }
        byte[] bodyArray = body.getBytes();
        // 加密
        byte[] decDataByte = se.encData(Integer.parseInt(subDomainId), bodyArray, ccksId, CkeysTypeEnum.CKEYS80, EncTypeEnum.AES_192_CBC);
        String decDataStr = Base64.encodeBase64String(decDataByte);
        logger.debug("返回的加密报文体为 :" + decDataStr);
        return decDataByte;
    }

    /**
     * 验签
     *
     * @param se
     * @param subDomainId
     * @param ccksId
     * @param signData
     * @param oriData
     * @return
     */
    public static boolean checkSign(SE se, String subDomainId, String ccksId, String signData, String oriData) {
        logger.debug("需验签的数据为：" + oriData + ",签名后的数据为：" + signData + ",使用的se为：" + se.getCurrentDomainName() + ",子域为：" + subDomainId);
        if (signData == null || se == null || subDomainId == null || ccksId == null || oriData == null) {
            logger.debug("验签数据不正确！");
            return false;
        }
        boolean signCheckResultdevSe = se.checkSign(Integer.parseInt(subDomainId), ccksId, Base64.decodeBase64(signData), oriData.getBytes());
        if (!signCheckResultdevSe) {
            logger.debug("验证签名失败！");
            return false;
        }
        logger.debug("验签成功！");
        return true;

    }

    /**
     * 对数据进行签名
     *
     * @param signData    需要签名的数据
     * @param se          签名需要的se
     * @param subDomainId 子域标示
     * @return 签名后的数据
     * @throws SecurityLibExecption
     * @throws NumberFormatException
     */
    public static byte[] signData(String signData, SE se, String subDomainId) throws NumberFormatException, SecurityLibExecption {
        logger.debug("需签名的数据为：" + signData + ",使用的se为：" + se.getCurrentDomainName() + ",子域为：" + subDomainId);
        if (signData == null || se == null || subDomainId == null) {
            return null;
        }
        byte[] signDataArray = signData.getBytes();
        // 进行签名
        byte[] signArray = se.signData(Integer.parseInt(subDomainId), signDataArray, 4, 0);
        logger.debug("需签名的数据为：" + signData + "签名后的数据为：" + Base64.encodeBase64String(signArray));
        return signArray;
    }

    /**
     * 使用 sha 256 计算hash
     *
     * @param enBodyData 需要计算hash的数据
     * @return 计算后的hash值
     * @throws NoSuchAlgorithmException
     */
    public static String getBodyHash(String enBodyData) throws NoSuchAlgorithmException {
        // 获得类型
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

        // 计算获得hash
        byte[] hashByteBuffer = sha256.digest(enBodyData.getBytes());

        logger.debug("计算报文体得到的hash： " + Arrays.toString(hashByteBuffer));

        // 将 byte[] 转换为 string
        StringBuffer strHexString = new StringBuffer();
        // 遍历 byte buffer
        for (int i = 0; i < hashByteBuffer.length; i++) {
            String hex = Integer.toHexString(0xff & hashByteBuffer[i]);
            if (hex.length() == 1) {
                strHexString.append('0');
            }
            strHexString.append(hex);
        }
        // 得到返回結果
        String strResult = strHexString.toString();
        logger.debug("计算报文体得到的hash string： " + strResult);
        return strResult;
    }

    /**
     * 解密数据
     *
     * @param subDomainId 子域标识
     * @param encdata     加密数据
     * @param se          使用的se
     * @return 解密后的数据
     * @throws NumberFormatException
     * @throws SecurityLibExecption
     */
    public static byte[] decode(String subDomainId, String encdata, SE se) throws NumberFormatException, SecurityLibExecption {
        logger.debug("解密使用的 subDomainId：{}，需要解密的数据：{}，se域名：{}", subDomainId, encdata, se.getCurrentDomainName());
        return se.decData(Integer.parseInt(subDomainId), Base64.decodeBase64(encdata), CkeysTypeEnum.CKEYS80, EncTypeEnum.AES_192_CBC);
    }
}

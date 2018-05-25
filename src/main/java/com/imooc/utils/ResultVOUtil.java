package com.imooc.utils;

import com.imooc.vo.ResultVo;

/**
 * Created by songyouyu on 2018/5/23
 */
public class ResultVOUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("success");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}

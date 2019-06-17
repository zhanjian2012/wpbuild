package com.wp.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName IdsSplitUtil
 * @Description 分割一个或多个String类型的id，使其变成list
 * @Author yuxi
 * @Date 2019/5/28 16:51
 * @Version 1.0
 **/
public class IdsSplitUtil
{
    public static List<Long> strConvertList(String ids)
    {
        List<Long> list = Arrays.stream(ids.split(","))
                .map((item) -> Long.parseLong(item))
                .collect(Collectors.toList());
        return list;
    }
}

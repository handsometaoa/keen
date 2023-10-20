package com.simple.keen.common.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.simple.keen.common.base.query.PageQuery;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * PageHelper工具类
 *
 * @author SinceNovember
 * @date 2022/11/25
 */
public final class PageHelperUtils {

    private PageHelperUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final int DEFAULT_PAGE_NUM = 1;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final String DEFAULT_ORDER_BY = "create_time";

    private static final String ORDER_TYPE_DESC = "desc";

    private static final String FONT_ORDER_TYPE_DESC = "descending";

    private static final String FONT_ORDER_TYPE_ASC = "ascending";

    private static final String FONT_ORDER_TYPE_SUFFIX = "ending";


    /**
     * 分页包装
     *
     * @param pageQuery 页面查询
     */
    public static void startPage(PageQuery pageQuery, String... orderParams) {
        if (pageQuery.getPageSize() == 0) {
            pageQuery.setPageNum(DEFAULT_PAGE_NUM);
            pageQuery.setPageSize(DEFAULT_PAGE_SIZE);
        }
        if (orderParams.length > 0) {
            if (orderParams.length == 1) {
                pageQuery.setOrderBy(orderParams[0]);
            } else {
                pageQuery.setOrderBy(orderParams[0]);
                pageQuery.setOrderType(orderParams[1]);
            }
        }
        if (StringUtils.isBlank(pageQuery.getOrderBy()) ||
                StringUtils.isBlank(pageQuery.getOrderType()) ||
                Objects.equals(pageQuery.getOrderType(), "null")) {
            pageQuery.setOrderBy(DEFAULT_ORDER_BY);
            pageQuery.setOrderType(ORDER_TYPE_DESC);
        }
        //后置处理查询
        postProcessPageQuery(pageQuery);

        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(),
                orderParams.length == 1 ? pageQuery.getOrderBy() :
                        StringUtils.toUnderlineCase(pageQuery.getOrderBy()) + " "
                                + pageQuery.getOrderType());
    }

    private static void postProcessPageQuery(PageQuery pageQuery) {
        if (StringUtils.equalsAny(pageQuery.getOrderType(), FONT_ORDER_TYPE_DESC,
                FONT_ORDER_TYPE_ASC)) {
            pageQuery.setOrderType(
                    StringUtils.subBefore(pageQuery.getOrderType(), FONT_ORDER_TYPE_SUFFIX, false));
        }
    }

    /**
     * 将分页的dto列表转为分页vo列表
     *
     * @param sources         来源列表
     * @param convertFunction 转换函数
     * @return
     */
    public static <S, R> PageSerializable<R> convertPageDto2Vo(List<S> sources,
                                                               Function<List<S>, List<R>> convertFunction) {
        Assert.notNull(convertFunction, "convertFunction cannot null");

        if (CollectionUtils.isEmpty(sources)) {
            return new PageSerializable<>(Collections.emptyList());
        }

        PageSerializable pageSerializable = PageSerializable.of(sources);
        pageSerializable.setList(convertFunction.apply(sources));
        return (PageSerializable<R>) pageSerializable;
    }

}

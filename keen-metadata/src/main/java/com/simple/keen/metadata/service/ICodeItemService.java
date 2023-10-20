package com.simple.keen.metadata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageSerializable;
import com.simple.keen.metadata.model.entity.CodeItem;
import com.simple.keen.metadata.model.query.CodeItemQuery;
import com.simple.keen.metadata.model.vo.CodeItemVO;

import java.util.List;

public interface ICodeItemService extends IService<CodeItem> {

    /**
     * 分页获取代码子项
     *
     * @param roleQuery 代码子项查询
     * @return 分页系代码子项VO
     */
    PageSerializable<CodeItemVO> pageCodeItem(CodeItemQuery roleQuery);

    /**
     * 添加或更新代码子项
     *
     * @param roleQuery 代码子项查询
     */
    void addOrUpdateCodeItem(CodeItemQuery roleQuery);

    /**
     * 通过id获取代码子项
     *
     * @param id id
     * @return 代码子项VO
     */
    CodeItemVO getCodeItemById(Integer id);

    /**
     * 根据代码名和子项文本获取代码子项
     *
     * @param codeName 代码名
     * @param itemText 子项文本
     * @return 子项文本
     */
    CodeItemVO getCodeItemByCodeNameAndItemText(String codeName, String itemText);

    /**
     * 根据代码名和子项文本获取代码项值
     *
     * @param codeName 代码名
     * @param itemText 子项文本
     * @return 子项文本
     */
    String getCodeItemValueByCodeNameAndItemText(String codeName, String itemText);

    /**
     * 删除代码子项
     *
     * @param ids id
     */
    void deleteCodeItem(List<Integer> ids);

}

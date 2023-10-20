package com.simple.keen.metadata.mapping;

import com.simple.keen.metadata.model.dto.CodeMainDTO;
import com.simple.keen.metadata.model.entity.CodeMain;
import com.simple.keen.metadata.model.query.CodeMainQuery;
import com.simple.keen.metadata.model.vo.CodeMainVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/2/3
 */
@Mapper
public interface CodeMainMapping {

    CodeMainMapping INSTANCE = Mappers.getMapper(CodeMainMapping.class);

    List<CodeMainVO> toCodeMainVOList(List<CodeMainDTO> codeMainDTOS);

    CodeMainVO toCodeMainVO(CodeMainDTO codeMainDTO);

    CodeMainVO toCodeMainVO(CodeMain codeMain);

    CodeMainDTO toCodeMainDTO(CodeMainQuery codeMainQuery);

    CodeMain toCodeMain(CodeMainDTO codeMainDTO);

}

package com.simple.keen.metadata.model.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/2/4
 */
@Data
@ToString
public class CodeMainDTO {

    private Integer id;

    private String codeName;

    private String description;

    private Integer orderNum;

    private LocalDateTime createTime;

}

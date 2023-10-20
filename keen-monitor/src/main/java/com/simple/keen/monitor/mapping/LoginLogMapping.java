package com.simple.keen.monitor.mapping;

import com.simple.keen.monitor.model.dto.LoginLogDTO;
import com.simple.keen.monitor.model.dto.RecentLoginLogDTO;
import com.simple.keen.monitor.model.entity.LoginLog;
import com.simple.keen.monitor.model.query.LoginLogQuery;
import com.simple.keen.monitor.model.vo.LoginLogVO;
import com.simple.keen.monitor.model.vo.RecentLoginLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/2/6
 */
@Mapper
public interface LoginLogMapping {

    LoginLogMapping INSTANCE = Mappers.getMapper(LoginLogMapping.class);

    List<LoginLogVO> toLoginLogVOList(List<LoginLogDTO> loginLogDTOS);

    LoginLogVO toLoginLogVO(LoginLogDTO loginLogDTO);

    LoginLogVO toLoginLogVO(LoginLog loginLog);

    List<RecentLoginLogVO> toRecentLoginLogVOList(List<RecentLoginLogDTO> recentLoginLogDTOS);

    RecentLoginLogVO toRecentLoginLogVO(RecentLoginLogDTO recentLoginLogDTO);

    LoginLogDTO toLoginLogDTO(LoginLogQuery loginLogQuery);

    LoginLog toLoginLog(LoginLogDTO loginLogDTO);

}

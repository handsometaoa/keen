package com.simple.keen.monitor.controller;

import com.simple.keen.common.base.Response;
import com.simple.keen.monitor.model.query.OperateLogQuery;
import com.simple.keen.monitor.service.IOperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/2/6
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitor/operateLog")
public class OperateLogController {

    private final IOperateLogService operateLogService;

    @GetMapping("page")
    public Response pageOperateLog(OperateLogQuery operateLogQuery) {
        return Response.ok(operateLogService.pageOperateLog(operateLogQuery));
    }

    @GetMapping
    public Response getOperateLog(@NotNull Integer id) {
        return Response.ok(operateLogService.getOperateLogById(id));
    }

    @PostMapping
    public Response addOrUpdateOperateLog(@Validated @RequestBody OperateLogQuery operateLogQuery) {
        operateLogService.addOperateLog(operateLogQuery);
        return Response.ok();
    }

    @PostMapping("delete")
    public Response deleteOperateLog(@NotNull @RequestBody OperateLogQuery operateLogQuery) {
        operateLogService.deleteOperateLog(operateLogQuery.getIds());
        return Response.ok();
    }

}

package com.simple.keen.metadata.controller;

import com.simple.keen.common.base.Response;
import com.simple.keen.metadata.model.query.CodeItemQuery;
import com.simple.keen.metadata.service.ICodeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/2/3
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/metadata/codeItem")
public class CodeItemController {

    private final ICodeItemService codeItemService;

    @GetMapping("page")
    public Response pageCodeItem(CodeItemQuery codeItemQuery) {
        return Response.ok(codeItemService.pageCodeItem(codeItemQuery));
    }

    @GetMapping
    public Response getCodeItem(@NotNull Integer id) {
        return Response.ok(codeItemService.getCodeItemById(id));
    }

    @PostMapping
    public Response addOrUpdateCodeItem(@Validated @RequestBody CodeItemQuery codeItemQuery) {
        codeItemService.addOrUpdateCodeItem(codeItemQuery);
        return Response.ok();
    }

    @PostMapping("delete")
    public Response deleteCodeItem(@NotNull @RequestBody CodeItemQuery codeItemQuery) {
        codeItemService.deleteCodeItem(codeItemQuery.getIds());
        return Response.ok();
    }
}

package com.simple.keen.message.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.simple.keen.common.base.Response;
import com.simple.keen.message.model.query.UserNotificationMessageQuery;
import com.simple.keen.message.service.IUserNotificationMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/1/6
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/message/notification/user")
public class UserNotificationMessageController {

    private final IUserNotificationMessageService userNotificationMessageService;

    @GetMapping("page")
    public Response pageUserNotificationMessage(
            UserNotificationMessageQuery userNotificationMessageQuery) {
        return Response.ok(userNotificationMessageService.pageUserNotificationMessage(
                userNotificationMessageQuery));
    }

    @GetMapping
    public Response getUserNotificationMessage() {
        return Response.ok(userNotificationMessageService.getUserNotificationMessageById(
                StpUtil.getLoginIdAsInt()));
    }

    @PostMapping
    public Response addOrUpdateUserNotificationMessage(
            @Validated @RequestBody UserNotificationMessageQuery userNotificationMessageQuery) {
        userNotificationMessageService.addOrUpdateUserNotificationMessage(
                userNotificationMessageQuery);
        return Response.ok();
    }

    @PostMapping("delete")
    public Response deleteUserNotificationMessage(
            @NotNull @RequestBody UserNotificationMessageQuery userNotificationMessageQuery) {
        userNotificationMessageService.deleteUserNotificationMessage(
                userNotificationMessageQuery.getIds());
        return Response.ok();
    }

}

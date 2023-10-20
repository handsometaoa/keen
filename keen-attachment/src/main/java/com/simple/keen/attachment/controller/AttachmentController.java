package com.simple.keen.attachment.controller;

import com.simple.keen.attachment.model.query.AttachmentFolderAndInfoQuery;
import com.simple.keen.attachment.service.IAttachmentService;
import com.simple.keen.common.base.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/5/25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final IAttachmentService attachmentService;

    @PostMapping("upload")
    public Response uploadAttachment(@RequestParam("file") MultipartFile file, @RequestParam("folderId") Integer folderId) {
        return Response.ok(attachmentService.uploadAttachment(file, folderId));
    }

    @PostMapping("uploadImage")
    public Response uploadImage(@RequestParam("file") MultipartFile file) {
        return Response.ok(attachmentService.uploadImage(file));
    }

    @GetMapping("download")
    public void downloadAttachment(@RequestParam("attachmentId") Integer attachmentId) {
        attachmentService.downloadAttachment(attachmentId);
    }

    @PostMapping("delete")
    public Response deleteAttachment(@NotNull @RequestBody AttachmentFolderAndInfoQuery query) {
        attachmentService.deleteAttachment(query.getIds());
        return Response.ok();
    }

}

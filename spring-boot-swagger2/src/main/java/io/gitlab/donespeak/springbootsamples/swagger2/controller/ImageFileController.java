package io.gitlab.donespeak.springbootsamples.swagger2.controller;

import io.gitlab.donespeak.springbootsamples.swagger2.swagger2.SwaggerApiTags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 17:01
 */
@Slf4j
@Api(value = "文件上传", tags = {SwaggerApiTags.FILE})
@RestController
@RequestMapping("/file")
public class ImageFileController {

	@ApiOperation(value = "上传图片",notes = "图片大小不可超过3Mb")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "imageWidth", value = "图片宽度", required = true, dataType = "Integer", paramType = "form"),
		@ApiImplicitParam(name = "imageHeight", value = "图片高度", required = true, dataType = "Integer", paramType = "form")
	})
	@PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
	public void uploadImage(int imageWidth, int imageHeight, @ApiParam(value = "图片", required = true) MultipartFile file) {
		// TODO file 类别和大小的约束
		log.info("imageWidth: " + imageWidth + ", imageHeight: " + imageHeight + ", imageName: " + file.getName());
	}
}

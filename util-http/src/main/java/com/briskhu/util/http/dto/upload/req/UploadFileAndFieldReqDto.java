package com.briskhu.util.http.dto.upload.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-04-16
 **/
@Data
public class UploadFileAndFieldReqDto implements Serializable {
    private static final long serialVersionUID = 1922539722768004483L;

    /**
     * 用户账号<p/>
     * 11位手机号。
     */
    @NotBlank
    @Pattern(regexp = "\\d{11,11}")
    private String userAccount;

    /**
     * Excel文档<p/>
     */
    @NotNull
    private MultipartFile excelFile;
}
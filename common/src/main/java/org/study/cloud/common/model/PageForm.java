package org.study.cloud.common.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
@Data
@ToString(callSuper = true)
public class PageForm extends BaseForm {
    private Integer pageNum;
    private Integer pageSize;
}

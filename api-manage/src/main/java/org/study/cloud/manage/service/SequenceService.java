package org.study.cloud.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.cloud.common.entity.Sequence;
import org.study.cloud.common.model.Result;
import org.study.cloud.common.model.manage.SequenceForm;
import org.study.cloud.manage.mapper.SequenceMapper;

import java.util.List;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-06 17:19
 */
@Service
public class SequenceService {
    @Autowired
    private SequenceMapper sequenceMapper;

    public Result list(SequenceForm sequenceForm) {
        PageMethod.startPage(sequenceForm.getPageNum(), sequenceForm.getPageSize());
        List<Sequence> list = sequenceMapper.list();
        return Result.ok(new PageInfo<>(list));
    }

}

package org.study.cloud.manage.mapper;

import org.study.cloud.common.entity.Sequence;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SequenceMapper extends Mapper<Sequence> {

    List<Sequence> list();
}
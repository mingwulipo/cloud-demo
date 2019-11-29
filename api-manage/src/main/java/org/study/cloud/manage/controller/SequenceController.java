package org.study.cloud.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.cloud.common.model.Result;
import org.study.cloud.common.model.manage.SequenceForm;
import org.study.cloud.manage.service.SequenceService;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-11-06 17:18
 */
@RestController
@RequestMapping("sequence")
public class SequenceController {
    @Autowired
    private SequenceService sequenceService;

    //http://localhost:9040/sequence/list
    @GetMapping("list")
    public Result list(SequenceForm sequenceForm) {
        return sequenceService.list(sequenceForm);
    }

}

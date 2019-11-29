package org.study.cloud.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class Sequence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "seq_name")
    private String seqName;

    @Column(name = "init_value")
    private Long initValue;

    @Column(name = "increment_value")
    private Long incrementValue;

    @Column(name = "current_value")
    private Long currentValue;

    private static final long serialVersionUID = 1L;

}
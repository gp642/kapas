package com.kapas.workorder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowTask {

    private Integer task_number;

    private String task_id;

    private String task_name;

    private String description;

    private Boolean is_required;

    private List<MetaDataField> fields;

}

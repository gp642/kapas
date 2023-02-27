package com.kapas.workorder.model;

import com.kapas.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workflow {

    private Integer id;

    private String workflow_id;

    private String name;

    private Boolean is_active;

    private Integer version;

    private String description;

    private List<WorkflowTask> taskList;

    private User created_by;

    private User modified_by;

    private Timestamp creation_time;

    private Timestamp modification_time;

}

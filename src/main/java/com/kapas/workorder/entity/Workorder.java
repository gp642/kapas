package com.kapas.workorder.entity;


import com.kapas.user.entity.User;
import com.kapas.workorder.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workorder")
public class Workorder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String workorder_id;

    private Status status;

    private Boolean is_active;

    private String meta_data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workflow_id_fk", nullable = false)
    private Workflow workflow;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_to", nullable = false)
    private User assigned_to;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User created_by;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modified_by", nullable = false)
    private User modified_by;

    private Timestamp creation_time;

    private Timestamp modification_time;

}

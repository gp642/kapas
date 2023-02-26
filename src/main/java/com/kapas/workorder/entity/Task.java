package com.kapas.workorder.entity;


import com.kapas.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    private enum Status {
        NOT_STARTED("NOT STARTED"),
        IN_PROGRESS("IN PROGRESS"),
        COMPLETED("COMPLETED"),
        RE_OPENED("RE OPENED"),
        CLOSED("CLOSED");

        private String status;

        private String getStatus() {
            return this.status;
        }

        private Status(String status) {
            this.status = status;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String task_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workorder_id_fk", nullable = false)
    private Workorder workorder;

    private String title;

    private String remark;

    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_to", nullable = false)

    private User assigned_to;

    private Boolean is_active;

    private Integer task_number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User created_by;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modified_by", nullable = false)
    private User modified_by;

    private Timestamp creation_time;

    private Timestamp modification_time;

}

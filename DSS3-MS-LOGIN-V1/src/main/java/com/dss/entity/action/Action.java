package com.dss.entity.action;

import com.dss.entity.resources.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DSS_ACTION")
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    @Id
    @Column(name = "ACTION_ID")
    private String actionId;

    @Column(name = "ACTION_NAME")
    private String actionName;

    @ManyToOne
    private Resources resources;
}

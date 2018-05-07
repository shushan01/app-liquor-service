package com.app.framework.auth.model;

import com.app.framework.base.BaseObject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by yangyijun on 2018/4/13.
 */
@MappedSuperclass
public class BaseEntity extends BaseObject implements Serializable {

    private static final long serialVersionUID = -1101683279680617696L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //    @NotNull
    protected Long creator;

    //    @NotNull
    protected Long ctime;

    //    @NotNull
    protected Long modifier;

    //    @NotNull
    protected Long utime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }
}

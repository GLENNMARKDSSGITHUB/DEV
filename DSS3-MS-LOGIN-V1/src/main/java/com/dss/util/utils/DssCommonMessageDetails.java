/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.util.utils;

import java.util.List;
import java.util.Map;

/**
 * This is a DssCommonMessageDetails Class.
 */

public class DssCommonMessageDetails {
    private String content;
    private boolean success;
    private List<?> objList;
    private Map<?,?> objMap;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<?> getObjList() {
        return objList;
    }

    public void setObjList(List<?> objList) {
        this.objList = objList;
    }

    public Map<?, ?> getObjMap() {
        return objMap;
    }

    public void setObjMap(Map<?, ?> objMap) {
        this.objMap = objMap;
    }

    public DssCommonMessageDetails() {
        super();
    }
}

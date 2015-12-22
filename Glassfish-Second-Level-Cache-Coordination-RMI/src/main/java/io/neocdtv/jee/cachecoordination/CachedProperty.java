/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.cachecoordination;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author xix
 */
@Entity
@Table(name = "CACHED_PROPERTY")
public class CachedProperty implements Serializable {

    @Id
    @Column
    private String name;

    @Version
    @Column
    private long version;

    @Column(name = "SOME_VALUE")
    private String someValue;

    @Column(name = "ANOTHER_VALUE")
    private String anotherValue;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    public String getAnotherValue() {
        return anotherValue;
    }

    public void setAnotherValue(String anotherValue) {
        this.anotherValue = anotherValue;
    }
}

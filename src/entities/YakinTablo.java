/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruhi ÇELİK
 */
@Entity
@Table(name = "yakinTablo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "YakinTablo.findAll", query = "SELECT y FROM YakinTablo y")
    , @NamedQuery(name = "YakinTablo.findBySicil", query ="SELECT y FROM YakinTablo y WHERE y.sicil = :sicil")
    , @NamedQuery(name = "YakinTablo.findById", query = "SELECT y FROM YakinTablo y WHERE y.id = :id")
    , @NamedQuery(name = "YakinTablo.findByName", query = "SELECT y FROM YakinTablo y WHERE y.name = :name")
    , @NamedQuery(name = "YakinTablo.findBySirname", query = "SELECT y FROM YakinTablo y WHERE y.sirname = :sirname")
    , @NamedQuery(name = "YakinTablo.findByTcKimlik", query = "SELECT y FROM YakinTablo y WHERE y.tcKimlik = :tcKimlik")
    , @NamedQuery(name = "YakinTablo.findByBirthdate", query = "SELECT y FROM YakinTablo y WHERE y.birthdate = :birthdate")
    , @NamedQuery(name = "YakinTablo.findByYakinlik", query = "SELECT y FROM YakinTablo y WHERE y.yakinlik = :yakinlik")})
public class YakinTablo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "sirname")
    private String sirname;
    @Column(name = "tcKimlik")
    private String tcKimlik;
    @Column(name = "birthdate")
    private String birthdate;
    @Column(name = "yakinlik")
    private String yakinlik;
@Column(name = "sicil")
    private Integer sicil;

    public Integer getSicil() {
        return sicil;
    }

    public void setSicil(Integer sicil) {
        this.sicil = sicil;
    }

    
    public YakinTablo() {
    }

    public YakinTablo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        String oldSirname = this.sirname;
        this.sirname = sirname;
        changeSupport.firePropertyChange("sirname", oldSirname, sirname);
    }

    public String getTcKimlik() {
        return tcKimlik;
    }

    public void setTcKimlik(String tcKimlik) {
        String oldTcKimlik = this.tcKimlik;
        this.tcKimlik = tcKimlik;
        changeSupport.firePropertyChange("tcKimlik", oldTcKimlik, tcKimlik);
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        String oldBirthdate = this.birthdate;
        this.birthdate = birthdate;
        changeSupport.firePropertyChange("birthdate", oldBirthdate, birthdate);
    }

    public String getYakinlik() {
        return yakinlik;
    }

    public void setYakinlik(String yakinlik) {
        String oldYakinlik = this.yakinlik;
        this.yakinlik = yakinlik;
        changeSupport.firePropertyChange("yakinlik", oldYakinlik, yakinlik);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YakinTablo)) {
            return false;
        }
        YakinTablo other = (YakinTablo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.YakinTablo[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

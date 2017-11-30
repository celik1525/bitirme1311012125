/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    , @NamedQuery(name = "YakinTablo.findById", query = "SELECT y FROM YakinTablo y WHERE y.id = :id")
    , @NamedQuery(name = "YakinTablo.findByName", query = "SELECT y FROM YakinTablo y WHERE y.name = :name")
    , @NamedQuery(name = "YakinTablo.findBySirname", query = "SELECT y FROM YakinTablo y WHERE y.sirname = :sirname")
    , @NamedQuery(name = "YakinTablo.findByTcKimlik", query = "SELECT y FROM YakinTablo y WHERE y.tcKimlik = :tcKimlik")
    , @NamedQuery(name = "YakinTablo.findByBirthdate", query = "SELECT y FROM YakinTablo y WHERE y.birthdate = :birthdate")
    , @NamedQuery(name = "YakinTablo.findByYakinlik", query = "SELECT y FROM YakinTablo y WHERE y.yakinlik = :yakinlik")})
public class YakinTablo implements Serializable {

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

    public YakinTablo() {
    }

    public YakinTablo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    public String getTcKimlik() {
        return tcKimlik;
    }

    public void setTcKimlik(String tcKimlik) {
        this.tcKimlik = tcKimlik;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getYakinlik() {
        return yakinlik;
    }

    public void setYakinlik(String yakinlik) {
        this.yakinlik = yakinlik;
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
    
}

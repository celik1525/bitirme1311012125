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
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
    , @NamedQuery(name = "Job.findBySira", query = "SELECT j FROM Job j WHERE j.sira = :sira")
    , @NamedQuery(name = "Job.findBySicil", query = "SELECT j FROM Job j WHERE j.sicil = :sicil")
    , @NamedQuery(name = "Job.findByBegin", query = "SELECT j FROM Job j WHERE j.begin = :begin")
    , @NamedQuery(name = "Job.findByEnd", query = "SELECT j FROM Job j WHERE j.end = :end")
    , @NamedQuery(name = "Job.findBySenesi", query = "SELECT j FROM Job j WHERE j.senesi = :senesi")
    , @NamedQuery(name = "Job.findByBirim", query = "SELECT j FROM Job j WHERE j.birim = :birim")
    , @NamedQuery(name = "Job.findByGorev", query = "SELECT j FROM Job j WHERE j.gorev = :gorev")
    , @NamedQuery(name = "Job.findByGorevsekli", query = "SELECT j FROM Job j WHERE j.gorevsekli = :gorevsekli")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sira")
    private Integer sira;
    @Column(name = "sicil")
    private Integer sicil;
    @Column(name = "begin")
    private String begin;
    @Column(name = "end")
    private String end;
    @Column(name = "senesi")
    private Integer senesi;
    @Column(name = "birim")
    private String birim;
    @Column(name = "gorev")
    private String gorev;
    @Column(name = "gorevsekli")
    private String gorevsekli;

    public Job() {
    }

    public Job(Integer sira) {
        this.sira = sira;
    }

    public Integer getSira() {
        return sira;
    }

    public void setSira(Integer sira) {
        this.sira = sira;
    }

    public Integer getSicil() {
        return sicil;
    }

    public void setSicil(Integer sicil) {
        this.sicil = sicil;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getSenesi() {
        return senesi;
    }

    public void setSenesi(Integer senesi) {
        this.senesi = senesi;
    }

   

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public String getGorevsekli() {
        return gorevsekli;
    }

    public void setGorevsekli(String gorevsekli) {
        this.gorevsekli = gorevsekli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sira != null ? sira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.sira == null && other.sira != null) || (this.sira != null && !this.sira.equals(other.sira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Job[ sira=" + sira + " ]";
    }
    
}

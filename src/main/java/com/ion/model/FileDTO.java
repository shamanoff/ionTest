package com.ion.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;
import java.sql.SQLException;

@Entity
@Getter
@Setter
public class FileDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private Blob filecontent;
    private String hash;


    public void setFilecontent(byte[] bytes) throws SQLException {
        this.filecontent = new javax.sql.rowset.serial.SerialBlob(bytes);
    }
    public void setFilecontent(Blob blob) throws SQLException {
        this.filecontent = blob;
    }

}

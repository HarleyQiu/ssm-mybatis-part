package org.example.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName teacher
 */
@Data
public class Teacher implements Serializable {
    /**
     * 
     */
    private String tId;

    /**
     * 
     */
    private String tName;

    private static final long serialVersionUID = 1L;
}
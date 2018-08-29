package team.redrock.shorturl.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Url implements Serializable {

    private String longpath;
    private String hashid;
    private String shortpath;
    private Long id;
}

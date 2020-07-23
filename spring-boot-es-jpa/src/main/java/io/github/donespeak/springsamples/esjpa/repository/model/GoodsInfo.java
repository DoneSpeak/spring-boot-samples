package io.github.donespeak.springsamples.esjpa.repository.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author DoneSpeak
 */
@Data
@Document(indexName = "goods")
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = -4642147796783993827L;
    @Id
    private Long id;
    private String name;
    private String caption;
}

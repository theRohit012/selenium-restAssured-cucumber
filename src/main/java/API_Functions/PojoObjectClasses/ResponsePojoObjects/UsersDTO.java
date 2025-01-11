package API_Functions.PojoObjectClasses.ResponsePojoObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersDTO {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<String> data;

}

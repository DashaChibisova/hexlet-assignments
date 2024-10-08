package exercise.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Setter
@Getter
public class PostDTO {
    private Long id;
    private String body;
    private String title;
    private List<CommentDTO> comments;

}
// END

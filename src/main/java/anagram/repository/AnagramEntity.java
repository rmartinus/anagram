package anagram.repository;

import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "anagram")
public class AnagramEntity extends BaseEntity {
    @TextIndexed
    private String string;
    private Set<String> permutations;
}
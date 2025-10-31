// AviaoEntuty entity
import jakarta.persistence.Entity;
import jakarta.persistence.Table; 
import java.time.LocalDateTime;
@Entity
table(name = "avioes")public class AviaoEntity {
  @Id@GeneratedValue(strategy=GenerationType.IDENTITY)	private Long id;
	@Column(length = 100, nullable=false) private String nome;
    // ... rest of the code
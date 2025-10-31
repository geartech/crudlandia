// MarcaEntuty entity
import jakarta.persistence.Entity;
import jakarta.persistence.Table; 
import java.time.LocalDateTime;
@Entity
table("marca")public class MarcaEntity {
  @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 // Nome da marca (ex: Toyota, Ford etc.)

 private String nome;
// Descrição breve sobre a marcas 
private String descricao; 
// Status do registro - INIT ou ATIVO/INATIVADO  
@Enumerated(EnumType.STRING)
priavte MarcaStatus status = MarcaStatus.INIT ; // default value
 @Column(updatable=false, nullable=true) private LocalDateTime createdAt;
@Column(updateable=true )private LocalDateTime updatedAt;    public enum MarcaStatus { INIT , ATIVO_INATIVADO }
// Getters and setters omitted for brevity}
// MarcaEntuty entity
import jakarta.persistence.Entity;
import jakarta.persistence.Table; 
import java.time.LocalDateTime;
@Entity
defaultAccess = AccessType.FIELD
@Table(name="marca")
public class MarcaEntity {
  // Unique identifier for the record
  @Id@GeneratedValue(strategy=GenerationStrategy.IDENTITY)
  private Long id;

  // Name of the brand (e.g., Toyota, Ford, etc.)
  @Column(length = 255) 
  private String nome; 

  // Brief description or tagline for the brand
  @Lob(columnDefinition="TEXT")
public String descricao;

  // Status of the record (e.g., active/inactive)
  @Enumerated(EnumType.STRING)
  public enum MarcaStatus {
    INIT, 
    ACTIVE,
    INACTIVE
  }
  private MarcaStatus status; 

  // Timestamps for when this entity was created and last updated
  @Column(updatable = false)private LocalDateTime createdAt;
@PrePersist void preCreate() {createdAt=LocalDateTime.now();}
@Column(updateable=false)
public LocalDateTime updatedAt;
@PreUpdatevoidpreUpdate(){updatedAt=LocalDateTime.now();} 
// Getters & setters for the fields
  public Long getId () {
    return id; }
   @JsonProperty(access = AccessType.READ_ONLY)public String getNome() {return nome;} // ... }
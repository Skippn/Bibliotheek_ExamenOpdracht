package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "plaatsnaam")
public class BoekLocatie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locatieID;

	@NotNull
	@Min(value = 50, message = "{boekLocatie.plaatscode.minimumValue}")
	@Max(value = 300, message = "{boekLocatie.plaatscode.maximumValue}")
	private int plaatscode1;

	@NotNull
	@Min(value = 50, message = "{boekLocatie.plaatscode.minimumValue}")
	@Max(value = 300, message = "{boekLocatie.plaatscode.maximumValue}")
	private int plaatscode2;

	@NotNull
	private String plaatsnaam;

	public BoekLocatie(int plaatscode1, int plaatscode2, String plaatsnaam) {
		this.plaatscode1 = plaatscode1;
		this.plaatscode2 = plaatscode2;
		this.plaatsnaam = plaatsnaam;
	}

}
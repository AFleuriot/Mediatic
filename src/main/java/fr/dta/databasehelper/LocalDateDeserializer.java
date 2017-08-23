package fr.dta.databasehelper;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected LocalDateDeserializer() {
		super(LocalDate.class);

	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String valeur = p.readValueAs(String.class);
		try {
			return LocalDateTime.ofInstant(Instant.parse(valeur), ZoneId.systemDefault()).toLocalDate();
		}
		catch(DateTimeParseException e) {
			return LocalDate.parse(valeur, DateTimeFormatter.ISO_LOCAL_DATE);
		}
	}

}

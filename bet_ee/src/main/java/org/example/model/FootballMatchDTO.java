package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.example.exception.ErrorMessage;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FootballMatchDTO {
    private Long id;

    private String homeTeamName;

    @Min(value = 0L, message = ErrorMessage.POSITIVE_MESSAGE)
    private Long homeScore;

    private String awayTeamName;

    @Min(value = 0L, message = ErrorMessage.POSITIVE_MESSAGE)
    private Long awayScore;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = ErrorMessage.FUTURE_OR_PRESENT)
    private LocalDateTime startDate;
}

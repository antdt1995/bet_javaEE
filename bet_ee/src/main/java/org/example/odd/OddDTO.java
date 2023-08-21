package org.example.odd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.example.enumclass.ResultEnum;
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
public class OddDTO {
    private Long id;
    private ResultEnum oddType;
    private String homeTeamName;
    @Min(value = 0L, message = ErrorMessage.POSITIVE_MESSAGE)
    private Double oddRate;
    private Double zetScore;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = ErrorMessage.FUTURE_OR_PRESENT)
    private LocalDateTime endDate;
}

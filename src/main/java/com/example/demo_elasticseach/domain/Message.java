package com.example.demo_elasticseach.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private String message;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

}

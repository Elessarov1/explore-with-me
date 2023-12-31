package ru.practicum.ewm.service.dto.event;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.dto.category.CategoryResponseDto;
import ru.practicum.ewm.service.dto.event.enums.State;
import ru.practicum.ewm.service.dto.user.UserShortDto;
import ru.practicum.ewm.service.entity.Location;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {
    String annotation;
    CategoryResponseDto category;
    Integer confirmedRequests;
    String createdOn;
    String description;
    String eventDate;
    Long id;
    UserShortDto initiator;
    Location location;
    Boolean paid;
    Integer participantLimit;
    String publishedOn;
    Boolean requestModeration;
    State state;
    String title;
    @Builder.Default
    Long views = 0L;
    String adminComment;
}

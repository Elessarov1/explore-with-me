package ru.practicum.stat.server.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stat.dto.EndpointHitDto;
import ru.practicum.stat.dto.ViewStatsDto;
import ru.practicum.stat.server.service.StatService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StatController {
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    StatService statService;

    @RequestMapping(method = RequestMethod.GET, path = "/stats")
    public List<ViewStatsDto> getStats(@RequestParam @DateTimeFormat(pattern = DATETIME_PATTERN) LocalDateTime start,
                                       @RequestParam @DateTimeFormat(pattern = DATETIME_PATTERN) LocalDateTime end,
                                       @RequestParam(required = false) String[] uris,
                                       @RequestParam(defaultValue = "false") boolean unique) {
            return statService.getViewStats(start, end, uris, unique);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/hit")
    public EndpointHitDto addHit(@RequestBody @Valid EndpointHitDto endpointHitDto) {
        return statService.addHit(endpointHitDto);
    }
}

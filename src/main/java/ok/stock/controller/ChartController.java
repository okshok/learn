package ok.stock.controller;

import lombok.RequiredArgsConstructor;
import ok.common.annotation.OkJsonResponse;
import ok.common.messsage.DefaultResponse;
import ok.stock.model.DailyChart;
import ok.stock.service.ChartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/stock/chart")
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/daily")
    @OkJsonResponse
    public List<DailyChart> getDailyChart(@RequestParam String symbol, @RequestParam @DateTimeFormat(pattern = "yyyyMMdd")LocalDate date) {

        return chartService.getDailyChart(symbol, date);
    }


    @PostMapping("/daily")
    @OkJsonResponse
    public DefaultResponse insertDailyChart (@RequestParam String symbol, @RequestParam @DateTimeFormat(pattern = "yyyyMMdd")LocalDate date) {
        chartService.updateDailyChart(symbol, date);
        return null;
    }
}

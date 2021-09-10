package ok.stock.controller;

import lombok.RequiredArgsConstructor;
import ok.common.annotation.OkJsonResponse;
import ok.common.messsage.DefaultResponse;
import ok.stock.model.Company;
import ok.stock.service.FundamentalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock/fundamental")
@RequiredArgsConstructor
public class FundamentalController {


    private final FundamentalService fundamentalService;

    @GetMapping("/company/{symbol}")
    @OkJsonResponse
    public Company getCompany(@PathVariable String symbol) {
        return fundamentalService.getCompanyInfo(symbol);
    }


    @PostMapping("/company/{symbol}")
    @OkJsonResponse
    public DefaultResponse insertCompany(@PathVariable String symbol) {
        fundamentalService.insertCompanyInfo(symbol);
        return null;
    }
}

package dev.haguel.dds.controller;

import dev.haguel.dds.model.History;
import dev.haguel.dds.service.HistoryService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping(EndPoints.GET_HISTORY_PAGE)
    public String getHistory(Model model) {
        EndPoints.setMainMenuEndpoints(model);

        List<History> historyList = historyService.getAll();

        model.addAttribute("historyList", historyList);

        return "history";
    }
}
